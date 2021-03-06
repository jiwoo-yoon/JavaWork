package com.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.CategoryDAO;
import com.lec.beans.CategoryDTO;

public class CateListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		CategoryDTO[] arr = null;
		CategoryDAO dao = new CategoryDAO();  // DAO 객체 생성
		
		// ajax response 에 필요한 값들
		StringBuffer message = new StringBuffer();
		String status = "FAIL";   // 기본 FAIL

		//입력한 값을 받아오기
		String dep = request.getParameter("depth");
		String par = request.getParameter("parent");
		
		if(par == null) {
			try {
				arr = dao.select();
				status = "OK";
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			try {
				int depth = Integer.parseInt(dep);
				int parent = Integer.parseInt(par);
				arr = dao.selectCa(depth, parent);
				status = "OK";
				
				if(arr == null) {
					message.append("[해당 데이터가 없습니다]");
				} else {
					status = "OK";
				}
			} catch (SQLException e) {
				//e.printStackTrace();
				message.append("[트랜잭션 에러:" + e.getMessage() + "]");
			} catch (Exception e) {
				message.append("[예외발생:" + e.getMessage() + "]");
			}
		}
			request.setAttribute("list", arr);
			request.setAttribute("status", status);
			request.setAttribute("message", message.toString());
		}
		
	
				
	}

