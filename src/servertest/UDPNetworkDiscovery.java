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
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Filipe
 */
public class UDPNetworkDiscovery  {

    public DatagramSocket ds;
    int port = 6845;
    
    public UDPNetworkDiscovery(){
        
    }
    //eventualmente passar um bool como parametro
    public void UDPBroadcast() throws SocketException{
        
        ds = new DatagramSocket();
        ds.setBroadcast(true);
    
        

       byte[] sendData = "DISCOVERY_SERVER_REQUEST".getBytes();

//       DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), port);
        try {
            //
            ds.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(UDPNetworkDiscovery.class.getName()).log(Level.SEVERE, null, ex);
        }

   System.out.println (getClass().getName() + ">>> Request Packet sent to: 255.255.255.255 (DEFAULT)");
    }
    



}
