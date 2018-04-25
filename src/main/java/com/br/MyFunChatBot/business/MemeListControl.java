package com.br.MyFunChatBot.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.br.MyFunChatBot.customizedTypes.CircularArrayList;
import com.br.MyFunChatBot.ulils.MemeVO;

public class MemeListControl{
	
	private static MemeListControl memeListControl;
	
	private static HashMap<String,MemeVO> hashmapMemeVO;
	private static ArrayList<MemeVO> arrayListMemeVOToPlay;
	private static ArrayList<String> listMemeString;
	
	private MemeListControl() {
		listMemeString = new ArrayList<String>();
		hashmapMemeVO = new HashMap<String,MemeVO>();
		arrayListMemeVOToPlay = new ArrayList<MemeVO>();	
		loadMemeList();
	}
	
	public static MemeListControl getInstance() {
		if(memeListControl == null) {
			memeListControl = new MemeListControl();
		}
		return memeListControl;
	}
	
	private static void loadMemeList(){
		hashmapMemeVO.clear();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("memeList.txt")));
			String line;
		    while ((line = reader.readLine()) != null){
		    	if(!line.equalsIgnoreCase("") && line.split("::").length == 3){
			    	listMemeString.add(line);		    		
		    		hashmapMemeVO.put(line.split("::")[0].toUpperCase(), new MemeVO(line.split("::")[0].toUpperCase(),line.split("::")[1].toUpperCase(),line.split("::")[2]));
		    	}
		    }
		    reader.close();			
		} 
		catch (FileNotFoundException e) {
			FileWriter configFileWriter;
			try {
				configFileWriter = new FileWriter("memeList.txt");
				configFileWriter.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> getStringMemeList(){
		getInstance();
		return listMemeString;
	}
	
	public static void saveMemeList() {
		getInstance();
		MemeVO memeVO;
		try {
			FileWriter configFileWriter = new FileWriter("memeList.txt");
			Iterator<MemeVO> iteratorMemeList = hashmapMemeVO.values().iterator();
			while(iteratorMemeList.hasNext()) {
				memeVO = iteratorMemeList.next();
				configFileWriter.append(memeVO.getKeyWord().toUpperCase()+"::"+memeVO.getKeyID().toUpperCase()+"::"+memeVO.getMemeLink()+"\n");
			}
			configFileWriter.close();			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static void setMemeVO(MemeVO memeVO){
		getInstance();
		if(hashmapMemeVO.containsKey(memeVO.getKeyWord().toUpperCase())) {
			hashmapMemeVO.remove(memeVO.getKeyWord().toUpperCase());				
		}
		hashmapMemeVO.put(memeVO.getKeyWord().toUpperCase(), memeVO);		
	}
	
	public static MemeVO getMemeVO(String keyWord){
		if(hashmapMemeVO.containsKey(keyWord.toUpperCase())) {
			return (MemeVO)hashmapMemeVO.get(keyWord.toUpperCase());
		}
		return null;
	}
	
	public static void clearMemeList() {
		hashmapMemeVO.clear();
	}
	
	public static boolean hasMemeToPlay() {
		if(arrayListMemeVOToPlay.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public static MemeVO getNextToPlay() {
		if(hasMemeToPlay()) {
			return arrayListMemeVOToPlay.remove(0);
		}
		return null;
	}
	
	public static MemeVO isMeme(String message) {
		if(hashmapMemeVO.containsKey(message.toUpperCase())) {
			return hashmapMemeVO.get(message.toUpperCase());
		}
		return null;
	}
	
	public static boolean isInListToPlay(String keyID) {
		for(int i=0; i<arrayListMemeVOToPlay.size(); i++) {
			if (arrayListMemeVOToPlay.get(i).getKeyID().toUpperCase().equalsIgnoreCase(keyID.toUpperCase())) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean putMemeToPlay(String user, MemeVO memeVO) {
		if(!isInListToPlay(memeVO.getKeyID())){
			memeVO.setUserRequest(user);
			arrayListMemeVOToPlay.add(memeVO);
			return true;
		}
		return false;
	}
	
}