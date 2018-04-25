package com.br.MyFunChatBot.http;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class URLBotChestHandler extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5159895940675486491L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try {
			doGet(request,response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	resp.setStatus(HttpServletResponse.SC_OK);
    	resp.addHeader("Refresh", "5");
    	PrintWriter out = resp.getWriter();
    	out.println("<h1>"+URLBotChestHandler.class.getName()+"</h1>");
    	out.println("<h1>"+System.currentTimeMillis()+"</h1>");
    }
}
