package spider;

import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class HandleHtml {

    public boolean saveHtmlToLocal(Document doc, String name, String format){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.getProperty("user.dir")).append(File.separator).append(name).append(".").append(format);
        File file = new File(stringBuilder.toString());
        FileOutputStream fos = null;

        if(!file.exists()){
            try {

                file.createNewFile();
                fos = new FileOutputStream(file);
                fos.write(doc.outerHtml().getBytes("utf-8"));
                fos.flush();

            } catch (IOException e) {

                e.printStackTrace();

            }finally {

                if(fos != null){
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return true;
    }
}
