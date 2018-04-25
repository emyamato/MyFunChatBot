package com.br.MyFunChatBot.business;

import java.util.ArrayList;
import java.util.Collections;

import com.br.MyFunChatBot.ulils.MyFunctions;
import com.br.MyFunChatBot.ulils.VotingVO;
import com.br.MyFunChatBot.main.MyFunChatBot;

public class VotingThread /*implements Runnable*/{
	
	private static VotingThread votingThread;
	private static boolean isRunning;
	private static VotingVO votingVO;
	private static ArrayList<String> arrayListViewers;
	private static ArrayList<String> allVoteType;
	private static ArrayList<String> allVotes;
	private static long time;
	
	public VotingThread(){
		arrayListViewers = new ArrayList<String>();
		allVoteType = new ArrayList<String>();
		allVotes = new ArrayList<String>();
		time = 0;
	}
	
	public static VotingThread getInstance(){
		if(votingThread == null){
			votingThread = new VotingThread();
		}
		return votingThread;
	}

/*
	public void run(){
		if( votingVO == null || !votingVO.isValid() ){
			return;
		}
		arrayListViewers.clear();
		allVotes.clear();
		isRunning = true;
		long period = 0;
		long startTime = System.currentTimeMillis();
		
		MessageControl.sendMessage(getStartVotingMessage());
		while((period < votingVO.getTimer()*60000) && isRunning){
			period = System.currentTimeMillis() - startTime;
			MyFunChatBot.setVotingProgressBar(period, votingVO.getTimer()*60000);
		}
		MessageControl.sendMessage(getStopVotingMessage());
		MyFunChatBot.setVotingOver();
	}
*/
	
	public static void addVote(String viewer, String vote){
		if(isRunning && !arrayListViewers.contains(viewer) && isVote(vote.toUpperCase())){
			arrayListViewers.add(viewer);
			String viewerVote = "";
			if(allVoteType.contains(vote.toUpperCase())){
				viewerVote = vote.toUpperCase();
				allVotes.add(viewerVote);
			}
			else if(Integer.parseInt(vote)-1 <= allVoteType.size()){
				viewerVote = allVoteType.get(Integer.parseInt(vote)-1);
				allVotes.add(viewerVote);
			}
			MessageControl.sendMessage("Thanks "+viewer+" for voting "+ viewerVote);
		}
	}
	
	public static boolean isVote(String vote){
		if(allVoteType.contains(vote.toUpperCase())){
			return true;
		}
		else if(MyFunctions.isNumber(vote)){
			if (Integer.parseInt(vote)-1 < allVoteType.size()){
				return true;				
			}
		}
		return false;
	}
	
	public static void setVotingVO(VotingVO votingVO){
		VotingThread.votingVO = votingVO;
		int i = 0;
		allVoteType = new ArrayList<String>();
		if(votingVO.getOption1() != null && !votingVO.getOption1().equalsIgnoreCase("")){
			allVoteType.add(i, votingVO.getOption1().toUpperCase());
			i++;
		}

		if(votingVO.getOption2() != null && !votingVO.getOption2().equalsIgnoreCase("")){
			allVoteType.add(i, votingVO.getOption2().toUpperCase());
			i++;
		}

		if(votingVO.getOption3() != null && !votingVO.getOption3().equalsIgnoreCase("")){
			allVoteType.add(i, votingVO.getOption3().toUpperCase());
			i++;
		}

		if(votingVO.getOption4() != null && !votingVO.getOption4().equalsIgnoreCase("")){
			allVoteType.add(i, votingVO.getOption4().toUpperCase());
			i++;
		}

		if(votingVO.getOption5() != null && !votingVO.getOption5().equalsIgnoreCase("")){
			allVoteType.add(i, votingVO.getOption5().toUpperCase());
			i++;
		}

		if(votingVO.getOption6() != null && !votingVO.getOption6().equalsIgnoreCase("")){
			allVoteType.add(i, votingVO.getOption6().toUpperCase());
			i++;
		}
	}
	
	public static boolean isReady(){
		getInstance();		
		if( votingVO == null || !votingVO.isValid() ){
			return false;
		}
		return true;
	}
	
	public static void addSecond(){
		getInstance();
		if(time <= 0){
			arrayListViewers.clear();
			allVotes.clear();
			MessageControl.sendMessage(getStartVotingMessage());
			time = 0;
		}
		else if (time > votingVO.getTimer()*60000){
			MessageControl.sendMessage(getStopVotingMessage());
			MyFunChatBot.setVotingOver();
			isRunning = false;
			votingVO = null;
			time = 0;
		}
		MyFunChatBot.setVotingProgressBar(time, votingVO.getTimer()*60000);		
		time = time + 1000;		
	}
	
	public static void start(){
		getInstance();		
		isRunning = true;
		time = 0;
	}
	
	public static boolean isRunning(){
		getInstance();		
		return isRunning;
	}
	
	public static void stop(){
		MessageControl.sendMessage(getStopVotingMessage());
		MyFunChatBot.setVotingOver();
		isRunning = false;
		votingVO = null;		
	}
/*
	public static void stop(){
		isRunning = false;
		Thread.currentThread().interrupt();
		MessageControl.sendMessage(getStopVotingMessage());
		MyFunChatBot.setVotingOver();		
	}
*/	
	private static String getStartVotingMessage(){
		String message = " << "+votingVO.getTitle()+" >> Vote ";
		for (int i = 0; i < allVoteType.size(); i++){
			message = message.concat(" | <["+(i+1)+"] "+allVoteType.get(i)+">");
		}		
		return message;
	}
	
	private static String getStopVotingMessage(){
		String message = " << "+votingVO.getTitle()+" >> Final Voting Results: ";
		for (int i = 0; i < allVoteType.size(); i++){
			message = message.concat( " | <("+Collections.frequency(allVotes.subList(0, allVotes.size()),allVoteType.get(i))+") votes for "+allVoteType.get(i)+"> ");
		}		
		return message;
	}	

}
