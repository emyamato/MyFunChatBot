package com.br.MyFunChatBot.main;

import java.awt.Color;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.br.MyFunChatBot.business.MyFunChatBotControl;
import com.br.MyFunChatBot.ulils.BotGeneralSetupVO;
import com.br.MyFunChatBot.ulils.JSONParser;
import com.br.MyFunChatBot.ulils.StreamInfoVO;
import com.br.MyFunChatBot.ulils.TwitchAccessClass;
import com.mb3364.http.AsyncHttpClient;
import com.mb3364.http.HttpClient;
import com.mb3364.http.HttpRequestMethod;
import com.mb3364.http.HttpResponseHandler;
import com.mb3364.twitch.api.Twitch;
import com.mb3364.twitch.api.auth.Scopes;
import com.mb3364.twitch.api.handlers.ChannelResponseHandler;
import com.mb3364.twitch.api.models.Channel;

import de.comhix.twitch.api.TwitchApi;
import de.comhix.twitch.api.data.DetailedUser;
import de.comhix.twitch.api.oauth.ClientInformation;
import io.reactivex.Observable;

public class MyFunChatBot2
{
	private static Twitch twitch;

	public static void main(String[] args) {
		System.out.println(72%60);
		//System.out.println(String.format("#%06x", Color.BLUE.getRGB() & 0x00FFFFFF));

	}

	public static void setUserToken()
	{
//		getInstance();
		twitch = new Twitch();
		twitch.setClientId(BotGeneralSetupVO.getTwitchClientId());
		URI callbackUri = null;
		try {
			callbackUri = new URI("http://127.0.0.1:23522/authorize.html");
		} 
		catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		String authUrl = twitch.auth().getAuthenticationUrl(twitch.getClientId(), callbackUri, Scopes.CHANNEL_CHECK_SUBSCRIPTION, Scopes.CHANNEL_EDITOR, Scopes.CHAT_LOGIN );

		try {
			String url = authUrl + "&force_verify=true";
			Desktop.getDesktop().browse(URI.create(url));
		} 
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		boolean authSuccess = twitch.auth().awaitAccessToken();
		
		if (authSuccess) {
			  BotGeneralSetupVO.setTwitchAccessToken(twitch.auth().getAccessToken());
			  setChannel();
			  System.out.println(BotGeneralSetupVO.getTwitchChannel());
		} 
		else {
			  System.out.println(twitch.auth().getAuthenticationError());
		}
	}
	
	public static void setChannel()
	{
		try {
			JSONObject json = new JSONObject(JSONParser.readUrl(String.format("https://api.twitch.tv/kraken?oauth_token=%s",BotGeneralSetupVO.getTwitchAccessToken())));
			String[] channelsArray = ((String)json.getJSONObject("_links").get("channels")).split("/");
			BotGeneralSetupVO.setTwitchChannel(channelsArray[channelsArray.length-1]);
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static ArrayList<String> getModeratorsList()
	{
		ArrayList<String> moderatorsArray = new ArrayList<String>();
		try {
			JSONObject json = new JSONObject(JSONParser.readUrl(String.format("https://tmi.twitch.tv/group/user/%s/chatters", BotGeneralSetupVO.getTwitchChannel())));
			JSONArray moderatorsJArray = (json.getJSONObject("chatters").getJSONArray("moderators"));
			for (int i = 0; i<moderatorsJArray.length(); i++) {
				moderatorsArray.add(moderatorsJArray.getString(i));
			}
			return moderatorsArray;
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public static ArrayList<String> getViewersList()
	{
		ArrayList<String> viewersArray = new ArrayList<String>();
		try {
			JSONObject json = new JSONObject(JSONParser.readUrl(String.format("https://tmi.twitch.tv/group/user/%s/chatters", BotGeneralSetupVO.getTwitchChannel())));
			JSONArray viewersJArray = (json.getJSONObject("chatters").getJSONArray("viewers"));
			for (int i = 0; i<viewersJArray.length(); i++) {
				viewersArray.add(viewersJArray.getString(i));
			}
			return viewersArray;
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}	

}
