/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Main {

    public static void main(String[] args) throws IOException {

        new Main().runServer();

    }

    public void runServer() throws IOException {

        Thread ch1 = new UDPNetworkServer();
        ch1.start();

        Thread ch = new ConnectionHandler();

        ch.start();
    }
}
