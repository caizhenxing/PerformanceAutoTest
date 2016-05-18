package com.systoon.qc.jtlhandle;

public class JtlSummaryResult {
	private long minResponseTime;
	private long maxResponseTime;
	private long sumResponseTime;
	private long averageResponseTime;
	private long sumSampleCounters;
	private double errorCounters;
	private String errorPercentage;
	private String throughputPerSec;  //单位：每秒
	private String throughputPerMin;  //单位：每分钟
	private double sumBytes;
	private String bytesPerSec;       //单位：KB/s
	private String averagebytes;
	
	public JtlSummaryResult(){
		minResponseTime = -1;
		maxResponseTime = 0;
		sumResponseTime = 0;
		averageResponseTime = 0;
		sumSampleCounters = 0;
		errorCounters = 0;
		sumBytes = 0;
	}

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

	public long getSumResponseTime() {
		return sumResponseTime;
	}

	public void setSumResponseTime(long sumResponseTime) {
		this.sumResponseTime += sumResponseTime;
	}

	public long getAverageResponseTime() {
		return averageResponseTime;
	}

	public void setAverageResponseTime(long averageResponseTime) {
		this.averageResponseTime = averageResponseTime;
	}

	public long getSumSampleCounters() {
		return sumSampleCounters;
	}

	public void setSumSampleCounters(long sumSampleCounters) {
		this.sumSampleCounters = sumSampleCounters;
	}
	
	public void setSumSampleCounters() {
		this.sumSampleCounters++;
	}

	public double getErrorCounters() {
		return errorCounters;
	}

	public void setErrorCounters(double errorCounters) {
		this.errorCounters = errorCounters;
	}
	
	public void setErrorCounters() {
		this.errorCounters++;
	}

	public String getErrorPercentage() {
		return errorPercentage;
	}

	public void setErrorPercentage(String errorPercentage) {
		this.errorPercentage = errorPercentage;
	}

	public String getThroughputPerSec() {
		return throughputPerSec;
	}

	public void setThroughputPerSec(String throughputPerSec) {
		this.throughputPerSec = throughputPerSec;
	}

	public String getThroughputPerMin() {
		return throughputPerMin;
	}

	public void setThroughputPerMin(String throughputPerMin) {
		this.throughputPerMin = throughputPerMin;
	}

	public double getSumBytes() {
		return sumBytes;
	}

	public void setSumBytes(double sumBytes) {
		this.sumBytes += sumBytes;
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

	public JtlSummaryResult(long minResponseTime, long maxResponseTime, long averageResponseTime,
			long sumSampleCounters, String errorPercentage, String throughputPerSec,
			String throughputPerMin, String bytesPerSec, String averagebytes) {
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
				+ ", sumResponseTime=" + sumResponseTime + ", averageResponseTime=" + averageResponseTime
				+ ", sumSampleCounters=" + sumSampleCounters + ", errorCounters=" + errorCounters + ", errorPercentage="
				+ errorPercentage + ", throughputPerSec=" + throughputPerSec + ", throughputPerMin=" + throughputPerMin
				+ ", sumBytes=" + sumBytes + ", bytesPerSec=" + bytesPerSec + ", averagebytes=" + averagebytes + "]";
	}


}
