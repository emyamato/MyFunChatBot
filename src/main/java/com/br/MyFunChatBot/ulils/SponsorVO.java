package com.br.MyFunChatBot.ulils;

public class SponsorVO {
	private String v_KeyWord;
	private String v_KeyID;
	private String v_SponsorLink;
	private String v_SponsorSite;
	
	private String v_UserRequest;
	
	public SponsorVO(String keyWord, String keyID, String sponsorLink, String sponsorSite) {
		v_KeyWord = keyWord;
		v_KeyID = keyID;
		v_SponsorLink = sponsorLink;
		v_SponsorSite = sponsorSite;
	}
	
	public String getKeyWord() {
		return v_KeyWord;
	}
	
	public void setKeyWord(String keyWord) {
		v_KeyWord = keyWord;
	}
	
	public String getKeyID() {
		return v_KeyID;
	}
	
	public void setKeyID(String keyID) {
		v_KeyID = keyID;
	}
	
	public String getSponsorLink() {
		return v_SponsorLink;
	}
	
	public void setSponsorLink(String sponsorLink) {
		v_SponsorLink = sponsorLink;
	}
	
	public String getSponsorSite() {
		return v_SponsorSite;
	}
	
	public void setSponsorSite(String sponsorSite) {
		v_SponsorSite = sponsorSite;
	}	
	
	public String getUserRequest() {
		return v_UserRequest;
	}
	
	public void setUserRequest(String userRequest) {
		v_UserRequest = userRequest;
	}
}
