const btn = document.querySelector("#block");
const headeritem = document.querySelectorAll("tr.table_inner");
const count = headeritem.length;

window.addEventListener("load", windowload)

function windowload(){
    console.log(count);
    if (count <= 10){
        btn.classList.toggle("block");
    }
}

