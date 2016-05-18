/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ini4j.Ini;

class ConfigurationReader {

    private static final String config_location = ".\\config.ini";
    private static Ini ini = null;

    protected static String readUDP_Port() {
        ini = getIniFile();
        if (ini != null) {
            return ini.get("Server Configuration", "udp_port");
        }
        return "";
    }

    protected static String readTCP_Port() {
        ini = getIniFile();
        if (ini != null) {
            return ini.get("Server Configuration", "tcp_port");
        }
        return "";
    }

    protected static String readFiles_Location() {
        ini = getIniFile();
        if (ini != null) {
            String files_location = ini.get("Server Configuration", "files_location");
            String userName = new com.sun.security.auth.module.NTSystem().getName();
            return files_location.replaceAll("@utilizador", userName);
        }
        return "";
    }

    private static Ini getIniFile() {
        ini = null;
        try {
            ini = new Ini(new File(config_location));
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ini;
    }

}
