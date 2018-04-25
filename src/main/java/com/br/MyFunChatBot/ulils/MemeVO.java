package com.br.MyFunChatBot.ulils;

public class MemeVO {
	private String v_KeyWord;
	private String v_KeyID;
	private String v_MemeLink;
	
	private String v_UserRequest;
	
	public MemeVO(String keyWord, String keyID, String memeLink) {
		v_KeyWord = keyWord;
		v_KeyID = keyID;
		v_MemeLink = memeLink;
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
	
	public String getMemeLink() {
		return v_MemeLink;
	}
	
	public void setMemeLink(String memeLink) {
		v_MemeLink = memeLink;
	}
	
	public String getUserRequest() {
		return v_UserRequest;
	}
	
	public void setUserRequest(String userRequest) {
		v_UserRequest = userRequest;
	}
}
