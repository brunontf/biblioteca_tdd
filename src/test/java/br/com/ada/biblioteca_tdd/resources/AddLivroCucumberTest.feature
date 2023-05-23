# language: pt
@LivroCucumber
Funcionalidade: Adicionar livro
  O usuario deve conseguir adicionar livros à base de dados inserindo, ao menos, as informações obrigatórias.

  Cenario: Usuario adiciona um livro
    Quando o usuario adicionar um livro de titulo "<titulo>"
    E resumo "<resumo>"
    E preco <preco>
    E numero de paginas <numero_de_paginas>
    E isbn "<isbn>"
    E sumario "<sumario>"
    E data de publicacao "<dataPublicacao>"
    Entao deve ser capaz de adicionar um livro

    Exemplos:
     |  titulo  |  resumo   |  preco  |  numero_de_paginas  |  isbn   |  sumario     |  dataPublicacao |
     |  harryP  |resumoLivro|   500   |         110         |isbn-123 | sumarioTeste |   2023-06-06    |

    #  testando a retirada de parametros opcionais
     |  harryP  |resumoLivro|   500   |         110         |isbn-123 |              |   2023-06-06    |
     |  harryP  |resumoLivro|   500   |         110         |isbn-123 | sumarioTeste |                 |

    #  testando a retirada de atributos obrigatorios
     |          |resumoLivro|   500   |         110         |isbn-123 | sumarioTeste |   2023-06-06    |
     |  harryP  |           |   500   |         110         |isbn-123 | sumarioTeste |   2023-06-06    |
     |  harryP  |resumoLivro|         |         110         |isbn-123 | sumarioTeste |   2023-06-06    |
     |  harryP  |resumoLivro|   500   |                     |isbn-123 | sumarioTeste |   2023-06-06    |
     |  harryP  |resumoLivro|   500   |         110         |         | sumarioTeste |   2023-06-06    |

    #  testando data presente ou passada
     |  harryP  |resumoLivro|   500   |         110         |isbn-123 | sumarioTeste |      teste      |
     |  harryP  |resumoLivro|   500   |         110         |isbn-123 | sumarioTeste |   2023-01-01    |
     |  harryP  |resumoLivro|   500   |         110         |isbn-123 | sumarioTeste |   2023-05-18    |

    #  testando preço com valor mínimo de 20
     |  harryP  |resumoLivro|  19,50  |         110         |isbn-123 | sumarioTeste |   2023-06-06    |
     |  harryP  |resumoLivro|   20    |         110         |isbn-123 | sumarioTeste |   2023-06-06    |

    #  testando número de página mínimo de 100
     |  harryP  |resumoLivro|   500   |         99          |isbn-123 | sumarioTeste |   2023-06-06    |
     |  harryP  |resumoLivro|   500   |         100         |isbn-123 | sumarioTeste |   2023-06-06    |
     |  harryP  |resumoLivro|   500   |         101         |isbn-123 | sumarioTeste |   2023-06-06    |
     
    #  testando resumo com maximo de 500 caracteres
     |  harryP  |resumoLivro|   50000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000   |         110         |isbn-123 | sumarioTeste |   2023-06-06    |
     |  harryP  |resumoLivro|   500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001   |         110         |isbn-123 | sumarioTeste |   2023-06-06    |
