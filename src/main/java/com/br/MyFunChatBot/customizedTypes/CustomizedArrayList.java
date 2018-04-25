package com.br.MyFunChatBot.customizedTypes;

import java.util.ArrayList;

public class CustomizedArrayList {
	
	public final static String TYPE_AUTOANSWER = "1";
	public final static String TYPE_BADWORD = "2";
	
	private ArrayList<String[]> customizedArrayList;
	
	public CustomizedArrayList(){
		customizedArrayList = new ArrayList<String[]>();
	}
	
	public CustomizedArrayList(String table){
		String[] tableSplit = table.split(":::");
		customizedArrayList = new ArrayList<String[]>(); 
		for(int i = 0; i < tableSplit.length; i++){
			customizedArrayList.add(tableSplit[i].split("::"));
		}
	}
	
	public void add(String keyword, String answer, String type){
		String[] temp = {keyword,answer,type};
		customizedArrayList.add(temp);
	}
	
	public String[] get(int index){
		return customizedArrayList.get(index);
	}
	
	public int size()
	{
		return customizedArrayList.size();
	}
}
