package com.br.MyFunChatBot.http;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.MyFunChatBot.business.MessageControl;
import com.br.MyFunChatBot.business.SponsorListControl;
import com.br.MyFunChatBot.ulils.BotGeneralSetupVO;
import com.br.MyFunChatBot.ulils.SponsorVO;

public class URLBotSponsorHandler extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private SponsorVO sponsorVO;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try {
			doGet(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	resp.setStatus(HttpServletResponse.SC_OK);
    	PrintWriter out = resp.getWriter();
    	
    	sponsorVO = SponsorListControl.getNextToPlay();
    	
    	if(sponsorVO != null) {
    		if(BotGeneralSetupVO.getBotSponsorMessageEnabled()){
    			MessageControl.sendMessage(BotGeneralSetupVO.printBotSponsorMessage().replace("[SPONSOR]",sponsorVO.getKeyID()).replace("[SPONSORLINK]",sponsorVO.getSponsorSite()));
    		}
			out.println("<html>");
			out.println("<STYLE>");
			out.println(("video { width: 80%; height: auto; border-top: 2vw solid [BORDERCOLOR]; border-left: 2vw solid [BORDERCOLOR]; border-right: 2vw solid [BORDERCOLOR]; border-bottom: 2vw solid [BORDERCOLOR]; border-radius: 25px 25px 25px 25px;box-shadow: -5px 10px 10px grey;} ").replace("[BORDERCOLOR]",BotGeneralSetupVO.getBorderColorToString()));
			out.println("</STYLE> ");
			out.println("<SCRIPT> ");
			out.println("function videoEnded() {	document.getElementById('Sponsor').style.visibility = 'hidden'; setTimeout(function(){	location.reload();}, 5000); } ");
			out.println("function sleep(ms) { return new Promise(resolve => setTimeout(resolve, ms)); } ");
			out.println("</SCRIPT> ");
			out.println("<body> ");
			out.println("<figure> ");
			out.println(("<video id=\"Sponsor\" autoplay=\"autoplay\" src=\"/memes/[SPONSOR]\" type=\"video/mp4\" onended=\"videoEnded()\"></video> ").replace("[SPONSOR]", sponsorVO.getSponsorLink()));		
			out.println("</figure> ");
			out.println("</body> ");
			out.println("</html>");
	    	out.close();
    	}
    	else {
			out.println("<html>");
			out.println("<HEAD><meta http-equiv=\"refresh\" content=\"5\" > </HEAD>");
			out.println("<body> ");
			out.println("</body> ");
			out.println("</html>");
	    	out.close();    		
    	}
    }
}
