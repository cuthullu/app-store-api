package com.scottlogic.thull.appstore.api;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TomcatRunner {

    public static void main(String[] args) throws Exception {

        String webappDirLocation = "WebContent/";
        Tomcat tomcat = new Tomcat();
        
        Configuration cfg = new Configuration();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        cfg.setProperty("hibernate.connection.url", System.getenv("DATABASE_URL"));
        cfg.setProperty("hibernate.connection.url", System.getenv("DATABASE_URL"));
        cfg.setProperty("hibernate.connection.url", System.getenv("DATABASE_URL"));

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        tomcat.setPort(Integer.valueOf(webPort));
        
        Context context = tomcat.addWebapp("/api", new File(
                webappDirLocation).getAbsolutePath());
        // Define and bind web.xml file location.
        File configFile = new File(webappDirLocation + "WEB-INF/web.xml");
        context.setConfigFile(configFile.toURI().toURL());
        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }
}