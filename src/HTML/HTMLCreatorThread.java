package HTML;

import SharedFolders.SharedFoldersManager;
import UDPNetwork.UDPNetworkClient;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HTMLCreatorThread extends Thread {

    SharedFoldersManager SFManager;

    boolean terminar;

    public HTMLCreatorThread(SharedFoldersManager SFManager) {
        this.SFManager = SFManager;
    }

    @Override
    public void run() {
        terminar = false;
        while (true) {

            if (terminar) {
                break;
            } else {

                try {
                    UDPNetworkClient uDPNetworkClient = new UDPNetworkClient();
                    uDPNetworkClient.run();

                    HTMLParser hp = new HTMLParser(this.SFManager);
                    hp.buildHTML(SharedFoldersManager.getDirectoryListFilenames());

                    this.SFManager.newlistSharedFolders();
                    
                   

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(HTMLCreatorThread.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(HTMLCreatorThread.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    HTMLCreatorThread.sleep(30000);
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    public void end() {
        this.interrupt();
        terminar = true;
    }
}
