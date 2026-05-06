function mostrarLista() {
    let div = document.querySelector("#miLista");
    let div2 = document.querySelector("#miLista2");
    if (div.style.display === "none" || div.style.display === "") {
        div.style.display = "block";
        div2.style.display = "block";
    } else {
        div.style.display = "none";
        div2.style.display = "none";
    }
}

function imprimirResultado(){
    window.print();
}