package com.github.abola.opendatacrawler.cwb.tocsv;

import com.github.abola.crawler.CrawlerPack;
import org.jsoup.nodes.Document;

/**
 * Created Abola Lee <a href="mailto:abola921@gmail.com">Abola Lee</a>
 */
public abstract class AbstractToCsv implements InterfaceToCsv{


    public String toCsv(String dataid, String authKey){

        Document jsoup = getRemoteResource(dataid, authKey);
        String csv = xmlToCsv(jsoup);

        return csv;
    }

    protected Document getRemoteResource(String dataid, String authKey){
        String uri = String.format(uriFormat, dataid, authKey);
        return CrawlerPack.start()
                .setRemoteEncoding("UTF-8")
                .getFromXml(uri);
    }
}
