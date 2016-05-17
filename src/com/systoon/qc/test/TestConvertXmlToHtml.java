package com.systoon.qc.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.systoon.qc.jtlhandle.ConvertXmlToHtml;

public class TestConvertXmlToHtml {

	@Test
	public void test() {
		new ConvertXmlToHtml().convertJtlToHtml(
				"/Users/perfermance/JmeterTest/results/jtl/saveOperInfo_ok_20161526.jtl",
				"/Users/perfermance/JmeterTest/apache-jmeter-2.13/extras/jmeter-results-report_21.xsl",
				"/Users/perfermance/JmeterTest/results/html/saveOperInfo_ok_20161526.html");
	
	}

}
