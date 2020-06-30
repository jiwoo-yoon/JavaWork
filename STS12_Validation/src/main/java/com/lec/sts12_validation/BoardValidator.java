package com.lec.sts12_validation;

import com.lec.beans.WriteDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class BoardValidator implements Validator {


    // 이 Validator가 제공된 Class의 인스턴스(aClass)를 유효성검사 할 수 있는가?
    @Override
    public boolean supports(Class<?> aClass) {
        return WriteDTO.class.isAssignableFrom(aClass); // 검증할 객체의 클래스 타입인지 확인 : WriteDTO = aClass; 가능여부
    }

    //주어진 객체(target)에 유효성검사를 하고
    //유효성검사에 오류가 있는 경우 주어진 객체에 이 오류들을 errors 에 등록 한다.
    @Override
    public void validate(Object o, Errors errors) {
        System.out.println("validate()");
        WriteDTO dto = (WriteDTO)o;

        int uid = dto.getUid();
        if(uid == 0){
            System.out.println("uid 오류");
            //에러 등록 rejectValue (field, errorCode);
            errors.rejectValue("uid", "invalidUid");
        }

        String name = dto.getName();
        if(name == null || name.trim().isEmpty()){
            System.out.println("name 오류 : 반드시 한글자라도 입력해야 합니다.");
            errors.rejectValue("name", "emptyName");
        }

        //ValidationUtils 사용
        //단순히 빈 폼 데이터를 처리할때는 아래 와 같이 사용 가능
        //두번째 매개변수 'subject' 는 반드시 target 클래스의 필드명 이어야 함
        //게다가 Errors 에 등록될때도 동일한 field 명으로 등록된다.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "emptySubject");
    }
}
