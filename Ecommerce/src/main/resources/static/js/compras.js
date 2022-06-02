const verCompras = document.querySelector("[data-comprar]");
let myArray = verCompras.href.split("/",6);
const idUsuario = myArray[4];

const numerocompras = document.querySelector("[data-cantidad]");

const cantidadProducto = () => fetch(`http://localhost:8080/cantidad/${idUsuario}`).then(resp => resp.json());

cantidadProducto().then( (dato) => {
    numerocompras.innerHTML = dato;
});