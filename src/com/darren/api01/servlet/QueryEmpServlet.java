package com.darren.api01.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;

import com.darren.api01.bean.Emp;
import com.darren.api01.bean.Result;
import com.darren.api01.dao.EmpMapper;
import com.darren.api01.utils.JsonManager;

public class QueryEmpServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public QueryEmpServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 通过配置文件构建SqlSessionFactory对象
		 * */
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
		.build(Resources.getResourceAsStream("mybatis-config.xml"));
		/*
		 * 通过SqlSessionFactory对象来打开获取SqlSession对象
		 * */
		SqlSession session = sessionFactory.openSession();
		/*
		 * 通过sqlSession对象来获取Mapper对象
		 * */
		EmpMapper empMapper = session.getMapper(EmpMapper.class);
		/*
		 * 声明并实例化数据传输对象result
		 * */
		Result<List<Emp>> result = new Result<List<Emp>>();
		List<Emp> list = null;
		try {
			
			/*
			 * 通过Mapper对象发送sql语句，并执行，获取返回的结果
			 */
			list = empMapper.queryAll();
			result.setCode(200);
			result.setMsg("请求成功");
			result.setResult(list);
		} catch (Exception e) {
			result.setCode(500);
			result.setMsg("请求失败");
			result.setResult(list);
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
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
