package com.less.apkparser.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import net.sourceforge.plantuml.SourceStringReader;

public class PlantUMLHelper {

	public static void createUML(String input,String destPath) {
		try {
			File file = new File(destPath);
			OutputStream outputStream = new FileOutputStream(file);
			SourceStringReader sourceStringReader = new SourceStringReader(input);
			String desc = sourceStringReader.generateImage(outputStream);
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
