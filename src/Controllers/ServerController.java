package Controllers;

import HTML.HTMLCreatorThread;
import SharedFolders.SharedFoldersManager;
import TCPNetwork.TCP_HTTP_Server;
import UDPNetwork.UDPNetworkServer;

public class ServerController {

    UDPNetworkServer threadUDPServer;
    HTMLCreatorThread htmlThread;
    TCP_HTTP_Server threadTCPServer;
    SharedFoldersManager SFManager;

    public ServerController() {
        SFManager = new SharedFoldersManager();
        this.threadUDPServer = new UDPNetworkServer(SFManager);
        this.htmlThread = new HTMLCreatorThread(SFManager);
        this.threadTCPServer = new TCP_HTTP_Server();
    }

    public boolean startServer() {

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
            System.out.println("\n>>> Servidor iniciado com sucesso!!");
            return true;

        } else if (udpState.compareTo("NEW") == 0 && tcpState.compareTo("NEW") == 0 && htmlState.compareTo("NEW") == 0) {

            this.threadUDPServer.start();
            this.htmlThread.start();
            this.threadTCPServer.start();
            System.out.println("\n>>> Servidor iniciado com sucesso!!");
            return true;

        } else if (udpState.compareTo("RUNNABLE") == 0 || tcpState.compareTo("RUNNABLE") == 0 || htmlState.compareTo("RUNNABLE") == 0) {
            System.out.println("\n>>> Servidor já se encontra ligado !!");
            return false;
        }
        return false;
    }

    public boolean stopServer() {

        String udpState = this.threadUDPServer.getState().toString();
        String tcpState = this.threadTCPServer.getState().toString();
        String htmlState = this.htmlThread.getState().toString();

        if (udpState.compareTo("TERMINATED") == 0 && tcpState.compareTo("TERMINATED") == 0 && htmlState.compareTo("TERMINATED") == 0) {
            System.out.println("\n>>> Servidor já se encontra desligado !!");
            return false;
        } else if (udpState.compareTo("NEW") == 0 && tcpState.compareTo("NEW") == 0 && htmlState.compareTo("NEW") == 0) {
            System.out.println("\n>>> Servidor já se encontra desligado !!");
            return false;
        } else {
            this.threadTCPServer.end();
            this.threadUDPServer.end();
            this.htmlThread.end();
            System.out.println("\n>>> Servidor encerrado com sucesso!!");
            return true;
        }

    }

    public SharedFoldersManager getSFManager() {
        return SFManager;
    }
}
