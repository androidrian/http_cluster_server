/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertest;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Filipe
 */
public class DiscoveryThread implements Runnable
{

    @Override
    public void run() {

        try {
            new UDPNetworkDiscovery().UDPBroadcast();
       
        } catch (SocketException ex) {
            Logger.getLogger(DiscoveryThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(DiscoveryThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DiscoveryThread getInstance(){
        return DiscoveryThreadHolder.INSTANCE;
    }
    
   private static class DiscoveryThreadHolder{
       private static final DiscoveryThread INSTANCE = new DiscoveryThread();
   }
}
