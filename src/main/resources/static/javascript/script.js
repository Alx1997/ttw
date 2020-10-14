let view = document.querySelector("#item_0");
const headersr = document.querySelector("#btn_next");
const headeritem = document.querySelectorAll("div.test_item");
const count = headeritem.length;
let i = 0;

window.addEventListener("load", windowload)

function windowload(){
    console.log(count);
view.classList.remove("test_item--hidden");
}

headersr.addEventListener("click", headerNextClick);


function headerNextClick(){
    i = i + 1;
    if(i == (count)){
    
          view = document.querySelector("#btn_hidden");
          console.log(view);
          view.classList.toggle("test_item--hidden");
          view.previousElementSibling.classList.toggle("test_item--hidden");

    }else{
        view.classList.toggle("test_item--hidden");
        view = view.nextElementSibling;
        view.classList.toggle("test_item--hidden");

    }


    
}
