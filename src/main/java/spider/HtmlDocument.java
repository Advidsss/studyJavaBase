package spider;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class HtmlDocument {

    Document doc;
    Document doc1;

    //获取对应url获取的文本
    public Document getOutHtml(String url){

        try {
            doc = Jsoup.connect(url).get();

            //post请求
//            doc1 = Jsoup.connect(url).data("query", "Java").userAgent("Mozilla").cookie("auth", "token").timeout(3000).post();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

}
