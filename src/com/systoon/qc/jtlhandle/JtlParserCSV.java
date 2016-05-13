package com.systoon.qc.jtlhandle;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.sun.corba.se.impl.io.InputStreamHook;

public class JtlParserCSV {
	
	@Test
	public void jtlParser(){
		String jtl = "/Users/perfermance/JmeterTest/results/jtl/usr_login_1605121605.jtl";
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(new File(jtl)));
			String msg = null;
			while((msg = br.readLine()) != null){
				String[] results = msg.split(",");
				for(String result:results){
					System.out.print("\"" + result + "\" ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{

			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}


}
