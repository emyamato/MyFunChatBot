package com.br.MyFunChatBot.ulils;

public class RankVO {
	private int rankLevel;
	private String rankName;
	private int rankMin;
	private int rankBonus;
	
	public RankVO(int rankLevel, String rankName, int rankMin, int rankBonus){
		this.rankLevel = rankLevel;
		this.rankName = rankName;
		this.rankMin = rankMin;
		this.rankBonus = rankBonus;
	}
	
	public RankVO(String stringRank){
		this.rankLevel = Integer.parseInt(stringRank.split("::")[0]);
		this.rankName = stringRank.split("::")[1];
		this.rankMin = Integer.parseInt(stringRank.split("::")[2]);
		this.rankBonus = Integer.parseInt(stringRank.split("::")[3]);
	}
	
	public int getRankLevel() {
		return rankLevel;
	}
	
	public void setRankLevel(int rankLevel) {
		this.rankLevel = rankLevel;
	}
	
	public String getRankName() {
		return rankName;
	}
	
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	
	public int getRankMin() {
		return rankMin;
	}
	
	public void setRankMin(int rankMin) {
		this.rankMin = rankMin;
	}
	
	public int getRankBonus() {
		return rankBonus;
	}
	
	public void setRankBonus(int rankBonus) {
		this.rankBonus = rankBonus;
	}
	
	public String getRankVOToString(){
		return (this.rankLevel+"::"+this.rankName+"::"+this.rankMin+"::"+this.rankBonus);
	}
}
