package com.test.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletContext_Ex
 */
@WebServlet("/SCEx")
public class ServletContext_Ex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletContext_Ex() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ServletContext 객체를 통해 초기화 값 얻어오기
		String id = getServletContext().getInitParameter("id");
		String pw = getServletContext().getInitParameter("password");
		String local = getServletContext().getInitParameter("local");

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		// html 형식으로 출력
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("id : " + id + "<br>");
		out.println("비밀번호 :" + pw + "<br>");
		out.println("지역 :" + local + "<br>");
		out.println("</body>");
		out.println("</html>");
		// 콘솔화면에 출력
		System.out.println("id : " + id);
		System.out.println("pw :" + pw);
		System.out.println("local : " + local);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
