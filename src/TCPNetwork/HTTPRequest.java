/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCPNetwork;

public class HTTPRequest {
    
    //1ª linha do request contem 3 partes --> 1-request, 2-nome 3-versao HTTP
    
    String filename;
    
    //criamos um construtor para aceitar a String
    public HTTPRequest(String request){
        //com a resposta, apenas a 1ª linha é importante
        String lines[] = request.split("\n"); //ficamos com todoas as linhas do request separadas
        //esta linha separa 1º a linha usando os espaços e depois selelciona o 2º item
        //que e o nosso filename
        filename = lines[0].split(" ")[1];
    }
    
}
