package com.darren.api01.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.darren.api01.bean.Emp;
import com.darren.api01.bean.Result;
import com.darren.api01.dao.EmpMapper;
import com.darren.api01.utils.JsonManager;

public class DeleteEmpServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeleteEmpServlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Result<String> result=new Result<String>();
		SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder()
		.build(Resources.getResourceAsStream("mybatis-config.xml"));
		SqlSession session=sqlSessionFactory.openSession();
		EmpMapper empMapper=session.getMapper(EmpMapper.class);
		try {
			
			empMapper.deleteEmp(1006);
			/*
			 * 如果正常执行说明请求成功
			 * */
			result.setCode(200);
			result.setMsg("请求成功");
			result.setResult("刪除成功");
		} catch (Exception e) {
			/*
			 * 如果出现异常说明请求失败
			 * */
			result.setCode(500);
			result.setMsg("请求失败");
			result.setResult("刪除失败");
		}
		session.commit();
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(JsonManager.beanToJson(result));
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
