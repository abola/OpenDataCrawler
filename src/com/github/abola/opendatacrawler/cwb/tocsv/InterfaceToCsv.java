package com.github.abola.opendatacrawler.cwb.tocsv;

import org.jsoup.nodes.Document;

/**
 * Created Abola Lee <a href="mailto:abola921@gmail.com">Abola Lee</a>
 */
public interface InterfaceToCsv {

    String uriFormat = "http://opendata.cwb.gov.tw/opendataapi?dataid=%s&authorizationkey=%s";

    // 主要接口
    String toCsv(String dataid, String authKey);


    // 每個資料集，可能有各自不同的格式
    String xmlToCsv(Document xml);


}
