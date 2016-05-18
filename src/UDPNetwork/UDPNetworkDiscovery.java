/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDPNetwork;

import HTML.HTMLParser;
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
public class UDPNetworkDiscovery extends Thread {

    HTMLParser hp = new HTMLParser();
    
    
    public DatagramSocket ds;
    public DatagramSocket dl;
    int port = 8888;

    @Override
    public void run() {
        
        try {

            ds = new DatagramSocket();
            dl = new DatagramSocket();
            
            ds.setBroadcast(true);
            byte[] sendData = "DISCOVERY_SERVER_REQUEST".getBytes();
            
            //serialize o vetor de ficheiros
            String[] directoryFileList = hp.getDirectoryListFilenames();
            for(String s : directoryFileList){
                System.out.println(s);
            }
            String fileNames = "";
            for(int i =0; i< directoryFileList.length; i++){
                fileNames = fileNames + directoryFileList[i] + ":";
            }
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(directoryFileList);
            oos.close();
            
            byte[] directoryListData = fileNames.getBytes();
            System.out.println("*******************");
            System.out.println("String: "+ fileNames);
            DatagramPacket directoryListPacket;
            DatagramPacket sendPacket;
           
            directoryListPacket = new DatagramPacket(directoryListData, directoryListData.length,InetAddress.getByName("255.255.255.255"),port);
            dl.send(directoryListPacket);
            
            sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), port);
            ds.send(sendPacket);

            System.out.println(getClass().getName() + ">>> Request Packet sent to: 255.255.255.255");

            //fazer o broadcast do UDP para descobrir os servidores ativos
            Enumeration interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) interfaces.nextElement();

                if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                    continue;
                }

                for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                    InetAddress broadcast = interfaceAddress.getBroadcast();//só para IPv4 para IPv6 retorna null
                    if (broadcast == null) {
                        continue;
                    }
                    //enviar o broadcast
                    try {
                        DatagramPacket packetFileListBroadcast = new DatagramPacket(directoryListData, directoryListData.length, broadcast, port);
                        
                        DatagramPacket packetBroadcast = new DatagramPacket(sendData, sendData.length, broadcast, port);
                        ds.send(packetBroadcast);
                        dl.send(packetFileListBroadcast);

                    } catch (IOException e) {

                    }

                    System.out.println(getClass().getName() + ">>> Request Packet sent to:" + broadcast.getHostAddress()
                            + "; Interface:  " + networkInterface.getDisplayName());
                }
            }

            System.out.println(getClass().getName() + ">>> Done Looping over all network interfaces. Waiting for reply!");

            //waiting for response
            byte[] recBuff = new byte[512];
            byte[] recListDataBuff = new byte[1024];

            try {
                DatagramPacket receivePacket = new DatagramPacket(recBuff, recBuff.length);
                ds.receive(receivePacket);
                
                DatagramPacket receiveDataListPacket = new DatagramPacket(recListDataBuff, recListDataBuff.length);
                dl.receive(receiveDataListPacket);

                System.out.println(getClass().getName() + ">>> Broadcast response from server" + receivePacket.getAddress());
                System.out.println(getClass().getName() + ">>> Resposta de lista de ficheiros recebida do servidor" + receiveDataListPacket.getAddress());

                
               // ######### SERVER #########
                
//                new ObjectInputStream(new ByteArrayInputStream(directoryListData)).readObject();
                
                String message2 = new String(receiveDataListPacket.getData());
                
                
                String message = new String(receivePacket.getData()).trim();
                if (message.equals("DISCOVERY_SERVER_REQUEST")) {
                    System.out.println("Packet received from:" + receivePacket.getAddress());
                }
            } catch (IOException e) {

            } 
            ds.close();

        } catch (UnknownHostException ex) {
            Logger.getLogger(UDPNetworkDiscovery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UDPNetworkDiscovery.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
