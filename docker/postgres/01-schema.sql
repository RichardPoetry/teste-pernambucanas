CREATE TABLE produto (

    id VARCHAR(36) PRIMARY KEY,

    nome VARCHAR(255) NOT NULL,

    categoria VARCHAR(100) NOT NULL,

    preco NUMERIC(10,2) NOT NULL,

    estoque INTEGER NOT NULL
);

CREATE TABLE cliente (

    id VARCHAR(255) PRIMARY KEY,

    nome VARCHAR(255) NOT NULL,

    tipo VARCHAR(20) NOT NULL
);

CREATE TABLE pedido (

    id VARCHAR(255) PRIMARY KEY,

    cliente_id VARCHAR(255) NOT NULL,

    subtotal NUMERIC(10,2) NOT NULL,

    desconto NUMERIC(10,2) NOT NULL,

    frete NUMERIC(10,2) NOT NULL,

    total NUMERIC(10,2) NOT NULL,

    pontos_gerados INTEGER,

    cupom VARCHAR(30),

    status VARCHAR(30),

    CONSTRAINT fk_pedido_cliente
        FOREIGN KEY (cliente_id)
        REFERENCES cliente(id)
);

CREATE TABLE item_pedido (

    id VARCHAR(255) PRIMARY KEY,

    pedido_id VARCHAR(255) NOT NULL,

    produto_id VARCHAR(255) NOT NULL,

    quantidade INTEGER NOT NULL,

    preco_unitario NUMERIC(10,2) NOT NULL,

    CONSTRAINT fk_item_pedido
        FOREIGN KEY (pedido_id)
        REFERENCES pedido(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_item_produto
        FOREIGN KEY (produto_id)
        REFERENCES produto(id)
);