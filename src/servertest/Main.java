/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertest;

import HTML.HTMLCreatorThread;
import SharedFolders.SharedFoldersManager;
import TCPNetwork.TCP_HTTP_Server;

import UDPNetwork.UDPNetworkClient;
import UDPNetwork.UDPNetworkServer;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
       
        new Main().runServer();

    }

    public void runServer() throws IOException {
        SharedFoldersManager SFManager = new SharedFoldersManager();
        
        
        Thread threadUDPServer = new UDPNetworkServer(SFManager);
        threadUDPServer.start();

        
        
    HTMLCreatorThread htmlThread= new HTMLCreatorThread(SFManager);
        htmlThread.start();
        
        
        Thread threadTCPServer = new TCP_HTTP_Server();
        threadTCPServer.start();
        
        Thread threadUDPNetworkDiscovery = new UDPNetworkClient();
                threadUDPNetworkDiscovery.start();
                
    }
}
