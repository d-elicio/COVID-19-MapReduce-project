package ProgettiBigData.CovidMapReduce3;

public class Oggetto {
	
	private String key;
	private int value;
	
	public String getKey() {
		return key;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setKey(String A) {
		key=A;
	}
	
	public void setValue(int B) {
		value=B;
	}
	
	public void put(String A, int B) {
		key=A;
		value=B;
	}

}
