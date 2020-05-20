package com.lec.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletForm
 */
@WebServlet("/FormOk")
public class ServletForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request.getParameterNames() 사용
		// Enumeration<String> 값 리턴
		System.out.println("getParameterNames() 사용");
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String param_name = names.nextElement();
			String param_value = request.getParameter(param_name); // 이렇게 하면. String[] 의 경우 첫번째 값만 나옴
			System.out.println(param_name + " : " + param_value);
		} // end while

		// request.getParameterMap() 사용
		// Map<String, String[]> 리턴
		System.out.println("getParameterMap() 사용");
		Map<String, String[]> paramMap = request.getParameterMap();
		for (String key : paramMap.keySet()) {
			System.out.println(key + " : " + Arrays.toString(paramMap.get(key)));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pass = request.getParameter("pw");

		String[] hobbys = request.getParameterValues("hobby");
		String gender = request.getParameter("gender");
		String local = request.getParameter("local");
		String memo = request.getParameter("memo");

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("hidden : " + data1 + ", " + data2 + "<br/>");
		out.println("이름 : " + name + "<br/>");
		out.println("아이디 :" + id + "<br/>");
		out.println("비밀번호 :" + pass + "<br />");
		out.println("취미 :" + Arrays.toString(hobbys) + "<br/>");
		out.println("성별 :" + gender + "<br/>");
		out.println("지역 :" + local + "<br/>");
		out.println("메모 :" + memo + "<br/>");
		out.println("</body></html>");


		out.close(); // out 출력스트림을 닫아줍니다
		
		// request.getParameterNames() 사용
		// Enumeration<String> 값 리턴
		System.out.println("getParameterNames() 사용");
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String param_name = names.nextElement();
			String param_value = request.getParameter(param_name); // 이렇게 하면. String[] 의 경우 첫번째 값만 나옴
			System.out.println(param_name + " : " + param_value);
		} // end while

		// request.getParameterMap() 사용
		// Map<String, String[]> 리턴
		System.out.println("getParameterMap() 사용");
		Map<String, String[]> paramMap = request.getParameterMap();
		for (String key : paramMap.keySet()) {
			System.out.println(key + " : " + Arrays.toString(paramMap.get(key)));
		}
	}

}
