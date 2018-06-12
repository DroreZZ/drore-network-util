package com.drore.cloud.tdp.pingip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Copyright: Zhejiang Drore Technology Co., Ltd  2018 <br/>
 * @Desc: ping操作<br/>
 * @ProjectName: drore-network-util <br/>
 * @Date: 2018/5/22 10:43 <br/>
 * @Author: zhangz
 */
public class PingAndPutIp  {
    public static Logger log = LoggerFactory.getLogger(PingAndPutIp.class);


    /**
     * @param ip
     * @return
     */
    public static String isConnect(String ip){
        boolean connect = false;
        Runtime runtime = Runtime.getRuntime();
        Process process;
        StringBuffer sb = new StringBuffer();
        String result="";
        try {
            process = runtime.exec("ping " + ip);
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"GBK");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            System.out.println("----"+sb);
            is.close();
            isr.close();
            br.close();

            if (null != sb && !sb.toString().equals("")) {
                String logString = "";
                if (sb.toString().indexOf("TTL") > 0) {
                    // 网络畅通
                    connect = true;
                    result="IP网络畅通";
                } else {
                    result="网络不畅通";
                    connect = false;

                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
        return result;
    }

    /**
     * 1 为ping命令 2 为路由跟踪
     * @param ip
     * @return
     */
    public static String tracert(String ip){
        boolean connect = false;
        Runtime runtime = Runtime.getRuntime();
        Process process;
        StringBuffer sb = new StringBuffer();
        String result="";
        try {

                process = runtime.exec("tracert " + ip);


            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"GBK");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            is.close();
            isr.close();
            br.close();


        } catch (IOException e) {
            e.printStackTrace();

        }
        return sb.toString();
    }
}
