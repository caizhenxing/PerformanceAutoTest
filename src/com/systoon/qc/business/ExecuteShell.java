package com.systoon.qc.business;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExecuteShell {

	public Process executeShell(String shellCommand, String executeShellLogFile) throws IOException {
		System.out.println("shellCommand:" + shellCommand);

		// 格式化日期时间，记录日志时使用
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS ");
		StringBuffer stringBuffer = null;
		Process pid = null;

		String args = dateFormat.format(new Date()) + "准备执行Shell命令 " + shellCommand + " \r\n";
		System.out.println(args);
		
		//执行shell命令
		try {

			String[] cmd = { "/bin/sh", "-c", shellCommand };
			// 执行Shell命令
			pid = Runtime.getRuntime().exec(cmd);
			if (pid != null) {
				System.out.println("进程号：" + pid);
			} else {
				System.out.println("没有pid\r\n");
			}

		} catch (Exception ioe) {
			stringBuffer.append("执行Shell命令时发生异常：\r\n").append(ioe.getMessage()).append("\r\n");
		} finally {
			if (stringBuffer != null) {
				OutputStreamWriter outputStreamWriter = null;
				try {
					
					// 将Shell的执行情况输出到日志文件中
					OutputStream outputStream = new FileOutputStream(executeShellLogFile);
					outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
					outputStreamWriter.write(stringBuffer.toString());
					System.out.println("stringBuffer.toString():" + stringBuffer.toString());
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					outputStreamWriter.close();
				}
			}
		
		}
		return pid;
	}

}
