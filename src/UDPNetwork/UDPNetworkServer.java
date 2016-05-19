/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDPNetwork;

import Configuration.Configuration;
import SharedFolders.SharedFolder;
import SharedFolders.SharedFoldersManager;
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
               
                
                //mensagem recebida
                String messageReceived = new String(packet.getData()).trim();
                System.out.println(messageReceived);

                //packet recebido
                if (messageReceived.compareTo("Share Folder") == 0) {
                    System.out.println("passa");
                    //serialize o vetor de ficheiros
                    String sharedFolderInfo = "";
                    SharedFolder localSharedFolder = SharedFoldersManager.getLocalSharedFolder();

                    sharedFolderInfo += localSharedFolder.getMachineName();
                    sharedFolderInfo += "|";

                    String[] directoryFileList = localSharedFolder.getListFilesNames();

                    String fileNames = "";
                    for (int i = 0; i < directoryFileList.length; i++) {
                        fileNames = fileNames + directoryFileList[i] + ":";
                    }
                    sharedFolderInfo += fileNames;
                    byte[] directoryListData = sharedFolderInfo.getBytes();

                    
                    String packetAddress = "" + packet.getAddress();
                    packetAddress = packetAddress.replaceAll("/", "");
                    
                    //enviar o share folder local
                    if ((InetAddress.getLocalHost().getHostAddress()).compareTo(packetAddress) != 0) {
                        byte[] sendData = "Packet Recebido".getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, packet.getAddress(), packet.getPort());

                        System.out.println("\nServidor UDP >>> Enviada confirmação da receção de packet para: " + sendPacket.getAddress().getHostAddress());

                        socket.send(sendPacket);
                    }

                    System.out.println("Servidor UDP >>> Packet enviado com sucesso para: " + packet.getAddress());
                
                } else {
                    System.out.println("\nServidor UDP >>> Packet recebido de: " + new String(packet.getData()).trim());

                }
                String message = new String(packet.getData()).trim();
                String[] fileList = message.split(":");

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
