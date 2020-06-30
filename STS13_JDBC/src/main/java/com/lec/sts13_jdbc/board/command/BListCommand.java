package com.lec.sts13_jdbc.board.command;

import com.lec.sts13_jdbc.board.beans.BWriteDAO;
import com.lec.sts13_jdbc.board.beans.BWriteDTO;
import org.springframework.ui.Model;

import java.util.ArrayList;

public class BListCommand implements BCommand {
    @Override
    public void execute(Model model) {
        BWriteDAO dao = new BWriteDAO();
        ArrayList<BWriteDTO> list = dao.select();
        model.addAttribute("list", list);
    }
}
