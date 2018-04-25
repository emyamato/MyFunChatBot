package com.br.MyFunChatBot.main;

import org.eclipse.jetty.server.Server;

public class ServletTest{

    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8085);
        server.start();
        server.dumpStdErr();
        server.join();
    }
}