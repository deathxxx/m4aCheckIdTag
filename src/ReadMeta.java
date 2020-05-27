import org.jcodec.containers.mp4.boxes.MetaValue;
import org.jcodec.movtool.MetadataEditor;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ReadMeta {
    public Boolean checkIdTrackInFiles(String file) throws IOException {
        if(getFileSizeBytes(file) != 0) {
//            System.out.println("exist " + searchFileByDate.getFileSizeBytes(file));
            MetadataEditor mediaMeta = MetadataEditor.createFrom(new File(file));
            Map<Integer, MetaValue> itunesMeta = mediaMeta.getItunesMeta();

            if (itunesMeta != null) {
//            System.out.println("iTunes metadata:");
                if(itunesMeta.containsKey(-1453101708)) {
                    Long idF = Long.valueOf(itunesMeta.get(-1453101708).getString());
                    if (idF > 0L) {
//                    System.out.println(id);
                        return true;
                    }
                }
            }
        }

        return false;

    }


    /**
     * return size in bytes
     * @param fileNAme
     * @return
     */
    public long getFileSizeBytes(String fileNAme) {
        File file = new File(fileNAme);

        long fileSizeInBytes = file.length();

        return fileSizeInBytes;
    }
}
