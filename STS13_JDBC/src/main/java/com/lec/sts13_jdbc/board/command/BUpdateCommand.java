package com.lec.sts13_jdbc.board.command;

import com.lec.sts13_jdbc.board.beans.BWriteDAO;
import com.lec.sts13_jdbc.board.beans.BWriteDTO;
import org.springframework.ui.Model;

import java.util.Map;

public class BUpdateCommand implements BCommand{
    @Override
    public void execute(Model model) {
        Map<String, Object> map = model.asMap();
        BWriteDTO dto = (BWriteDTO) map.get("dto");
        BWriteDAO dao = new BWriteDAO();
        int list = dao.update(dto);
        model.addAttribute("list", list);
    }
}
