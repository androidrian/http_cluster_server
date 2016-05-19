package HTML;

import SharedFolders.SharedFoldersManager;
import UDPNetwork.UDPNetworkClient;
import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HTMLCreatorThread extends Thread {

    SharedFoldersManager SFManager;

    public HTMLCreatorThread(SharedFoldersManager SFManager) {
        this.SFManager = SFManager;
    }

    @Override
    public void run() {
        while (true) {

            try {

                Thread threadUDPNetworkDiscovery = new UDPNetworkClient();
                threadUDPNetworkDiscovery.start();
                
                HTMLParser hp = new HTMLParser(this.SFManager);
                hp.buildHTML(SharedFoldersManager.getDirectoryListFilenames());

                this.SFManager.newlistSharedFolders();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(HTMLCreatorThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(HTMLCreatorThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {

                HTMLCreatorThread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HTMLCreatorThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
