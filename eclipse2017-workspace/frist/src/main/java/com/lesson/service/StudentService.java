package com.lesson.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lession.model.Student;
import com.lesson.dao.StudentDao;
public class StudentService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		if("preadd".equals(action)) {
			this.preadd(req,resp);
		}else if("add".equals(action)) {
			this.add(req, resp);
		}else if("list".equals(action)){
			this.list(req,resp);
		}else if("rem".equals(action)){
			this.rem(req,resp);
		}
	}
	
	private void rem(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String olddata = req.getParameter("data");
			StudentDao sd = new StudentDao();
			sd.studentRem(olddata);
			resp.sendRedirect("student?action=list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void preadd(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("id");
		String score = req.getParameter("score");
		Student student =null;
		try {
			if((id!=null)&&(score!=null)) {
				StudentDao sd = new StudentDao();
				double dscore = Double.parseDouble(score);
				student = sd.findByScoreAndId(dscore, id);
				req.setAttribute("student",student);
			}
			req.getRequestDispatcher("preadd.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void list(HttpServletRequest req, HttpServletResponse resp){
		String currentPage = req.getParameter("currentPage");
		if( currentPage==null||currentPage.isEmpty()){
			currentPage="1";
		}
		try {
			StudentDao sd = new StudentDao();
			List<Student> studentList = sd.studentList(currentPage);
			req.setAttribute("studentList", studentList);
			req.getRequestDispatcher("list.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void add(HttpServletRequest req, HttpServletResponse resp) {
		String olddata = req.getParameter("olddata");
		StudentDao sd = new StudentDao();
		String[] split = olddata.split(",");
		if(split!=null&&(split.length>0)&&!split[0].isEmpty()){
			String id=split[0]+",";
			String name = req.getParameter("name")+",";
			String score = req.getParameter("score");
			String birthday =req.getParameter("birthday")+",";
			String description =req.getParameter("description");
			double score1 = Double.parseDouble(score);
			String data=id+name+birthday+description;
			System.out.println(data);
			sd.studentRem(olddata);
			sd.studentAdd(data,score1);
		}else {//添加
			String uuid = UUID.randomUUID().toString().replaceAll("-", "")+",";
			String name = req.getParameter("name")+",";
			String birthday =req.getParameter("birthday")+",";
			String description =req.getParameter("description");
			String scoreStr = req.getParameter("score");
			double score = Double.parseDouble(scoreStr);
			String data=uuid+name+birthday+description;
			sd.studentAdd(data,score);
		}
		try {
			resp.sendRedirect("student?action=list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
