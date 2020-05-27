import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteReport {

    /**
     *
     * @param filePath
     * @param fileName
     * @param content
     */
    public void writeFile(String filePath, String fileName, String content, boolean printResult) {

        BufferedWriter bw = null;
        FileWriter fw = null;

        File directory = new File(filePath);
        if (! directory.exists()){
//            directory.mkdir();
            directory.mkdirs();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }
        try {

            fw = new FileWriter(filePath+fileName);
            bw = new BufferedWriter(fw);
            bw.write(content);

            if (printResult) { System.out.println("Write file -> Done"); }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

    }


    public void appendStrToFile(String fileName,
                                String str)
    {
        try {

            // Open given file in append mode.
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(fileName, true));
            out.write(str);
            out.close();
        }
        catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }

}
