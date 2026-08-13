
# Kickstock ERP - Backend

Gerenciador de estoque e lotes que relaciona fornecedores, empresas e contratos entre elas. O foco do sistema é agilizar a transação de informação e na praticidade entre venda e compra de produtos.

As empresas podem criar contratos com fornecedores e, a partir deles, solicitar a compra de lotes de produtos. As empresas podem dar baixa no estoque 



## Tecnologias utilizadas e Ferramentas

**Servidor:** Java 17, Spring Boot v4.1.0, PostgreSQL, Hibernate, Maven.  

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
