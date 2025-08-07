CREATE TABLE autores (
                         nome VARCHAR(255) NOT NULL,
                         ano_nascimento INT NOT NULL,
                         ano_morte INT,
                         PRIMARY KEY (nome, ano_nascimento)
);

CREATE TABLE livros (
                        id BIGINT PRIMARY KEY,
                        titulo VARCHAR(255) NOT NULL
);

CREATE TABLE livro_autor (
                             livro_id BIGINT NOT NULL,
                             autor_nome VARCHAR(255) NOT NULL,
                             autor_ano_nascimento INT NOT NULL,
                             PRIMARY KEY (livro_id, autor_nome, autor_ano_nascimento),
                             CONSTRAINT fk_livro FOREIGN KEY(livro_id) REFERENCES livros(id) ON DELETE CASCADE,
                             CONSTRAINT fk_autor FOREIGN KEY(autor_nome, autor_ano_nascimento) REFERENCES autores(nome, ano_nascimento) ON DELETE CASCADE
);

CREATE TABLE livro_resumos (
                               livro_id BIGINT NOT NULL,
                               resumo TEXT NOT NULL,
                               CONSTRAINT fk_livro_resumo FOREIGN KEY(livro_id) REFERENCES livros(id) ON DELETE CASCADE
);

CREATE TABLE livro_idiomas (
                               livro_id BIGINT NOT NULL,
                               idioma VARCHAR(50) NOT NULL,
                               PRIMARY KEY (livro_id, idioma),
                               CONSTRAINT fk_livro_idioma FOREIGN KEY(livro_id) REFERENCES livros(id) ON DELETE CASCADE
);


CREATE INDEX idx_livro_autor_livro_id ON livro_autor(livro_id);
CREATE INDEX idx_livro_autor_autor_nome ON livro_autor(autor_nome);
CREATE INDEX idx_livro_resumos_livro_id ON livro_resumos(livro_id);