package com.systoon.qc.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExecuteJmeter {

	public Process executeJmeter(String jmeterExe, String jmxRealPlan, String jtlResult, String lOADERIP1) throws IOException {
		/* ִ执行Jmeter测试计划*/
		String command = "/bin/sh " + " -c " + "\"" + jmeterExe + " -n " + " -t " + jmxRealPlan + " -l " + jtlResult + " -R " + lOADERIP1 + "\"";
//		String command = "cmd /c " + jmeterExe + " -n " + " -t " + jmxPlan + " -l " + jtlResult;
		System.out.println(command);
		Runtime run = Runtime.getRuntime();
		Process process = run.exec(command);
		
		
		
		
		
		
		return process;
	}

}
