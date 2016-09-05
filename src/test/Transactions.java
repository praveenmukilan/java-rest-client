package test;

import java.util.Comparator;

public class Transactions {
	
	String traderId;
	double value;
	long timestamp;

	public long getTimeStamp() {
		return timestamp;
	}

	public void setTimeStamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getTraderId() {
		return traderId;
	}

	public void setTraderId(String traderId) {
		this.traderId = traderId;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	public String toString(){
		return "id : "+traderId + " | timestamp : "+timestamp+" | value : "+value;
	}


}
