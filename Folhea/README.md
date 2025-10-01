# Folhea - Rede Social sobre Livros

Este repositório contém o código-fonte do projeto desenvolvido para a disciplina [Nome da Disciplina]. O projeto, chamado **Folhea**, é uma aplicação web para uma rede social focada em leitores e amantes de livros.

---

## Tema Escolhido

**Folhea** – Rede Social para leitores (Referências: WattPad, Skoob, Goodreads)

- Autenticação: Deverá ser possível criar um usuário e autenticar na aplicação. Todas as operações da aplicação serão vinculadas ao usuário autenticado;
- O usuário pode criar seu perfil, adicionar seu livros, postar trechos comentados, tags favoritas e comentar em posts de outros usuários;
- Cada post pode também possuir uma ou mais tags, definidas pelo usuário;
- O usuário deverá conseguir adicionar avaliações, assim como ser redirecionado para a página de leitura do livro e postar seus próprios trabalhos no formato da aplicação;
- O perfil deverá conter estatísticas de cada usuário, públicas ou não, contendo seus livros, tempo de leitura, maiores tempos de inatividade, quantidade e qualidade de avaliações, e gêneros/autores mais lidos;
- O usuários contára com um sistema de salvamento/favoritos (Biblioteca), junto de abas de busca por tags, títulos, autores e gêneros, e um sistema de página estilo For You.

---

## Equipe

- Any Gabriely Silva Moraes – SP3118801 - user: any-g  
- Sol Aniel Martins – SP3118673 - user: Sol-Aniel  
- Laura Jardim Santos  – SP3116697- user: laura1050santos  
- Melissa Cardoso Schiavone – SP311600x - user: Moonghosty
- Ana Marcélia - SP3116069 - user: ryghiuhswf

---

## Sobre o Projeto

- Desenvolvido em Java com Spring Boot, utilizando banco de dados SQLite.
- Arquitetura composta por backend (API RESTful) e frontend SPA (React/Angular/etc).
- O projeto foi construído em equipe, com contribuição ativa de todos os membros no GitHub.
- O projeto contempla autenticação de usuários, gerenciamento de perfis, postagem de resenhas, sistema de amizades e interação entre usuários.

---

## Funcionalidades Principais

- Criação e autenticação de usuários.
- Cadastro e edição de perfis pessoais.
- Publicação de resenhas e avaliações de livros.
- Sistema de conexões/amizades entre usuários.
- Feed de atualizações e interações sociais.
- Sistema de tags para categorizar livros e resenhas.
- Página de perfil com histórico de leituras e interações.
- Segurança com páginas protegidas, redirecionamento para login se necessário.

---

## Entregas

- **3º Bimestre:** Implementação básica com criação e autenticação de usuários e acesso a página protegida.  
- **4º Bimestre:** Aplicação completa com todas as funcionalidades descritas e apresentação em vídeo.

---
## Protótipo Figma
https://github.com/Sol-Aniel/Folhea/tree/main

## Instruções para Configuração e Execução

1. Clone este repositório:  
   ```bash
   git clone [link-do-repositorio]

Configure o ambiente Java e Spring Boot.

Configure o banco SQLite conforme instruções específicas (ver seção de configuração).

Execute a aplicação backend e frontend conforme o guia fornecido.

Utilize as tags Git para identificar as entregas:

bash
Copiar
Editar
git tag -a entrega_3bim -m "Entrega do terceiro bimestre"
git push origin --tags

git tag -a entrega_4bim -m "Entrega do quarto bimestre"
git push origin --tags
