package com.lec.sts15_mybatis.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @RequestMapping("/buy_ticket")
    public String buy_ticket(){
        return "ticket/buy_ticket";
    }

    @RequestMapping("/buy_ticket_card")
    public String buy_ticket_card(TicketDTO dto, Model model){
        System.out.println("/buy_ticket_card");
        System.out.println("user id : " + dto.getUserId());
        System.out.println("ticket count : " + dto.getTicketCount());

        String page = "ticket/buy_ticket_done";

        try{
            ticketService.buyTicket(dto);
            model.addAttribute("ticketInfo", dto);
        }catch (Exception e){
            e.printStackTrace();
            page = "ticket/buy_ticket_fail";
        }
        return page;
    }
}
