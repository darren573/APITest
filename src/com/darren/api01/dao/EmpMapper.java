package com.darren.api01.dao;

import java.util.List;

import com.darren.api01.bean.Emp;

public interface EmpMapper {
	//查询
	public List<Emp> queryAll();
	//添加
	public void addEmp(Emp emp);
	//修改
	public void updateEmp(Emp emp);
	//删除
	public void deleteEmp(int eid);
	
}
