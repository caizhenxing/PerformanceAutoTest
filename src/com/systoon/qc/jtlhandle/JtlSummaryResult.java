package com.systoon.qc.jtlhandle;

public class JtlSummaryResult {
	private long minResponseTime;
	private long maxResponseTime;
	private long averageResponseTime;
	private long sumSampleCounters;
	private String errorPercentage;
	private String throughputPerSec;  //单位：每秒
	private String throughputPerMin;  //单位：每分钟
	private String bytesPerSec;       //单位：KB/s
	private String averagebytes;
	
	public long getMinResponseTime() {
		return minResponseTime;
	}
	public void setMinResponseTime(long minResponseTime) {
		this.minResponseTime = minResponseTime;
	}
	public long getMaxResponseTime() {
		return maxResponseTime;
	}
	public void setMaxResponseTime(long maxResponseTime) {
		this.maxResponseTime = maxResponseTime;
	}
	public long getAverageResponseTime() {
		return averageResponseTime;
	}
	public void setAverageResponseTime(long averageResponseTime) {
		this.averageResponseTime = averageResponseTime;
	}
	public long getsumSampleCounters() {
		return sumSampleCounters;
	}
	public void setsumSampleCounters(long sumSampleCounters) {
		this.sumSampleCounters = sumSampleCounters;
	}
	public String geterrorPercentage() {
		return errorPercentage;
	}
	public void seterrorPercentage(String errorPercentage) {
		this.errorPercentage = errorPercentage;
	}
	public String getthroughputPerSec() {
		return throughputPerSec;
	}
	public void setthroughputPerSec(String throughputPerSec) {
		this.throughputPerSec = throughputPerSec;
	}
	public String getThroughputPerMin() {
		return throughputPerMin;
	}
	public void setThroughputPerMin(String throughputPerMin) {
		this.throughputPerMin = throughputPerMin;
	}
	public String getBytesPerSec() {
		return bytesPerSec;
	}
	public void setBytesPerSec(String bytesPerSec) {
		this.bytesPerSec = bytesPerSec;
	}
	public String getAveragebytes() {
		return averagebytes;
	}
	public void setAveragebytes(String averagebytes) {
		this.averagebytes = averagebytes;
	}	
	public JtlSummaryResult(){
		
	}
	public JtlSummaryResult(long minResponseTime, long maxResponseTime, long averageResponseTime,
			long sumSampleCounters, String errorPercentage, String throughputPerSec, String throughputPerMin,
			String bytesPerSec, String averagebytes) {
		super();
		this.minResponseTime = minResponseTime;
		this.maxResponseTime = maxResponseTime;
		this.averageResponseTime = averageResponseTime;
		this.sumSampleCounters = sumSampleCounters;
		this.errorPercentage = errorPercentage;
		this.throughputPerSec = throughputPerSec;
		this.throughputPerMin = throughputPerMin;
		this.bytesPerSec = bytesPerSec;
		this.averagebytes = averagebytes;
	}
	@Override
	public String toString() {
		return "JtlSummaryResult [minResponseTime=" + minResponseTime + ", maxResponseTime=" + maxResponseTime
				+ ", averageResponseTime=" + averageResponseTime + ", sumSampleCounters=" + sumSampleCounters
				+ ", errorPercentage=" + errorPercentage + ", throughputPerSec=" + throughputPerSec
				+ ", throughputPerMin=" + throughputPerMin + ", bytesPerSec=" + bytesPerSec + ", averagebytes="
				+ averagebytes + "]";
	}
	
	

	
	
	

}
