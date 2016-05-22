/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuration;

import Controllers.ConfigurationController;

public class Configuration {

    public String udp_port;

    public String tcp_port;

    public String files_location;

    public static String getFilesLocation() {
        return ConfigurationController.readFiles_Location();
    }

    public static int getUDP_Port() {
        return Integer.parseInt(ConfigurationController.readUDP_Port());
    }

    public static int getTCP_Port() {
        return Integer.parseInt(ConfigurationController.readTCP_Port());
    }
}
