package com.lec.sts15_mybatis.board.command;

import java.util.Arrays;
import java.util.Map;

import com.lec.sts15_mybatis.board.C;
import com.lec.sts15_mybatis.board.beans.IWriteDAO;
import org.springframework.ui.Model;

public class BSelectCommand implements BCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		int uid = (Integer)map.get("uid");
//		BWriteDAO dao = new BWriteDAO();
//		BWriteDTO dto = dao.selectByUid(uid);
		//마바
		IWriteDAO dao = C.sqlSession.getMapper(IWriteDAO.class);

		model.addAttribute("list", Arrays.asList(dao.selectByUid(uid)));
		

	}

}















