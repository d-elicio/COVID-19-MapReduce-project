package ProgettiBigData.CovidMapReduce;

public class Elements {
	
	private String key;
	private double value;
	
	public String getKey() {
		return key;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setKey(String A) {
		key=A;
	}
	
	public void setValue(double A) {
		value=A;
	}
	
	public void put(String A, double B) {
		key=A;
		value=B;
	}
	
	

}
