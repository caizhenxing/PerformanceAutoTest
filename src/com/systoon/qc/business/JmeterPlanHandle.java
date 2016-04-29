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

public class JmeterPlanHandle {

	public JmeterPlanHandle() {
		super();
	}

	public String renameJmxPlan(String path) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmm");
		String ctime = formatter.format(new Date());
		return path.substring(1, path.length()).replace('/', '_') + "_" + ctime + ".jmx";
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
	public boolean handleJmxFile(String JmxPlanTemple, String baseJmxPath, String JmxPlanName, String ip, String port,
			String path, String method, String parameters, String vuser, String assertion) {

		/*1、复制一份测试计划*/
		
		return true;
	}

	// 通过文件管道复制文件
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

	// 通过文件输入输出流复制文件

	
	public static void main(String[] args) {
		System.out.println(new JmeterPlanHandle().renameJmxPlan("/user/login"));
	}

}
