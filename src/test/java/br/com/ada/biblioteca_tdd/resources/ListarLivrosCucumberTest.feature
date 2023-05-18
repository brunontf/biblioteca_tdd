# language: pt
@LivroCucumber
Funcionalidade: Listar livros
  O programa deve fornecer todos os livros cadastrados no banco de dados.
   
  Cenario: O usuario chama GET /livros
    Quando o usuario chama a funçao listarLivros
    Entao o usuario deve obter status code 200
    # E o usuario deve obter a lista de "livros"


   