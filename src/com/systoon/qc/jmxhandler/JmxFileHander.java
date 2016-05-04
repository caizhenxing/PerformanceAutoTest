package com.systoon.qc.jmxhandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JmxFileHander {

	/**
	 * function:renameJmxPlan
	 * 
	 * @param path
	 * @return
	 */
	public String renameJmxPlan(String path) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmm");
		String ctime = formatter.format(new Date());
		if (path.length() > 1) {
			return path.substring(1, path.length()).replace('/', '_') + "_" + ctime + ".jmx";
		}else{
			return ctime + ".jmx";
		}
	}

	/**
	 * Reader/Writer --> BufferedReader／BufferedWriter 带缓冲区buffer 的字符流 读取
	 * BufferedReader 构造器 new BufferedReader(Reader in); 只有该类
	 * 
	 * @param src
	 * @param dest
	 */
	public void copyFile(String src, String dest) {
		BufferedReader br = null;
		BufferedWriter bw = null;

		try {
			br = new BufferedReader(new FileReader(new File(src)));
			bw = new BufferedWriter(new FileWriter(new File(dest)));
			String msg = null;
			while ((msg = br.readLine()) != null) {
				bw.write(msg);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (br != null) {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
