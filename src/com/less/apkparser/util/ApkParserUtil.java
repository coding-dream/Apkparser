package com.less.apkparser.util;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;
import net.dongliu.apk.parser.bean.DexClass;

public class ApkParserUtil {

	public static void getDexClasses(String apkPath){
		try(ApkFile apkFile = new ApkFile(new File(apkPath))) {
		    DexClass[] classes = apkFile.getDexClasses();
		    for (DexClass dexClass : classes) {
		        System.out.println("====>" + dexClass);
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String apkPath = "F:\\Test\\a.apk";
//		getDexClasses(apkPath);
		getApkXml(apkPath);

	}

	private static void getApkXml(String apkPath) {
		try {
			ApkFile apkFile = new ApkFile(new File(apkPath));
//			getApkMetaInfo(apkFile);
			String manifestXml = apkFile.getManifestXml();
//		    String xml = apkFile.transBinaryXml("res/menu/main.xml");
		    String xml = apkFile.transBinaryXml("AndroidManifest.xml");
		    System.err.println(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static ApkMeta getApkMetaInfo(ApkFile apkFile) {
		try {
			apkFile.setPreferredLocale(Locale.SIMPLIFIED_CHINESE);
			ApkMeta apkMeta = apkFile.getApkMeta();
			return apkMeta;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
