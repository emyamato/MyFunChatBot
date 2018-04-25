package com.br.MyFunChatBot.business;

import com.br.MyFunChatBot.customizedTypes.CircularArrayList;
import com.br.MyFunChatBot.ulils.BotGeneralSetupVO;
import com.br.MyFunChatBot.ulils.TwitchBotListener;

public class AutoMessageThread /* implements Runnable */{
	private static AutoMessageThread autoMessageThread;

//	private static boolean isRunning;
	
	private static CircularArrayList<String> circularAutoMessage;
	
	private AutoMessageThread(){
//		isRunning = false;
		circularAutoMessage = BotGeneralSetupVO.getAutoMessageList();
	}
	
	public static AutoMessageThread getInstance(){
		if(autoMessageThread == null){
			autoMessageThread = new AutoMessageThread();
		}
		return autoMessageThread;
	}
/*	
	public void run(){
		isRunning = true;
		
		while(isRunning){
			try{
				Thread.sleep(BotGeneralSetupVO.getAutoMessageTimer());		
			}
			catch (Exception e){
				e.printStackTrace();	
			}
			
			MessageControl.sendMessage(circularAutoMessage.next());
/*			
			if(BotGeneralSetupVO.getTwitchOn()){
				TwitchBotListener.sendMessage(circularAutoMessage.next());
			}
			else if(BotGeneralSetupVO.getYoutubeOn()){
			}
			else if(BotGeneralSetupVO.getDiscordOn()){
			}
			else if(BotGeneralSetupVO.getFacebookOn()){
			}	
		}
	}
	
	public static boolean isRunning(){
		return isRunning;
	}
	
	public static void stop(){
		isRunning = false;
		Thread.currentThread().interrupt();
	}
*/
	public static void scheduleAutoMessage(){
		getInstance();
		MessageControl.sendMessage(circularAutoMessage.next());
	}
}
