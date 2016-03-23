package com.systoon.qc.business;

public class ConvertJtlToHtml {
	
	private String reportHtml = null;
	private String reportUrl = null;
	
	public String getReportUrl() {
		return reportUrl;
	}

	public void convert(String resultJtlPath,String resultJtl){
		/*编写  ant 指定的  build.xml
		 * 插入report
		 * 定义HTML报告目录
		 * 
		 * */
		
		/* 定义tomcat虚拟目录
		 * report.xml
		 * 指定 docpath  **//*reportHtml
		 * 返回查看url
		 * 
		 * url = tomcat_ip:port/report
		 * */
	}
	
}
