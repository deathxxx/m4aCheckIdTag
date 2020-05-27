import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadDirectories {

    /**
     * читает по указанному пути файлы
     *
     * @param path
     * @param filter
     * @param print
     * @return
     */
    public List<String> readDir(String path, String filter, Boolean print) {
        List<String> filesArr = new ArrayList<>();
        try {

            File dir = new File(path);
            FileFilter fileFilter = new WildcardFileFilter(filter);
            File[] files = dir.listFiles(fileFilter);
            for (int i = 0; i < files.length; i++) {
                if (print) {
                    System.out.println(files[i]);
                }
                filesArr.add(files[i].getName());
            }
        } catch (Exception ex) {
            System.out.println("path - " + path);
            System.out.println("filter - " + filter);
            System.out.println("print - " + print);
            ex.printStackTrace();
            System.exit(1);
        }

        return filesArr;
    }


    /**
     * функция которая возвращает только директории
     *
     * @param path
     * @param filter
     * @param print
     * @return список директорий
     */
    public List<String> readDirOnly(String path, String filter, Boolean print) {
        List<String> filesArr = new ArrayList<>();

        File dir = new File(path); // current directory

        FileFilter fileFilter = new WildcardFileFilter(filter);
        File[] dirs = dir.listFiles(fileFilter);
        for (File directory : dirs) {
            if (directory.isDirectory()) {
                if (print) System.out.print("directory:");
                filesArr.add(directory.getName());
            } else {
                if (print) System.out.print("     file:");
            }
            try {
                if (print) System.out.println(directory.getCanonicalPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filesArr;
    }


    /**
     * проверка - директория ли это
     * @param directoryPath
     * @return
     */
    public boolean IsDirectory(String directoryPath) {

        File file = new File(directoryPath);

        if (file.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }


}
