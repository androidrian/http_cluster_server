/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertest;

import Configuration.Configuration;
import TCPNetwork.ConnectionHandler;
import UDPNetwork.UDPNetworkServer;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
        new Configuration().run();
        new Main().runServer();

    }

    public void runServer() throws IOException {
        Thread threadUDPServer = new UDPNetworkServer();
        threadUDPServer.start();

        Thread threadTCPServer = new ConnectionHandler();
        threadTCPServer.start();
    }
}
