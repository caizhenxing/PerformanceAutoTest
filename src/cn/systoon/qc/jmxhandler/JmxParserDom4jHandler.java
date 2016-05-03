package cn.systoon.qc.jmxhandler;

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
	public void createJmxPlan(String JmxPlanTemple,String newJmxPlan, String ip, String port,
			String path, String method, String parameters, String vuser, String assertion){
		
	}
	
	@Test
	public void testDom4j(){
//		String JmxPlanTemple = "/Users/perfermance/JmeterTest/apache-jmeter-2.13/bin/baidu.xml";
		String JmxPlanTemple = "/Users/perfermance/git/PerformanceAutoTest/src/1.xml";
		String newName = "NEW Test Name";
		String newFile = "new.jmx";
		
		createJmxPlan(JmxPlanTemple, newName,newFile);
		
	}
	
	public void createJmxPlan(String oldJmxPlanTemple,String newName,String newFile){
	
		SAXReader saxReader = null;
		Document doc = null;
		System.out.println(new File(oldJmxPlanTemple).getAbsolutePath());
		try {
			
			saxReader = new SAXReader();
			doc = saxReader.read(new File(oldJmxPlanTemple));
			List list = doc.selectNodes("/jmeterTestPlan/hashTree/TestPlan@testname");
			System.out.println("********");
			Iterator iter = list.iterator();
			while(iter.hasNext()){
				Attribute testNameAttr = (Attribute)iter.next();
				System.out.println("testname(old):" + testNameAttr.getValue());
				testNameAttr.setValue(newName);
				System.out.println("testname(new):" + testNameAttr.getValue());
				
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		try {
			FileWriter fileWriter = new FileWriter(new File(newFile));
			XMLWriter xmlWriter = new XMLWriter(fileWriter);
			xmlWriter.write(doc);
			xmlWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	
	
}
