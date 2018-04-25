package com.br.MyFunChatBot.business;

import com.br.MyFunChatBot.ulils.BotConstants;
import com.br.MyFunChatBot.ulils.BotGeneralSetupVO;
import com.br.MyFunChatBot.ulils.DiscordBotListener;
import com.br.MyFunChatBot.ulils.FacebookBotListener;
import com.br.MyFunChatBot.ulils.MemeVO;
import com.br.MyFunChatBot.ulils.MyFunctions;
import com.br.MyFunChatBot.ulils.TwitchBotListener;
import com.br.MyFunChatBot.ulils.YoutubeBotListener;

public class MessageControl {
	
	private static String tempMessage;
	private static MemeVO memeVO;
	
	public MessageControl(){
	}
	
	public static void MessageControlBusiness(String username, String message, int platform){
		
		if(BotGeneralSetupVO.getCommandsEnabled()
				&& message.startsWith(BotGeneralSetupVO.getCommandPrefix())
				&& ((RankingControl.isModerator(username, platform) && BotGeneralSetupVO.getcommandMod())
					|| (BotGeneralSetupVO.getChannelByPlatform(platform).toUpperCase().equalsIgnoreCase(username.toUpperCase()))
					|| (message.replaceFirst(BotGeneralSetupVO.getCommandPrefix(), "").toUpperCase().equalsIgnoreCase("RANK")))){
			executeCommand(username, message.replaceFirst(BotGeneralSetupVO.getCommandPrefix(), ""), platform);
			return;
		}
		
		if(VotingThread.isRunning() && VotingThread.isVote(message)){
			VotingThread.addVote(username, message);
			return;
		}
		
		if(BotGeneralSetupVO.getPointsEnabled() && message.toUpperCase().startsWith(BotGeneralSetupVO.getPointsName().toUpperCase())){
			executePointsCommands(username, message, platform);
			return;
		}		
		
		if(BotGeneralSetupVO.getAutoAnswerEnabled() && !(tempMessage = BotGeneralSetupVO.hasAutoAnswer(message)).equalsIgnoreCase("")){
			checkAutoAnswer(tempMessage,platform);
			return;
		}
		
		if(BotGeneralSetupVO.getBotMemeEnabled() && (memeVO = MemeListControl.isMeme(message)) != null){
			checkMemeCommands(username,memeVO,platform);
			return;
		}
	}
	
	private static void checkAutoAnswer(String message, int platform){
		if(platform == BotConstants.PLATFORM_TWITCH){
			TwitchBotListener.sendMessage(message);
		}
	}
	
	private static void executeCommand(String username, String command, int platform) {
		if(command.toUpperCase().equalsIgnoreCase("PING")){
			if (platform == BotConstants.PLATFORM_TWITCH) {
				TwitchBotListener.sendMessage("Pong");
			}
		}
		else if(command.toUpperCase().equalsIgnoreCase("RANK")){
			if (platform == BotConstants.PLATFORM_TWITCH) {
				TwitchBotListener.sendMessage(username+" has a "+RankingControl.getViewerTitle(username, platform)+" Rank!");
			}			
		}
		
	}
	
	private static void executePointsCommands(String username, String message, int platform){
		message = message.replace("@", "");
		if ((message.split(" ").length > 1) && (message.toUpperCase().startsWith(BotGeneralSetupVO.getPointsName().toUpperCase()))){
			if(message.split(" ").length == 2 && message.split(" ")[1].toUpperCase().equalsIgnoreCase("HELP")){
				sendMessage("To give some points to your friend, just use: "+BotGeneralSetupVO.getPointsName()+" <YOUR FRIEND NICK> <SOME POINTS> ! ");
			}
			else if(message.split(" ").length == 3 && MyFunctions.isNumber(message.split(" ")[2]) && RankingControl.hasViewer(message.split(" ")[1], platform)){
				int amount = 0;
				if(RankingControl.isOwner(username, platform)){
					amount = Integer.parseInt(message.split(" ")[2]);
					RankingControl.addPoint(message.split(" ")[1], platform, amount);
				}
				else{
					if(Integer.parseInt(message.split(" ")[2]) > RankingControl.getPointsAmount(username, platform)){
						amount = RankingControl.getPointsAmount(username, platform);
					}
					else{
						amount = Integer.parseInt(message.split(" ")[2]);
					}
					RankingControl.subPoints(username, platform, amount);
					RankingControl.addPoint(message.split(" ")[1], platform, amount);
				}
				sendMessage(" "+username+" has given "+amount+" "+BotGeneralSetupVO.getPointsName()+" to "+message.split(" ")[1]);
			}
		}
		else if((message.split(" ").length == 1) && (message.toUpperCase().contains(BotGeneralSetupVO.getPointsName().toUpperCase()))){
			if(platform == BotConstants.PLATFORM_TWITCH){
				if(RankingControl.isOwner(username, platform)){
					sendMessage(" "+username+" has all "+BotGeneralSetupVO.getPointsName()+" in the world!!!");
				}
				else{
					sendMessage(" "+username+" has "+RankingControl.getPointsAmount(username, platform)+" "+BotGeneralSetupVO.getPointsName());
				}
			}
			else if(platform == BotConstants.PLATFORM_YOUTUBE){
			}
			else if(platform == BotConstants.PLATFORM_DISCORD){
			}
			else if(platform == BotConstants.PLATFORM_FACEBOOK){
			}
		}
	}
	
	private static void checkMemeCommands(String username, MemeVO memeVO, int platform) {
		if(RankingControl.isOwner(username, platform)){
			MemeListControl.putMemeToPlay(username, memeVO);
		}
		else if( RankingControl.getPointsAmount(username, platform) > BotGeneralSetupVO.getBotMemeCost() ) {
			if(MemeListControl.putMemeToPlay(username, memeVO)){
				RankingControl.subPoints(username, platform, BotGeneralSetupVO.getBotMemeCost());
			}
		}
	}
	
	public static void sendMessage(String message){
		if(BotGeneralSetupVO.getTwitchOn()){
			TwitchBotListener.sendMessage(BotGeneralSetupVO.getASCIIEmoji()+" "+message);
		}
		if(BotGeneralSetupVO.getYoutubeOn()){
			YoutubeBotListener.sendMessage(BotGeneralSetupVO.getASCIIEmoji()+" "+message);
		}
		if(BotGeneralSetupVO.getDiscordOn()){
			DiscordBotListener.sendMessage(BotGeneralSetupVO.getASCIIEmoji()+" "+message);
		}
		if(BotGeneralSetupVO.getFacebookOn()){
			FacebookBotListener.sendMessage(BotGeneralSetupVO.getASCIIEmoji()+" "+message);
		}
	}
}
