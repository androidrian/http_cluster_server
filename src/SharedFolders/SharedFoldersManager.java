/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SharedFolders;

import Configuration.Configuration;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Joao
 */
public class SharedFoldersManager {

    List<SharedFolder> listSharedFolders = new ArrayList<>();

    public SharedFoldersManager() throws UnknownHostException {
        listSharedFolders.add(getLocalSharedFolder());
    }

    public List<SharedFolder> getlistSharedFolders() {
        return listSharedFolders;
    }

    public void addSharedFolder(SharedFolder sf) {
        listSharedFolders.add(sf);
    }

    public static String[] getDirectoryListFilenames() throws IllegalArgumentException {
        File file = new File(Configuration.getFilesLocation());
        String[] filelist = file.list();

        return filelist;
    }

    public static SharedFolder getLocalSharedFolder() throws UnknownHostException {
String tcp_port = ""+Configuration.getTCP_Port();
        System.out.println(InetAddress.getLocalHost().getHostName());
        return new SharedFolder(InetAddress.getLocalHost().getHostAddress(), InetAddress.getLocalHost().getHostName(), tcp_port, getDirectoryListFilenames());

    }

    //    private static String[] findDuplicateFiles(String[] fileList) {
//        String[] fileListNonDuplicate = new String[fileList.length];
//        int count = 0;
//        Arrays.sort(fileList);
//
//        for (int i = 0; i < fileList.length - 1; i++) {
//            if (!fileList[i].equalsIgnoreCase(fileList[i + 1])) {
//                fileListNonDuplicate[count] = fileList[i];
//                count++;
//            }
//        }
//        return fileListNonDuplicate;
//    }
}
