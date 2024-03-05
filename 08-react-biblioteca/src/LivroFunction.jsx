import React, { useState } from "react";

function Capitulo({ capitulo }) {
  const [contentHidden, setContentHidden] = useState(true);

  return (
    <>
      <div style={{ maxWidth: "40rem", textAlign: "justify" }}>
        <h2>
          Capítulo {capitulo.numero} - {capitulo.nome}
        </h2>
        <button onClick={() => setContentHidden(!contentHidden)}>{contentHidden ? "Mostrar conteúdo" : "Esconder conteúdo"}</button>
        {!contentHidden && <p>{capitulo.content}</p>}
      </div>
    </>
  );
}

const livros = [
  {
    titulo: "Tecnologia da informação",
    capitulos: [
      { numero: "1", nome: "Introdução", content: "Conteúdo da introdução..." },
      { numero: "2", nome: "Desenvolvimento", content: "Conteúdo do desenvolvimento..." },
      { numero: "3", nome: "Conclusão", content: "Conteúdo da conclusão..." },
    ],
  },
  {
    titulo: "Técnicas de desenvolvimento",
    capitulos: [
      { numero: "1", nome: "Syntax", content: "Arrays e objetos oribus eveniet velit molestias soluta tempo" },
      { numero: "2", nome: "Compiladores", content: "Compilação oribus eveniet velit molestias soluta tempo" },
      { numero: "3", nome: "Sistemas de dados", content: "Backend e frontend oribus eveniet velit molestias soluta tempo" },
    ],
  },
  {
    titulo: "Banco de dados",
    capitulos: [
      { numero: "1", nome: "Tipagem", content: "Varchar e seus malefícios  oribus eveniet velit molestias soluta tempo" },
      { numero: "2", nome: "Alteração de dados", content: "Update sem where  oribus eveniet velit molestias soluta tempo" },
      { numero: "3", nome: "Toma cuidado", content: "129301293021 rows affected  oribus eveniet velit molestias soluta tempo" },
    ],
  },
];

export default function Livro() {
  const [livroOpenStates, setLivroOpenStates] = useState(livros.map(() => false));

  const toggleLivro = (index) => {
    const newLivroOpenStates = [...livroOpenStates];
    newLivroOpenStates[index] = !newLivroOpenStates[index];
    setLivroOpenStates(newLivroOpenStates);
  };

  return (
    <>
      <section>
        <h1>LIVROS INTERESSANTES</h1>
        {livros.map((livro, index) => (
          <div key={index}>
            <h2 style={{ cursor: "pointer" }} onClick={() => toggleLivro(index)}>
              LIVRO - {livro.titulo}
            </h2>
            <button onClick={() => toggleLivro(index)}>{livroOpenStates[index] ? "Esconder capítulos" : "Mostrar capítulos"}</button>
            <section style={{ marginLeft: "4rem" }}>{livroOpenStates[index] && livro.capitulos.map((capitulo) => capitulo.numero != null && <Capitulo key={"capitulo-" + capitulo.numero} capitulo={capitulo} />)}</section>
          </div>
        ))}
      </section>
    </>
  );
}
