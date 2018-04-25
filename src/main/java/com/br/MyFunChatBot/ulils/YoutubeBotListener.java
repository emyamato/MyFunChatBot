package com.br.MyFunChatBot.ulils;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.PingEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;

import com.br.MyFunChatBot.business.MessageControl;
import com.br.MyFunChatBot.business.TwitchChatBotThread;

public class YoutubeBotListener extends ListenerAdapter 
{

	private static YoutubeBotListener twitchBotListener;

	private YoutubeBotListener() {
	}

	public static YoutubeBotListener getInstance(){
		if(twitchBotListener == null){
			twitchBotListener = new YoutubeBotListener();
		}
		return twitchBotListener;
	}

	@Override
	public void onGenericMessage(GenericMessageEvent event) throws Exception {	
		MessageControl.MessageControlBusiness(event.getUser().getNick(), event.getMessage(), BotConstants.PLATFORM_TWITCH);
	}

	@Override
	public void onPing(PingEvent event) throws Exception {
		TwitchChatBotThread.bot.sendRaw().rawLineNow(String.format("PONG %s\r\n", event.getPingValue()));
	}

	public static void sendMessage(String message) {
		TwitchChatBotThread.bot.sendIRC().message("#" + BotGeneralSetupVO.getTwitchChannel(), message);
	}
}
