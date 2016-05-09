package com.systoon.qc.jtlhandle;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ConvertJtlToHtml {

	public boolean convertJtlToHtml(String jtlResult, String xslTemple, String htmlReport) {

		try {
			// 1、将xslTemple 文件转换为 streamSource 类
			StreamSource streamSource = new StreamSource(new File(xslTemple));

			// 2、将jtlResult（xml）文件转换为 document 类
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(new File(jtlResult));

			// 3、以xsl模版 创建 transformer 实例
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer(streamSource);

			// 4、将document 类型的 xml 转成 DocumentSource类 的实例中
			// 将结果文件保存到DocumentResult 类的实例中
			DocumentSource xmlSource = new DocumentSource(document);
			DocumentResult outputTarget = new DocumentResult();

			// 5、进行转换 xmlSource 通过 xsl 转换成 html
			transformer.transform(xmlSource, outputTarget);

			Document htmlDoc = outputTarget.getDocument();

			XMLWriter writer = new XMLWriter(new FileWriter(new File(htmlReport)));
			writer.write(htmlDoc);
			writer.close();
			

			System.out.println("用xslt转换xml文件成功!");

			return true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}

	public static void main(String[] args) {
		new ConvertJtlToHtml().convertJtlToHtml(
				"/Users/perfermance/JmeterTest/results/jtl/saveOperInfo_ok_20161526.jtl",
				"/Users/perfermance/JmeterTest/apache-jmeter-2.13/extras/jmeter-results-report_21.xsl",
				"/Users/perfermance/JmeterTest/results/html/saveOperInfo_ok_20161526.html");
	}

}
