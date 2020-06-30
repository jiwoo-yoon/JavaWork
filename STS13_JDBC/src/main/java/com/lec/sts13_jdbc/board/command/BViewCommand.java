package com.lec.sts13_jdbc.board.command;

import com.lec.sts13_jdbc.board.beans.BWriteDAO;
import com.lec.sts13_jdbc.board.beans.BWriteDTO;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Map;

public class BViewCommand implements BCommand{

    @Override
    public void execute(Model model) {
        //Model 안에 있는 값(attribute) 꺼내는 방법
        Map<String, Object> map = model.asMap();
        int uid = (int)map.get("uid");
        BWriteDAO dao = new BWriteDAO();
        ArrayList<BWriteDTO> list = dao.view(uid);
        model.addAttribute("list", list);
    }
}
