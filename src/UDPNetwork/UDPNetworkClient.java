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

            
            
            
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ObjectOutputStream oos = new ObjectOutputStream(baos);
//            oos.writeObject(directoryFileList);
//            oos.close();

            

            DatagramPacket directoryListPacket;
            DatagramPacket sendPacket;
byte[] sendData = "Packet Recebido".getBytes();
            System.out.println("\nCliente UDP >>> Request Packet sent to: 255.255.255.255");
            directoryListPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), Configuration.getUDP_Port());
            dl.send(directoryListPacket);

//            //fazer o broadcast do UDP para descobrir os servidores ativos
//            //waiting for response 
//            long startTime = System.currentTimeMillis(); //fetch starting time
//            while (false || (System.currentTimeMillis() - startTime) < 30000) {
//                byte[] recBuff = new byte[512];
//                byte[] recListDataBuff = new byte[1024];
//
//                try {
//                    DatagramPacket receivePacket = new DatagramPacket(recBuff, recBuff.length);
//                    ds.receive(receivePacket);
//
//                    DatagramPacket receiveDataListPacket = new DatagramPacket(recListDataBuff, recListDataBuff.length);
//                    dl.receive(receiveDataListPacket);
//
//                    System.out.println("\nCliente UDP >>> Packet recebido com sucesso pelo servidor: " + receivePacket.getAddress());
//
//                    String message2 = new String(receiveDataListPacket.getData());
//
//                    String message = new String(receivePacket.getData()).trim();
//           
//                        System.out.println("\nCliente UDP >>> Packet received from:" + receivePacket.getAddress());
//                    
//
//                } catch (IOException e) {
//
//                }
//                ds.close();
//           }
        } catch (UnknownHostException ex) {
            Logger.getLogger(UDPNetworkClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UDPNetworkClient.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }

}
