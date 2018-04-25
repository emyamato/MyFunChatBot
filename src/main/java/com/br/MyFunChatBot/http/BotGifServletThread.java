package com.br.MyFunChatBot.http;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import com.br.MyFunChatBot.ulils.BotGeneralSetupVO;

public class BotGifServletThread implements Runnable{
	private static Server server;
	
	public BotGifServletThread(){
	}
	
	public void run(){
        server = new Server(BotGeneralSetupVO.getHttpPort());        
        ServletHandler handler = new ServletHandler();
        try{
			handler.addServletWithMapping(URLBotGifHandler.class, "/gif");
			server.setHandler(handler);
			server.start();
			server.join();
		} 
        catch (Exception e) {
			e.printStackTrace();
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

