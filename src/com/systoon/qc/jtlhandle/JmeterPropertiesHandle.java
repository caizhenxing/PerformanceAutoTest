package com.systoon.qc.jtlhandle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class JmeterPropertiesHandle {
	Properties properties = new Properties();

	public void getPropertis(String JmeterProperties, String outputFormatPropertie) throws FileNotFoundException {

		// outputFormatPropertie ="jmeter.save.saveservice.output_format"
		try {
			InputStream in = new FileInputStream(JmeterProperties);
			properties.load(in);
			String outputFormatPropertieKey = outputFormatPropertie;
			String outputFormatPropertieValue = properties.getProperty(outputFormatPropertie);
			System.out.println(outputFormatPropertieKey + " = " + outputFormatPropertieValue);
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setPropertis(String JmeterProperties, String outputFormatKey, String outputFormatValue)
			throws FileNotFoundException {

		// outputFormatPropertie ="jmeter.save.saveservice.output_format"
		try {
			InputStream in = new FileInputStream(JmeterProperties);
			properties.load(in);   //注释文件不被加载
			properties.setProperty(outputFormatKey, outputFormatValue);
			OutputStream out = new FileOutputStream(JmeterProperties);
			properties.store(out, null);
			out.flush();
			out.close();
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public static void main(String[] args) throws FileNotFoundException {

		String fileName = "/Users/perfermance/JmeterTest/apache-jmeter-2.13/bin/jmeter.properties";
		String outputFormatPropertie = "jmeter.save.saveservice.output_format";
		// InputStream in = new FileInputStream(fileName);
		// System.out.println(in);
		new JmeterPropertiesHandle().getPropertis(fileName, outputFormatPropertie);

		new JmeterPropertiesHandle().setPropertis(fileName, outputFormatPropertie, "csv");

		new JmeterPropertiesHandle().getPropertis(fileName, outputFormatPropertie);

	}

}
