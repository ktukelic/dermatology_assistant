package sbnz.blisskin.util;

import org.apache.maven.shared.invoker.*;

import java.io.File;
import java.util.Arrays;

public class MavenUtil {

    public static void mavenCleanAndInstallRules() throws MavenInvocationException, IllegalAccessException, InstantiationException {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File("C:\\Users\\kacji\\OneDrive - uns.ac.rs\\OSMI\\Projects\\dermatology_assistant\\blisskin\\blisskin-kjar\\pom.xml"));
        request.setGoals(Arrays.asList("clean", "install"));

        Invoker invoker = new DefaultInvoker();
        String home = "C:\\apache-maven-3.6.3\\";
        invoker.setMavenHome(new File(home));
        InvocationResult result = invoker.execute(request);

        if (result.getExitCode() == 0) {
            System.out.println("mvn clean install successfull");
        }
    }
}
