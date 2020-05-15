$(function () {
    $('.hot_link .reserve').click(function (e) {
        e.preventDefault(); //일반적으로 브라우저의 구현에 의존의해 처리되는 기존의 액션이 동작하지 않고, 그결과 이벤트가 발생하지 않음
        $('.modal').addClass('active');
    });
    $('.close').click(function (e) {
        e.preventDefault();
        $('.modal').removeClass('active');
        $(".form-control").removeClass("error");
        $(".form-control").next().slideUp(100);
    });

    var slides = $('.carousel').carousel();

    $('.prev').click(function () {
        slides.carousel('prev');
    });
    $('.next').click(function () {
        slides.carousel('next');
    });

    $('.login label').removeClass('active');
     
    $('.login input').click(function(){
        $(this).attr('placeholder','');
        $(this).siblings('label').addClass('on');
    });
            var $button = $(".login_button"),
            $fields = $(".form-control");

            $button.on("click", function(e) {
                e.preventDefault();
            $fields.each(function() {
                var value = $(this).val();
                if (value == "") {
                $(this).addClass("error");
                $(this).next().slideDown(300);
                }
            });
        });
        $fields.click(function(){
            $(this).removeClass("error");
            $(this).next().slideUp(100);
        });
});

//map위치 표현
var map;
var marker;

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: { lat: 37.6267711, lng: 127.0759383 },
        zoom: 17
    });

    marker = new google.maps.Marker({
        map : map,
        draggable: true,
        animation: google.maps.Animation.BOUNCE,
        position: { lat: 37.6267711, lng: 127.0759383 }
    });
    marker.addListener("click", toggleBounce);
}

function toggleBounce(){
    if(marker.getAnimation() !== null){
        marker.setAnimation(null);
    }else{
        marker.setAnimation(google.maps.Animation.BOUNCE);
    }
}