package com.github.abola.opendatacrawler.cwb.tocsv;


import com.google.common.primitives.Doubles;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * CWB O-A0003-001 局屬氣象站-現在天氣觀測報告
 *
 * 資料欄位說明
 * LAT 緯度 (座標系統採TWD67)
 * LON 經度 (座標系統採TWD67)
 * ELEV 高度，單位 公尺
 * WDIR 風向，單位 度，風向 0 表示無風
 * WDSD 風速，單位 公尺/秒
 * TEMP 溫度，單位 攝氏
 * HUMD 相對濕度，單位 百分比率，此處以實數 0-1.0 記錄
 * PRES 測站氣壓，單位 百帕
 * 24R 日累積雨量，單位 毫米
 * H_FX 小時瞬間最大陣風風速，單位 公尺/秒
 * H_XD 小時瞬間最大陣風風向，單位 度
 * H_FXT 小時瞬間最大陣風時間，hhmm (小時分鐘)
 * H_F10 本時最大10分鐘平均風速，單位 公尺/秒
 * H_10D 本時最大10分鐘平均風向，單位 度
 * H_F10T 本時最大10分鐘平均風速發生時間，hhmm (小時分鐘)
 * CITY 縣市
 * CITY_SN 縣市編號
 * TOWN 鄉鎮
 * TOWN_SN 鄉鎮編號
 *
 *
 * @version 1.0
 * @author Abola Lee<abola921@gmail.com>
 */
public class OA0003001ToCsv extends AbstractToCsv {

    /* data example
    <location>
        <lat>24.1475</lat>
        <lon>120.6759</lon>
        <locationName>臺中</locationName>
        <stationId>467490</stationId>
        <time>
            <obsTime>2016-07-18T09:30:00+08:00</obsTime>
        </time>
        <weatherElement>
            <elementName>TIME</elementName>
            <elementValue>
                <value>1</value>
            </elementValue>
        </weatherElement>
        <weatherElement>
            <elementName>ELEV</elementName>
            <elementValue>
                <value>84</value>
            </elementValue>
        </weatherElement>
        <weatherElement>
            <elementName>WDIR</elementName>
            <elementValue>
                <value>170.0</value>
            </elementValue>
        </weatherElement>
        <weatherElement>
            <elementName>WDSD</elementName>
            <elementValue>
                <value>2.5</value>
            </elementValue>
        </weatherElement>
        <weatherElement>
            <elementName>TEMP</elementName>
            <elementValue>
                <value>29.7</value>
            </elementValue>
        </weatherElement>
        <weatherElement>
            <elementName>HUMD</elementName>
            <elementValue>
                <value>0.72</value>
            </elementValue>
        </weatherElement>
        <weatherElement>
            <elementName>PRES</elementName>
            <elementValue>
                <value>1000.2</value>
            </elementValue>
        </weatherElement>
        <weatherElement>
            <elementName>24R</elementName>
            <elementValue>
                <value>0.0</value>
            </elementValue>
        </weatherElement>
        <weatherElement>
            <elementName>H_FX</elementName>
            <elementValue>
                <value>5.5</value>
            </elementValue>
        </weatherElement>
        <weatherElement>
            <elementName>H_XD</elementName>
            <elementValue>
                <value>170.0</value>
            </elementValue>
        </weatherElement>
        <weatherElement>
            <elementName>H_FXT</elementName>
            <elementValue>
                <value>0825</value>
            </elementValue>
        </weatherElement>
        <weatherElement>
            <elementName>H_F10</elementName>
            <elementValue>
                <value>2.8</value>
            </elementValue>
        </weatherElement>
        <weatherElement>
            <elementName>H_10D</elementName>
            <elementValue>
                <value>180.0</value>
            </elementValue>
        </weatherElement>
        <weatherElement>
            <elementName>H_F10T</elementName>
            <elementValue>
                <value>0819</value>
            </elementValue>
        </weatherElement>
        <parameter>
            <parameterName>CITY</parameterName>
            <parameterValue>臺中市</parameterValue>
        </parameter>
        <parameter>
            <parameterName>CITY_SN</parameterName>
            <parameterValue>02</parameterValue>
        </parameter>
        <parameter>
            <parameterName>TOWN</parameterName>
            <parameterValue>北區</parameterValue>
        </parameter>
        <parameter>
            <parameterName>TOWN_SN</parameterName>
            <parameterValue>128</parameterValue>
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
            Double WDSD = Doubles.tryParse( elem.select("weatherElement:has(elementName:containsOwn(WDSD)) value").text());
            Double TEMP = Doubles.tryParse( elem.select("weatherElement:has(elementName:containsOwn(TEMP)) value").text());
            Double HUMD = Doubles.tryParse( elem.select("weatherElement:has(elementName:containsOwn(HUMD)) value").text());
            Double PRES = Doubles.tryParse( elem.select("weatherElement:has(elementName:containsOwn(PRES)) value").text());

            Double H_24R = Doubles.tryParse( elem.select("weatherElement:has(elementName:matchesOwn(^24R$)) value").text());
            Double H_FX = Doubles.tryParse( elem.select("weatherElement:has(elementName:matchesOwn(^H_FX$)) value").text());
            Double H_XD = Doubles.tryParse( elem.select("weatherElement:has(elementName:matchesOwn(^H_XD$)) value").text());
            Double H_FXT = Doubles.tryParse( elem.select("weatherElement:has(elementName:matchesOwn(^H_FXT$)) value").text());
            Double H_F10 = Doubles.tryParse( elem.select("weatherElement:has(elementName:matchesOwn(^H_F10$)) value").text());
            Double H_10D = Doubles.tryParse( elem.select("weatherElement:has(elementName:matchesOwn(^H_10D$)) value").text());
            Double H_F10T = Doubles.tryParse( elem.select("weatherElement:has(elementName:matchesOwn(^H_F10T$)) value").text());


            String CITY = elem.select("parameter:has(parameterName:matchesOwn(^CITY$)) parameterValue").text();
            String CITY_SN = elem.select("parameter:has(parameterName:containsOwn(CITY_SN)) parameterValue").text();
            String TOWN = elem.select("parameter:has(parameterName:matchesOwn(^TOWN$)) parameterValue").text();
            String TOWN_SN = elem.select("parameter:has(parameterName:containsOwn(TOWN_SN)) parameterValue").text();

            buffer.append(
                lat+","+lon+","+locationName+","+stationId+","+time
                +","+ELEV   +","+WDIR+","+WDSD   +","+TEMP   +","+HUMD   +","+PRES
                +","+H_24R  +","+H_FX  +","+H_XD  +","+H_FXT+","+H_F10  +","+H_10D  +","+H_F10T
                +","+CITY   +","+CITY_SN+","+TOWN   +","+TOWN_SN
            );
            buffer.append("\n");
        }

        return buffer.toString();
    }

}
