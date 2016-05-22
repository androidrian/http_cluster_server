package TCPNetwork;

import Configuration.Configuration;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCP_HTTP_Server extends Thread { //ao extender para Thread, esta class torna-se numa Thread

    //DatagramSocket dataSocket = new DatagramSocket(6843);
    byte[] receivedData = new byte[1024];
    byte[] sendData = new byte[1024];

    boolean terminar;

    ServerSocket serverSocket;
    Socket s;

    PrintWriter pw;//para enviar o output para o cliente
    BufferedReader br;//para receber o input do cliente

    public TCP_HTTP_Server() {
    }

    //thread class contem o metodo run que é chamado automaticamente quando iniciamos a thread
    //aqui temos que ler o request e dar a resposta
    @Override
    public void run() {
        terminar = false;
        try {
            serverSocket = new ServerSocket(Configuration.getTCP_Port());

            while (!this.isInterrupted()) {

                if (terminar) {
                    break;
                }

                try {
                    try {
                        this.s = serverSocket.accept();
                    } catch (SocketException e) {
                    }
                    if (!terminar) {
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

                            } catch (IOException ex) {
                                Logger.getLogger(TCP_HTTP_Server.class.getName()).log(Level.SEVERE, null, ex);
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
                            Logger.getLogger(TCP_HTTP_Server.class.getName()).log(Level.SEVERE, null, e);
                        }
                    }

                } catch (IOException ex) {
                    Logger.getLogger(TCP_HTTP_Server.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception e) {
                    Logger.getLogger(TCP_HTTP_Server.class.getName()).log(Level.SEVERE, null, e);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(TCP_HTTP_Server.class.getName()).log(Level.SEVERE, null, ex);
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
        String response = "";

        //abre o ficheiro mencionado no request
        File f = new File(Configuration.getFilesLocation() + request);
        try {
            FileInputStream fis = new FileInputStream(f);
            int s;
            while ((s = fis.read()) != -1) { //-1 significa o fim do ficheiro
                response += (char) s;
            }
            fis.close();

        } catch (IOException ex) {

            Logger.getLogger(TCP_HTTP_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    public void end() {
        terminar = true;
        try {
            serverSocket.close();
        } catch (IOException ex) {
        }
        this.interrupt();
    }

}
