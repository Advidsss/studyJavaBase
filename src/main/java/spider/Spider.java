package spider;

import org.jsoup.nodes.Document;

public class Spider {

    public static void main(String[] args) {

        HtmlDocument htmlDocument = new HtmlDocument();
        Document document = htmlDocument.getOutHtml("http://music.163.com/");
        HandleHtml handleHtml = new HandleHtml();
        handleHtml.saveHtmlToLocal(document,"kugou","html");

    }
}
