/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuration;

import java.io.File;
import java.io.IOException;
import org.ini4j.Ini;

/**
 *
 * @author JoaoMoura
 */
public class Configuration {

    public Configuration() {
    }

    public void run() throws IOException {
        Ini ini = new Ini(new File("C:\\Users\\JoaoMoura\\Documents\\NetBeansProjects\\rcomp-2016\\config.ini"));
        System.out.println(ini.get("Server Configuration", "udp_port"));
        System.out.println(ini.get("Server Configuration", "tcp_port"));
        
     String userName = new com.sun.security.auth.module.NTSystem().getName();
        System.out.println(ini.get("Server Configuration", "files_location").replaceAll("@utilizador", userName));
        
    
    }
}
