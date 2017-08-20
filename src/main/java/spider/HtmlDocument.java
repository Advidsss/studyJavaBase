package spider;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class HtmlDocument {

    Document doc;

    //获取对应url获取的文本
    public Document getOutHtml(String url){

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

}
