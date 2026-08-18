
# 📦 Kickstock - ERP & Gestão de Estoque B2B

**Kickstock** é uma plataforma de gestão de estoque, lotes e relacionamentos B2B projetada para otimizar a comunicação e o fluxo de transações entre empresas e fornecedores.

O foco do sistema é trazer agilidade, rastreabilidade e praticidade para todo o ciclo de suprimentos — desde a formalização de contratos até a movimentação real de produtos. Através do Kickstock, as empresas podem estabelecer vínculos contratuais com fornecedores, realizar agendamentos e solicitações de reserva de lotes e manter um controle preciso de entrada e baixa de estoque de forma automatizada.

## Funcionalidades e Requisitos Funcionais:

### 🏢 A **Empresa** pode:

* **Autenticação**
    * Realizar cadastro na plataforma ✅
    * Efetuar login ✅

* **Gestão de Contratos e Compras**
    * Requisitar novos contratos ✅
    * Solicitar compra de lotes (ou agendar compras) ✅
    * Visualizar contratos (pendentes e aceitos) ✅

* **Controle de Estoque**
    * Dar entrada no estoque     
    * Dar baixa no estoque
    * Visualizar seus lotes 
    * Acompanhar histórico de movimentações (entradas e saídas)

---

### 🏭 O **Fornecedor** pode:

* **Gestão de Contratos e Pedidos**
    * Aceitar ou recusar contratos solicitados ✅
    * Aceitar ou recusar solicitações de compra de lotes ✅

* **Controle de Produtos**
    * Cadastrar novos lotes no estoque

## Tecnologias utilizadas e Ferramentas

**Servidor:** Java 17, Spring Boot v4.1.0, PostgreSQL, Hibernate, Maven, JUnit, Mockito.  

**Ferramentas:** Postman, Git, GitHub.


## Referência de API

### Empresa

#### Cadastro:

```http
  POST /v1/empresa/auth/register
```

| Parametro | Tipo     | 
| :-------- | :------- | 
| `nome_empresa` | `string` | 
| `email_empresa` | `string` |  
| `password_empresa` | `string` |  
| `cnpj_empresa` | `string` |

- retorno:

```
    "id": "e2975c56-60d6-46e4-baf8-ccbca9beae16",
    "nomeEmpresa": "Jonas Lanches",
    "email": "jonasLanches@gmail.com",
    "cnpj": "95.513.262/0001-67",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb25hc0xhbmNoZXNAZ21haWwuY29tIiwiaWF0IjoxNzg2NTc1NjY5LCJleHAiOjE3ODY2NjIwNjl9.DYAWBBDJxSxdQ4usKvAFk-jVnWP_CTBMiAMMikfRZTrZTZfD7d4jC5NWBpg8aDx6OIGRzdFkHXjuMvo7q2D9lw",
    "created_at": "2026-08-12T20:01:09.29380071"
```

#### Login:

```http
  POST /v1/empresa/auth/login
```

| Parametro | Tipo     | 
| :-------- | :------- | 
| `email_empresa` | `string` |  
| `password_empresa` | `string` |  


- retorno:

```
    "id": "e2975c56-60d6-46e4-baf8-ccbca9beae16",
    "nomeEmpresa": "Jonas Lanches",
    "email": "jonasLanches@gmail.com",
    "cnpj": "95.513.262/0001-67",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb25hc0xhbmNoZXNAZ21haWwuY29tIiwiaWF0IjoxNzg2NTc1NzgwLCJleHAiOjE3ODY2NjIxODB9.4UtZXcilgi8skef-4cAUw244Ky5h1w3dxnDH8xpiF0QMUgfLGlzZ7eA1fC73L9QVkzV4qwXEnsf8Msh2Uh0FuA",
    "created_at": "2026-08-12T20:01:09.293801"
```

#### Criar solicitação de Contrato:

```http
  POST /v1/empresa/requisicao_de_contrato
```

| Parametro | Tipo     | 
| :-------- | :------- | 
| `id_fornecedor` | `uuid` |  
| `data_encerramento` | `localdate` |  


- retorno:

```
  HttpStatus: 201 Created
```

#### Criar solicitação de compra de Lote:

```http
  POST /v1/empresa/solicitacao
```

| Parametro | Tipo     | 
| :-------- | :------- | 
| `id_fornecedor` | `uuid` |  
| `data_encerramento` | `localdate` |  


- retorno:

```
  HttpStatus: 201 Created
```

#### Buscar contratos da empresa:

```http
  POST /v1/empresa/contrato/{idEmpresa}
```

| Parametro/Path | Tipo     | 
|:---------------| :------- | 
| `idEmpresa`    | `uuid` |


- retorno:

```
    [
        {
          "id": "c920ad24-48b4-46ef-8a90-093fd307b42d",
          "data_encerramento": "2030-12-30",
          "status_contrato": "ACEITO",
          "codigo_contrato": "a502Ioz3gZ",
          "created_at": "2026-08-13T19:39:16.140461"
        }
    ]
    ...
```

#### Buscar lotes da empresa:

```http
  POST /v1/empresa/lotes/{idEmpresa}
```

| Parametro/Path | Tipo     | 
|:---------------| :------- | 
| `idEmpresa`    | `uuid` |


- retorno:

```
    [
        {
          "id": "c920ad24-48b4-46ef-8a90-093fd307b42d",
          "data_encerramento": "2030-12-30",
          "status_contrato": "ACEITO",
          "codigo_contrato": "a502Ioz3gZ",
          "created_at": "2026-08-13T19:39:16.140461"
        }
        ...
    ]
```

#### Solicitação de Lote

```http
  POST /v1/empresa/solicitacao
```

| Parametro          | Tipo         | 
|:-------------------|:-------------| 
| `id_empresa`       | `uuid`       |
| `id_fornecedor`    | `uuid`       |
| `tipo_solicitacao` | `string`     |
| `quantidade_lote`  | `integer`    |
| `idsLotes`         | `list[uuid]` |
| `data_compra`      | `localdate`  |


- retorno:

```
  HttpStatus: 201 Created
```
