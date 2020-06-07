package sbnz.blisskin.service;

import org.drools.template.ObjectDataCompiler;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sbnz.blisskin.model.Rule;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    // ToDo preimenuj u Rule service

    private final KieSession kieSession;

    public AdminService(@Qualifier("reasoning") KieSession kieSession) {
        this.kieSession = kieSession;
    }

    public String addNewRule(Rule rule) {
        String drl = applyRuleTemplate(rule);

        // ToDo dodati na neki od ovvih nacina
        // ToDo dodati samo kao fajl

//        createKieSessionFromDRL(drl);

//        KieSession ksession = new KieHelper().addContent(drl, ResourceType.DRL).build().newKieSession();

//        KieServices kieServices = KieServices.Factory.get();
//        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
//        kieFileSystem.write("src/main/resources/rules/rule.drl", drl);
//        kieServices.newKieBuilder(kieFileSystem).buildAll();
        return drl;
    }

    static private String applyRuleTemplate(Rule rule) {
        InputStream template = AdminService.class.getResourceAsStream("/rules/templates/rule-template.drt");
        List<Rule> data = new ArrayList<Rule>();
        data.add(rule);
        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);
        return drl;
    }

    private KieSession createKieSessionFromDRL(String drl) {
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);
        Results results = kieHelper.verify();
        if (results.hasMessages(Message.Level.WARNING, Message.Level.
                ERROR)) {
            List<Message> messages = results.getMessages(Message.
                    Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: " + message.getText());
            }
            throw new IllegalStateException("Compilation errors were found.Check the logs.");
        }
        return kieHelper.build().newKieSession();
    }
}
