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

import com.br.MyFunChatBot.customizedTypes.CircularArrayList;
import com.br.MyFunChatBot.ulils.BotGeneralSetupVO;
import com.br.MyFunChatBot.ulils.SponsorVO;

public class SponsorListControl{
	
	private static SponsorListControl sponsorListControl;
	
//	private static HashMap<String,SponsorVO> hashmapSponsorVO;
	private static CircularArrayList<SponsorVO> circularSponsorList;
	private static ArrayList<SponsorVO> arrayListSponsorVOToPlay;
	private static ArrayList<String> listSponsorString;
	
	private SponsorListControl() {
		listSponsorString = new ArrayList<String>();
//		hashmapSponsorVO = new HashMap<String,SponsorVO>();
		circularSponsorList = new CircularArrayList<SponsorVO>();
		arrayListSponsorVOToPlay = new ArrayList<SponsorVO>();	
		loadSponsorList();
	}
	
	public static SponsorListControl getInstance() {
		if(sponsorListControl == null) {
			sponsorListControl = new SponsorListControl();
		}
		return sponsorListControl;
	}
	
	private static void loadSponsorList(){
//		hashmapSponsorVO.clear();
		circularSponsorList.clear();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("sponsorList.txt")));
			String line;
		    while ((line = reader.readLine()) != null){
		    	if(!line.equalsIgnoreCase("") && line.split("::").length == 4){
			    	listSponsorString.add(line);
//		    		hashmapSponsorVO.put(line.split("::")[0].toUpperCase(), new SponsorVO(line.split("::")[0].toUpperCase(),line.split("::")[1].toUpperCase(),line.split("::")[2],line.split("::")[3]));
		    		circularSponsorList.add(new SponsorVO(line.split("::")[0].toUpperCase(),line.split("::")[1].toUpperCase(),line.split("::")[2],line.split("::")[3]));
		    	}
		    }
		    reader.close();			
		} 
		catch (FileNotFoundException e) {
			FileWriter configFileWriter;
			try {
				configFileWriter = new FileWriter("sponsorList.txt");
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
	
	public static void saveSponsorList() {
		getInstance();
		SponsorVO sponsorVO;
		try {
			FileWriter configFileWriter = new FileWriter("sponsorList.txt");
			while(!circularSponsorList.isEmpty()){
				sponsorVO = circularSponsorList.remove(0);
				configFileWriter.append(sponsorVO.getKeyWord().toUpperCase()+"::"+sponsorVO.getKeyID().toUpperCase()+"::"+sponsorVO.getSponsorLink()+"::"+sponsorVO.getSponsorSite()+"\n");
			}
			/*
			Iterator<SponsorVO> iteratorSponsorList = hashmapSponsorVO.values().iterator();
			while(iteratorSponsorList.hasNext()) {
				sponsorVO = iteratorSponsorList.next();
				configFileWriter.append(sponsorVO.getKeyWord().toUpperCase()+"::"+sponsorVO.getKeyID().toUpperCase()+"::"+sponsorVO.getSponsorLink()+"::"+sponsorVO.getSponsorSite()+"\n");
			}
			*/
			configFileWriter.close();			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public static ArrayList<String> getStringSponsorList(){
		getInstance();
		return listSponsorString;
	}

	/*
	public static void setSponsorVO(SponsorVO sponsorVO){
		getInstance();
		if(hashmapSponsorVO.containsKey(sponsorVO.getKeyWord().toUpperCase())) {
			hashmapSponsorVO.remove(sponsorVO.getKeyWord().toUpperCase());				
		}
		hashmapSponsorVO.put(sponsorVO.getKeyWord().toUpperCase(), sponsorVO);		
	}
*/
	public static void setSponsorVO(SponsorVO sponsorVO){
		getInstance();
		circularSponsorList.add(sponsorVO);		
	}

/*
	public static SponsorVO getSponsorVO(String keyWord){
		if(hashmapSponsorVO.containsKey(keyWord.toUpperCase())) {
			return (SponsorVO)hashmapSponsorVO.get(keyWord.toUpperCase());
		}
		return null;
	}

	public static void clearSponsorList() {
		hashmapSponsorVO.clear();
	}
*/
	public static void clearSponsorList() {
		circularSponsorList.clear();
	}

	public static boolean hasSponsorToPlay() {
		if(arrayListSponsorVOToPlay.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public static SponsorVO getNextToPlay() {
		if(hasSponsorToPlay()) {
			return arrayListSponsorVOToPlay.remove(0);
		}
		return null;
	}
/*	
	public static SponsorVO isSponsor(String message) {
		if(hashmapSponsorVO.containsKey(message.toUpperCase())) {
			return hashmapSponsorVO.get(message.toUpperCase());
		}
		return null;
	}
*/
	public static boolean isInListToPlay(String keyID) {
		for(int i=0; i<arrayListSponsorVOToPlay.size(); i++) {
			if (arrayListSponsorVOToPlay.get(i).getKeyID().toUpperCase().equalsIgnoreCase(keyID.toUpperCase())) {
				return true;
			}
		}
		return false;
	}
	
	public static void putSponsorToPlay(SponsorVO sponsorVO) {
		getInstance();		
		arrayListSponsorVOToPlay.add(sponsorVO);
	}
	
	public static void scheduleToPlay(){
		getInstance();		
		SponsorVO sponsorVO = circularSponsorList.next();
		putSponsorToPlay(sponsorVO);
	}
	
}