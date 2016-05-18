/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SharedFolder;

import Configuration.Configuration;
import java.io.File;
import java.util.Arrays;

/**
 *
 * @author Joao
 */
public class SharedFolderData {

    private static String[] findDuplicateFiles(String[] fileList) {

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

    public static String[] getDirectoryListFilenames() throws IllegalArgumentException {
        File file = new File(Configuration.getFilesLocation());
        String[] filelist = file.list();
        System.out.println(Configuration.getFilesLocation());
        //filelist = findDuplicateFiles(filelist);
        return filelist;
    }
}
