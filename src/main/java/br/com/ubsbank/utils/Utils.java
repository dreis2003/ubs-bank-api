package br.com.ubsbank.utils;

import java.io.File;
import java.io.FileFilter;

public class Utils {

	public static FileFilter filter = new FileFilter() {
		public boolean accept(File file) {
			return file.getName().endsWith(".json");
		}
	};
}
