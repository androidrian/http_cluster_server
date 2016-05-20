package HTML;

import Configuration.Configuration;
import SharedFolders.SharedFolder;
import SharedFolders.SharedFoldersManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class HTMLParser {

    String HTML_BEGIN = "<!DOCTYPE html>\n<html>\n";
    String HTML_END = "\n</html>\n";

    SharedFoldersManager SFManager;

    public HTMLParser(SharedFoldersManager SFManager) {
        this.SFManager = SFManager;
    }

    public String[] getDirectoryListFilenames() throws IllegalArgumentException {
        File file = new File(Configuration.getFilesLocation());
        String[] str = file.list();

        return str;
    }
    
    protected String headGeneration() {
        String ret = "";
        
        ret += "\t<style type='text/css'>" +
                "\t\tbody {\n" +
                "\t\t\tbackground-color: #02A3DA;\n" +
                "\t\t\tpadding: 0;\n" +
                "\t\t\tmargin: 0;\n" +
                "\t\t\tfont-family: \"Helvetica Neue\", Arial, SansSerif;\n" +
                "\t\t\tfont-size: 18px;\n" +
                "\t\t\tfont-weight: 400;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\ta{\n" +
                "\t\t\ttext-decoration: none;\n" +
                "\t\t\twidth: 100%;\n" +
                "\t\t\tpadding: 20px 20px 20px 45px;\n" +
                "\t\t\tbackground-color: #fff;\n" +
                "\t\t\tcolor: #02A3DA;\n" +
                "\t\t\topacity: 1;\n" +
                "\t\t\ttransition: all 0.25s ease-in-out;\n" +
                "\t\t\tfloat: left;\n" +
                "\t\t\tdisplay: block;\n" +
                "\t\t\tfont-size: 1.5em;\n" +
                "\t\t\tbackground-position: 10px center;\n" +
                "\t\t\tbackground-size: 30px;\n" +
                "\t\t\tbackground-repeat: no-repeat;\n" +
                "\t\t\tbackground-image: url(\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAERUlEQVRIS63We4hUVRzA8e89984a5labpllgQdYfFSEF/dELLMsikB4IqWT0MPpDLApDc5cwzDRKkiKCYtXMsodLQWWhWFAKPlYlwtV1XWX2ofvenZn7mHvuOSfO3Qc7zrqEdRmYC+fc85nf49wzDqOu9+p+N0Y7JEpjjAHHQWC/HIQD/aHGFTBQDOnpz9Hclmffhhed0Wucf18yaIH5d9+WzslkXAJfkosCClGCX5T0+j59eUUYxTSdO0vj2R6KoTMuUgKs/3aPeeq+WcyYWpUiBkMQxOQjRS4MyeULdPgxMlI0dHSz6/Ax7rrpBvY1Zvnt7efGjOSCgJKSkIQgcAkjSRQXiIqSHl8itaahtZfj2SwL77mVuv2n+CvbMSZSAqzdvscsmj0YgVIKjCbGJYoiQj8gHyoKkSFWkuOt3exvaqZm4UOoOGH99/toaOksQ0qANdt2m6fn3D4ExNiCO0IQqYQglATFhDg2afKONLdy5Ew37yy+n7a+PCfOdLLzSBP1ze0lSAnw1pe7zTMPDAMRWru2kTAOhErjRwlJrBBGcbozx0/1p1JgIFegoAzZtnPUHWjm54PHOfbJK+naJUDNF7vMCw/eMRKB1oPDtkXVEGILbFPk+5JdRxvT8XzeUDBxen/oZDun2zo58emr5cDy2l/M0kfvHKmB1jp9yM60kVjE7pFc0ZDImLQRIk0USc71+0yc6HG4qYWNPx7gTO3ycmDZRz+Y1+bfmwKJUsRaUzG0c2zm7RNGCAaKkjiWaJkQ2A6Tku7+iMsvm8D+k62s2/4HLZ+/Xg4s2bDNVC96ZDACbCo0NoaMBm/UFk2UoKiL+EmRYsFBmoju/pDpV1ax90SW5bU7ad+6ohxYsHazWff8vBTwZYLRmgTDBAQZx0Fh0AIq7H2i8ZOEoKgwiaQrFzBz+lX8eSzLsxt30LFtZTkwr+Yz8+HSJ1Kgx49wtUkX9YTAdcRgO1WAcMFTDko5hFqSRIquvM8t101l798tPL52K11frSoHZq+qNZuXPZYCvYXAtrv9pJHYCmTwmCAEsS1MBi6xYOLg2xrkQm68poqjTe3Mqd5Ez9fV/w4Y/Xa0mOsKXFsRzxmENWSEoMuPmDH5Ug6dbGVu9RZ6v7kYwEBFhYeUEld4eMJDG41wBl8nk6smcqCxlYdrttB3MYCNxnVdEiVxcckINy2LTWCsEqZU/g/AcMocuyvSHUh6KIVacXXlJA42tjL3v0QwDNhfbVvYQhkLKMW1V1SOD9z80gdm5+rFI13k2Kqed9kfrYVA2cRoiasFnucRxoppVZM4OFTkMWswGugYyCOHAJuC0VdYjNExOK6D4wo8x6EviJh1/TTqm9qYs3IT/d/VlLfplCffNPUfvzxyZI53mF9ozJ4Ns5a8T/eO1eVA5bw3TMPmFRezbskzMxesIfr13bEBO1Mam5QxCjAObf/mODIZmTEM/AMzv5w3rzYd3gAAAABJRU5ErkJggg==\");\n" +
                "\t\t}\n" +
                "\n" +
                "\t\ta:hover {\n" +
                "\t\t\tbackground-color: #02A3DA;\n" +
                "\t\t\tcolor: #fff;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\th1 {\n" +
                "\t\t\ttext-transform: uppercase;\n" +
                "\t\t\tcolor: #02A3DA;\n" +
                "\t\t\tborder-bottom: 1px solid #02A3DA;\n" +
                "\t\t\tpadding: 20px 0;\n" +
                "\t\t\twidth: 100%;\n" +
                "\t\t\tbackground-color: transparent;\n" +
                "\t\t\tborder-top: none;\n" +
                "\t\t\tborder-left: none;\n" +
                "\t\t\tborder-right: none;\n" +
                "\t\t\t-moz-box-shadow:    none;\n" +
                "\t\t\t-webkit-box-shadow: none;\n" +
                "\t\t\tbox-shadow:         none;\n" +
                "\t\t\tfont-size: 1.7em;\n" +
                "\t\t\toutline: none;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\th2 {\n" +
                "\t\t\tfont-size: 0.8em;\n" +
                "\t\t\tcolor: #c3c3c3;\n" +
                "\t\t}" +
                "\t\t.main-container {\n" +
                "\t\t\tdisplay: block;\n" +
                "\t\t\tfloat: none;\n" +
                "\t\t\tmargin: 0 auto;\n" +
                "\t\t\tbackground-color: #fff;\n" +
                "\t\t\tmin-height: 100vh;\n" +
                "\t\t\tmax-width: 1024px;\n" +
                "\t\t\tpadding: 100px;\n" +
                "\t\t\toverflow: hidden;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\t.file-list {\n" +
                "\t\t\tdisplay: none;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\t.server-container {\n" +
                "\t\t\twidth: 90%;\n" +
                "\t\t\tdisplay: block;\n" +
                "\t\t\tfloat: left;\n" +
                "\t\t\tmargin: 0px 5%;\n" +
                "\t\t\toverflow: hidden;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\t.pdf {\n" +
                "\t\t\tbackground-image: url(\"data:image/png;base64,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\");\n" +
                "\t\t}\n" +
                "\n" +
                "\t\t.doc,\n" +
                "\t\t.docx {\n" +
                "\t\t\tbackground-image: url(\"data:image/png;base64,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\");\n" +
                "\t\t}\n" +
                "\n" +
                "\t\t.xls,\n" +
                "\t\t.xlsx {\n" +
                "\t\t\tbackground-image: url(\"data:image/png;base64,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\");\n" +
                "\t\t}\n" +
                "\n" +
                "\t\t.pps,\n" +
                "\t\t.ppt {\n" +
                "\t\t\tbackground-image: url(\"data:image/png;base64,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\");\n" +
                "\t\t}\n" +
                "\n" +
                "\t\t.column {\n" +
                "\t\t\tdisplay: block;\n" +
                "\t\t\tfloat: left;\n" +
                "\t\t\twidth: 100%;\n" +
                "\t\t}\n" +
                "\n" +
                "/* Medium devices (desktops, 992px and up) */\n" +
                "\t\t@media (min-width: 992px) {\n" +
                "\t\t\t.column {\n" +
                "\t\t\t\twidth: 50%;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\tbody {\n" +
                "\t\t\t\tfont-size: 14px;\n" +
                "\t\t\t}\n" +
                "\t\t}" +
                "\t</style>";
        
        ret += "\t<script src=\"https://code.jquery.com/jquery-1.11.3.js\"></script>\n" +
                "\t<script type=\"text/javascript\">\n" +
                "\t\t$(\"document\").ready(function() {\n" +
                "\t\t\t$(\".server-container h1\").click(function() {\n" +
                "\t\t\t\t$(this).parent().children(\".file-list\").slideToggle();\n" +
                "\t\t\t});\n" +
                "\t\t\t$(\".server-container\").hover(function() {\n" +
                "\t\t\t\t$(this).children(\".serverHeader\").slideToggle();\n" +
                "\t\t\t},function() {\n" +
                "\t\t\t\t$(this).children(\".serverHeader\").slideToggle();\n" +
                "\t\t\t});\n" +
                "\t\t});\n" +
                "\t</script>";
        
        return ret;
    }
    
    protected String bodyGeneration() {
        String ret = "";
        String leftCol = "";
        String rightCol = "";
        String cssClass;
        String fileExtention;
        
        int i = 0;
        
        //////////Informação a colocar no html/////////////////////
        List<SharedFolder> listSharedFolders = SFManager.getlistSharedFolders();

        for (SharedFolder sf : listSharedFolders) {
            if (i%2 == 0) {
                leftCol += "\t\t\t<div class=\"server-container\">\n" +
                            "\t\t\t\t<h1>" + sf.getMachineName() + "</h1>\n" +
                            "\t\t\t\t<div class=\"serverHeader\">\n" +
                            "\t\t\t\t\t<h2>" + sf.getIpv4() + ":" + sf.getTcpPort() + "</h2>\n" +
                            "\t\t\t\t</div>\n";
                
                leftCol += "\t\t\t\t<div class=\"file-list\">\n";
                
                for (String file : sf.getListFilesNames()) {
                    cssClass = "";
                    
                    fileExtention = file.substring(file.length() - 3);
                    switch (fileExtention) {
                        case "pdf":
                            cssClass = "pdf";
                            break;
                        case "docx":
                            cssClass = "docx";
                            break;
                        case "xlsx":
                            cssClass = "xlsx";
                            break;
                        default:
                            break;
                    }
                    
                    leftCol += "\t\t\t\t\t<a class=\"" + cssClass + "\" title=\"" + file + "\" href=\"http://" + sf.getIpv4() + ":" + sf.getTcpPort() + "/" + file + "\" download>" + file + "</a>\n";
                }
                
                leftCol += "\t\t\t</div>";
            } else {
                rightCol += "\t\t\t<div class=\"server-container\">\n" +
                            "\t\t\t\t<h1>" + sf.getMachineName() + "</h1>\n" +
                            "\t\t\t\t<div class=\"serverHeader\">\n" +
                            "\t\t\t\t\t<h2>" + sf.getIpv4() + ":" + sf.getTcpPort() + "</h2>\n" +
                            "\t\t\t\t</div>\n";
                
                rightCol += "\t\t\t\t<div class=\"file-list\">\n";
                
                for (String file : sf.getListFilesNames()) {
                    cssClass = "";
                    
                    fileExtention = file.substring(file.length() - 3);
                    switch (fileExtention) {
                        case "pdf":
                            cssClass = "pdf";
                            break;
                        case "docx":
                            cssClass = "docx";
                            break;
                        case "xlsx":
                            cssClass = "xlsx";
                            break;
                        default:
                            break;
                    }
                    
                    rightCol += "\t\t\t\t\t<a class=\"" + cssClass + "\" title=\"" + file + "\" href=\"http://" + sf.getIpv4() + ":" + sf.getTcpPort() + "/" + file + "\" download>" + file + "</a>\n";
                }
                
                rightCol += "\t\t\t</div>";
            }
        }
        
        ret = "<div class=\"main-container\">";
        ret += "<div class=\"column\">";
        ret += leftCol;
        ret += "</div>";
        ret += "<div class=\"column\">";
        ret += rightCol;
        ret += "</div>";
        ret += "</div>";
        
        return ret;
    }

    public void buildHTML(String[] filenames) throws FileNotFoundException, UnknownHostException {
        File file = new File(Configuration.getFilesLocation() + "\\index.html");
        PrintWriter pw = new PrintWriter(file);

        pw.write(HTML_BEGIN);
        
        pw.write("\t<head>\n");
        pw.write("\t\t<meta charset=\"UTF-8\"/>");
        pw.write("\t\t<title> List Directory </title>\r\n");
        pw.write(this.headGeneration());
        pw.write("\t</head>\n");

        pw.write("\t<body>\n");
        pw.write(this.bodyGeneration());
        pw.write("\t</body>");

        pw.write(HTML_END);
        pw.flush();
        pw.close();
    }
}
