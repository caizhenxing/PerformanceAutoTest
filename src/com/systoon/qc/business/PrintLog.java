package com.systoon.qc.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.jsp.JspWriter;

public class PrintLog {
	public void printLog(Process process,PrintWriter out){
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String msg = null;
		try {
			while((msg = br.readLine()) != null){
				out.println(msg);
				out.println("<br><br>");
			}
			br.close();  
	        process.waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

		}
		
	}
	
	public void printJspLog(Process process,JspWriter out){
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String msg = null;
		try {
			while((msg = br.readLine()) != null){
				out.println(msg);
				out.println("<br><br>");
			}
			br.close();  
	        process.waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

		}
		
	}
}
