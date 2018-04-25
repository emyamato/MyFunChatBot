package com.br.MyFunChatBot.business;

import com.br.MyFunChatBot.ulils.BotGeneralSetupVO;

public class ScheduleServiceThread implements Runnable{
	
	private static ScheduleServiceThread scheduleServiceThread;
	private int sponsorTimer;
	private int pointTimer;
	private int autoMessageTimer;
	
	private ScheduleServiceThread(){
		sponsorTimer = 0;
		pointTimer = 0;
		autoMessageTimer = 0;
	}
	
	public static ScheduleServiceThread getInstance(){
		if(scheduleServiceThread == null){
			scheduleServiceThread = new ScheduleServiceThread();
		}
		return scheduleServiceThread;
	}
	
	public void run(){
		getInstance();
		while(BotGeneralSetupVO.getMyFunChatBotStatusOn()){
			try{
				Thread.sleep(1000);
				sponsorTimer++;
				pointTimer++;
				autoMessageTimer++;
				
				if(BotGeneralSetupVO.getBotSponsorEnabled() && sponsorTimer >= (BotGeneralSetupVO.getBotSponsorTime()*60)){
					SponsorListControl.scheduleToPlay();
					sponsorTimer = 0;
				}
	
				if(BotGeneralSetupVO.getPointsEnabled() && pointTimer >= (BotGeneralSetupVO.getPointsTimer()*60)){
					PointThread.schedulePoint();
					pointTimer = 0;
				}
				else if(pointTimer%60 == 0){
					PointThread.scheduleTime();
				}
				
				if(BotGeneralSetupVO.getAutoMessageEnabled() && autoMessageTimer >= (BotGeneralSetupVO.getAutoMessageTimer()*60)){
					AutoMessageThread.scheduleAutoMessage();
					autoMessageTimer = 0;
				}
				
				if( VotingThread.isReady() && VotingThread.isRunning()){
					VotingThread.addSecond();
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
