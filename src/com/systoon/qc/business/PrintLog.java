package com.systoon.qc.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Date;

import javax.servlet.jsp.JspWriter;

public class PrintLog {
	
	
	public void printLog(Process pid,JspWriter out,String logFile) throws IOException{
		if(pid != null){
			out.print("进程号：" + pid + "\r\n");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(pid.getInputStream()),1024);
//			pid.waitFor();  阻塞进程，等待命令执行完毕
			
//			读取内容，打印日志
			String msg = null;
			try {
				while(bufferedReader != null
			            && (msg = bufferedReader.readLine()) != null){
					/*打印日志到控制台*/
					out.println("<span>" + msg + "<span>");
					out.println("<br>");
					out.flush();
					/*写入到日志文件*/
					
					
					
					
					
					
					
				}
//				pid.waitFor();
				bufferedReader.close();  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
			}
		}else{
			out.print("进程没有启动");
			out.flush();
		}
	}
	
	public void printLog(Process pid,PrintWriter out,String logfile){
		if(pid != null){
			out.print("进程号：" + pid + "\r\n");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(pid.getInputStream()),1024);
//			pid.waitFor();  阻塞进程，等待命令执行完毕
			
//			读取内容，打印日志
			String msg = null;
			try {
				while(bufferedReader != null
						&& (msg = bufferedReader.readLine()) != null){
					out.println("<span>" + msg + "<span>");
					out.println("<br><br>");
					
					/*打印日志到控制台*/
				}
//				pid.waitFor();
				bufferedReader.close();  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
			}
		}else{
			out.print("进程没有启动");
		}
		
		
	}
}

