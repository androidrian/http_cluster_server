/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertest;

import HTML.HTMLCreatorThread;
import SharedFolders.SharedFoldersManager;
import TCPNetwork.TCP_HTTP_Server;
import UDPNetwork.UDPNetworkServer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joao
 */
public class ServerManager {

    UDPNetworkServer threadUDPServer;
    HTMLCreatorThread htmlThread;
    TCP_HTTP_Server threadTCPServer;
    SharedFoldersManager SFManager;

    public ServerManager() {
        SFManager = new SharedFoldersManager();
        this.threadUDPServer = new UDPNetworkServer(SFManager);
        this.htmlThread = new HTMLCreatorThread(SFManager);
        this.threadTCPServer = new TCP_HTTP_Server();
    }

    public void startServer()  {
System.out.println(this.threadUDPServer.getState());


if((this.threadUDPServer.getState().toString()).compareTo("TERMINATED")==0){
    
  

       this.threadUDPServer = new UDPNetworkServer(SFManager);
    this.threadUDPServer.start();

}else if (!this.threadUDPServer.isAlive()) {
            
            
            this.threadUDPServer.start();
            this.htmlThread.start();
            this.threadTCPServer.start();

        } else {
            System.out.println("\n>>> Server already started !!");
        }
    }

    public void stopServer() {
        this.threadTCPServer.end();
        this.threadUDPServer.end();
        this.htmlThread.end();
    }

    public SharedFoldersManager getSFManager() {
        return SFManager;
    }

}
