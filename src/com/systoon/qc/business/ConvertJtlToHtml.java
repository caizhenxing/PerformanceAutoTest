package com.systoon.qc.business;

public class ConvertJtlToHtml {
	
	private String reportHtml = null;
	private String reportUrl = null;
	
	public String getReportUrl() {
		return reportUrl;
	}

	public void convert(String resultJtlPath,String resultJtl){
		/*��д  ant ָ����  build.xml
		 * ����report
		 * ����HTML����Ŀ¼
		 * 
		 * */
		
		/* ����tomcat����Ŀ¼
		 * report.xml
		 * ָ�� docpath  **//*reportHtml
		 * ���ز鿴url
		 * 
		 * url = tomcat_ip:port/report
		 * */
	}
	
}
