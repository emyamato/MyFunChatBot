package com.br.MyFunChatBot.business;

import com.br.MyFunChatBot.http.BotChestServletThread;
import com.br.MyFunChatBot.http.BotGifServletThread;
import com.br.MyFunChatBot.http.BotMemeServletThread;
import com.br.MyFunChatBot.http.BotSponsorServletThread;
import com.br.MyFunChatBot.ulils.BotGeneralSetupVO;
import com.br.MyFunChatBot.ulils.VotingVO;

public class MyFunChatBotControl 
{
	public static void connectToTwitch(){
		TwitchChatBotThread.connect();
	}
	
	public static void startTwitchChatBot(){
		TwitchChatBotThread twitchChatBotThread = new TwitchChatBotThread();
		Thread threadTwitch = new Thread(twitchChatBotThread,"TwitchChatBotThread");
		threadTwitch.start();
	}
	
	public static void stopTwitchChatBot(){
		if(TwitchChatBotThread.bot.isConnected()){
			TwitchChatBotThread.bot.close();
		}
	}
/*
	public static void startPointThread(){
		PointThread pointThread = PointThread.getInstance();
		Thread threadPointThread = new Thread(pointThread,"PointControlThread");
//		threadPointThread.start();
	}
	
	public static void stopPointThread(){
		if (PointThread.isRunning()){
			PointThread.stop();
		}
	}
	public static void startAutoMessage(){
		AutoMessageThread autoMessageThread = AutoMessageThread.getInstance();
		Thread threadAutoMessageThread = new Thread(autoMessageThread,"AutoMessageThread");
		threadAutoMessageThread.start();
	}
	
	public static void stopAutoMessage(){
		if(AutoMessageThread.isRunning()){
			AutoMessageThread.stop();
		}
	}

	public static void startVoting(VotingVO votingVO){
		VotingThread votingThread = VotingThread.getInstance();
		votingThread.setVotingVO(votingVO);
		Thread threadVotingThread = new Thread(votingThread,"VotingThread");
		threadVotingThread.start();
	}
*/
	
	public static void stopVoting(){
		if(VotingThread.isRunning()){
			VotingThread.stop();
		}
	}
	
	public static void startHttpMemeBot(){
		BotMemeServletThread botMemeServletThread = new BotMemeServletThread();
		Thread threadBotServlet = new Thread(botMemeServletThread,"BotMemeServletThread");
		threadBotServlet.start();
	}
	
	public static void stopHttpMemeBot(){
		if(BotMemeServletThread.isConnected()){
			BotMemeServletThread.stop();
		}
	}
/*
	public static void startHttpGifBot(){
		BotGifServletThread botGifServletThread = new BotGifServletThread();
		Thread threadBotGifServlet = new Thread(botGifServletThread,"BotGifServletThread");
		threadBotGifServlet.start();
	}
	
	public static void stopHttpGifBot(){
		if(BotGifServletThread.isConnected()){
			BotGifServletThread.stop();
		}
	}

	public static void startHttpChestBot(){
		BotChestServletThread botChestServletThread = new BotChestServletThread();
		Thread threadBotChestServlet = new Thread(botChestServletThread,"BotChestServletThread");
		threadBotChestServlet.start();
	}
	
	public static void stopHttpChestBot(){
		if(BotChestServletThread.isConnected()){
			BotChestServletThread.stop();
		}
	}
/*
	public static void startHttpSponsorBot(){
		BotSponsorServletThread botSponsorServletThread = new BotSponsorServletThread();
		Thread threadBotSponsorServlet = new Thread(botSponsorServletThread,"BotSponsorServletThread");
		//threadBotSponsorServlet.start();
	}

	public static void stopHttpSponsorBot(){
		if(BotSponsorServletThread.isConnected()){
			BotSponsorServletThread.stop();
		}
	}
*/
	public static void startScheduleService(){
		Thread threadScheduleServiceThread = new Thread(ScheduleServiceThread.getInstance(),"BotSponsorServletThread");
		threadScheduleServiceThread.start();
	}
	
	public static void stopScheduleService(){
		if(BotGeneralSetupVO.getMyFunChatBotStatusOn()){
			BotGeneralSetupVO.setMyFunChatBotStatusOn(false);
		}
	}

}
