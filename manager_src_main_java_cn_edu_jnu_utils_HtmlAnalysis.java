package cn.edu.jnu.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;

public class HtmlAnalysis {
    public String analyseUtil(String contentByHtml){
        String document = Jsoup.parse(contentByHtml).text();
        //通过id获取内容
        return document;
    }
}
