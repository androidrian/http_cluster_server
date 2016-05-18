package HTML;

import SharedFolder.SharedFolderData;
import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HTMLCreatorThread extends Thread {

    @Override
    public void run() {
        while (true) {
            HTMLParser hp = new HTMLParser();
            try {
                hp.buildHTML(SharedFolderData.getDirectoryListFilenames());
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(HTMLCreatorThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(HTMLCreatorThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {

                HTMLCreatorThread.sleep(30000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HTMLCreatorThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
