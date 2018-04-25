package com.br.MyFunChatBot.ulils;

public class StreamInfoVO {
	private long startTime;
	private String game;
	private String title;
	
	public long getStartTime() {
		return startTime;
	}
	
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	public String getGame() {
		return game;
	}
	
	public void setGame(String game) {
		this.game = game;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public StreamInfoVO(long startTime, String game, String title){
		this.startTime = startTime;
		this.game = game;
		this.title = title;
	}
	
	
}
