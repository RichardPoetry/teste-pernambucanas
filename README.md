# Vestimentas Eden API

API REST desenvolvida em **Java 21** utilizando **Spring Boot** para gerenciamento de pedidos de uma loja de vestimentas.

O projeto foi desenvolvido com foco em **boas práticas de desenvolvimento**, aplicando conceitos de **Clean Code**, **SOLID**, **DTO/VO**, **MapStruct**, **Spring Data JPA** e testes unitários.

O sistema contempla o fluxo completo de criação e gerenciamento de pedidos, incluindo:

- Cadastro e consulta de pedidos;
- Consulta de produtos;
- Pagamento de pedidos;
- Cancelamento de pedidos;
- Cálculo automático de subtotal, desconto, frete e total;
- Programa de fidelidade baseado em pontos;
- Validação das regras de negócio;
- Tratamento centralizado de exceções.

---

# Tecnologias Utilizadas

| Tecnologia      | Versão |
|-----------------|---------|
| Java            | 21 |
| Spring Boot     | 3.x |
| Spring Web      | ✓ |
| Spring Data JPA | ✓ |
| Hibernate       | ✓ |
| PostgreSQL      | 17 |
| Maven           | ✓ |
| Lombok          | ✓ |
| MapStruct       | ✓ |
| JUnit 5         | ✓ |
| Mockito         | ✓ |
| Docker-compose  | ✓ |

---

# Arquitetura

O projeto segue uma arquitetura em camadas, separando responsabilidades e facilitando manutenção, testes e evolução da aplicação.

```
Cliente
   │
HTTP Request
   │
Controller
   │
Facade
   │
Service
   │
Repository
   │
PostgreSQL
```

As conversões entre objetos são realizadas pelos **Mappers (MapStruct)**.

```
Entity  ←→  VO  ←→  Response
           ↑
        Request
```

---

# Organização do Projeto

```
src
└── main
    ├── controller
    ├── facade
    ├── service
    ├── persistence
    │   ├── entity
    │   └── repository
    ├── model
    │   ├── mapper
    │   ├── request
    │   ├── response
    │   └── vo
    ├── exception
    └── config
```

---

# Responsabilidade de cada camada

## Controller

Responsável por expor os endpoints REST.

Nesta camada são recebidas as requisições HTTP, realizada a validação inicial dos parâmetros e encaminhamento da chamada para a Facade.

**Não existe regra de negócio nesta camada.**

---

## Facade

A camada de Facade atua como um orquestrador entre os Controllers e os Services.

Sua responsabilidade é coordenar chamadas para diferentes serviços quando necessário, mantendo o Controller simples e desacoplado da lógica da aplicação.

---

## Service

É a principal camada da aplicação.

Toda a regra de negócio encontra-se concentrada nesta camada.

Entre suas responsabilidades estão:

- validação das regras do pedido;
- cálculo de subtotal;
- cálculo de desconto;
- cálculo do frete;
- cálculo do total;
- cálculo de pontos de fidelidade;
- alteração de status do pedido;
- atualização de estoque;
- validações de cancelamento.

Dessa forma, Controllers permanecem responsáveis apenas pelo protocolo HTTP.

---

## Repository

Camada responsável pelo acesso aos dados utilizando Spring Data JPA.

Não contém regras de negócio, apenas operações de persistência.

Exemplos:

- buscar pedidos;
- salvar pedidos;
- buscar clientes;
- buscar produtos.

---

## Entity

Representam as tabelas existentes no banco de dados.

São utilizadas exclusivamente pela camada de persistência.

As Entities possuem relacionamento entre si através do Hibernate.

Exemplo:

```
Pedido
      │
      ├── Cliente
      │
      └── Itens
              │
              └── Produto
```

---

## VO (Value Object)

Os VOs representam objetos utilizados internamente pela aplicação.

Eles evitam que as Entities sejam expostas para outras camadas, desacoplando a regra de negócio da persistência.

Exemplos:

- PedidoVO
- ProdutoVO
- ClienteVO

---

## Request

Objetos responsáveis por representar os dados recebidos pela API.

São utilizados exclusivamente na entrada das requisições.

Também concentram as validações utilizando Bean Validation.

Exemplo:

- PedidoRequest
- ItemRequest

---

## Response

Objetos responsáveis pelo retorno da API.

Dessa forma a aplicação nunca expõe diretamente suas Entities.

Exemplos:

- PedidoResponse
- ProdutoResponse

---

## Mapper

O projeto utiliza **MapStruct** para realizar as conversões entre:

- Entity → VO
- VO → Entity
- VO → Response

Essa abordagem reduz código repetitivo e facilita manutenção.

---

## Exception

Todas as exceções de negócio são centralizadas nesta camada.

Exemplos:

- Pedido não encontrado;
- Cliente não encontrado;
- Produto não encontrado;
- Estoque insuficiente;
- Pedido não pode ser cancelado;
- Cupom inválido.

As exceções são tratadas por um **GlobalExceptionHandler**, responsável por padronizar os retornos HTTP.

# Fluxo da Aplicação

## Criar Pedido

```
Cliente
    │
    ▼
POST /pedido
    │
    ▼
Controller
    │
    ▼
Facade
    │
    ▼
ClienteService
    │
    ├── valida cliente
    │
ProdutoService
    │
    ├── valida produtos
    ├── valida estoque
    │
PedidoService
    │
    ├── calcula subtotal
    ├── calcula frete
    ├── calcula desconto
    ├── calcula total
    ├── cria entidade
    ├── atualiza estoque
    └── salva pedido
    │
    ▼
Repository
    │
    ▼
PostgreSQL
```

---

## Consultar Pedido

```
GET /pedido/{id}
        │
        ▼
Controller
        │
        ▼
Facade
        │
        ▼
PedidoService
        │
        ▼
Repository
        │
        ▼

```

---

## Pagar Pedido

```
PUT /pedido/{id}/pagar

        │
        ▼

Busca Pedido

        │

Status = PAGO

        │

Calcula Pontos

        │

Atualiza Pedido

        │

Retorna sucesso
```

---

## Cancelar Pedido

```
PUT /pedido/{id}/cancelar

        │

Busca Pedido

        │

Status permitido?

        │

Sim
 │               Não
 ▼                 ▼

Cancela      Lança exceção
Pedido
```

---

## Princípios adotados

Durante o desenvolvimento foram priorizados:

- Separação de responsabilidades;
- Desacoplamento entre camadas;
- Utilização de DTOs;
- Utilização de VO;
- Conversões centralizadas via MapStruct;
- Tratamento centralizado de exceções;
- Testes unitários dos Services e Mappers;
- Regras de negócio concentradas na camada Service;
- Persistência desacoplada utilizando Spring Data JPA.

# Endpoints

A API disponibiliza cinco endpoints REST.

| Método | Endpoint | Descrição |
|---------|-----------|------------|
| POST | `/pedido` | Cria um novo pedido |
| GET | `/pedido/{id}` | Consulta um pedido pelo identificador |
| PUT | `/pedido/{id}/pagar` | Realiza o pagamento do pedido |
| PUT | `/pedido/{id}/cancelar` | Cancela um pedido |
| GET | `/produto` | Lista todos os produtos |

---

# POST /pedido

Cria um novo pedido.

## Request

```json
{
  "clientId": "CLI001",
  "itens": [
    {
      "produtoId": "P1",
      "quantidade": 2
    },
    {
      "produtoId": "P2",
      "quantidade": 1
    }
  ],
  "cupom": "DESC10"
}
```

## Response

```json
{
  "id": "4fd7f8e7-bfc9-4d9b-8cb5-a8c4fae9c9f5",
  "nomeCliente": "Richard",
  "itens": [
    {
      "id": "ITEM001",
      "produtoId": "P1",
      "quantidade": 2,
      "precoUnitario": 50.00
    },
    {
      "id": "ITEM002",
      "produtoId": "P2",
      "quantidade": 1,
      "precoUnitario": 80.00
    }
  ],
  "subtotal": 180.00,
  "desconto": 18.00,
  "frete": 20.00,
  "total": 182.00,
  "pontosGerados": null,
  "cupom": "DESC10",
  "status": "CRIADO"
}
```

### Possíveis erros

| HTTP | Motivo |
|------|---------|
|400|Cupom inválido|
|400|Quantidade inválida|
|400|Estoque insuficiente|
|404|Cliente não encontrado|
|404|Produto não encontrado|

---

# GET /pedido/{id}

Consulta um pedido.

## Exemplo

```
GET /pedido/2fe8dc14-46f3-43ca-8fc3-becc3a266c55
```

## Response

```json
{
  "id": "2fe8dc14-46f3-43ca-8fc3-becc3a266c55",
  "nomeCliente": "Richard",
  "subtotal": 180.00,
  "desconto": 18.00,
  "frete": 20.00,
  "total": 182.00,
  "pontosGerados": null,
  "cupom": "DESC10",
  "status": "CRIADO",
  "itens": [
    {
      "produtoId": "P1",
      "quantidade": 2,
      "precoUnitario": 50.00
    }
  ]
}
```

### Possíveis erros

|HTTP|Descrição|
|----|---------|
|404|Pedido não encontrado|

---

# PUT /pedido/{id}/pagar

Realiza o pagamento do pedido.

Após o pagamento:

- altera o status para **PAGO**
- calcula os pontos de fidelidade
- persiste as alterações

## Response

```
204 No Content
```

---

# PUT /pedido/{id}/cancelar

Cancela um pedido.

Somente pedidos nos estados:

- CRIADO
- PAGO

podem ser cancelados.

### Possíveis erros

|HTTP|Descrição|
|----|---------|
|400|Pedido já enviado|
|400|Pedido entregue|
|404|Pedido não encontrado|

---

# GET /produto

Lista todos os produtos cadastrados.

## Response

```json
[
    {
        "nome":"Camiseta Básica",
        "categoria":"Camiseta",
        "preco":50.00,
        "estoque":"10"
    },
    {
        "nome":"Calça Jeans",
        "categoria":"Calça",
        "preco":120.00,
        "estoque":"5"
    }
]
```

# Como executar a aplicação

## Pré-requisitos

- Java 21
- Maven 3.9+
- PostgreSQL 17
- Docker Compose


---

## Clonar o projeto

```bash
git clone https://github.com/SEU_USUARIO/vestimentas-eden.git
```

```bash
cd vestimentas-eden
```

## Construindo a aplicação

```bash
cd docker
```
dentro da pasta /docker rode o comando para construir a aplicação:
``` bash
docker compose build
```
após a construção, suba o docker com:
```bash
docker compose up -d
```

---

A aplicação ficará disponível em

```
http://localhost:8080
```

e o banco
```
localhost:5432
```

---

# Executando os testes

Todos os testes podem ser executados com:

```bash
mvn test
```

Os testes contemplam:

- Mappers
- Services
- Regras de negócio
- Conversões
- Cálculos
- Casos de sucesso
- Casos de exceção

---

# Estrutura do Banco

O projeto utiliza PostgreSQL com Hibernate.

As principais entidades são:

```
Cliente
```

```
Produto
```

```
Pedido
```

```
ItemPedido
```

Relacionamentos:

```
Cliente 1 ------ N Pedido

Pedido 1 ------- N ItemPedido

Produto 1 ------ N ItemPedido
```

# Casos de Uso

A seguir são apresentados os cenários utilizados para validar as regras de negócio.

---

# Cobertura dos Testes

O projeto possui testes unitários para:

- ClienteMapper
- ProdutoMapper
- PedidoMapper
- ResponseMapper
- PedidoService
- Casos de sucesso
- Casos de erro
- Regras de cálculo
- Conversões
- Pagamento
- Cancelamento
- Consulta
- Criação de pedidos

Todos utilizando **JUnit 5** e **Mockito**.

---

## Perguntas sobre Decisões Técnicas

### 1. Como você organizou as camadas e por quê? Onde vive a regra de negócio?
**Resposta :**  
organizei minha camads em um fluxo que se organiza em: controller -> facade(há depender do caso) -> service -> repository.
meu controller fica responsável por receber e validar a request
facade orquestra as chamadas do service.
service fica concentrado a regra de negócio e a camada que faz suas eventuais operações no repository.
---

### 2. Onde você aplicou (ou deixou de aplicar) princípios de SOLID, e qual foi o trade-off?
**Resposta:**  
Para manter uma uma boa padronização do projeto, apliquei alguns principios como:

**SRP**:Cada classe possui apenas uma responsabilidade.

**OCP**: As regras de cálculo foram escritas para permitir inclusão de novas regras sem alterar significativamente a estrutura existente.

**DIP**:As dependências são injetadas pelo Spring através de Injeção de Dependência.

---

### 3. Como você isolou o cálculo do pedido para torná-lo testável?
**Resposta:**  
Todos os cálculos foram isolados em métodos independentes dentro da camada de Service, assim futuras alterações não
vão interferir diretamente na lógica de cada calculo. Tornando assim fácil para manutenção, testes isolados
e futuras evoluções.
---

### 4. Por que escolheu SQL para o núcleo e NoSQL para o histórico/extrato? O que mudaria em escala?
**Resposta:**  
SQL foi escolhido para o núcleo por sua consistência e suporte a transações, onde suas relações em tabelas assegura maior a evolução de futura entidade e tabelas no banco  
Embora não foi implementado NoSQL, foi adotado para histórico/extrato devido ao volume de dados e necessidade de consultas rápidas e flexíveis.
---

### 5. O consumidor pode receber o evento “pedido-pago” duplicado. Como seu código evita creditar os pontos duas vezes (idempotência)?
**Resposta:**  
Implementei idempotência através de **chaves únicas** e **controle transacional**.  
Quando recebemos a confirmação do pagamento, verificamos o status, se for igual a pago, geramos o ponto e atualizamos o status.
---

### 6. Se tivesse mais tempo, o que você faria diferente ou adicionaria?
**Resposta:**  
Com mais tempo, eu adicionaria:
- Monitoramento e métricas mais detalhadas.
- Melhor documentação e exemplos de uso.
- criação do swagger com exemplos de erros e sucesso.
- implementação de de serviços para atualização dos dois próximos status, a fim de garantir o resto do fluxo do status corretamente.
- integração ao um broker.
- criação de um batch para rodar de tempo em tempo e verificar os pedidos criado que não foram pagos, cancelar para os produtos voltar ao estoque

### OBSERVAÇÕES

