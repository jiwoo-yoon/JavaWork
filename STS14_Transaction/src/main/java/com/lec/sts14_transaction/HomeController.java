package com.lec.sts14_transaction;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

	//private TicketDAO1 dao1; // 트랜잭션 미적용
	//private TicketDAO2 dao2; // 트랜잭션 적용
	private TicketDAO3 dao3; // 트랜잭션 적용

	@Autowired
//	public void setDao(TicketDAO1 dao1){
//		this.dao1 = dao1;
//	}
//	public void setDao2(TicketDAO2 dao2){
//		this.dao2 = dao2;
//	}
	public void setDao3(TicketDAO3 dao3){
		this.dao3 = dao3;
	}

	@RequestMapping("/buy_ticket")
	public String buy_ticket(){
		return "buy_ticket"; // 티켓 구매 양식
	}

	// 티켓 구매 처리
	@RequestMapping("/buy_ticket_card")
	public String buy_ticket_card(TicketDTO dto, Model model){
		System.out.println("/buy_ticket_card");
		System.out.println("user id : " + dto.getUserId());
		System.out.println("ticket count : " + dto.getTicketCount());

		String page = "buy_ticket_done";
		try{
			dao3.buyTicket(dto); // 트랜잭션
			model.addAttribute("ticketInfo", dto);
		}catch (Exception e){
			e.printStackTrace();
			page = "buy_ticket_fail"; // 트랜잭션 오류 발생시 넘겨줌
		}

		return page;
	}

}
