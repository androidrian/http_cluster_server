/**
 * Esta class faz a gestão de todas as ligações
 */
package servertest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionHandler extends Thread { //ao extender para Thread, esta class torna-se numa Thread

    //DatagramSocket dataSocket = new DatagramSocket(6843);
    byte[] receivedData = new byte[1024];
    byte[] sendData = new byte[1024];
    HTMLParser hp = new HTMLParser();
    String[] filelist = hp.getDirectoryListFilenames();
    ServerSocket serverSocket;
    Socket s;

    PrintWriter pw;//para enviar o output para o cliente
    BufferedReader br;//para receber o input do cliente

    public ConnectionHandler() throws IOException {
        serverSocket = null;
        int port = 6543;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //thread class contem o metodo run que é chamado automaticamente quando iniciamos a thread
    //aqui temos que ler o request e dar a resposta
    @Override
    public void run() {

        while (true) {
            try {
                s = null;

                this.s = serverSocket.accept();

                br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                pw = new PrintWriter(s.getOutputStream());

                hp.printDirectoryListFilenames(filelist);
                try {
                    hp.buildHTML(filelist);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                        Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //criamos o request
                    HTTPRequest req = new HTTPRequest(sReq);

                    //passamos o request para o response para ter a resposta
                    HTTPResponse res = new HTTPResponse(req);
                    //write o output final para o pw
                    pw.write(res.response.toCharArray());
                    //pofechar por esta ordem
                    pw.close();
                    br.close();
                    s.close();//o sockect é o último a ser fechado
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException ex) {
                Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
