/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertest;

/**
 *
 * @author Filipe
 */
public class DiscoveryThread implements Runnable
{

    @Override
    public void run() {
        
    }
    
    public static DiscoveryThread getInstance(){
        return DiscoveryThreadHolder.INSTANCE;
    }
    
   private static class DiscoveryThreadHolder{
       private static final DiscoveryThread INSTANCE = new DiscoveryThread();
   }
}
