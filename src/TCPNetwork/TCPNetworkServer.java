/**
 * Esta class faz a gestão de todas as ligações
 */
package TCPNetwork;

import Configuration.Configuration;
import HTML.HTMLParser;
import SharedFolder.SharedFolderData;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPNetworkServer extends Thread { //ao extender para Thread, esta class torna-se numa Thread

    //DatagramSocket dataSocket = new DatagramSocket(6843);
    byte[] receivedData = new byte[1024];
    byte[] sendData = new byte[1024];
    HTMLParser hp = new HTMLParser();

    ServerSocket serverSocket;
    Socket s;

    PrintWriter pw;//para enviar o output para o cliente
    BufferedReader br;//para receber o input do cliente

    //thread class contem o metodo run que é chamado automaticamente quando iniciamos a thread
    //aqui temos que ler o request e dar a resposta
    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(Configuration.getTCP_Port());
        } catch (IOException ex) {
            Logger.getLogger(TCPNetworkServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (true) {
            try {
                s = null;

                this.s = serverSocket.accept();

                br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                pw = new PrintWriter(s.getOutputStream());

                try {
                    //aqui fazemos o get do request e passamos a string para o HTTPRequest
                    String sReq = "";

                    try {
                        //do br temos que ler o request
                        // ler até o request não chegar ou até br estar pronto
                        while (br.ready() || sReq.length() == 0) {
                            sReq += (char) br.read();
                        }
                        System.out.println(sReq); //para mostrar o request
                    } catch (IOException ex) {
                        Logger.getLogger(TCPNetworkServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //criamos o request
                    String req = HTTPRequest(sReq);

                    //passamos o request para o response para ter a resposta
                    String res = HTTPResponse(req);

                    //write o output final para o pw
                    pw.write(res.toCharArray());

                    //pofechar por esta ordem
                    pw.close();
                    br.close();
                    s.close();//o sockect é o último a ser fechado

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException ex) {
                Logger.getLogger(TCPNetworkServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String HTTPRequest(String request) {
        //com a resposta, apenas a 1ª linha é importante
        String lines[] = request.split("\n"); //ficamos com todoas as linhas do request separadas
        //esta linha separa 1º a linha usando os espaços e depois selelciona o 2º item
        //que e o nosso filename
        return lines[0].split(" ")[1];
    }

    public String HTTPResponse(String request) {
        String response = null;

        //abre o ficheiro mencionado no request
        File f = new File(Configuration.getFilesLocation() + request);
        System.out.println("passa");
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
            while ((s = fis.read()) != -1) { //-1 significa o fim do ficheiro
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
        return response;
    }
}
