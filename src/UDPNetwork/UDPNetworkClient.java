package UDPNetwork;

import Configuration.Configuration;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPNetworkClient implements Runnable {

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
