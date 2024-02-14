let x=1;//슬라이드의 이동거리를 결정하는 변수
let width=1170;//이미지의 가로길이
let count=document.getElementsByClassName("eventImage").length;//이미지의 개수
let eventTab=document.getElementById("eventTab");//가로 슬라이드
let categoryTab=document.getElementById("categoryTab");
let bar=document.getElementById("bar");

//페이지 로드 후 실행되는 익명함수
window.onload = function(){
    slideWidth=count*width;
    eventTab.style.width=`${slideWidth}px`;//frame의 길이를 이미지 개수와 길이를 통해 동적으로 결정

    //setInterval을 사용하여 일정 시간마다 moveSlide() 메서드 호출
    setInterval(moveEventSlide, 4000);
}

document.addEventListener('DOMContentLoaded', function () {
    // 마우스의 위치값 저장
    let x = 0;
    let y = 0;

    // 대상 Element 가져옴
    let bar = document.getElementById('bar');

    // 마우스 누른 순간 이벤트
    const mouseDownHandler = function (e) {
        // 누른 마우스 위치값을 가져와 저장
        x = e.clientX;

        // 마우스 이동 및 해제 이벤트를 등록
        document.addEventListener('mousemove', mouseMoveHandler);
        document.addEventListener('mouseup', mouseUpHandler);
    };

    const mouseMoveHandler = function (e) {
        // 마우스 이동시 초기 위치와의 거리차 계산
        const dx = e.clientX - x;

        if(bar.offsetLeft>=0 && bar.offsetLeft<=870){
            // 마우스 이동 거리 만큼 Element의 left 위치값에 반영
            bar.style.left = `${bar.offsetLeft + dx}px`;
            categoryTab.style.left = `${categoryTab.offsetLeft - dx*0.2}px`;
        } else if(bar.offsetLeft>870){
            bar.style.left = `${bar.offsetLeft - 10}px`;
        } else if(bar.offsetLeft<0){
            bar.style.left = `${bar.offsetLeft + 10}px`;
        }


        // 기준 위치 값을 현재 마우스 위치로 update
        x = e.clientX;
    };

    const mouseUpHandler = function () {
        // 마우스가 해제되면 이벤트도 같이 해제
        document.removeEventListener('mousemove', mouseMoveHandler);
        document.removeEventListener('mouseup', mouseUpHandler);
    };

    bar.addEventListener('mousedown', mouseDownHandler);
});

//imageTab의 위치에 따라 translateX의 값을 제어하는 메서드
function moveEventSlide(){
    if(x==count){//한바퀴를 다 돌았을 때 다시 처음으로 이동
        document.getElementById("eventTab").style.transform='translateX(0px)';
        x=1;
    }else{//슬라이드를 x와 이미지의 가로길이만큼 왼쪽으로 이동
        document.getElementById("eventTab").style.transform='translateX(-'+x*width+'px)';
        x++;
    }
}