// @author Johnathan Steven Salamanca Lancheros
// Se obtiene cada elemento que tenga un atributo href
var href = $("[href]");

// Así se obtiene el host.
var host = document.location.host;

console.log(host);

var SendInfo= [];
var urlsExternas = [];

// Así se obtiene cada uno de los href

for(i = 0; i < href.length; i++){
  var nTagname = href.eq(i).prop("tagName");
  var nLink = href.eq(i).prop("href");
  var nAccion = "href";

  if(nLink.includes(document.location.host) === false){

    // Se crea un objeto con los 3 atributos necesarios
    var urlExterna = {
      tagName: nTagname,
      link: nLink,
      action: nAccion
    }
    urlsExternas.push(urlExterna);
  }
}
console.log("Los href encontrados son " + href.eq(0).prop("tagName"));
console.log(href);

// Se obtiene cada elemento que tenga un atributo src
var src = $("[src]");

// Así se obtiene cada uno de los src

for(i = 0; i < src.length; i++){
  var nTagname = src.eq(i).prop("tagName");
  var nLink = src.eq(i).prop("src");
  var nAccion = "src";

  if((nLink.startsWith("http") || nLink.includes("www.")) && (nLink.includes(document.location.host) == false)){

    var urlExterna = {

      tagName: nTagname,
      link: nLink,
      action: nAccion

    }
    urlsExternas.push(urlExterna);
  }
}

// Se obtiene cada elemento que tenga un atributo action
var action = $("[action]");

// Así se obtiene cada uno de los src

for(i = 0; i < action.length; i++){
  var element = action.eq(i);
  var nTagname = action.eq(i).prop("tagName");
  var nLink = action.eq(i).prop('attributes').getNamedItem("action").value;

  var nAccion = "action";

  if((nLink.startsWith("http") || nLink.includes("www.")) && (nLink.includes(document.location.host) == false)){

    var urlExterna = {

      tagName: nTagname,
      link: nLink,
      action: nAccion

    }
    urlsExternas.push(urlExterna);
  }
}

var reporte = {
  urlOrigen: host,
  urlsDestino: urlsExternas
}

console.log(JSON.stringify(reporte))

// Envía el reporte generado al Servidor

$.ajax({
  type: 'post',
  url: 'https://tesisis.herokuapp.com/crear',
  data: JSON.stringify(reporte),
  contentType: "application/json; charset=utf-8",
  traditional: true,
  success: function (data) {
    console.log(JSON.stringify(data));
    if(data.respuesta == "Mal"){
      alert("Se encontró un contenido sospechoso. Por su seguridad, no diligencie su información personal en este sitio.");
    }
  }
});
