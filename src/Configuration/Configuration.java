/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuration;

public class Configuration {

    public String udp_port;

    public String tcp_port;

    public String files_location;

    public static String getFilesLocation() {
       return ConfigurationReader.readUDP_Port();
    }

    public static String getUDP_Port() {
        return ConfigurationReader.readUDP_Port();
    }
    
    public static String getTCP_Port() {
        return ConfigurationReader.readUDP_Port();
    }
}
