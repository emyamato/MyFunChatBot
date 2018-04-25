package com.br.MyFunChatBot.ulils;

public class MyFunctions {
	
	public static boolean isNumber(String st){
		try{
			Integer.parseInt(st);  
		}  
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true; 
	}
	
	public static Object nvl(Object obj, Object ret){
		if(obj == null || ((String)obj).equalsIgnoreCase("")){
			return ret;
		}
		else{
			return obj;
		}
	}

}
