package com.systoon.qc.bak;

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

//http://kongcodecenter.iteye.com/blog/1231177
//参考http://siye1982.iteye.com/blog/592405
//参考http://blog.csdn.net/christophe2008/article/details/6046456
public class JavaShellUtil {
    
 
    public int executeShell(String shellCommand,String executeShellLogFile,PrintWriter out) throws IOException {
        System.out.println("shellCommand:"+shellCommand);
        int success = 0;
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        // 格式化日期时间，记录日志时使用
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS ");
 
        try {
            stringBuffer.append(dateFormat.format(new Date()))
                    .append("准备执行Shell命令 ").append(shellCommand)
                    .append(" \r\n");
            Process pid = null;
            String[] cmd = { "/bin/sh", "-c", shellCommand };
            // 执行Shell命令
            pid = Runtime.getRuntime().exec(cmd);
            if (pid != null) {
                stringBuffer.append("进程号：").append(pid.toString())
                        .append("\r\n");
                // bufferedReader用于读取Shell的输出内容
                bufferedReader = new BufferedReader(new InputStreamReader(pid.getInputStream()), 1024);
//                pid.waitFor();   阻塞进程，等待命令执行完毕
//                System.out.println(stringBuffer);
                out.print(stringBuffer); //jsp页面打印
                String line = null;
                // 读取Shell的输出内容，并添加到stringBuffer中,并打印
                while (bufferedReader != null
                		&& (line = bufferedReader.readLine()) != null) {
                	stringBuffer.append(line).append("\r\n");
//                	System.out.println(line);
                	out.print(line);
                	
                }
            } else {
                stringBuffer.append("没有pid\r\n");
            }
            stringBuffer.append(dateFormat.format(new Date())).append(
                    "Shell命令执行完毕\r\n执行结果为：\r\n");
        
            System.out.println("stringBuffer:"+stringBuffer);
        } catch (Exception ioe) {
            stringBuffer.append("执行Shell命令时发生异常：\r\n").append(ioe.getMessage())
                    .append("\r\n");
        } finally {
            if (bufferedReader != null) {
                OutputStreamWriter outputStreamWriter = null;
                try {
                    bufferedReader.close();
                    // 将Shell的执行情况输出到日志文件中
                    OutputStream outputStream = new FileOutputStream(executeShellLogFile);
                    outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                    outputStreamWriter.write(stringBuffer.toString());
                    System.out.println("stringBuffer.toString():"+stringBuffer.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    outputStreamWriter.close();
                }
            }
            success = 1;
        }
        return success;
    }
 
}
