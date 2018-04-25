package com.br.MyFunChatBot.ulils;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;

import com.br.MyFunChatBot.main.MyFunChatBot;
import com.mb3364.twitch.api.Twitch;
import com.mb3364.twitch.api.auth.Scopes;

public class TwitchAccessClass 
{
	
	private TwitchAccessClass()
	{}

	public static void setUserToken()
	{
		Twitch twitch = new Twitch();
		twitch.setClientId(BotGeneralSetupVO.getTwitchClientId());
		URI callbackUri = null;
		try {
			callbackUri = new URI("http://127.0.0.1:23522/authorize.html");
		} 
		catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		MyFunChatBot.changingStatus("TwitchConnectionStatusRefresh", "Wainting for authentication...");
		String authUrl = twitch.auth().getAuthenticationUrl(twitch.getClientId(), callbackUri, Scopes.CHANNEL_CHECK_SUBSCRIPTION, Scopes.CHANNEL_EDITOR, Scopes.CHAT_LOGIN );

		try {
			String url = authUrl + "&force_verify=true";
			Desktop.getDesktop().browse(URI.create(url));
		} 
		catch (IOException e) {
			System.out.println(e.getMessage());
			MyFunChatBot.changingLedStatus("TWITCH","DISCONNECTED");						
		}
		
		boolean authSuccess = twitch.auth().awaitAccessToken();
		
		if (authSuccess) {
			MyFunChatBot.changingStatus("TwitchConnectionStatusRefresh", "Authentication OK...");			
			BotGeneralSetupVO.setTwitchAccessToken(twitch.auth().getAccessToken());
		} 
		else {
			  //System.out.println(twitch.auth().getAuthenticationError());
			  MyFunChatBot.changingLedStatus("TWITCH","DISCONNECTED");						  
		}
		
		ConfigFile.saveConfig();
	}
	
	public static boolean isValidToken(String token)
	{
		if(token == null) {
			return false;
		}
		try {
			JSONObject json = new JSONObject(JSONParser.readUrl(String.format("https://api.twitch.tv/kraken?oauth_token=%s",token)));
			Boolean valid = (Boolean)json.getJSONObject("token").get("valid");
			if(!valid) {
				MyFunChatBot.changingStatus("TwitchConnectionStatusRefresh", "Token is not valid...");
				MyFunChatBot.changingLedStatus("TWITCH","DISCONNECTED");							
				return false;
			}
		} 
		catch (Exception e) {
			MyFunChatBot.changingStatus("TwitchConnectionStatusRefresh", "Token is not valid...");
			MyFunChatBot.changingLedStatus("TWITCH","DISCONNECTED");						
			return false;
		} 
		return true;
	}
	
	public static void setChannel()
	{
		MyFunChatBot.changingStatus("TwitchConnectionStatusRefresh", "Retrieving channel...");
		try {
			JSONObject json = new JSONObject(JSONParser.readUrl(String.format("https://api.twitch.tv/kraken?oauth_token=%s",BotGeneralSetupVO.getTwitchAccessToken())));
			String[] channelsArray = ((String)json.getJSONObject("_links").get("channels")).split("/");
			BotGeneralSetupVO.setTwitchChannel(channelsArray[channelsArray.length-1]);
			MyFunChatBot.changingStatus("TwitchConnectionChannelRefresh", BotGeneralSetupVO.getTwitchChannel());
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		MyFunChatBot.changingStatus("TwitchConnectionStatusRefresh", "Channel OK...");
	}
	
	public static ArrayList<ChatterVO> getModeratorsList()
	{
		ArrayList<ChatterVO> moderatorsArray = new ArrayList<ChatterVO>();
		try {
			JSONObject json = new JSONObject(JSONParser.readUrl(String.format("https://tmi.twitch.tv/group/user/%s/chatters", BotGeneralSetupVO.getTwitchChannel())));
			JSONArray moderatorsJArray = (json.getJSONObject("chatters").getJSONArray("moderators"));
			for (int i = 0; i<moderatorsJArray.length(); i++) {
				moderatorsArray.add(new ChatterVO(moderatorsJArray.getString(i),BotConstants.PLATFORM_TWITCH,BotConstants.STATUS_MODERATOR,0));
			}
			return moderatorsArray;
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public static ArrayList<ChatterVO> getViewersList()
	{
		ArrayList<ChatterVO> viewersArray = new ArrayList<ChatterVO>();
		try {
			JSONObject json = new JSONObject(JSONParser.readUrl(String.format("https://tmi.twitch.tv/group/user/%s/chatters", BotGeneralSetupVO.getTwitchChannel())));
			JSONArray viewersJArray = (json.getJSONObject("chatters").getJSONArray("viewers"));
			for (int i = 0; i<viewersJArray.length(); i++) {
				viewersArray.add(new ChatterVO(viewersJArray.getString(i),BotConstants.PLATFORM_TWITCH,BotConstants.STATUS_VIEWER,0));
			}
			return viewersArray;
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public static ArrayList<ChatterVO> getChattersList(){
		
		ArrayList<ChatterVO> chattersArray = new ArrayList<ChatterVO>();
		ChatterVO chatter = null;
		try {
			JSONObject json = new JSONObject(JSONParser.readUrl(String.format("https://tmi.twitch.tv/group/user/%s/chatters", BotGeneralSetupVO.getTwitchChannel())));
			JSONArray moderatorsJArray = (json.getJSONObject("chatters").getJSONArray("moderators"));
			JSONArray viewersJArray = (json.getJSONObject("chatters").getJSONArray("viewers"));
			for (int i = 0; i<moderatorsJArray.length(); i++) {
				if(moderatorsJArray.getString(i).toUpperCase().equalsIgnoreCase(BotGeneralSetupVO.getTwitchChannel().toUpperCase())){
					chatter = new ChatterVO(moderatorsJArray.getString(i),BotConstants.PLATFORM_TWITCH,BotConstants.STATUS_OWNER,0);
				}
				else{
					chatter = new ChatterVO(moderatorsJArray.getString(i),BotConstants.PLATFORM_TWITCH,BotConstants.STATUS_MODERATOR,0);
				}
				chattersArray.add(chatter);
			}
			for (int i = 0; i<viewersJArray.length(); i++) {
				chatter = new ChatterVO(viewersJArray.getString(i),BotConstants.PLATFORM_TWITCH,BotConstants.STATUS_VIEWER,0);
				chattersArray.add(chatter);
			}			
			return chattersArray;
		}
		catch (Exception e) {
			return null;			
		} 	
	}

	public static int getChatterCount()
	{
		try {
			JSONObject json = new JSONObject(JSONParser.readUrl(String.format("https://tmi.twitch.tv/group/user/%s/chatters", BotGeneralSetupVO.getTwitchChannel())));
			return Integer.parseInt((String)json.get("chatter_count"));
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		return 0;
	}
	
	public static StreamInfoVO getStreamInfo(){
		try {
			JSONObject json = new JSONObject(JSONParser.readUrl(String.format("https://api.twitch.tv/kraken/streams?client_id=%s&channel=wfirept", BotConstants.TWITCHCLIENTID,BotGeneralSetupVO.getTwitchChannel())));
			if(json.getJSONArray("streams").isNull(0)){
				return null;
			}
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
			return new StreamInfoVO(
					format.parse((String)json.getJSONArray("streams").getJSONObject(0).get("created_at")).getTime(),
					(String)json.getJSONArray("streams").getJSONObject(0).getJSONObject("channel").get("status"),
					(String)json.getJSONArray("streams").getJSONObject(0).get("game"));
		}
		catch (Exception e) {
			e.printStackTrace();			
		} 
		return null;	
	}
}
