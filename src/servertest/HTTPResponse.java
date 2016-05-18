/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HTTPResponse {
    
    HTTPRequest req;
    //a resposta final gerada pelo GET
    String response;
    
    //o caminho da pasta do servidor
    String userName = new com.sun.security.auth.module.NTSystem().getName();
    String root = "C:\\Users\\"+userName+"\\Desktop\\root";
    
    
    public HTTPResponse(HTTPRequest request) {
        req = request;
        
        //abre o ficheiro mencionado no request
        File f = new File(root + req.filename);
        
        try {
            //para ler o ficheiro
            FileInputStream fis = new FileInputStream(f);
            response = "HTTP1.1 200 \r\n"; //versao http e 200 codigo status
            //200 significa que esta tudo ok
            
            response += "server: O servidor JAVA/1.0 \r\n"; //id do servidor
            response += "Content-Type: text/html \r\n"; //response se esta no formato html
            response += "Connection: close \r\n"; // esta linha diz ao browser para fechar a ligação
            response += "Content-Length: " + f.length() + " \r\n"; //tamanho do ficheiro de resposta
            response += "\r\n"; // depois de uma linha linha em branco tem-se que fazer o append do ficheiro
            int s;
            while((s=fis.read()) != -1){ //-1 significa o fim do ficheiro
          response += (char) s;
        }
        fis.close();
        //substituir esta parte depois talvez com um case para cada erro, 200, 404, 500, etc...
        } catch (FileNotFoundException ex) {
            response = response.replace("200", "404");
            System.out.println("Ficheiro não existente.");
            Logger.getLogger(HTTPResponse.class.getName()).log(Level.SEVERE, null, ex);
            //senao conseguir encontrar o ficheiro dá erro 404
        } catch (IOException ex) {
            //se for outro erro dá erro interno 500
            response = response.replace("200", "500");
            Logger.getLogger(HTTPResponse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
