package org.guo.ibatis.dao.po;

//µ¼Ñİ
public class Director  extends User{
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		
		return super.toString() + " address:" + address;
	}
}
