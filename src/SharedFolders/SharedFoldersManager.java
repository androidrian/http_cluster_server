package SharedFolders;

import Configuration.Configuration;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SharedFoldersManager {

    List<SharedFolder> listSharedFolders;

    public SharedFoldersManager() {
        this.newlistSharedFolders();
    }

    public void newlistSharedFolders() {
        listSharedFolders = new ArrayList<>();
        this.addSharedFolder(getLocalSharedFolder());
    }

    public List<SharedFolder> getlistSharedFolders() {
        return listSharedFolders;
    }

    public void addSharedFolder(SharedFolder sf) {
        listSharedFolders.add(sf);
        findDuplicateFiles();
    }

    public static String[] getDirectoryListFilenames() throws IllegalArgumentException {
        File file = new File(Configuration.getFilesLocation());
        String[] filelist = file.list();

        return filelist;
    }

    public static SharedFolder getLocalSharedFolder() {
        String tcp_port = "" + Configuration.getTCP_Port();

        try {
            return new SharedFolder(InetAddress.getLocalHost().getHostAddress(), InetAddress.getLocalHost().getHostName(), tcp_port, getDirectoryListFilenames());
        } catch (UnknownHostException ex) {
            Logger.getLogger(SharedFoldersManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new SharedFolder("", "", "", new String[1]);
    }

    private void findDuplicateFiles() {
        int i, j;
        for (i = 0; i < this.listSharedFolders.size() - 1; i++) {
            String[] listFilesNames1 = this.listSharedFolders.get(i).getListFilesNames();

            List<String> listTmp = new ArrayList<>();

            for (String fileName1 : listFilesNames1) {

                boolean allDiff = true;
                for (j = 1; j < this.listSharedFolders.size(); j++) {

                    String[] listFilesNames2 = this.listSharedFolders.get(j).getListFilesNames();
                    for (String fileName2 : listFilesNames2) {

                        if (fileName1.equalsIgnoreCase(fileName2)) {
                            allDiff = false;
                        }
                    }
                }
                if (allDiff) {
                    listTmp.add(fileName1);
                }
            }
            
            String[] strarray = listTmp.toArray(new String[listTmp.size()]);
            this.listSharedFolders.get(i).setListFilesNames(strarray);
        }
    }
}
