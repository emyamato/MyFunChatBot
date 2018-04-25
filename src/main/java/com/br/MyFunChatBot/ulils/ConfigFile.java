package com.br.MyFunChatBot.ulils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import com.br.MyFunChatBot.customizedTypes.CircularArrayList;

public class ConfigFile{
	
	public static void saveConfig(){
		File configFile = new File("myfunchatbot.properties");
		try {
			FileWriter configFileWriter = new FileWriter(configFile);
			configFileWriter.append("######### TWITCH CONFIG #########\n");
			configFileWriter.append("twitch.accesstoken="+BotGeneralSetupVO.getTwitchAccessToken()+"\n");
			configFileWriter.append("twitch.channel="+BotGeneralSetupVO.getTwitchChannel()+"\n");			
			configFileWriter.append("######### MYFUNCHATBOT CONFIG #########\n");
			configFileWriter.append("myfunchatbot.httpPort="+BotGeneralSetupVO.getHttpPort()+"\n");
			configFileWriter.append("myfunchatbot.borderColor="+BotGeneralSetupVO.getBorderColorToString()+"\n");
			configFileWriter.append("myfunchatbot.asciiEmoji="+BotGeneralSetupVO.getASCIIEmoji()+"\n");
			configFileWriter.append("myfunchatbot.botMemeEnabled="+BotGeneralSetupVO.getBotMemeEnabled()+"\n");
			configFileWriter.append("myfunchatbot.botMemeCost="+BotGeneralSetupVO.getBotMemeCost()+"\n");
			configFileWriter.append("myfunchatbot.botGifEnabled="+BotGeneralSetupVO.getBotGifEnabled()+"\n");
			configFileWriter.append("myfunchatbot.botChestEnabled="+BotGeneralSetupVO.getBotChestEnabled()+"\n");
			configFileWriter.append("myfunchatbot.botSponsorEnabled="+BotGeneralSetupVO.getBotSponsorEnabled()+"\n");
			configFileWriter.append("myfunchatbot.botSponsorTime="+BotGeneralSetupVO.getBotSponsorTime()+"\n");
			configFileWriter.append("myfunchatbot.botSponsorMessageEnabled="+BotGeneralSetupVO.getBotSponsorMessageEnabled()+"\n");
			configFileWriter.append("myfunchatbot.botSponsorMessage="+BotGeneralSetupVO.getBotSponsorMessage()+"\n");
			configFileWriter.append("myfunchatbot.commandsEnabled="+BotGeneralSetupVO.getCommandsEnabled()+"\n");
			configFileWriter.append("myfunchatbot.commandPrefix="+BotGeneralSetupVO.getCommandPrefix()+"\n");
			configFileWriter.append("myfunchatbot.commandMod="+BotGeneralSetupVO.getcommandMod()+"\n");
			configFileWriter.append("myfunchatbot.commandPing="+BotGeneralSetupVO.getCommandPing()+"\n");
			configFileWriter.append("myfunchatbot.commandUptime="+BotGeneralSetupVO.getCommandUptime()+"\n");
			configFileWriter.append("myfunchatbot.commandGame="+BotGeneralSetupVO.getCommandGame()+"\n");
			configFileWriter.append("myfunchatbot.commandRank="+BotGeneralSetupVO.getCommandRank()+"\n");
			configFileWriter.append("myfunchatbot.commandLinks="+BotGeneralSetupVO.getCommandLinks()+"\n");			
			configFileWriter.append("myfunchatbot.autoAnswerEnabled="+BotGeneralSetupVO.getAutoAnswerEnabled()+"\n");
			configFileWriter.append("myfunchatbot.autoAnswerTable="+BotGeneralSetupVO.getAutoAnswerArrayListToString()+"\n");
			configFileWriter.append("myfunchatbot.autoAnswerTimer="+BotGeneralSetupVO.getAutoAnswerTimer()+"\n");
			configFileWriter.append("myfunchatbot.pointsEnabled="+BotGeneralSetupVO.getPointsEnabled()+"\n");			
			configFileWriter.append("myfunchatbot.pointsName="+BotGeneralSetupVO.getPointsName()+"\n");
			configFileWriter.append("myfunchatbot.pointsMod="+BotGeneralSetupVO.getPointsMod()+"\n");
			configFileWriter.append("myfunchatbot.pointsExtraMod="+BotGeneralSetupVO.getPointsExtraMod()+"\n");
			configFileWriter.append("myfunchatbot.pointsPerTime="+BotGeneralSetupVO.getPointsPerTime()+"\n");
			configFileWriter.append("myfunchatbot.pointsTimer="+BotGeneralSetupVO.getPointsTimer()+"\n");
			configFileWriter.append("myfunchatbot.rankEnabled="+BotGeneralSetupVO.getRankEnabled()+"\n");
			configFileWriter.append("myfunchatbot.rankHashMap="+BotGeneralSetupVO.getRankHashMapToString()+"\n");
			configFileWriter.append("myfunchatbot.votingHashMap="+BotGeneralSetupVO.getVotingHashMapToString()+"\n");
			configFileWriter.append("myfunchatbot.autoMessageEnabled="+BotGeneralSetupVO.getAutoMessageEnabled()+"\n");
			configFileWriter.append("myfunchatbot.autoMessageTimer="+BotGeneralSetupVO.getAutoMessageTimer()+"\n");
			configFileWriter.append("myfunchatbot.autoMessageList="+BotGeneralSetupVO.getAutoMessageArrayListToString()+"\n");
			configFileWriter.append("myfunchatbot.autoMessageTwitchEnabled="+BotGeneralSetupVO.getTwitchOn()+"\n");
			configFileWriter.append("myfunchatbot.autoMessageYoutubeEnabled="+BotGeneralSetupVO.getYoutubeOn()+"\n");
			configFileWriter.append("myfunchatbot.autoMessageDiscordEnabled="+BotGeneralSetupVO.getDiscordOn()+"\n");
			configFileWriter.append("myfunchatbot.autoMessageFacebookEnabled="+BotGeneralSetupVO.getFacebookOn()+"\n");
			configFileWriter.append("myfunchatbot.badwords="+BotGeneralSetupVO.getBadWords()+"\n");
	
			configFileWriter.flush();
			configFileWriter.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadConfig(){
		File configFile = new File("myfunchatbot.properties");
		try {
			FileReader fileCofigReader = new FileReader(configFile);
			Properties configProperties = new Properties();
			configProperties.load(fileCofigReader);

			if(configProperties.getProperty("myfunchatbot.httpPort") != null){
				BotGeneralSetupVO.setHttpPort(Integer.parseInt(configProperties.getProperty("myfunchatbot.httpPort")));				
			}
			
			if(configProperties.getProperty("myfunchatbot.borderColor") != null){
				BotGeneralSetupVO.setBorderColor(configProperties.getProperty("myfunchatbot.borderColor"));				
			}
			
			if(configProperties.getProperty("myfunchatbot.asciiEmoji") != null){
				BotGeneralSetupVO.setASCIIEmoji(configProperties.getProperty("myfunchatbot.asciiEmoji"));				
			}			

			if(configProperties.getProperty("myfunchatbot.botMemeEnabled") != null){
				BotGeneralSetupVO.setBotMemeEnabled(stringToBool(configProperties.getProperty("myfunchatbot.botMemeEnabled")));				
			}
			
			
			if(configProperties.getProperty("myfunchatbot.botMemeCost") != null){
				BotGeneralSetupVO.setBotMemeCost(Integer.parseInt(configProperties.getProperty("myfunchatbot.botMemeCost")));				
			}
			
			if(configProperties.getProperty("myfunchatbot.botGifEnabled") != null){
				BotGeneralSetupVO.setBotGifEnabled(stringToBool(configProperties.getProperty("myfunchatbot.botGifEnabled")));				
			}
			
			if(configProperties.getProperty("myfunchatbot.botChestEnabled") != null){
				BotGeneralSetupVO.setBotChestEnabled(stringToBool(configProperties.getProperty("myfunchatbot.botChestEnabled")));				
			}
			
			if(configProperties.getProperty("myfunchatbot.botSponsorEnabled") != null){
				BotGeneralSetupVO.setBotSponsorEnabled(stringToBool(configProperties.getProperty("myfunchatbot.botSponsorEnabled")));				
			}
			
			if(configProperties.getProperty("myfunchatbot.botSponsorTime") != null){
				BotGeneralSetupVO.setBotSponsorTime(Integer.parseInt(configProperties.getProperty("myfunchatbot.botSponsorTime")));				
			}
			
			if(configProperties.getProperty("myfunchatbot.botSponsorMessageEnabled") != null){
				BotGeneralSetupVO.setBotSponsorMessageEnabled(stringToBool(configProperties.getProperty("myfunchatbot.botSponsorMessageEnabled")));				
			}
			
			if(configProperties.getProperty("myfunchatbot.botSponsorMessage") != null){
				BotGeneralSetupVO.setBotSponsorMessage(configProperties.getProperty("myfunchatbot.botSponsorMessage"));				
			}			
			
			if(configProperties.getProperty("twitch.accesstoken") != null){
				BotGeneralSetupVO.setTwitchAccessToken(configProperties.getProperty("twitch.accesstoken"));				
			}
			
			if(configProperties.getProperty("twitch.channel") != null){
				BotGeneralSetupVO.setTwitchChannel(configProperties.getProperty("twitch.channel"));				
			}

			if(configProperties.getProperty("myfunchatbot.commandsEnabled") != null){
				BotGeneralSetupVO.setCommandsEnabled(stringToBool(configProperties.getProperty("myfunchatbot.commandsEnabled")));				
			}

			if(configProperties.getProperty("myfunchatbot.commandPrefix") != null){
				BotGeneralSetupVO.setCommandPrefix(configProperties.getProperty("myfunchatbot.commandPrefix"));				
			}
			
			if(configProperties.getProperty("myfunchatbot.commandMod") != null){
				BotGeneralSetupVO.setCommandMod(stringToBool(configProperties.getProperty("myfunchatbot.commandMod")));				
			}
			
			if(configProperties.getProperty("myfunchatbot.commandPing") != null){
				BotGeneralSetupVO.setCommandPing(stringToBool(configProperties.getProperty("myfunchatbot.commandPing")));				
			}
			
			if(configProperties.getProperty("myfunchatbot.commandUptime") != null){
				BotGeneralSetupVO.setCommandUptime(stringToBool(configProperties.getProperty("myfunchatbot.commandUptime")));				
			}
			
			if(configProperties.getProperty("myfunchatbot.commandGame") != null){
				BotGeneralSetupVO.setCommandGame(stringToBool(configProperties.getProperty("myfunchatbot.commandGame")));				
			}

			if(configProperties.getProperty("myfunchatbot.commandRank") != null){
				BotGeneralSetupVO.setCommandRank(stringToBool(configProperties.getProperty("myfunchatbot.commandRank")));				
			}

			if(configProperties.getProperty("myfunchatbot.commandLinks") != null){
				BotGeneralSetupVO.setCommandLinks(stringToBool(configProperties.getProperty("myfunchatbot.commandLinks")));				
			}

			if(configProperties.getProperty("myfunchatbot.autoAnswerEnabled") != null){
				BotGeneralSetupVO.setAutoAnswerEnabled(stringToBool(configProperties.getProperty("myfunchatbot.autoAnswerEnabled")));				
			}

			if(configProperties.getProperty("myfunchatbot.autoAnswerTable") != null){
				BotGeneralSetupVO.setAutoAnswerArrayList(configProperties.getProperty("myfunchatbot.autoAnswerTable"));				
			}

			if(configProperties.getProperty("myfunchatbot.autoAnswerTimer") != null){
				BotGeneralSetupVO.setAutoAnswerTimer(Integer.parseInt(configProperties.getProperty("myfunchatbot.autoAnswerTimer")));				
			}

			if(configProperties.getProperty("myfunchatbot.votingHashMap") != null){
				BotGeneralSetupVO.setVotingHashMap(configProperties.getProperty("myfunchatbot.votingHashMap"));				
			}
			
			if(configProperties.getProperty("myfunchatbot.pointsEnabled") != null){
				BotGeneralSetupVO.setPointsEnabled(stringToBool(configProperties.getProperty("myfunchatbot.pointsEnabled")));				
			}

			if(configProperties.getProperty("myfunchatbot.pointsName") != null){
				BotGeneralSetupVO.setPointsName(configProperties.getProperty("myfunchatbot.pointsName"));				
			}
			
			if(configProperties.getProperty("myfunchatbot.pointsMod") != null){
				BotGeneralSetupVO.setPointsMod(stringToBool(configProperties.getProperty("myfunchatbot.pointsMod")));				
			}

			if(configProperties.getProperty("myfunchatbot.pointsExtraMod") != null){
				BotGeneralSetupVO.setPointsExtraMod(Integer.parseInt(configProperties.getProperty("myfunchatbot.pointsExtraMod")));				
			}

			if(configProperties.getProperty("myfunchatbot.pointsPerTime") != null){
				BotGeneralSetupVO.setPointsPerTime(Integer.parseInt(configProperties.getProperty("myfunchatbot.pointsPerTime")));				
			}

			if(configProperties.getProperty("myfunchatbot.pointsTimer") != null){
				BotGeneralSetupVO.setPointsTimer(Integer.parseInt(configProperties.getProperty("myfunchatbot.pointsTimer")));				
			}
			
			if(configProperties.getProperty("myfunchatbot.rankEnabled") != null){
				BotGeneralSetupVO.setRankEnabled(stringToBool(configProperties.getProperty("myfunchatbot.rankEnabled")));				
			}
			
			if(configProperties.getProperty("myfunchatbot.rankHashMap") != null){
				BotGeneralSetupVO.setRankHashMap(configProperties.getProperty("myfunchatbot.rankHashMap"));				
			}
		
			if(configProperties.getProperty("myfunchatbot.autoMessageEnabled") != null){
				BotGeneralSetupVO.setAutoMessageEnabled(stringToBool(configProperties.getProperty("myfunchatbot.autoMessageEnabled")));				
			}

			if(configProperties.getProperty("myfunchatbot.autoMessageTimer") != null){
				BotGeneralSetupVO.setAutoMessageTimer(Integer.parseInt(configProperties.getProperty("myfunchatbot.autoMessageTimer")));				
			}

			if(configProperties.getProperty("myfunchatbot.autoMessageList") != null){
				BotGeneralSetupVO.setAutoMessageList(new CircularArrayList<String>(Arrays.asList(configProperties.getProperty("myfunchatbot.autoMessageList").split(":::"))));
			}

			if(configProperties.getProperty("myfunchatbot.twitchOn") != null){
				BotGeneralSetupVO.setTwitchOn((stringToBool(configProperties.getProperty("myfunchatbot.twitchOn"))));				
			}

			if(configProperties.getProperty("myfunchatbot.youtubeOn") != null){
				BotGeneralSetupVO.setYoutubeOn(stringToBool(configProperties.getProperty("myfunchatbot.youtubeOn")));				
			}
			
			if(configProperties.getProperty("myfunchatbot.discordOn") != null){
				BotGeneralSetupVO.setDiscordOn(stringToBool(configProperties.getProperty("myfunchatbot.discordOn")));				
			}
			
			if(configProperties.getProperty("myfunchatbot.facebookOn") != null){
				BotGeneralSetupVO.setFacebookOn(stringToBool(configProperties.getProperty("myfunchatbot.facebookOn")));				
			}
			
			if(configProperties.getProperty("myfunchatbot.badwords") != null){
				BotGeneralSetupVO.setBadWords(new ArrayList<String>(Arrays.asList(configProperties.getProperty("myfunchatbot.badwords").split(","))));				
			}

			fileCofigReader.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Boolean stringToBool(String bool){
		if(bool.equalsIgnoreCase("TRUE")){
			return true;
		}
		else if(bool.equalsIgnoreCase("FALSE")){
			return false;
		}
		else{
			return null;
		}
	}
}