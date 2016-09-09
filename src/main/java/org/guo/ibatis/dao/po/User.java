package org.guo.ibatis.dao.po;

import java.util.List;


/**
 * <p>Title: User.java</p>
 * <p>Description: xxxx平台软件</p>
 * <p>Copyright: Copyright (c) 2011-2012 xxx信息技术有限公司</p>
 * <p>Company: XXX信息技术有限公司</p>
 * @author 郭海龙
 * @version 1.0 创建时间：2013年9月5日 上午9:13:25
 */
public class User {
	private int id;
	private String name;
	private int sex; //0:女 1.男
	private int age;
	private String title;
	private String phone;
	private String address;
	private List<String> roles; //角色
	
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		String info = "id:" + id + "_" + "name:" + name + "_" + "title:" + title;
		return info;
	}
	
	
}
