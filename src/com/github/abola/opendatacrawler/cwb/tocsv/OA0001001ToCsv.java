package com.github.abola.opendatacrawler.cwb.tocsv;


import com.google.common.primitives.Doubles;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * CWB O-A0001-001 自動氣象站-氣象觀測資料
 *
 * @version 1.0
 * @author Abola Lee<abola921@gmail.com>
 */
public class OA0001001ToCsv extends AbstractToCsv {

    /* data example
    <location>
        <lat>24.9943</lat>
        <lon>121.3150</lon>
        <locationName>桃園</locationName>
        <stationId>C0C480</stationId>
        <time>
            <obsTime>2016-07-17T21:10:00+08:00</obsTime>
        </time>
        <weatherElement>
            <elementName>ELEV</elementName>
            <elementValue>
                <value>105.0</value>
            </elementValue>
        </weatherElement>
        <weatherElement>
            <elementName>WDIR</elementName>
            <elementValue>
                <value>0.0</value>
            </elementValue>
        </weatherElement>
        <weatherElement>
            <elementName>WDSD</elementName>
            <elementValue>
                <value>0.0</value>
            </elementValue>
        </weatherElement>
        <weatherElement>
            <elementName>TEMP</elementName>
            <elementValue>
                <value>29.4</value>
            </elementValue>
        </weatherElement>
        <weatherElement>
            <elementName>HUMD</elementName>
            <elementValue>
                <value>0.68</value>
            </elementValue>
        </weatherElement>
        <weatherElement>
            <elementName>PRES</elementName>
            <elementValue>
                <value>998.3</value>
            </elementValue>
        </weatherElement>
        <weatherElement>
            <elementName>SUN</elementName>
            <elementValue>
                <value>-99.0</value>
            </elementValue>
        </weatherElement>
        <weatherElement>
            <elementName>H_24R</elementName>
            <elementValue>
                <value>-97.0</value>
            </elementValue>
        </weatherElement>
        <weatherElement>
            <elementName>WS15M</elementName>
            <elementValue>
                <value>0.0</value>
            </elementValue>
        </weatherElement>
        <weatherElement>
            <elementName>WD15M</elementName>
            <elementValue>
                <value>0</value>
            </elementValue>
        </weatherElement>
        <weatherElement>
            <elementName>WS15T</elementName>
            <elementValue>
                <value>2105</value>
            </elementValue>
        </weatherElement>
        <parameter>
            <parameterName>CITY</parameterName>
            <parameterValue>桃園市</parameterValue>
        </parameter>
        <parameter>
            <parameterName>CITY_SN</parameterName>
            <parameterValue>08</parameterValue>
        </parameter>
        <parameter>
            <parameterName>TOWN</parameterName>
            <parameterValue>桃園區</parameterValue>
        </parameter>
        <parameter>
            <parameterName>TOWN_SN</parameterName>
            <parameterValue>042</parameterValue>
        </parameter>
    </location>
     */

    public String xmlToCsv(Document xml){

        StringBuilder buffer = new StringBuilder();

        for(Element elem :xml.select("location")){

            Double lat = Doubles.tryParse( elem.select("lat").text());
            Double lon = Doubles.tryParse( elem.select("lon").text());
            String locationName = elem.select("locationName").text();
            String stationId = elem.select("stationId").text();
            String time = elem.select("time obsTime").text();

            Double ELEV = Doubles.tryParse( elem.select("weatherElement:has(elementName:containsOwn(ELEV)) value").text());
            Double WDIR = Doubles.tryParse( elem.select("weatherElement:has(elementName:containsOwn(WDIR)) value").text());
            Double TEMP = Doubles.tryParse( elem.select("weatherElement:has(elementName:containsOwn(TEMP)) value").text());
            Double HUMD = Doubles.tryParse( elem.select("weatherElement:has(elementName:containsOwn(HUMD)) value").text());
            Double PRES = Doubles.tryParse( elem.select("weatherElement:has(elementName:containsOwn(PRES)) value").text());
            Double SUN = Doubles.tryParse( elem.select("weatherElement:has(elementName:containsOwn(SUN)) value").text());
            Double H_24R = Doubles.tryParse( elem.select("weatherElement:has(elementName:containsOwn(H_24R)) value").text());
            Double WS15M = Doubles.tryParse( elem.select("weatherElement:has(elementName:containsOwn(WS15M)) value").text());
            Double WD15M = Doubles.tryParse( elem.select("weatherElement:has(elementName:containsOwn(WD15M)) value").text());
            Double WS15T = Doubles.tryParse( elem.select("weatherElement:has(elementName:containsOwn(WS15T)) value").text());

            String CITY = elem.select("parameter:has(parameterName:containsOwn(CITY)) parameterValue").text();
            String CITY_SN = elem.select("parameter:has(parameterName:containsOwn(CITY_SN)) parameterValue").text();
            String TOWN = elem.select("parameter:has(parameterName:containsOwn(TOWN)) parameterValue").text();
            String TOWN_SN = elem.select("parameter:has(parameterName:containsOwn(TOWN_SN)) parameterValue").text();

            buffer.append(
                lat+","+lon+","+locationName+","+stationId+","+time
                +","+ELEV+","+WDIR+","+TEMP+","+HUMD+","+PRES
                +","+SUN+","+H_24R+","+WS15M+","+WD15M+","+WS15T
                +","+CITY+","+CITY_SN+","+TOWN+","+TOWN_SN
            );
            buffer.append("\n");
        }

        return buffer.toString();
    }

}
