package com.less.apkparser.util;

import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;
import net.dongliu.apk.parser.bean.DexClass;

public class ApkParserUtil {

	public static void main(String[] args) throws Exception {
		String apkPath = "F:\\Test\\c.apk";
		ApkFile apkFile = new ApkFile(apkPath);

		ApkMeta apkMeta = getApkMetaInfo(apkFile);
		System.out.println("====> " + apkMeta);
		getApkXml(apkFile);
		Set<String> packageSets = getDexPackageNames(apkFile);
		for(String pName : packageSets){
			System.out.println(pName);
		}
	}

	public static Set<String> getDexPackageNames(ApkFile apkFile){
		Set<String> packageSets = new HashSet<>();
		try {
		    DexClass[] classes = apkFile.getDexClasses();
		    for (DexClass dexClass : classes) {
		    	packageSets.add(dexClass.getPackageName());
		    }
		    return packageSets;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getApkXml(ApkFile apkFile) {
		try {
			String manifestXml = apkFile.getManifestXml();
		    // String xml = apkFile.transBinaryXml("res/menu/main.xml");
		    String xml = apkFile.transBinaryXml("AndroidManifest.xml");
		    return xml;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
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
