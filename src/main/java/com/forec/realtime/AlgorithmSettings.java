package com.forec.realtime;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class AlgorithmSettings {

	private final String currency;
	private final int dataLength;
	
	public AlgorithmSettings(String currency, int dataLength) {
		super();
		this.currency = currency;
		this.dataLength = dataLength;
	}

	public String getCurrency() {
		return currency;
	}

	public int getDataLength() {
		return dataLength;
	}

	public static AlgorithmSettings fromPropertiesFile(String filePath) throws Exception {
		File f = new File(filePath);
		Properties prop = new Properties();
		prop.load(new FileInputStream(f));
		String currency = prop.getProperty("data.currency");
		Integer dataLength = Integer.valueOf(prop.getProperty("data.length"));
		return new AlgorithmSettings(currency, dataLength);
	}
	
	
	
}
