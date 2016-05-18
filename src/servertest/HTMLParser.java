/**
 * Gera um ficheiro HTML
 */
package servertest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.PrintWriter;


public class HTMLParser {


String HTML_BEGIN = "<html>\r\n<body>\r\n";
String HTML_END = "</body>\r\n</html>\r\n";
String userName = new com.sun.security.auth.module.NTSystem().getName();
String root = "C:\\Users\\"+userName+"\\Desktop\\root";


public HTMLParser(){
    
}

public void printDirectoryListFilenames(String[] str) {
        System.out.println("*************************************");
        System.out.println("List Directory");
        System.out.print("*************************************");
        for (String names : str) {
            System.out.println(names);
        }
        System.out.println("*************************************");

    }

public String[] getDirectoryListFilenames() throws IllegalArgumentException{
    File file = new File(root);
        String[] str = file.list();
        
        return str;
}

public String getFileRef(String filename){
    String ref;
    ref = "<a href="+"\""+filename+"\""+">"+filename+"</a><br>\r\n";  
    
    return ref;
}

public void buildHTML(String[] filenames) throws FileNotFoundException {
    
    File file = new File(root + "\\index.html");
    PrintWriter pw = new PrintWriter(file);
    
    pw.write(HTML_BEGIN);
    pw.write(htmlTitle("List Directory")); //não está a funcionar, por um titulo, ver consturção HTML (não é importante)
    for(String s : filenames){
        pw.write(getFileRef(s));
    }
    pw.write(HTML_END);
    pw.flush();
    pw.close();
}

public String htmlTitle(String s){
    String title = "<title>" +s+ "</title>\r\n";
    return title;
}
}

