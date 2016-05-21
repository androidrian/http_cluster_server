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

    public void startServer() {

        String udpState = this.threadUDPServer.getState().toString();
        String tcpState = this.threadTCPServer.getState().toString();
        String htmlState = this.htmlThread.getState().toString();

        if (udpState.compareTo("TERMINATED") == 0 && tcpState.compareTo("TERMINATED") == 0 && htmlState.compareTo("TERMINATED") == 0) {

            this.threadUDPServer = new UDPNetworkServer(SFManager);
            this.htmlThread = new HTMLCreatorThread(SFManager);
            this.threadTCPServer = new TCP_HTTP_Server();

            this.threadUDPServer.start();
            this.htmlThread.start();
            this.threadTCPServer.start();

        } else if (udpState.compareTo("NEW") == 0 && tcpState.compareTo("NEW") == 0 && htmlState.compareTo("NEW") == 0) {

            this.threadUDPServer.start();
            this.htmlThread.start();
            this.threadTCPServer.start();

        } else if (udpState.compareTo("RUNNABLE") == 0 || tcpState.compareTo("RUNNABLE") == 0 || htmlState.compareTo("RUNNABLE") == 0) {
            System.out.println("\n>>> Servidor com processos ativos!!");
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
