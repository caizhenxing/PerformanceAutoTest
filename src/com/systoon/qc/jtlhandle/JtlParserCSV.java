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
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.junit.Test;

import com.sun.corba.se.impl.io.InputStreamHook;

/**
 * 解析CSV文件
 * 
 * @author 139997
 * @date 2016-05-17 注意： 1、该文件解析必须保证生成结果时，jtl保存为CSV格式 2、日志配置必须与Jmeter模版一致
 */
public class JtlParserCSV {

	@Test
	public JtlSummaryResult jtlParser(String jtlResultFile) {

		long startTime = 0;
		long endTime = 0;
		double elapsedTime = 0;
		long minResponseTime = 0;
		long maxResponseTime = 0;
		long sumResponseTime = 0;
		long averageResponseTime = 0;
		long sumSampleCounters = 0;
		double errorCounters = 0;
		double sumBytes = 0;
		String errorPercentage;
		String throughputPerSec; // 单位：每秒
		String throughputPerMin; // 单位：每分
		String bytesPerSec;
		String averagebytes;

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(jtlResultFile)));
			String msg = null;
			while ((msg = br.readLine()) != null) {
				// 1、统计请求数量
				sumSampleCounters++;
				String[] results = msg.split(",");

				// 2、标记压测总时间
				if (startTime == 0) {
					startTime = Long.parseLong(results[0]);
				}
				endTime = Long.parseLong(results[0]);

				// 3、纪录响应时间(最大，最小，总)
				sumResponseTime += Long.parseLong(results[1]);

				if (minResponseTime == 0) {
					minResponseTime = Long.parseLong(results[1]);
				}

				if (Long.parseLong(results[1]) < minResponseTime) {
					minResponseTime = Long.parseLong(results[1]);
				}

				if (Long.parseLong(results[1]) > maxResponseTime) {
					maxResponseTime = Long.parseLong(results[1]);
				}

				// 4、统计错误数
				Integer responseCode = 0;
				try {
					responseCode = Integer.parseInt(results[3]);
				} catch (Exception e) {
					// e.printStackTrace();
				}
				
				if (responseCode / 100 != 2 || Boolean.parseBoolean(results[7]) == false) {
					errorCounters++;
				}
				
				// 5、统计网络流量（请求的）
				sumBytes += Double.parseDouble(results[8]);

			}

			// 计算
			// 1、吞吐量
			NumberFormat formatter = new DecimalFormat("0.00");
			elapsedTime = endTime - startTime;  //秒
			throughputPerSec = formatter.format(sumSampleCounters / (elapsedTime / 1000));
			throughputPerMin = formatter.format(sumSampleCounters / (elapsedTime / 1000 / 60));

			// 2、平均响应时间
			averageResponseTime = sumResponseTime / sumSampleCounters;

			// 3、错误率
			NumberFormat nt = NumberFormat.getPercentInstance();
			nt.setMinimumFractionDigits(2);
			errorPercentage = nt.format(errorCounters / sumSampleCounters);

			// 4、流量
			bytesPerSec = formatter.format(sumBytes / (elapsedTime / 1000) / 1024);  //返回 KB/sec
			averagebytes = formatter.format(sumBytes / sumSampleCounters);

			JtlSummaryResult jtlSummaryResult = new JtlSummaryResult(minResponseTime, maxResponseTime,
					averageResponseTime, sumSampleCounters, errorPercentage, throughputPerSec, throughputPerMin,
					bytesPerSec, averagebytes);
			return jtlSummaryResult;

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

}
