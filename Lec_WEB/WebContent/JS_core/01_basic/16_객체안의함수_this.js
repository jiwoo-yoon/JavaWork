const dog = {
    name: "개",
    sound: "멍~",

    //객체 안의 함수 정의하는 다양한 방법들

    //방법1
    say1 : function aaa(){
        console.log(this.sound); // this는 함수가 위치한(소유한) 객체
    },
    //방법2 : 이름없는 함수
    say2 : function(){
        console.log(this.sound);
    },
    //방법3 : 최신 JS 방식 (추천)
    say3(){
        console.log(this.sound);
    },
    //arrow function, this가 문제 된다.
      // 이유
    // function 키워드로 만든 함수에서의 this는
    // 자기가 속해있는, 객체를 가리키는데..
    // 화살표 함수의 경우
    // this를 자기가 속해 있는 곳으로
    // 연결하지 않습니다.
    // 화살표 함수는 this 가 뭔지 모릅니다!
    say4 : () => {
        console.log(this.sound);
    }
}
dog.say1();
dog.say2();
dog.say3();
dog.say4();
console.log("-------------------------------")

const cat = {
    name: "야옹",
    sound: "냥"
};

//cat.say1(); 에러
cat.say1 = dog.say1;
dog.say1();
cat.say1(); // 이때 this는 cat이 된다.

const sayCat = cat.say1;
console.log(typeof sayCat);
sayCat(); // 연결이 된 this가 없다, undefined 나옴