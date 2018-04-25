package com.br.MyFunChatBot.ulils;

public class ChatterVO {

	private String chatter;
	private int platform;
	private int status;
	private int totalTime;
	private int points;
	
	public String getChatter() {
		return chatter;
	}

	public void setChatter(String chatter) {
		this.chatter = chatter;
	}

	public int getPlatform() {
		return platform;
	}

	public void setPlatform(int platform) {
		this.platform = platform;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public void addPoints(int points){
		this.points = this.points+points;
	}
	
	public void subPoints(int points){
		this.points = this.points - points;
		if (this.points < 0){
			this.points = 0;
		}
	}
	
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	
	public int getTotalTime() {
		return this.totalTime;
	}
	
	public void addTotalTime() {
		this.totalTime++;
	}
	
	public void addTotalTime(int time) {
		this.totalTime = this.totalTime + time;
	}	
	
	public ChatterVO(){
	}
	
	public ChatterVO(String chatter, int platform, int status, int points){
		this.chatter = chatter;
		this.platform = platform;
		this.status = status;
		this.points = points;	
	}

	public ChatterVO(String chatter, int platform, int status, int points, int totalTime){
		this.chatter = chatter;
		this.platform = platform;
		this.status = status;
		this.points = points;
		this.totalTime = totalTime;
	}
}
