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

    SharedFoldersManager SFManager;

    public UDPNetworkServer(SharedFoldersManager SFManager) {
        this.SFManager = SFManager;
    }

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

                //packet recebido
                
                if (messageReceived.compareTo("Share Folder") == 0) {

                    //serialize o vetor de ficheiros
                    SharedFolder localSharedFolder = SharedFoldersManager.getLocalSharedFolder();

                    String sharedFolderFiles = "";

                    String[] directoryFileList = localSharedFolder.getListFilesNames();

                    String fileNames = "";
                    for (int i = 0; i < directoryFileList.length; i++) {
                        fileNames = fileNames + directoryFileList[i] + ":";
                    }
                    sharedFolderFiles += fileNames;

                    byte[] directoryListData = sharedFolderFiles.getBytes();

                    //enviar o share folder local
                    String packetAddress = packet.getAddress().toString().replaceAll("/", "");
                    if ((InetAddress.getLocalHost().getHostAddress()).compareTo(packetAddress) != 0) {
 
                        DatagramPacket sendPacket = new DatagramPacket(directoryListData, directoryListData.length, InetAddress.getByName(packetAddress), packet.getPort());

                        System.out.println("\nServidor UDP >>> Enviado packet com Share Folder para: " + sendPacket.getAddress().getHostAddress());

                        socket.send(sendPacket);
                    }

                } else {

                    String packetAddress = packet.getAddress().toString().replaceAll("/", "");
                    System.out.println("\nServidor UDP >>> Recebido packet com Share Folder de: " + packetAddress);

                    String hostname = packet.getAddress().getHostName().replaceAll(".lan", "");
                    String tcpport = "" + Configuration.getTCP_Port();
                    String[] fileList = messageReceived.split(":");

                    SFManager.addSharedFolder(new SharedFolder(packetAddress, hostname, tcpport, fileList));

                    System.out.println("\n" + messageReceived);
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
