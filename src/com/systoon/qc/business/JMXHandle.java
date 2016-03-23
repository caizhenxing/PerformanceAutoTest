package com.systoon.qc.business;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JMXHandle {
	
	private String generatjmxfilename;
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmm");
	String ctime = formatter.format(new Date());
	
	public JMXHandle() {
		super();
	}

	public void handleJmxFile(String ip,String port,String path,String param,String vuser){
		generatjmxfilename = path.replaceAll("/", "_") + "_" + vuser + "_" + ctime + ".jmx";
		
		
		
		/*替换Jmx模版中相关参数数据
		 * 
		 * 
		 * 
		 * */
	}
	
	public String getGeneratjmxfilename() {
		return generatjmxfilename;
	}
	
	
}
