package com.systoon.qc.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.junit.Test;


import com.systoon.qc.jtlhandle.JtlParserCSV;
import com.systoon.qc.jtlhandle.JtlSummaryResult;

public class TestJtlParser {

	@Test
	public void testJtlParser() {

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
