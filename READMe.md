# Loja API


API gerenciador de loja para ser consumido pelo projeto [Loja Frontend](https://git.gft.com/aolj/loja-frontend). Cadastra as informações dos produtos incluindo fotos.
Permite aos usuarios cadastrados realizar pedidos de compra.

### Funcionalidades

- [x] Registro e autenticação de usuarios
- [x] Limita acesso a operações de risco para usuarios do nivel Admin
- [x] Cadastro de produtos.
- [x] Upload de imagens dos produtos.
- [x] Validação e gestão do estoque disponivel ao momento de realizar uma compra.
- [x] Realizar e listar ordens de compra.

Para fins de teste, o banco de dados será populado com algumas entidades no momento de execução da aplicação.


## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

* Você instalou `Java 11`


## ☕ Usando PartidoPolitico API

Na pasta principal rodar o seguinte comando

```
./mvnw spring-boot:run
```

Para fins de teste, utilize um dos usuarios padrão para gerar um JWToken:

**ADMIN**:
* CPF: 48067379092
* Senha: Gft2022

**USER**:
* CPF: 12281159078
* Senha: Gft2022

Testar a API usando Postman (Postman JSON Link)

```
https://www.getpostman.com/collections/3c2fe607a85d585a77a7
```

---
**IMPORTANTE**

É preciso que o projeto rode na porta 8081 do seu computador. Caso contrario, varias funcionalidades podem se ver afetadas.

---

## 📝 Créditos

Esse projeto foi desenvolvido por [Alex López](https://github.com/lop19029) como parte do programa Starter da  [GFT](https://www.gft.com/br/pt).
