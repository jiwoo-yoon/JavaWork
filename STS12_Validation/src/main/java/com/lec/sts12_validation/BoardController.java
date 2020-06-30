package com.lec.sts12_validation;

import com.lec.beans.WriteDTO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @RequestMapping("write.do")
    public String write(){
        return "board/write";
    }

    @RequestMapping("writeOk.do")
    public String writeOk(@ModelAttribute("w") @Valid WriteDTO dto, // 바인딩하는 시점에서 검증(Valid)한다. @InitBinder 로 등록된 validator 로
                          BindingResult result // validator 가 유효성 검사를 한 결과가 담긴 객체.
    ){
        System.out.println("writeOk(): " + dto.getUid() + ":" + dto.getName());
        //System.out.println("validate 전"); showErrors(result);
        //System.out.println("에러 개수:" + result.getErrorCount());
        String page = "board/writeOk";

        //validator 객체 생성, 그러나 Valid 쓰면 validate()를 직접 호출x
        //BoardValidator validator = new BoardValidator();
        //dto -> object, result -> errors 를 받는 형태
        //validator.validate(dto, result);
        System.out.println("validate 후"); showErrors(result);
        if(result.hasErrors()){
            page = "board/write";
        }
        return page;
    }

    // error 에 담겨 있는 에러들을 다 출력해보는 메소드
    // field = 에러목록들??
    public void showErrors(Errors errors){
        if(errors.hasErrors()){
            System.out.println("에러 개수 : " + errors.getErrorCount());
            System.out.println("\t[field]\t|[code]");
            List<FieldError> errList = errors.getFieldErrors();
            for(FieldError err : errList){
                System.out.println("\t" + err.getField() + "\t|" + err.getCode());
            }
        }else{
            System.out.println("에러 없슴");
        }
    }

    // 이 컨트롤러 클래스의 handler 에서 폼 데이터를 바인딩할때 검증하는 객체 지정정
   @InitBinder
    public void initBinder(WebDataBinder binder){
        //setValidator(Validator 객체)
        binder.setValidator(new BoardValidator());
    }

}
