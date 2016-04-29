package com.systoon.qc.business;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspWriter;

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
	public boolean handleJmxFile(String JmxPlanTemple,String JmxPlan, String ip, String port,
			String path, String method, String parameters, String vuser, String assertion) {


		/*1、复制一份测试计划*/
		copyFile(JmxPlanTemple, JmxPlan);
		
		/*2、解析JMX（xml）替换对应参数*/
		
		return true;
	}



	/**
	 * Reader/Writer  -->  BufferedReader／BufferedWriter
	 * 带缓冲区buffer 的字符流 读取
	 * BufferedReader 构造器 new BufferedReader(Reader in);  
	 * 只有该类
	 * @param src
	 * @param dest
	 */
	public void copyFile(String src,String dest){
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		try {
			br = new BufferedReader(new FileReader(new File(src)));
			bw = new BufferedWriter(new FileWriter(new File(dest)));
			String msg = null;
			while((msg = br.readLine()) != null){
				bw.write(msg);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(bw != null){
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(br != null){
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	
	public static void main(String[] args) {
		System.out.println(new JmeterPlanHandle().renameJmxPlan("/user/login"));
	}

}
