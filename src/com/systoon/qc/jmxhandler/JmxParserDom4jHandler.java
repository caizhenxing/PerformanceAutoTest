package com.systoon.qc.jmxhandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class JmxParserDom4jHandler {

	public void createJmxPlan(String jmxPlanTemple, String jmxPlan, String ip, String port, String path,
			String method, String parameters, String vuser, String assertion) {

		SAXReader saxReader = null;
		Document doc = null;
		try {

			saxReader = new SAXReader();
			doc = saxReader.read(new File(jmxPlanTemple));
			
			System.out.println("开始生成JmxPlan文件......");

			// 1、替换 并发用户数 Vuser
			List<Element> vuserList = doc.selectNodes("/jmeterTestPlan/hashTree/hashTree/ThreadGroup/stringProp");
			Iterator<Element> iter = vuserList.iterator();
			while (iter.hasNext()) {
				Element vuserEle = iter.next();
				if (vuserEle.attribute("name").getValue().equals("ThreadGroup.num_threads")) {
					vuserEle.setText(vuser);
					System.out.println("设置 ThreadGroup.num_threads: \"" + vuserEle.getText() + " \"......");

				}
			}

			// 替换 http Request args 参数"
			List<Element> httpRequestArgsList = doc.selectNodes(
					"/jmeterTestPlan/hashTree/hashTree/hashTree/HTTPSamplerProxy/elementProp/collectionProp/elementProp/stringProp");
			Iterator<Element> httpRequestArgsIter = httpRequestArgsList.iterator();
			while (httpRequestArgsIter.hasNext()) {
				Element args = httpRequestArgsIter.next();
				if (args.attribute("name").getValue().equals("Argument.value")) {
					args.setText(parameters);
					System.out.println("设置 Argument.value: \"" + args.getText() + " \"......");
				}
			}

			// 替换 http server 参数
			List<Element> httpServerList = doc
					.selectNodes("/jmeterTestPlan/hashTree/hashTree/hashTree/HTTPSamplerProxy/stringProp");
			Iterator<Element> httpServerListIter = httpServerList.iterator();
			while (httpServerListIter.hasNext()) {
				Element httpServer = httpServerListIter.next();
				switch (httpServer.attribute("name").getValue()) {

				case "HTTPSampler.domain":
					httpServer.setText(ip);
					System.out.println("设置 HTTPSampler.domain: \"" + httpServer.getText() + " \"......");
					break;

				case "HTTPSampler.port":
					httpServer.setText(port);
					System.out.println("设置 HTTPSampler.port: \"" + httpServer.getText() + " \"......");
					break;

				case "HTTPSampler.path":
					httpServer.setText(path);
					System.out.println("设置 HTTPSampler.path: \"" + httpServer.getText() + " \"......");
					break;

				case "HTTPSampler.method":
					httpServer.setText(method);
					System.out.println("设置 HTTPSampler.method: \"" + httpServer.getText() + " \"......");
					break;

				default:
					break;
				}
			}

			// 替换 http Request Assertion 参数"
			List<Element> httpRequestAssertionList = doc.selectNodes(
					"/jmeterTestPlan/hashTree/hashTree/hashTree/ResponseAssertion/collectionProp/stringProp");
			Iterator<Element> httpRequestAssertionIter = httpRequestAssertionList.iterator();
			while (httpRequestAssertionIter.hasNext()) {
				Element assertionEle = httpRequestAssertionIter.next();
				assertionEle.setText(assertion);
				System.out.println("设置 Asserion.test_strings: \"" + assertionEle.getText() + " \"......");
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}

		try {
			FileWriter fileWriter = new FileWriter(new File(jmxPlan));
			XMLWriter xmlWriter = new XMLWriter(fileWriter);
			xmlWriter.write(doc);
			System.out.println("jmxplan 计划文件已生成完毕。");
			System.out.println("文件已生成完毕。");
			System.out.println("文件地址： " + new File(jmxPlan).getAbsolutePath());
			xmlWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			
		}

	}

}
