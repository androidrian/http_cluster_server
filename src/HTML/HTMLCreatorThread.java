package HTML;

import java.util.logging.Level;
import java.util.logging.Logger;


public class HTMLCreatorThread extends Thread {

    @Override
    public void run() {
        while (true) {
            HTMLParser hp = new HTMLParser();
          
            try {
                HTMLCreatorThread.sleep(30000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HTMLCreatorThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("passa");
        }
    }
}
