package com.br.MyFunChatBot.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import com.br.MyFunChatBot.ulils.BotConstants;
import com.br.MyFunChatBot.ulils.BotGeneralSetupVO;
import com.br.MyFunChatBot.ulils.ChatterVO;

public class RankingControl{
	
	private static RankingControl rankingFile;
	
	private static HashMap<String,ChatterVO> hashmapChatterVO;
	
	private RankingControl() {
		hashmapChatterVO = new HashMap<String,ChatterVO>();		
		loadChatterList();
	}
	
	public static RankingControl getInstance() {
		if(rankingFile == null) {
			rankingFile = new RankingControl();
		}
		return rankingFile;
	}
	
	private static void loadChatterList(){
		hashmapChatterVO.clear();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("rankingList.txt")));
			String line;
			int extraMod = 0;
		    while ((line = reader.readLine()) != null){
		    	if(!line.equalsIgnoreCase("")){
		    		if(Integer.parseInt(line.split("::")[2]) == BotConstants.STATUS_MODERATOR){
		    			extraMod = BotGeneralSetupVO.getPointsExtraMod();
		    		}
		    		else{
		    			extraMod = 0;
		    		}
		    		
		    		hashmapChatterVO.put(line.split("::")[0].toUpperCase()+"-"+line.split("::")[1], new ChatterVO(line.split("::")[0],Integer.parseInt(line.split("::")[1]),Integer.parseInt(line.split("::")[2]),extraMod,Integer.parseInt(line.split("::")[4])));
		    	}
		    }
		    reader.close();			
		} 
		catch (FileNotFoundException e) {
			FileWriter configFileWriter;
			try {
				configFileWriter = new FileWriter("rankingList.txt");
				configFileWriter.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void saveChatterList() {
		getInstance();
		ChatterVO chatterVO = new ChatterVO();
		try {
			FileWriter configFileWriter = new FileWriter("rankingList.txt");
			Iterator<ChatterVO> iteratorChatterList = hashmapChatterVO.values().iterator();
			while(iteratorChatterList.hasNext()) {
				chatterVO = iteratorChatterList.next();
				configFileWriter.append(chatterVO.getChatter()+"::"+chatterVO.getPlatform()+"::"+chatterVO.getStatus()+"::"+chatterVO.getPoints()+"::"+chatterVO.getTotalTime()+"\n");
			}
			configFileWriter.close();			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static void addTime(String username, int platform, int status, int time) {
		getInstance();
		ChatterVO newChatterVO = null;
		if(hashmapChatterVO.containsKey(username.toUpperCase()+"-"+platform)) {
			newChatterVO = hashmapChatterVO.remove(username.toUpperCase()+"-"+platform);
			newChatterVO.addTotalTime(time);
			hashmapChatterVO.put(newChatterVO.getChatter().toUpperCase()+"-"+newChatterVO.getPlatform(), newChatterVO);
		}
		else {
			hashmapChatterVO.put(username.toUpperCase()+"-"+platform,new ChatterVO(username,platform,status,0,1));
		}
	}
	
	public static void addPoint(String username, int platform, int status, int points) {
		getInstance();
		ChatterVO newChatterVO = null;
		if(hashmapChatterVO.containsKey(username.toUpperCase()+"-"+platform)) {
			newChatterVO = hashmapChatterVO.remove(username.toUpperCase()+"-"+platform);
			newChatterVO.addPoints(points);
			hashmapChatterVO.put(newChatterVO.getChatter().toUpperCase()+"-"+newChatterVO.getPlatform(), newChatterVO);
		}
		else {
			hashmapChatterVO.put(username.toUpperCase()+"-"+platform,new ChatterVO(username,platform,status,points,0));
		}
	}

	public static void addPoint(String username, int platform, int points) {
		getInstance();
		ChatterVO newChatterVO = null;
		if(hashmapChatterVO.containsKey(username.toUpperCase()+"-"+platform)) {
			newChatterVO = hashmapChatterVO.remove(username.toUpperCase()+"-"+platform);
			newChatterVO.addPoints(points);
			hashmapChatterVO.put(newChatterVO.getChatter().toUpperCase()+"-"+newChatterVO.getPlatform(), newChatterVO);
		}
	}

	public static void subPoints(String username, int platform, int points) {
		getInstance();
		ChatterVO newChatterVO = null;
		if(hashmapChatterVO.containsKey(username.toUpperCase()+"-"+platform)) {
			newChatterVO = hashmapChatterVO.remove(username.toUpperCase()+"-"+platform);
			newChatterVO.subPoints(points);
			hashmapChatterVO.put(newChatterVO.getChatter().toUpperCase()+"-"+newChatterVO.getPlatform(), newChatterVO);
		}
	}	
	
	public static void addPointAndTime(String username, int platform, int status, int time) {
		getInstance();
		int extraModPoint = 0;
		if(BotConstants.STATUS_MODERATOR == status){
			extraModPoint = BotGeneralSetupVO.getPointsExtraMod();
		}

		ChatterVO newChatterVO = null;
		if(hashmapChatterVO.containsKey(username.toUpperCase()+"-"+platform)) {
			newChatterVO = hashmapChatterVO.remove(username.toUpperCase()+"-"+platform);
			if(newChatterVO.getStatus() != status){
				newChatterVO.setStatus(status);
			}
			newChatterVO.addPoints(BotGeneralSetupVO.getPointsPerTime(newChatterVO.getTotalTime()));
			newChatterVO.addTotalTime(time);
			hashmapChatterVO.put(newChatterVO.getChatter().toUpperCase()+"-"+newChatterVO.getPlatform(), newChatterVO);
		}
		else {
			hashmapChatterVO.put(username.toUpperCase()+"-"+platform,new ChatterVO(username,platform,status,extraModPoint,time));
		}
	}
	
	public static int getPointsAmount(String viewer, int platform){
		if(hashmapChatterVO.containsKey(viewer.toUpperCase()+"-"+platform)){
			return hashmapChatterVO.get(viewer.toUpperCase()+"-"+platform).getPoints();
		}
		return 0;		
	}
	
	public static void resetPoints(){
		getInstance();
		ChatterVO chatterVO;
		Iterator<ChatterVO> iteratorChatterList = hashmapChatterVO.values().iterator();
		while(iteratorChatterList.hasNext()) {
			chatterVO = iteratorChatterList.next();
			if(chatterVO.getStatus() == BotConstants.STATUS_MODERATOR){
				chatterVO.setPoints(BotGeneralSetupVO.getPointsExtraMod());
			}
			else{
				chatterVO.setPoints(0);
			}
			hashmapChatterVO.remove(chatterVO.getChatter().toUpperCase()+"-"+chatterVO.getPlatform());
			hashmapChatterVO.put(chatterVO.getChatter().toUpperCase()+"-"+chatterVO.getPlatform(), chatterVO);			
		}		
	}
	
	public static boolean hasViewer(String username, int platform){
		getInstance();
		return hashmapChatterVO.containsKey(username.toUpperCase()+"-"+platform);	
	}
	
	public static ChatterVO[] getViewersData(){
		return hashmapChatterVO.values().toArray(new ChatterVO[hashmapChatterVO.values().size()]);
	}
	
	public static void setChatterVO(ChatterVO chatterVO){
		getInstance();
		ChatterVO newChatterVO = null;
		if(hashmapChatterVO.containsKey(chatterVO.getChatter().toUpperCase()+"-"+chatterVO.getPlatform())) {
			newChatterVO = hashmapChatterVO.remove(chatterVO.getChatter().toUpperCase()+"-"+chatterVO.getPlatform());
			newChatterVO.setTotalTime(chatterVO.getTotalTime());
			hashmapChatterVO.put(newChatterVO.getChatter().toUpperCase()+"-"+newChatterVO.getPlatform(), newChatterVO);				
		}
	}
	
	public static String getViewerTitle(String viewer, int platform){
		if(hashmapChatterVO.containsKey(viewer.toUpperCase()+"-"+platform)) {
			if(((ChatterVO)hashmapChatterVO.get(viewer.toUpperCase()+"-"+platform)).getStatus() == BotConstants.STATUS_OWNER){
				return "Master";
			}
			return BotGeneralSetupVO.getRankTitle(((ChatterVO)hashmapChatterVO.get(viewer.toUpperCase()+"-"+platform)).getTotalTime());
		}
		return "No";
	}
	
	public static int getViewerTotalTime(String viewer, int platform){
		if(hashmapChatterVO.containsKey(viewer.toUpperCase()+"-"+platform)) {
			return ((ChatterVO)hashmapChatterVO.get(viewer.toUpperCase()+"-"+platform)).getTotalTime();
		}
		return 0;
	}
	
	public static boolean isModerator(String viewer, int platform){
		if(hashmapChatterVO.containsKey(viewer.toUpperCase()+"-"+platform)){
			if(hashmapChatterVO.get(viewer.toUpperCase()+"-"+platform).getStatus() == BotConstants.STATUS_MODERATOR){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isOwner(String viewer, int platform){
		if(hashmapChatterVO.containsKey(viewer.toUpperCase()+"-"+platform)){
			if(hashmapChatterVO.get(viewer.toUpperCase()+"-"+platform).getStatus() == BotConstants.STATUS_OWNER){
				return true;
			}
		}
		return false;
	}
	
}