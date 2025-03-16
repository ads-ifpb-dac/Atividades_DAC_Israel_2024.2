CREATE TABLE ingresso (
    id SERIAL PRIMARY KEY,
    evento_id INT NOT NULL,
    usuario_id INT NOT NULL,
    utilizado BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_ingresso_evento FOREIGN KEY (evento_id) REFERENCES evento(id),
    CONSTRAINT fk_ingresso_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);