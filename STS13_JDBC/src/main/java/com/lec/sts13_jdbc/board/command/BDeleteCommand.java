package com.lec.sts13_jdbc.board.command;

import com.lec.sts13_jdbc.board.beans.BWriteDAO;
import com.lec.sts13_jdbc.board.beans.BWriteDTO;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Map;

public class BDeleteCommand implements BCommand{
    @Override
    public void execute(Model model) {
        Map<String, Object> map = model.asMap();
        BWriteDTO dto = (BWriteDTO) map.get("dto");
        BWriteDAO dao = new BWriteDAO();
        int result = dao.delete(dto);
        model.addAttribute("result", result);;
    }
}
