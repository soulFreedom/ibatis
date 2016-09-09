package org.guo.ibatis.dao.po;

public class Actor extends User {
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		
		return super.toString() + " phone:" + phone;
	}
	
	
}
