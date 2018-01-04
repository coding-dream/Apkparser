package com.less.apkparser.util;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class CompressUtil {

	/**
	 * 解压zip中的某一个文件
	 * @param chooseFilePath
	 * @param srcPath
	 * @param destPath
	 */
	public static void unzipFile(String chooseFilePath,String srcPath,String destPath){
		try {
			ZipFile zipFile = new ZipFile(srcPath);
			zipFile.extractFile(chooseFilePath, destPath);
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		unzipFile("classes.dex", "F:/Test/a.apk", "F:/Test/");
	}
}
