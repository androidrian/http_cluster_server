package HTML;

import Configuration.Configuration;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class HTMLParser {

    String HTML_BEGIN = "<!DOCTYPE html>\n<html>\n";
    String HTML_END = "</table>\n</body>\n</html>\n";

    public HTMLParser() {

    }

    public String[] getDirectoryListFilenames() throws IllegalArgumentException {
        File file = new File(Configuration.getFilesLocation());
        String[] str = file.list();

        return str;
    }

    public String getFileRef(String filename) {
        String ref;
        ref = "<a href=" + "\"" + filename + "\"" + " download>" + filename + "</a>";

        return ref;
    }

    public void buildHTML(String[] filenames) throws FileNotFoundException,UnknownHostException {

        File file = new File(Configuration.getFilesLocation() + "\\index.html");
        PrintWriter pw = new PrintWriter(file);

        pw.write(HTML_BEGIN);
        //head
        pw.write("<head>\n");
        pw.write("<title> List Directory </title>\r\n");
        pw.write("</head>\n");

        
        pw.write("<th>");pw.write(InetAddress.getLocalHost().getHostAddress());pw.write("</th>\n");
        
        pw.write("<body>\n");
        pw.write("<tr>\n");
        pw.write("<table border=\"1\" cellpadding=\"5\" cellspacing=\"5\">\n");
        pw.write("<th>");pw.write("List Names");pw.write("</th>\n");
        pw.write("</tr>\n");
        
        for (String s : filenames) {
            pw.write("<tr>\n");
            pw.write("<td>");
            pw.write(getFileRef(s));
            pw.write("</td>\n");
            pw.write("</tr>\n");
        }
        
        pw.write(HTML_END);
        pw.flush();
        pw.close();
    }
}
