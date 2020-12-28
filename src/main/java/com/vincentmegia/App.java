package com.vincentmegia;

import com.vincentmegia.models.Game;
import com.vincentmegia.processors.FamilyProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.management.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;

public class App
{
    public static void main( String[] args ) throws IOException {
        var context = new AnnotationConfigApplicationContext(HibernateConfiguration.class);
        var familyProcessor = context.getBean(FamilyProcessor.class);
//        familyProcessor.process();
        try {
            ObjectName objectName = new ObjectName("com.baeldung.tutorial:type=basic,name=game");
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            server.registerMBean(new Game(), objectName);
        } catch (MalformedObjectNameException | InstanceAlreadyExistsException |
                MBeanRegistrationException | NotCompliantMBeanException e) {
            // handle exceptions
        }

        var br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        System.out.println( "Hello World!" );
    }
}
