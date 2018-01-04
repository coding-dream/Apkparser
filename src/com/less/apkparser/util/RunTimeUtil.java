package com.less.apkparser.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RunTimeUtil {

	public static String run(String cmd){
		try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(cmd);
            InputStream is = process.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                stringBuffer.append(line).append("\n");
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}

	public static void main(String[] args) {
		String phantomJsCommand = "phantomjs";
		String crawlJsPath = System.getProperty("user.dir") + File.separator + "crawl.js";
		String param = "https://www.baidu.com";
		String cmd = "phantomJsCommand" + " " + crawlJsPath + " " + param;
		run(cmd);
	}
}
