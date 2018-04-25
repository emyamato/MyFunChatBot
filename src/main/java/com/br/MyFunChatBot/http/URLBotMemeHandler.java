package com.br.MyFunChatBot.http;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.MyFunChatBot.business.MemeListControl;
import com.br.MyFunChatBot.ulils.BotGeneralSetupVO;
import com.br.MyFunChatBot.ulils.MemeVO;

public class URLBotMemeHandler extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MemeVO memeVO;

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
    	
    	memeVO = MemeListControl.getNextToPlay();
    	
    	if(memeVO != null) {
			out.println("<html>");
			out.println("<STYLE>");
			out.println(("video { width: 80%; height: auto; border-top: 2vw solid [BORDERCOLOR]; border-left: 2vw solid [BORDERCOLOR]; border-right: 2vw solid [BORDERCOLOR]; border-bottom: 0vw solid [BORDERCOLOR]; border-radius: 25px 25px 0px 0px;box-shadow: -5px 10px 10px grey;} ").replace("[BORDERCOLOR]",BotGeneralSetupVO.getBorderColorToString()));
			out.println(("figcaption { align: center; position: relative; width: 80%; height: auto; text-align: center; border-radius: 0px 0px 25px 25px; font-size: 7vw; padding: 2vw; background-color: [BORDERCOLOR]; color: #FFFFFF; font-weight: bold;box-shadow: -5px 10px 10px grey;} ").replace("[BORDERCOLOR]",BotGeneralSetupVO.getBorderColorToString()));
			out.println("</STYLE> ");
			out.println("<SCRIPT> ");
			out.println("function videoEnded() {	document.getElementById('Meme').style.visibility = 'hidden'; document.getElementById('legenda').style.visibility = 'hidden'; setTimeout(function(){	location.reload();}, 1000); } ");
			out.println("function sleep(ms) { return new Promise(resolve => setTimeout(resolve, ms)); } ");
			out.println("</SCRIPT> ");
			out.println("<body> ");
			out.println("<figure> ");
			out.println(("<video id=\"Meme\" autoplay=\"autoplay\" src=\"/memes/[MEMEFILE]\" type=\"video/mp4\" onended=\"videoEnded()\"></video> ").replace("[MEMEFILE]", memeVO.getMemeLink()));		
			out.println(("<figcaption id=\"legenda\">[USER]</figcaption> ").replace("[USER]", memeVO.getUserRequest()));
			out.println("</figure> ");
			out.println("</body> ");
			out.println("</html>");
	    	out.close();
    	}
    	else {
			out.println("<html>");
			out.println("<HEAD><meta http-equiv=\"refresh\" content=\"1\" > </HEAD>");
			out.println("<body> ");
			out.println("</body> ");
			out.println("</html>");
	    	out.close();    		
    	}
    }
}
