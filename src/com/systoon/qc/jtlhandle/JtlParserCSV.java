package com.systoon.qc.jtlhandle;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * 解析CSV文件
 * 
 * @author 139997
 * @date 2016-05-17 注意： 1、该文件解析必须保证生成结果时，jtl保存为CSV格式 2、日志配置必须与Jmeter模版一致
 */
public class JtlParserCSV {

	public Map<String, JtlSummaryResult> jtlParser(String jtlResultFile) {

		JtlSummaryResult SummaryResult = new JtlSummaryResult();
		Map<String, JtlSummaryResult> jtlSummaryResultMap = new HashMap<String, JtlSummaryResult>();
		jtlSummaryResultMap.put("Summary", SummaryResult);
		
		double startTime = 0;
		double endTime = 0;

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(jtlResultFile)));
			String msg = null;
			while ((msg = br.readLine()) != null) {
				String[] results = msg.split(",");
				// 1、标记压测总时间
				if (startTime == 0) {
					startTime = Long.parseLong(results[0]);
				}
				endTime = Long.parseLong(results[0]);
				
				jtlResultLogHandler(jtlSummaryResultMap, results);
			}

			// 计算

			calculateResult(jtlSummaryResultMap, startTime, endTime);

			return jtlSummaryResultMap;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private void calculateResult(Map<String, JtlSummaryResult> jtlSummaryResultMap, double startTime, double endTime) {
		double elapsedTime;
		JtlSummaryResult jtlSummaryResult = null;
		JtlSummaryResult summaryResult = jtlSummaryResultMap.get("Summary");
		NumberFormat formatter = new DecimalFormat("0.00"); // 设置精度
		elapsedTime = endTime - startTime; // 毫秒
		NumberFormat nt = NumberFormat.getPercentInstance();
		nt.setMinimumFractionDigits(2);

		Set<String> set = jtlSummaryResultMap.keySet();
		for (Object obj : set) {

			jtlSummaryResult = jtlSummaryResultMap.get(obj);

			// 1、label对象的吞吐量
			jtlSummaryResult.setThroughputPerSec(
					formatter.format(jtlSummaryResult.getSumSampleCounters() / (elapsedTime / 1000)));
			jtlSummaryResult.setThroughputPerMin(
					formatter.format(jtlSummaryResult.getSumSampleCounters() / (elapsedTime / 1000 / 60)));

			// 2、label对象的平均响应时间
			jtlSummaryResult.setAverageResponseTime(
					jtlSummaryResult.getSumResponseTime() / jtlSummaryResult.getSumSampleCounters());

			// 3、label对象的错误率
			jtlSummaryResult.setErrorPercentage(
					nt.format(jtlSummaryResult.getErrorCounters() / jtlSummaryResult.getSumSampleCounters()));

			// 4、label对象的流量
			jtlSummaryResult
					.setBytesPerSec(formatter.format(jtlSummaryResult.getSumBytes() / (elapsedTime / 1000) / 1024));

			jtlSummaryResult.setAveragebytes(
					formatter.format(jtlSummaryResult.getSumBytes() / jtlSummaryResult.getSumSampleCounters()));

			// 5、计算总的最大最小响应时间
			if (jtlSummaryResult.getMinResponseTime() < summaryResult.getMinResponseTime()) {
				summaryResult.setMinResponseTime(jtlSummaryResult.getMinResponseTime());
			}

			if (jtlSummaryResult.getMaxResponseTime() > summaryResult.getMaxResponseTime()) {
				summaryResult.setMaxResponseTime(jtlSummaryResult.getMaxResponseTime());
			}

		}

		// 1、总吞吐量
		summaryResult.setThroughputPerSec(formatter.format(summaryResult.getSumSampleCounters() / (elapsedTime / 1000)));
		summaryResult.setThroughputPerMin(formatter.format(summaryResult.getSumSampleCounters() / (elapsedTime / 1000 / 60)));

		// 2、平均响应时间
		summaryResult.setAverageResponseTime(summaryResult.getSumResponseTime() / summaryResult.getSumSampleCounters());

		// 3、错误率（总）
		summaryResult.setErrorPercentage(nt.format(summaryResult.getErrorCounters() / summaryResult.getSumSampleCounters())); 

		// 4、流量(总)
		summaryResult.setBytesPerSec(formatter.format(summaryResult.getSumBytes() / (elapsedTime / 1000) / 1024));  // 返回
		// KB/sec
		summaryResult.setAveragebytes(formatter.format(summaryResult.getSumBytes()  / summaryResult.getSumSampleCounters()));
	}

	private void jtlResultLogHandler(Map<String, JtlSummaryResult> jtlSummaryResultMap, String[] results) {
		String label;
		long minResponseTime = 0;
		JtlSummaryResult jtlSummaryResult = null;
		JtlSummaryResult summaryResult = null;
		
		label = results[2];
		if (!jtlSummaryResultMap.containsKey(label)) {
			jtlSummaryResultMap.put(label, new JtlSummaryResult());
		} 
			
		jtlSummaryResult = jtlSummaryResultMap.get(label);
		summaryResult = jtlSummaryResultMap.get("Summary");
		
		// 1、统计请求数量(总数量)
		summaryResult.setSumSampleCounters();
		jtlSummaryResult.setSumSampleCounters();


		// 3、记录响应时间(总)
		summaryResult.setSumResponseTime(Long.parseLong(results[1]));
		// 3.1 记录label对应对象的总时间
		jtlSummaryResult.setSumResponseTime(Long.parseLong(results[1]));

		// 3.2 label对应对象的最小时间初始化
		minResponseTime = jtlSummaryResult.getMinResponseTime();
		if (minResponseTime == -1) {
			jtlSummaryResult.setMinResponseTime(Long.parseLong(results[1]));
			summaryResult.setMinResponseTime(Long.parseLong(results[1]));  //总的最小响应时间初始化

		}
		// 3.3 label对应对象的最小时间更新
		if (Long.parseLong(results[1]) < minResponseTime) {
			jtlSummaryResult.setMinResponseTime(Long.parseLong(results[1]));
		}
		// 3.4 label对应对象的最大时间更新
		if (Long.parseLong(results[1]) > jtlSummaryResult.getMaxResponseTime()) {
			jtlSummaryResult.setMaxResponseTime(Long.parseLong(results[1]));
		}

		// 4、统计错误数
		Integer responseCode = 0;
		try {
			responseCode = Integer.parseInt(results[3]);
		} catch (Exception e) {
			// e.printStackTrace();
		}

		if (!(responseCode / 100 == 2 || responseCode / 100 == 3 && Boolean.parseBoolean(results[7]) == true)){
			summaryResult.setErrorCounters();
			jtlSummaryResult.setErrorCounters(); // 重载方法 自增一
		}

		// 5、统计网络流量（请求的）
		summaryResult.setSumBytes(Double.parseDouble(results[8]));
		jtlSummaryResult.setSumBytes(Double.parseDouble(results[8]));
	}
	
	
	public static void main(String[] args) {
//		String jtlResultFile = "/Users/perfermance/JmeterTest/results/jtl/usr_login_1605121605.jtl";
//		String jtlResultFile = "/Users/perfermance/JmeterTest/results/jtl/800.jtl";
		String jtlResultFile = "/Users/perfermance/JmeterTest/results/jtl/900.jtl";
		Map<String,JtlSummaryResult> jtlSummaryResultMap = new JtlParserCSV().jtlParser(jtlResultFile);
		Set<String> set = jtlSummaryResultMap.keySet();
		for(Object obj:set){
			System.out.println(obj + "---->" + jtlSummaryResultMap.get(obj));
		}
		
	} 

	
	

}
