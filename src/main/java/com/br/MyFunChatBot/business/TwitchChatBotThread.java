package com.br.MyFunChatBot.business;

import java.io.IOException;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.cap.EnableCapHandler;
import org.pircbotx.exception.IrcException;

import com.br.MyFunChatBot.main.MyFunChatBot;
import com.br.MyFunChatBot.ulils.BotGeneralSetupVO;
import com.br.MyFunChatBot.ulils.ConfigFile;
import com.br.MyFunChatBot.ulils.TwitchAccessClass;
import com.br.MyFunChatBot.ulils.TwitchBotListener;

public class TwitchChatBotThread implements Runnable
{
 

	public static PircBotX bot;
	
	public void run()
	{
		if (BotGeneralSetupVO.getTwitchAccessToken() == null){
			connect();
		}
		MyFunChatBot.changingStatus("TwitchConnectionStatusRefresh", "Connecting...");
		Configuration config = new Configuration.Builder()
			.setAutoNickChange(false)
			.setOnJoinWhoEnabled(false)
			.setCapEnabled(true)
			.addCapHandler(new EnableCapHandler("twitch.tv/membership"))
			.addServer("irc.twitch.tv", 6667)
			.setName(BotGeneralSetupVO.getBotName())
			.setServerPassword("oauth:"+BotGeneralSetupVO.getTwitchAccessToken())
			.addListener(TwitchBotListener.getInstance())
			.addAutoJoinChannel("#" + BotGeneralSetupVO.getTwitchChannel())
			.buildConfiguration();

		bot = new PircBotX(config);
		try 
		{		
			bot.startBot();
			/*
			MyFunChatBot.changingStatus("TwitchConnectionStatusRefresh", "Disconnected");
			MyFunChatBot.changingLedStatus("TWITCH","DISCONNECTED");
			*/
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IrcException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void connect(){
		MyFunChatBot.changingStatus("TwitchConnectionStatusRefresh", "Verifing token...");
		if ((BotGeneralSetupVO.getTwitchAccessToken() == null) || !TwitchAccessClass.isValidToken(BotGeneralSetupVO.getTwitchAccessToken())){
			TwitchAccessClass.setUserToken();
		}
		TwitchAccessClass.setChannel();
		ConfigFile.saveConfig();
		BotGeneralSetupVO.setTwitchOn(true);
		MyFunChatBot.changingStatus("TwitchConnectionStatusRefresh", "Token OK");
		MyFunChatBot.changingLedStatus("TWITCH","CONNECTED");	
	}
}
