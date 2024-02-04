function contar() {
  const texto = document.getElementById("texto").value;

  const letrasNumeros = texto.replace(/[^a-zA-Z0-9\s]/g, "");

  const textoSemEspacos = texto.replace(/\s/g, "");

  document.getElementById("contadorLetrasNumeros").innerText = textoSemEspacos.length;

  const palavras = letrasNumeros.split(/\s+/).filter(function (word) {
    return word.length > 0;
  });

  document.getElementById("contadorPalavras").innerText = palavras.length;
}
