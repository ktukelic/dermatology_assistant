package sbnz.blisskin.service;

import org.apache.maven.shared.invoker.MavenInvocationException;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.stereotype.Service;
import sbnz.blisskin.model.Rule;
import sbnz.blisskin.repository.DrlRepository;
import sbnz.blisskin.util.MavenUtil;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Service
public class RuleService {

    private final KieSession kieSession;
    private final DrlRepository drlRepository;

    public RuleService(KieSession kieSession, DrlRepository drlRepository) {
        this.kieSession = kieSession;
        this.drlRepository = drlRepository;
    }

    public String addNewRule(Rule rule) throws MavenInvocationException, InstantiationException, IllegalAccessException {
        String drl = applyRuleTemplate(rule);
        verify(drl);

        drlRepository.save(rule.getName(), drl);
        MavenUtil.mavenCleanAndInstallRules();
        return drl;
    }

    static private String applyRuleTemplate(Rule rule) {
        InputStream template = RuleService.class.getResourceAsStream("/rules/templates/rule-template.drt");
        List<Rule> data = Collections.singletonList(rule);
        ObjectDataCompiler converter = new ObjectDataCompiler();
        return converter.compile(data, template);
    }

    private void verify(String drl) {
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);
        Results results = kieHelper.verify();
        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)) {
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: " + message.getText());
            }
            throw new IllegalStateException("Compilation errors were found.Check the logs.");
        }
    }
}
