/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
