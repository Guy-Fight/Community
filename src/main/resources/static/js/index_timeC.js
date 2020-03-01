var timeC = document.getElementsByClassName("timeC");
for(var i = 0; i < timeC.length; i++){
    var time = $(timeC[i]).attr("tip");
    var timeNow = new Date();
    time = timeNow - time;
    var sec = Math.floor(time/1000);
    var showSec = sec%60;
    var min = (sec-showSec)/60;
    var showMin = min%60;
    var hou = (min-showMin)/60;
    var showHou = hou%24;
    var day = (hou - showHou)/24;
    timeC[i].innerHTML = jl(sec,min,hou,day);
}

function jl(sec,min,hou,day) {
    if(day == 0 && hou == 0 && min == 0){
        return "刚刚";
    }else if(day == 0 && hou == 0 && min > 0){
        return min + "分钟前";
    }else if(day == 0 && hou > 0){
        return hou + "小时前";
    }else if(day < 30){
        return day + "天前";
    }else if(day < 365){
        return Math.floor(day/30) + "个月前";
    }else{
        return Math.floor(day/365) + "年前";
    }

}