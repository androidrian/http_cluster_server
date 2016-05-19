/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDPNetwork;

import Configuration.Configuration;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPNetworkServer extends Thread {

    DatagramSocket socket;

    @Override
    public void run() {

        try {
            //Keep a socket open to listen to all the UDP trafic that is destined for this port

            socket = new DatagramSocket(Configuration.getUDP_Port(), InetAddress.getByName("0.0.0.0"));

            socket.setBroadcast(true);

            while (true) {

                System.out.println("\nServidor UDP >>> Pronto a receber broadcast packets!");

                //receber um packet
                byte[] recvBuf = new byte[15000];

                DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);

                socket.receive(packet);

                //packet recebido
                if ((new String(packet.getData())).compareTo("Packet Recebido") == 0) {

                    System.out.println("Servidor UDP >>> Packet enviado com sucesso para: " + packet.getAddress());
                } else {
                    System.out.println("\nServidor UDP >>> Packet recebido de: " + packet.getAddress().getHostAddress());

                }
                String message = new String(packet.getData()).trim();
                String[] fileList = message.split(":");

                //enviar uma resposta
                String packetAddress = "" + packet.getAddress();
                packetAddress = packetAddress.replaceAll("/", "");

                if ((InetAddress.getLocalHost().getHostAddress()).compareTo(packetAddress) != 0) {
                    byte[] sendData = "Packet Recebido".getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, packet.getAddress(), packet.getPort());

                    System.out.println("\nServidor UDP >>> Enviada confirmação da receção de packet para: " + sendPacket.getAddress().getHostAddress());

                    socket.send(sendPacket);
                }

            }

        } catch (SocketException ex) {
            Logger.getLogger(UDPNetworkServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(UDPNetworkServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UDPNetworkServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
