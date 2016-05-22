package servertest;

import Controllers.ServerController;
import UI.MainUI;

import java.io.IOException;

public class Main {
    
    public static void main(String[] args) throws IOException {

        MainUI mainUI = new MainUI(new ServerController());

        mainUI.run();

    }

}
