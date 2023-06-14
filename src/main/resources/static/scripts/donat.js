document.addEventListener("DOMContentLoaded", function () {
    showContainer2();
});

let divQiwi = document.getElementById("qiwi-div");
let divSber = document.getElementById("sber-div");
let divEther = document.getElementById("ether-div");


let btnQiwi = document.getElementById("qiwi-btn");
let btnSber = document.getElementById("sber-btn");
let btnEther = document.getElementById("ether-btn");

let btnQiwiM = document.getElementById("qiwi-btn-m");
let btnSberM = document.getElementById("sber-btn-m");
let btnEtherM = document.getElementById("ether-btn-m");

let elems = document.getElementsByClassName("btn");
function setBorder(btnMain, btn1, btn2){
    btnMain.style.border = "2px solid #FFFFFF";
    btn1.style.border = "none";
    btn2.style.border = "none";
}

function showContainer1() {
    setBorder(btnQiwi, btnSber, btnEther);
    divQiwi.style.display = "block";
    divSber.style.display = "none";
    divEther.style.display = "none";
}

function showContainer2() {
    setBorder(btnSber, btnQiwi, btnEther);
    divQiwi.style.display = "none";
    divSber.style.display = "block";
    divEther.style.display = "none";
}

function showContainer3() {
    setBorder(btnEther , btnSber, btnQiwi);
    divQiwi.style.display = "none";
    divSber.style.display = "none";
    divEther.style.display = "block";
}


function copyTxtE() {
    var copyText = document.getElementById("copy-txt-e");
    copyText.select();
    navigator.clipboard.writeText("0x2aBbC8B87a5DF4721F2445889a843DFeDf78aDf7");
}
function copyTxtS() {
    var copyText = document.getElementById("copy-txt-s");
    copyText.select();
    navigator.clipboard.writeText(copyText.value);
}

function copyTxtEM() {
    navigator.clipboard.writeText("0x2aBbC8B87a5DF4721F2445889a843DFeDf78aDf7");
    alert("Реквизиты скопированы!\nСпасибо за вашу поддержку.");
}
function copyTxtSM() {
    navigator.clipboard.writeText("4276380243534927");
    alert("Реквизиты скопированы!\nСпасибо за вашу поддержку.");
}
