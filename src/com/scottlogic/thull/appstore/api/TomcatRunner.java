package com.scottlogic.thull.appstore.api;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TomcatRunner {
	static final Logger LOG = LoggerFactory.getLogger(TomcatRunner.class);

	public static void main(String[] args) throws Exception {
    	LOG.info("Creating embedded tomcat container");

        String webappDirLocation = "WebContent/";
        Tomcat tomcat = new Tomcat();
        
        
        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }
        
        

        tomcat.setPort(Integer.valueOf(webPort));
        
        //Add the api app
        String appPath = "/api";
        
        Context context = tomcat.addWebapp(appPath, new File(
        		webappDirLocation).getAbsolutePath());
        // Define and bind web.xml file location.
        File configFile = new File(webappDirLocation + "WEB-INF/web.xml");
        context.setConfigFile(configFile.toURI().toURL());
        LOG.info("configuring app with basedir: " + 
        		new File("./" + webappDirLocation).getAbsolutePath() + 
        		" To path: " + appPath + " On port: " + webPort);
        

        tomcat.start();
        tomcat.getServer().await();
    }
}