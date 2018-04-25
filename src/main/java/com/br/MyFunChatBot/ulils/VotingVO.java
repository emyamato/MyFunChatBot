package com.br.MyFunChatBot.ulils;

public class VotingVO {
	private String title;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String option5;
	private String option6;
	private int timer;
	
	public VotingVO(String title, int timer, String option1, String option2, String option3, String option4, String option5, String option6){
		this.title = title;
		this.timer = timer;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.option5 = option5;
		this.option6 = option6;
	}
	
	public VotingVO(String votingVOString){
		title = votingVOString.split("::")[0].replace("'","");
		timer = Integer.parseInt(votingVOString.split("::")[1].replace("'",""));
		option1 = votingVOString.split("::")[2].replace("'","");
		option2 = votingVOString.split("::")[3].replace("'","");
		option3 = votingVOString.split("::")[4].replace("'","");
		option4 = votingVOString.split("::")[5].replace("'","");
		option5 = votingVOString.split("::")[6].replace("'","");
		option6 = votingVOString.split("::")[7].replace("'","");
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getOption1() {
		return option1;
	}
	
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	
	public String getOption2() {
		return option2;
	}
	
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	
	public String getOption3() {
		return option3;
	}
	
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	
	public String getOption4() {
		return option4;
	}
	
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	
	public String getOption5() {
		return option5;
	}
	
	public void setOption5(String option5) {
		this.option5 = option5;
	}
	
	public String getOption6() {
		return option6;
	}
	
	public void setOption6(String option6) {
		this.option6 = option6;
	}
	
	public int getTimer() {
		return timer;
	}
	
	public void setTimer(int timer) {
		this.timer = timer;
	}
	
	public boolean isValid(){
		
		if(title == null || title.equalsIgnoreCase("")){
			return false;
		}
		
		if(timer == 0){
			return false;
		}
		
		if(option1.equalsIgnoreCase("") &&
				option2.equalsIgnoreCase("") &&
				option3.equalsIgnoreCase("") &&
				option4.equalsIgnoreCase("") &&
				option5.equalsIgnoreCase("") &&
				option6.equalsIgnoreCase("")){
			return false;
		}
		
		return true;
	}
	
	public String getVotingVOToString(){
		return "'"+title+"'::'"+timer+"'::'"+option1+"'::'"+option2+"'::'"+option3+"'::'"+option4+"'::'"+option5+"'::'"+option6+"'";
	}
}
