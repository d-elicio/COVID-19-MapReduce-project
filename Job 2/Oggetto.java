package ProgettiBigData.CovidMapReduce2;

public class Oggetto {
	
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
	
	public void setValue(double B) {
		value=B;
	}
	
	public void put(String A, double B ){
		key=A;
		value=B;
	}
}
