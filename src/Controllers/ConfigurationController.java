package Controllers;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ini4j.Ini;

public class ConfigurationController {

    private static final String config_location = ".\\config.ini";
    private static Ini ini = null;

    public ConfigurationController() {
    }

    public static String readUDP_Port() {
        ini = getIniFile();
        if (ini != null) {
            return ini.get("Server Configuration", "udp_port");
        }
        return "";
    }

    public static String readTCP_Port() {
        ini = getIniFile();
        if (ini != null) {
            return ini.get("Server Configuration", "tcp_port");
        }
        return "";
    }

    public static String readFiles_Location() {
        ini = getIniFile();
        if (ini != null) {
            String files_location = ini.get("Server Configuration", "files_location");
            String userName = new com.sun.security.auth.module.NTSystem().getName();
            return files_location.replaceAll("@utilizador", userName);
        }
        return "";
    }

    public boolean setUDPPort(String udpPort) {
        File file = new File(config_location);

        try {
            ini = new Ini(file);
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ini.put("Server Configuration", "udp_port", udpPort);

        try {
            ini.store(file);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean setTCPPort(String tcpPort) {
        File file = new File(config_location);

        try {
            ini = new Ini(file);
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ini.put("Server Configuration", "tcp_port", tcpPort);

        try {
            ini.store(file);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean setFiles_Location(String folderLocation) {
        File file = new File(config_location);

        try {
            ini = new Ini(file);
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ini.put("Server Configuration", "files_location", folderLocation);
       
        try {
            ini.store(file);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static Ini getIniFile() {
        ini = null;
        try {
            ini = new Ini(new File(config_location));
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ini;
    }

}
