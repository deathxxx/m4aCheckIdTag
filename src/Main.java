import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     * читает передаваемую из консоли строку 1 параметр
     * arg[0] - директория которую надо прочитать
     * <p>
     * выводит количество фалов (m4a) в подкаталогах и количество файлов у которых нет ид тега
     *
     * @param args
     */
    public static void main(String[] args) {

        if (args.length > 0 && args[0].length() > 0) {

            ReadDirectories readDirectories = new ReadDirectories();
            String path = args[0];
//            String path = "/mnt/media/dex/Music/archive/mp4/00sclubhits";

            if (readDirectories.IsDirectory(path)) {

                //read subdirectory
                List<String> dir = readDirectories.readDirOnly(path, "*", false);

                //array coolect all files
                List<String> fullPAthFiles = new ArrayList<>();

                for (String d : dir) {

                    String pathInner = path + "/" + d;
                    //read all files in subdirectory
                    List<String> m4a = readDirectories.readDir(pathInner, "*.m4a", false);

                    for (String v : m4a) {
                        fullPAthFiles.add(pathInner + "/" + v);
                    }

                }

                System.out.println(fullPAthFiles.size());


                List<String> filesWithoutMeta = new ArrayList<>();

                //read metadata
                ReadMeta readMeta = new ReadMeta();
                for (String f : fullPAthFiles) {
                    try {
                        if (readMeta.checkIdTrackInFiles(f)) {

                        } else {
                            filesWithoutMeta.add(f);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                System.out.println("no id (count) - " + filesWithoutMeta.size());

                //write report
                WriteReport writeReport = new WriteReport();
                String reportName = path.substring(path.lastIndexOf("/") + 1, path.length()) + "-noid3.txt";
                writeReport.writeFile("./",reportName,"", false);
                for (String w : filesWithoutMeta) {
                    writeReport.appendStrToFile(reportName, w + "\n");
                }

            } else {
                System.out.println("its not a directory - " + args[0]);
            }
        } else {
            System.out.println("no input folder");
        }

    }
}
