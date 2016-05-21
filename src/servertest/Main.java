/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertest;

import HTML.HTMLCreatorThread;
import SharedFolders.SharedFoldersManager;
import TCPNetwork.TCP_HTTP_Server;
import UI.MainUI;

import UDPNetwork.UDPNetworkServer;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
       MainUI mainUI = new MainUI(new ServerManager());
       
       mainUI.run();
        
        
    }

  

 
}
