package com.github.abola.opendatacrawler.cwb;


import com.github.abola.opendatacrawler.cwb.tocsv.InterfaceToCsv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * CWS 資料轉換 XML to CSV 格式
 *
 * 使用範例
 * ./toCsv {dataid} {yourAuthKey}
 * example ./toCsv O-A0001-001 CWB-1234ABCD-78EF-GH90-12XY-IJKL12345678
 *
 * Created Abola Lee <a href="mailto:abola921@gmail.com">Abola Lee</a>
 */
public class ToCsv {

    static Logger log = LoggerFactory.getLogger(ToCsv.class);

    public static void main(String[] args){
        // frok NullPointerException to suspend jenkins job
        String npe = null;

        if ( args.length == 0 ) {
            log.error("parameter dataid(0) and authKey(1) need");
            npe.toString();
        }
        else if ( args.length == 1 ) {
            log.error("parameter authKey(1) need");
            npe.toString();
        }
        else if ( args[1].length() < "CWB-1234ABCD-78EF-GH90-12XY-IJKL12345678".length() ) {
            log.error("parameter authKey(1) length failed. key example: CWB-1234ABCD-78EF-GH90-12XY-IJKL12345678");
            npe.toString();
        }else{
            log.debug( "dataid :" + args[0] );
            log.debug( "authKey:" + args[1] );

            String dataid = args[0].trim();
            String authKey = args[1].trim();

            try {
                InterfaceToCsv cwbData = (InterfaceToCsv) Class.forName("com.github.abola.opendatacrawler.cwb.tocsv." + dataid.replace("-","") + "ToCsv").newInstance();

                System.out.println(
                        cwbData.toCsv(dataid, authKey)
                );

            }catch( ClassNotFoundException jnfe ){
                log.error("dataid: " + dataid + " not found or support yet . ");
            }catch( ClassCastException cce ){
                log.error("ClassCast exception cause: " + cce.getMessage());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        }
    }
}
