package com.systoon.qc.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.systoon.qc.jtlhandle.JtlParserCSV;
import com.systoon.qc.jtlhandle.JtlSummaryResult;

public class TestJtlParser {

	@Test
	public void testJtlParser() {

//		String jtlResultFile = "/Users/perfermance/JmeterTest/results/jtl/usr_login_1605121605.jtl";
//		String jtlResultFile = "/Users/perfermance/JmeterTest/results/jtl/800.jtl";
		String jtlResultFile = "/Users/perfermance/JmeterTest/results/jtl/801.jtl";
		JtlSummaryResult jtlSummaryResult = new JtlParserCSV().jtlParser(jtlResultFile);
		System.out.println(jtlSummaryResult);
		
//		System.out.println(System.currentTimeMillis());
//		
//		System.out.println(new Date(1463451198952l));
//		System.out.println(new Date(1463023004906l));
		
	}

}
