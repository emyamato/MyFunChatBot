package com.br.MyFunChatBot.business;

import java.util.ArrayList;

import com.br.MyFunChatBot.ulils.BotGeneralSetupVO;
import com.br.MyFunChatBot.ulils.ChatterVO;
import com.br.MyFunChatBot.ulils.TwitchAccessClass;

public class PointThread /*implements Runnable*/{
	private static PointThread pointThread;
	
///	private static boolean isRunning;
	
	private PointThread() {
//		isRunning = false;
	}
	
	public static PointThread getInstance(){
		if (pointThread == null){
			pointThread = new PointThread();
		}
		return pointThread;
	}
/*	
	public void run() {
		int timer = 0;
		getInstance();
		isRunning = true;
		ArrayList<ChatterVO> chatters = new ArrayList<ChatterVO>();
		while(isRunning) {
			try {
				Thread.sleep(60000);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			timer++;
			chatters = TwitchAccessClass.getChattersList();
			if(chatters != null){
				for (int i = 0; i < chatters.size(); i++) {
					if(BotGeneralSetupVO.getPointsEnabled()) {
						if(timer >= BotGeneralSetupVO.getPointsTimer()){
							RankingControl.addPointAndTime(chatters.get(i).getChatter(), chatters.get(i).getPlatform(), chatters.get(i).getStatus(), 1);
						}
						else{
							RankingControl.addTime(chatters.get(i).getChatter(), chatters.get(i).getPlatform(), chatters.get(i).getStatus(), 1);
						}
					}
				}
				if(timer >= BotGeneralSetupVO.getPointsTimer()){
					timer = 0;
				}
			}
		}
	}
	
	public static boolean isRunning(){
		return isRunning;
	}

	public static void stop(){
		isRunning = false;
	}
*/
	public static void schedulePoint(){
		ArrayList<ChatterVO> chatters = new ArrayList<ChatterVO>();
		try{
			chatters = TwitchAccessClass.getChattersList();
		}
		catch(Exception e){
			return;
		}
		if(chatters != null){
			for (int i = 0; i < chatters.size(); i++) {
				RankingControl.addPointAndTime(chatters.get(i).getChatter(), chatters.get(i).getPlatform(), chatters.get(i).getStatus(), 1);
			}
		}
	}
	
	public static void scheduleTime(){
		ArrayList<ChatterVO> chatters = new ArrayList<ChatterVO>();
		try{
			chatters = TwitchAccessClass.getChattersList();
		}
		catch(Exception e){
			return;
		}
		if(chatters != null){
			for (int i = 0; i < chatters.size(); i++) {
				RankingControl.addTime(chatters.get(i).getChatter(), chatters.get(i).getPlatform(), chatters.get(i).getStatus(), 1);
			}
		}		
	}
}
