/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Filipe
 */
public class Main {
    private InetAddress ip;
    ServerSocket serverSocket;
    int port = 6543;
    public static void main(String[] args) throws IOException{
        
        new Main().runServer(); 
    }
    
    
    public void runServer() throws IOException{
        System.out.println("Server has started...");
        serverSocket = new ServerSocket(port);
        //para aceitar as ligações
        acceptRequests();
    }
    
    private void acceptRequests() throws IOException{
        while(true){ //aceita todos os requests (keep alive)
            //ligação ao cliente é na forma de socket  
            //que contém o stream para input e output
            Socket s = serverSocket.accept();
            ConnectionHandler ch = new ConnectionHandler(s);
            
            //ch é a thread, temos que iniciar usando o metodo start
            ch.start();//chama o metodo run
        }
    }
}
