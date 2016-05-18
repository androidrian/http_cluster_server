/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertest;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Filipe
 */
public class UDPNetworkDiscovery extends Thread{

    public DatagramSocket ds;
    int port = 8888;

    public UDPNetworkDiscovery() {

    }

    //eventualmente passar um bool como parametro para activar ou descativer o discovery
    public void UDPBroadcast() throws SocketException, UnknownHostException {
        System.out.println("passa");
        ds = new DatagramSocket();
        ds.setBroadcast(true);

        byte[] sendData = "DISCOVERY_SERVER_REQUEST".getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("192.168.1.77"), port);
        try {

            ds.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(UDPNetworkDiscovery.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(getClass().getName() + ">>> Request Packet sent to: 255.255.255.255 (DEFAULT)");

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
                    DatagramPacket packetBroadcast = new DatagramPacket(sendData, sendData.length, broadcast, port);
                    ds.send(packetBroadcast);

                } catch (IOException e) {

                }

                System.out.println(getClass().getName() + ">>> Request Packet sent to:" + broadcast.getHostAddress()
                        + "; Interface:  " + networkInterface.getDisplayName());
            }
        }

        System.out.println(getClass().getName() + ">>> Done Looping over all network interfaces. Waiting for reply!");

        //waiting for response
        byte[] recBuff = new byte[512];

        try {
            DatagramPacket receivePacket = new DatagramPacket(recBuff, recBuff.length);
            ds.receive(receivePacket);
       
        
        System.out.println(getClass().getName() + ">>> Broadcast response from server" + receivePacket.getAddress());
         
        
        //comparar a mensagem recebida
        String message = new String(receivePacket.getData()).trim();
        if(message.equals("DISCOVERY_SERVER_REQUEST")){
            System.out.println("Packet received from:" + receivePacket.getAddress());
        }
        } catch (IOException e) {

        }
        ds.close();
        
    }

}
