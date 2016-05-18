/**
 * Gera um ficheiro HTML
 */
package HTML;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class HTMLParser {

    String HTML_BEGIN = "<html>\n";
    String HTML_END = "</table>\n</body>\n</html>\n";
    String userName = new com.sun.security.auth.module.NTSystem().getName();
    String root = "C:\\Users\\" + userName + "\\Desktop\\root";

    public HTMLParser() {

    }

    public void printDirectoryListFilenames(String[] str) {
        System.out.println("*************************************");
        System.out.println("List Directory");
        System.out.println("*************************************");
        for (String names : str) {
            System.out.println(names);
        }
        System.out.println("*************************************");

    }

    public String[] getDirectoryListFilenames() throws IllegalArgumentException {
        File file = new File(root);
        String[] str = file.list();

        return str;
    }

    public String[] findDuplicateFiles(String[] fileList) {

        String[] fileListNonDuplicate = new String[fileList.length];
        int count = 0;
        Arrays.sort(fileList);

        for (int i = 0; i < fileList.length; i++) {
            if (!fileList[i].equalsIgnoreCase(fileList[i + 1])) {
                fileListNonDuplicate[count] = fileList[i];
                count++;
            }
        }
        return fileListNonDuplicate;
    }

    public String getFileRef(String filename) {
        String ref;
        ref = "<a href=" + "\"" + filename + "\"" + ">" + filename + "</a>";

        return ref;
    }

    public void buildHTML(String[] filenames) throws FileNotFoundException,UnknownHostException {

        File file = new File(root + "\\index.html");
        PrintWriter pw = new PrintWriter(file);

        pw.write(HTML_BEGIN);
        //head
        pw.write("<head>\n");
        pw.write(title("List Directory"));
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

    public String title(String s) {
        String title = "<title>" + s + "</title>\r\n";
        return title;
    }
}
