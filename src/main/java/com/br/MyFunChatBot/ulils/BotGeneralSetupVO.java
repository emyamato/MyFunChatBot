package com.br.MyFunChatBot.ulils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import com.br.MyFunChatBot.customizedTypes.CircularArrayList;
import com.br.MyFunChatBot.customizedTypes.CustomizedArrayList;

public class BotGeneralSetupVO 
{
// BOT PARAMETERS
	private static BotGeneralSetupVO botGeneralSetupVO;
	
// TWITCH PARAMETERS
	private static String v_twitchAccessToken;

// MYFUNCHATBOT PARAMETERS
	
	private static String				v_ASCIIEmoji;
	private static int					v_HttpPort;
	private static boolean				v_MyFunChatBotStatusOn;
	private static Color				v_BorderColor;
	private static boolean				v_BotMemeEnabled;
	private static int					v_BotMemeCost;
	private static boolean				v_BotGifEnabled;
	private static boolean				v_BotChestEnabled;
	private static boolean				v_TwitchOn;
	private static boolean				v_YoutubeOn;
	private static boolean				v_DiscordOn;
	private static boolean				v_FacebookOn;
	
// COMMANDS
	private static boolean 				v_CommandsEnabled;
	private static String 				v_CommandPrefix;
	private static boolean				v_CommandMod;
	private static boolean				v_CommandPing;
	private static boolean				v_CommandUptime;
	private static boolean				v_CommandGame;
	private static boolean				v_CommandRank;
	private static boolean				v_CommandLinks;
	
//AUTOANSWERS
	private static boolean				v_AutoAnswerEnabled;
	private static CustomizedArrayList	v_AutoAnswerArrayList;
	private static int				v_AutoAnswerTimer;
	
//POINT SYSTEM
	private static boolean				v_PointsEnabled;
	private static String				v_PointsName;
	private static boolean				v_PointsMod;
	private static int					v_PointsExtraMod;
	private static int					v_PointsTimer;
	private static int					v_PointsPerTime;
	
	//BADWORDS
	private static ArrayList<String> 	v_BadWords;
	
	private static String				v_twitchChannel;
	private static String				v_youtubeChannel;
	private static String				v_discordChannel;
	private static String				v_facebookChannel;
	
	//AUTOMESSAGES
	private static boolean				v_AutoMessageEnabled;
	private static int					v_AutoMessageTimer;
	private static CircularArrayList<String>	v_AutoMessageList;
	
	//VOTING
	private static HashMap<String,VotingVO>		v_VotingVOHashMap;
	
	//RANKING
	private static boolean				v_RankEnabled;
	private static HashMap<String,RankVO>		v_RankVOHashMap;
	
	//SPONSOR
	private static boolean				v_BotSponsorEnabled;
	private static boolean				v_BotSponsorMessageEnabled;
	private static String				v_BotSponsorMessage;
	private static int					v_BotSponsorTime;
	private static String				v_BotSponsorSite;	
	
	private BotGeneralSetupVO(){
		v_VotingVOHashMap = new HashMap<String,VotingVO>();
		v_RankVOHashMap = new HashMap<String,RankVO>();
	}
	
	public static BotGeneralSetupVO getInstance(){
		if(botGeneralSetupVO == null){
			botGeneralSetupVO = new BotGeneralSetupVO();
		}
		return botGeneralSetupVO;
	}
	
	public static String getASCIIEmoji(){
		return v_ASCIIEmoji;
	}
	
	public static void setASCIIEmoji(String asciiEmoji){
		v_ASCIIEmoji = asciiEmoji;
	}
		
	public static int getHttpPort(){
		return v_HttpPort;
	}
	
	public static void setHttpPort(int port){
		v_HttpPort = port;
	}
	
	public static boolean getMyFunChatBotStatusOn(){
		return v_MyFunChatBotStatusOn;
	}
	
	public static void setMyFunChatBotStatusOn(boolean myFunChatBotStatusOn){
		v_MyFunChatBotStatusOn = myFunChatBotStatusOn;
	}
	
	public static Color getBorderColor(){
		return v_BorderColor;
	}
	
	public static String getBorderColorToString(){
		if(v_BorderColor == null){
			return "#ffffff";
		}
		return String.format("#%06x", v_BorderColor.getRGB() & 0x00FFFFFF);
	}
	
	public static void setBorderColor(Color borderColor){
		v_BorderColor = borderColor;
	}
	
	public static void setBorderColor(String borderColor){
		v_BorderColor = Color.decode(borderColor);
	}	
	
	public static void setBotMemeEnabled(boolean botMemeEnabled){
		v_BotMemeEnabled = botMemeEnabled;
	}
	
	public static boolean getBotMemeEnabled(){
		return v_BotMemeEnabled;
	}
	
	public static void setBotMemeCost(int memeCost) {
		v_BotMemeCost = memeCost;
	}
	
	public static int getBotMemeCost() {
		return v_BotMemeCost;
	}
	
	public static void setBotGifEnabled(boolean botGifEnabled){
		v_BotGifEnabled = botGifEnabled;
	}
	
	public static boolean getBotGifEnabled(){
		return v_BotGifEnabled;
	}

	public static void setBotChestEnabled(boolean botChestEnabled){
		v_BotChestEnabled = botChestEnabled;
	}
	
	public static boolean getBotChestEnabled(){
		return v_BotChestEnabled;
	}

	public static void setBotSponsorEnabled(boolean botSponsorEnabled){
		v_BotSponsorEnabled = botSponsorEnabled;
	}
	
	public static boolean getBotSponsorEnabled(){
		return v_BotSponsorEnabled;
	}
	
	public static void setBotSponsorTime(int sponsorTime) {
		v_BotSponsorTime = sponsorTime;
	}
	
	public static int getBotSponsorTime() {
		return v_BotSponsorTime;
	}

	public static void setBotSponsorSite(String sponsorSite) {
		v_BotSponsorSite = sponsorSite;
	}
	
	public static String getBoSponsorSite() {
		return v_BotSponsorSite;
	}	
	
	public static void setBotSponsorMessageEnabled(boolean sponsorMessageEnabled){
		v_BotSponsorMessageEnabled = sponsorMessageEnabled;
	}
	
	public static boolean getBotSponsorMessageEnabled(){
		return v_BotSponsorMessageEnabled;
	}
	
	public static void setBotSponsorMessage(String sponsorMessage){
		v_BotSponsorMessage = sponsorMessage.replace("{u}","[SPONSOR]").replace("{l}","[SPONSORLINK]");
	}
	
	public static String getBotSponsorMessage(){
		if(v_BotSponsorMessage == null){
			return "";
		}
		return v_BotSponsorMessage.replace("[SPONSOR]","{u}").replace("[SPONSORLINK]","{l}");
	}
	
	public static String printBotSponsorMessage(){
		if(v_BotSponsorMessage == null){
			return "";
		}
		return v_BotSponsorMessage;
	}	

	public static boolean getTwitchOn(){
		return v_TwitchOn;
	}
	
	public static void setTwitchOn(boolean twitchOn){
		v_TwitchOn = twitchOn;
	}
	
	public static boolean getYoutubeOn(){
		return v_YoutubeOn;
	}
	
	public static void setYoutubeOn(boolean youtubeOn){
		v_YoutubeOn = youtubeOn;
	}
	
	public static boolean getDiscordOn(){
		return v_DiscordOn;
	}
	
	public static void setDiscordOn(boolean discordOn){
		v_DiscordOn = discordOn;
	}
	
	public static boolean getFacebookOn(){
		return v_FacebookOn;
	}
	
	public static void setFacebookOn(boolean facebookOn){
		v_FacebookOn = facebookOn;
	}	
	
	public static void setCommandsEnabled(Boolean enable){
		getInstance();
		v_CommandsEnabled = enable;
	}
	
	public static boolean getCommandsEnabled(){
		getInstance();		
		return v_CommandsEnabled;
	}
	
	public static void setCommandPrefix(String commandPrefix){
		getInstance();		
		v_CommandPrefix = commandPrefix;
	}
	
	public static String getCommandPrefix(){
		getInstance();		
		return v_CommandPrefix;
	}
	
	public static boolean getcommandMod() {
		getInstance();		
		return v_CommandMod;
	}

	public static void setCommandMod(boolean commandMod) {
		getInstance();		
		v_CommandMod = commandMod;
	}

	public static boolean getCommandPing() {
		getInstance();		
		return v_CommandPing;
	}

	public static void setCommandPing(boolean commandPing) {
		getInstance();		
		v_CommandPing = commandPing;
	}

	public static boolean getCommandUptime() {
		getInstance();		
		return v_CommandUptime;
	}

	public static void setCommandUptime(boolean commandUptime) {
		getInstance();		
		v_CommandUptime = commandUptime;
	}

	public static boolean getCommandGame() {
		getInstance();		
		return v_CommandGame;
	}

	public static void setCommandGame(boolean commandGame) {
		getInstance();		
		v_CommandGame = commandGame;
	}
	
	public static boolean getCommandRank() {
		getInstance();		
		return v_CommandRank;
	}

	public static void setCommandRank(boolean commandRank) {
		getInstance();		
		v_CommandRank = commandRank;
	}
	
	public static boolean getCommandLinks() {
		getInstance();		
		return v_CommandLinks;
	}

	public static void setCommandLinks(boolean commandLinks) {
		getInstance();		
		v_CommandLinks = commandLinks;
	}
	
	public static void setAutoAnswerEnabled(boolean autoAnswerEnable){
		getInstance();
		v_AutoAnswerEnabled = autoAnswerEnable;
	}
	
	public static boolean getAutoAnswerEnabled(){
		getInstance();
		return v_AutoAnswerEnabled;
	}
	
	public static void setAutoAnswerArrayList(CustomizedArrayList autoAnswerArrayList){
		getInstance();
		v_AutoAnswerArrayList = autoAnswerArrayList;
	}
	
	public static void setAutoAnswerArrayList(String autoAnswerArrayList){
		getInstance();
		v_AutoAnswerArrayList = new CustomizedArrayList(autoAnswerArrayList);
	}	
	
	public static CustomizedArrayList getAutoAnswerArrayList(){
		getInstance();
		return v_AutoAnswerArrayList;
	}
	
	public static String getAutoAnswerArrayListToString(){
		String text = "";

		for(int i = 0; i < v_AutoAnswerArrayList.size(); i++){
			if(i==0){
				text = v_AutoAnswerArrayList.get(i)[0]+"::"+v_AutoAnswerArrayList.get(i)[1];
			}
			else{
				text = text.concat(":::"+v_AutoAnswerArrayList.get(i)[0]+"::"+v_AutoAnswerArrayList.get(i)[1]);
			}
		}
		return text;
	}
	
	public static String hasAutoAnswer(String message){
		for(int i =0; i < v_AutoAnswerArrayList.size(); i++){
			if (message.toUpperCase().contains(v_AutoAnswerArrayList.get(i)[0].toUpperCase())){
				return v_AutoAnswerArrayList.get(i)[1];
			}
		}
		return "";
	}
	
	public static int getAutoAnswerTimer(){
		getInstance();
		return v_AutoAnswerTimer;
	}
	
	public static void setAutoAnswerTimer(int autoAnswerTimer){
		v_AutoAnswerTimer = autoAnswerTimer;
	}
	
	public static boolean getPointsEnabled(){
		return v_PointsEnabled;
	}
	
	public static void setPointsEnabled(boolean pointsEnabled){
		v_PointsEnabled = pointsEnabled;
	}
	
	public static String getPointsName(){
		return v_PointsName;
	}
	
	public static void setPointsName(String pointsName){
		v_PointsName = pointsName;
	}

	public static boolean getPointsMod() {
		return v_PointsMod;
	}
	
	public static void setPointsMod(boolean pointsMod) {
		v_PointsMod = pointsMod;
	}

	public static int getPointsExtraMod() {
		return v_PointsExtraMod;
	}
	
	public static void setPointsExtraMod(int pointsExtraMod) {
		v_PointsExtraMod = pointsExtraMod;
	}

	public static int getPointsTimer() {
		return v_PointsTimer;
	}
	
	public static void setPointsTimer(int pointsTimer){
		v_PointsTimer = pointsTimer;
	}
	
	public static int getPointsPerTime(){
		return v_PointsPerTime;
	}	
	
	public static int getPointsPerTime(int totalTime){
		return (v_PointsPerTime*getRankBonus(totalTime));
	}
	
	public static void setPointsPerTime(int pointsPerTime){
		v_PointsPerTime = pointsPerTime;
	}
	
	public static void setGeneralSetup(String commandPrefix, ArrayList<String> badWords){
		setCommandPrefix(commandPrefix);
		v_BadWords = badWords;		
	}
	
	public static void setBadWords(ArrayList<String> badWords){
		getInstance();		
		v_BadWords = badWords;
	}
	
	public static ArrayList<String> getBadWords(){
		getInstance();		
		return v_BadWords;
	}
	
	public static boolean getAutoMessageEnabled(){
		getInstance();		
		return v_AutoMessageEnabled;
	}
	
	public static void setAutoMessageEnabled(boolean autoMessageEnabled){
		getInstance();		
		v_AutoMessageEnabled= autoMessageEnabled;
	}
	
	public static int getAutoMessageTimer(){
		getInstance();
		return v_AutoMessageTimer;
	}
	
	public static void setAutoMessageTimer(int autoMessageTimer){
		getInstance();		
		v_AutoMessageTimer = autoMessageTimer;
	}
	
	public static CircularArrayList<String> getAutoMessageList(){
		getInstance();		
		return v_AutoMessageList;
	}
	
	public static void setAutoMessageList(CircularArrayList<String> arrayList){
		getInstance();		
		v_AutoMessageList = arrayList;
	}
	
	public static String getAutoMessageArrayListToString(){
		getInstance();		
		String text = "";

		for(int i = 0; i < v_AutoMessageList.size(); i++){
			if(i == 0){
				text = v_AutoMessageList.get(i);				
			}
			else{
				text = text.concat(":::"+v_AutoMessageList.get(i));
			}
		}
		return text;
	}	
	
	public static void addRankVOHashMap(RankVO rankVO){
		getInstance();
		if(v_RankVOHashMap.containsKey(rankVO.getRankLevel()+"")){
			v_RankVOHashMap.remove(rankVO.getRankLevel()+"");
		}
		v_RankVOHashMap.put(rankVO.getRankLevel()+"",rankVO);
	}
	
	public static void removeRankVOHashMap(int rank){
		getInstance();
		v_RankVOHashMap.remove(rank+"");
	}
	
	public static String getRankTranslate(int rank){
		getInstance();
		if(v_RankVOHashMap.containsKey(rank+"")){
			return v_RankVOHashMap.get(rank+"").getRankName();
		}
		return null;
	}

	public static String getRankTitle(int totalTime){
		getInstance();
		if(v_RankVOHashMap == null){
			return "";
		}
		for(int i = 0; i < v_RankVOHashMap.size(); i++ ){
			if(totalTime < v_RankVOHashMap.get(i+"").getRankMin()){
				return v_RankVOHashMap.get((i-1)+"").getRankName();
			}
		}
		
		if(v_RankVOHashMap.size() == 0){
			return "";
		}
		return v_RankVOHashMap.get((v_RankVOHashMap.size()-1)+"").getRankName();
	}
	
	public static int getRankBonus(int totalTime){
		getInstance();
		if(v_RankVOHashMap == null){
			return 0;
		}

		if(v_RankVOHashMap.size() == 0){
			return 0;
		}
		
		for(int i = 0; i < v_RankVOHashMap.size(); i++ ){
			if(totalTime < v_RankVOHashMap.get(i+"").getRankMin()){
				return v_RankVOHashMap.get((i-1)+"").getRankBonus();
			}
		}
		return v_RankVOHashMap.get((v_RankVOHashMap.size()-1)+"").getRankBonus();
	}	
	
	public static int getRankSize(){
		getInstance();
		if(v_RankVOHashMap == null){
			return 0;
		}
		return v_RankVOHashMap.size();
	}
	
	public static RankVO getRank(int rank){
		getInstance();
		return v_RankVOHashMap.get(rank+"");
	}
	
	public static void setRankEnabled(boolean rankEnabled){
		v_RankEnabled = rankEnabled;
	}
	
	public static boolean getRankEnabled(){
		return v_RankEnabled;
	}
	
	public static String getRankHashMapToString(){
		String text = "";

		for(int i = 0; i < v_RankVOHashMap.size(); i++){
			if(i==0){
				text = ((RankVO)v_RankVOHashMap.values().toArray()[i]).getRankVOToString();
			}
			else{
				text = text.concat(":::"+((RankVO)v_RankVOHashMap.values().toArray()[i]).getRankVOToString());
			}
		}
		return text;		
	}
	
	public static void setRankHashMap(String stringRankHashMap){
		getInstance();
		if(stringRankHashMap == null){
			v_RankVOHashMap = new HashMap<String,RankVO>();
			return;
		}
		String[] stringRankVO = stringRankHashMap.split(":::");
		RankVO rankVO = null;
		v_RankVOHashMap = new HashMap<String,RankVO>();
		for (int i = 0; i < stringRankVO.length;i++){
			rankVO = new RankVO(stringRankVO[i]);
			v_RankVOHashMap.put(""+rankVO.getRankLevel(),rankVO);
		}
	}	
	
	public static void addVotingHashMapVO(VotingVO votingVOHashMap){
		getInstance();
		if(v_VotingVOHashMap.containsKey(votingVOHashMap.getTitle())){
			v_VotingVOHashMap.remove(votingVOHashMap.getTitle());
		};
		v_VotingVOHashMap.put(votingVOHashMap.getTitle(),votingVOHashMap);
	}
	
	public static VotingVO getVotingVOHashMap(int i){
		getInstance();
		return (VotingVO)v_VotingVOHashMap.values().toArray()[i];
	}
	
	public static VotingVO getVotingVOHashMap(String title){
		getInstance();
		return v_VotingVOHashMap.get(title);
	}
	
	public static int getVotingVOHashMapSize(){
		getInstance();
		if(v_VotingVOHashMap == null){
			return 0;
		}		
		return v_VotingVOHashMap.size();
	}
	
	public static VotingVO removeVotingVOHashMap(String title){
		getInstance();
		return v_VotingVOHashMap.remove(title);
	}
	
	public static String getVotingHashMapToString(){
		String text = "";

		for(int i = 0; i < v_VotingVOHashMap.size(); i++){
			if(i==0){
				text = ((VotingVO)v_VotingVOHashMap.values().toArray()[i]).getVotingVOToString();
			}
			else{
				text = text.concat(":::"+((VotingVO)v_VotingVOHashMap.values().toArray()[i]).getVotingVOToString());
			}
		}
		return text;
	}
	
	public static void setVotingHashMap(String stringVotingHashMap){
		getInstance();
		if(stringVotingHashMap == null){
			v_VotingVOHashMap = new HashMap<String,VotingVO>();
			return;
		}
		String[] stringVotingVO = stringVotingHashMap.split(":::");
		VotingVO votingVO = null;
		v_VotingVOHashMap = new HashMap<String,VotingVO>();
		for (int i = 0; i < stringVotingVO.length;i++){
			votingVO = new VotingVO(stringVotingVO[i]);
			v_VotingVOHashMap.put(votingVO.getTitle(),votingVO);
		}
	}
	
	public static void setTwitchChannel(String twitchChannel){
		getInstance();		
		v_twitchChannel = twitchChannel;
	}
	
	public static String getTwitchChannel(){
		getInstance();		
		return v_twitchChannel;
	}
	
	public static String getChannelByPlatform(int platform){
		if(platform == BotConstants.PLATFORM_TWITCH){
			return v_twitchChannel;
		}
		else if (platform == BotConstants.PLATFORM_YOUTUBE){
			return v_youtubeChannel;
		}
		else if (platform == BotConstants.PLATFORM_DISCORD){
			return v_discordChannel;
		}
		else if (platform == BotConstants.PLATFORM_FACEBOOK){
			return v_facebookChannel;
		}
		return "";
	}
	
	public static String getTwitchClientId(){
		getInstance();
		return BotConstants.TWITCHCLIENTID;
	}
	
	public static void setTwitchAccessToken(String twitchAccessToken){
		getInstance();
		v_twitchAccessToken = twitchAccessToken;
	}
	
	public static String getTwitchAccessToken(){
		getInstance();
		return v_twitchAccessToken;
	}
	
	public static String getBotName(){
		getInstance();		
		return BotConstants.BOTNAME;
	}
	
}
