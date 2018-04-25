package com.br.MyFunChatBot.http;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.br.MyFunChatBot.ulils.BotGeneralSetupVO;

public class BotSponsorServletThread implements Runnable{
	private static Server server;
	
	public BotSponsorServletThread(){
	}
	
	public void run(){
		
//        System.setProperty("org.eclipse.jetty.LEVEL","INFO");

        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(BotGeneralSetupVO.getHttpPort());
        server.addConnector(connector);

        String homePath = System.getProperty("user.home");
        String pwdPath = System.getProperty("user.dir");

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setResourceBase(pwdPath);
        context.setContextPath("/");
        server.setHandler(context);

        ServletHolder holderDynamic = new ServletHolder("/sponsor", URLBotSponsorHandler.class);
        context.addServlet(holderDynamic, "/meme/*");

        ServletHolder holderHome = new ServletHolder("static-home", DefaultServlet.class);
        holderHome.setInitParameter("resourceBase",homePath);
        holderHome.setInitParameter("dirAllowed","true");
        holderHome.setInitParameter("pathInfoOnly","true");
        context.addServlet(holderHome,"/home/*");

        ServletHolder holderPwd = new ServletHolder("default", DefaultServlet.class);
        holderPwd.setInitParameter("dirAllowed","true");
        context.addServlet(holderPwd,"/");

        try
        {
            server.start();
            server.dump(System.err);
            server.join();
        }
        catch (Throwable t)
        {
            t.printStackTrace(System.err);
        }
     }
	
	public static boolean isConnected(){
		return server.isRunning();
	}
	
	public static void stop(){
		try {
			if(server.isRunning()){
				server.stop();	
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

