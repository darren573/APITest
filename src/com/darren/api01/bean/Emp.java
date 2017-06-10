package com.darren.api01.bean;

public class Emp {
	private int eid;
	private String ename;
	private String sex;
	
	public Emp(int eid) {
		super();
		this.eid = eid;
	}
	public Emp(String ename, String sex) {
		super();
		this.ename = ename;
		this.sex = sex;
	}
	public Emp(int eid, String ename, String sex) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.sex = sex;
	}
	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
