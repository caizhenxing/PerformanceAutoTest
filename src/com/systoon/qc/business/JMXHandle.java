package com.systoon.qc.business;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JMXHandle {

	private String generatjmxfilename;

	SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmm");
	String ctime = formatter.format(new Date());

	public JMXHandle() {
		super();
	}

	public void handleJmxFile(String ip, String port, String path, String param, String vuser) {
		generatjmxfilename = path.replaceAll("/", "_") + "_" + vuser + "_" + ctime + ".jmx";

		/*
		 * 替换Jmx模版中相关参数数据
		 * 
		 * 
		 * 
		 */
	}

	public String getGeneratjmxfilename() {
		return generatjmxfilename;
	}

	//通过文件管道复制文件
	public void fileChannelCopy(File s, File t) {

		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;
		try {
			fi = new FileInputStream(s);
			fo = new FileOutputStream(t);
			in = fi.getChannel();// 得到对应的文件通道
			out = fo.getChannel();// 得到对应的文件通道
			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fi.close();
				in.close();
				fo.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

	}
	
	//通过文件输入输出流复制文件
	public void copy(File s,File t){
		InputStream fis = null;
		OutputStream fos = null;
		
		try {
			fis = new BufferedInputStream(new FileInputStream(s));
			fos = new BufferedOutputStream(new FileOutputStream(t));
			byte[] buf = new byte[1024];
			int i = 0;
			while((i = fis.read(buf)) != -1){
				fos.write(buf, 0, i);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				fos.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
	

}
