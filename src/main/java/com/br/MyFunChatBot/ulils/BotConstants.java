package com.br.MyFunChatBot.ulils;

public final class BotConstants {
	
	public static final String BOTNAME = "MyFunChatBot";

	public static final String TWITCHCLIENTID = "vaw5c6k69k51xyp3vb54rmr9n7de4v";
	
	public static final int STATUS_MYFUNCHATBOT_ON = 1;
	public static final int STATUS_MYFUNCHATBOT_OFF = 0;
	
	public final static int PLATFORM_TWITCH = 1;
	public final static int PLATFORM_YOUTUBE = 2;
	public final static int PLATFORM_DISCORD = 3;
	public final static int PLATFORM_FACEBOOK = 4;
	
	public final static int STATUS_VIEWER = 0;
	public final static int STATUS_MODERATOR = 1;
	public final static int STATUS_OWNER = 2;
	
	public final static int INTERATION_TYPE_MEME = 0;
	public final static int INTERATION_TYPE_GIF = 1;
	public final static int INTERATION_TYPE_CHEST = 2;
	public final static int INTERATION_TYPE_SPONSOR = 3;
	
	public static String getPlatformTranslate(int i){
		if(i == PLATFORM_TWITCH){
			return "Twitch";
		}
		else if (i == PLATFORM_YOUTUBE){
			return "Youtube";
		}
		else if (i == PLATFORM_DISCORD){
			return "Discord";
		}
		else if (i == PLATFORM_FACEBOOK){
			return "Facebook";
		}
		return null;
	}
	
	public static int getPlatformTranslate(String platform){
		if(platform.toUpperCase().equalsIgnoreCase("TWITCH")){
			return PLATFORM_TWITCH;
		}
		else if (platform.toUpperCase().equalsIgnoreCase("YOUTUBE")){
			return PLATFORM_YOUTUBE;
		}
		else if (platform.toUpperCase().equalsIgnoreCase("DISCORD")){
			return PLATFORM_DISCORD;
		}
		else if (platform.toUpperCase().equalsIgnoreCase("FACEBOOK")){
			return PLATFORM_FACEBOOK;
		}
		return 0;
	}
	
	public static String getStatusTranslate(int i){
		if(i == STATUS_VIEWER){
			return "Viewer";
		}
		else if(i == STATUS_MODERATOR){
			return "Moderator";
		}
		else if(i == STATUS_OWNER){
			return "Owner";
		}		
		return null;
	}
	
	public static int getStatusTranslate(String status){
		if(status.equalsIgnoreCase("VIEWER")){
			return STATUS_VIEWER;
		}
		else if(status.equalsIgnoreCase("MODERATOR")){
			return STATUS_MODERATOR;
		}
		else if(status.equalsIgnoreCase("OWNER")){
			return STATUS_OWNER;
		}		
		return STATUS_VIEWER;
	}	
	
}
