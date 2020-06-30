package com.lec.sts13_jdbc.board.command;

import com.lec.sts13_jdbc.board.beans.BWriteDAO;
import com.lec.sts13_jdbc.board.beans.BWriteDTO;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Map;

public class BSviewCommand implements BCommand{
    @Override
    public void execute(Model model) {
        Map<String, Object> map = model.asMap();
        int uid = (int)map.get("uid");
        BWriteDAO dao = new BWriteDAO();
        ArrayList<BWriteDTO> list = dao.sview(uid);
        model.addAttribute("list", list);
    }
}
