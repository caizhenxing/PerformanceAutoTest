package cn.systoon.qc.jmxhandler;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class JmxParserSAXHandler {
	
	public void JmxParserHandler(String JmxPlan) throws ParserConfigurationException, SAXException{
		
		//1、创建解析对象工厂
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		
		//2、创建解析器
		SAXParser sp = saxParserFactory.newSAXParser();
		
		//3、得到读取器
		XMLReader reader = sp.getXMLReader();
		
		//4、设置内容处理器
		reader.setContentHandler(new JMXContentHandler());
		
		//5、读取XML文件
		try {
			reader.parse(JmxPlan);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * 处理JMX计划 1、重命名JMX文件，ApiName_Vuser_ctime.jmx 2、创建JMX文件 3、复制模版文件到新的JMX文件中
	 * 4、解析JMX文件，替换相应参数，保存JMX文件 5、将绝对路径写入数据库（后期实现） 6、返回
	 * 
	 * @param JmxtempleName
	 * @param BaseJmxPath
	 * @param Url
	 *            (web server:ip:port)
	 * @param Api
	 *            (path)
	 * @param Arguments
	 *            (parameters/body data)
	 * @param Method
	 *            (get/post)
	 * @param Assertion
	 *            (string)
	 * @param Vuser
	 * @return JmxPlanName
	 */
	public boolean handleJmxFile(String JmxPlanTemple,String JmxPlan, String ip, String port,
			String path, String method, String parameters, String vuser, String assertion) {


		return true;
	}

}

class JMXContentHandler implements ContentHandler{

	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub
		
	}
	
}
