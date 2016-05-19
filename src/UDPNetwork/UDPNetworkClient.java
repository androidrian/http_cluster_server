/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDPNetwork;

import Configuration.Configuration;
import HTML.HTMLParser;
import SharedFolders.SharedFolder;
import SharedFolders.SharedFoldersManager;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Filipe
 */
public class UDPNetworkClient extends Thread {

    HTMLParser hp = new HTMLParser();

    public DatagramSocket ds;
    public DatagramSocket dl;

    @Override
    public void run() {

        try {

            ds = new DatagramSocket();
            dl = new DatagramSocket();
            ds.setBroadcast(true);

            DatagramPacket sendPacket;
            byte[] sendData = "Share Folder".getBytes();
            
            System.out.println("\nCliente UDP >>> Request Packet sent to: 255.255.255.255");
            
            sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), Configuration.getUDP_Port());
            
            dl.send(sendPacket);

        } catch (UnknownHostException ex) {
            Logger.getLogger(UDPNetworkClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UDPNetworkClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
