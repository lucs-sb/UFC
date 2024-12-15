
type Pessoa = String
type Livro = String
type BancodeDados = [(Pessoa, Livro)]

-- Base de dados para servir de teste para as funções
base :: BancodeDados
base = [("Francisco","Fogo e Sangue"),
    ("Victor","A Sociedade do Anel"), ("Victor","As duas Torres"),
    ("Simone","O Retorno do Rei"), ("Alana","A tormenta das espadas"),
    ("Luis","Fogo e Sangue")]

--Função que recebe uma Base e uma Pessoa e retorna a lista de livros que a Pessoa tem emprestado
livrosEmprestados :: BancodeDados -> Pessoa -> [Livro]
livrosEmprestados [] _ = []
livrosEmprestados ((inquilino,titulo) : resto) cliente
    |inquilino == cliente = titulo : livrosEmprestados resto cliente
    |otherwise = livrosEmprestados resto cliente


--Função que informa toda as pessoas que tomaram emprestado um determinado livro
listaPessoasPorLivro :: BancodeDados -> Livro -> [Pessoa]
listaPessoasPorLivro [] _ = []
listaPessoasPorLivro ((inquilino,titulo):resto) livro
    |titulo == livro = inquilino : listaPessoasPorLivro resto livro
    |otherwise = listaPessoasPorLivro resto livro


--Função que informa se um determinado livro está ou não emprestado
estaEmprestado :: BancodeDados -> Livro -> Bool
estaEmprestado [] _ = False
estaEmprestado ((inquilino,titulo):resto) livro
    |titulo == livro = True
    |otherwise = estaEmprestado resto livro


{- Função que informa a quantidade de livros que uma determinada pessoa
tomou emprestado -}
quantosLivros :: BancodeDados -> Pessoa -> Int
quantosLivros [] _ = 0
quantosLivros ((inquilino,titulo):resto) cliente
    |inquilino == cliente = 1 + quantosLivros resto cliente
    |otherwise = quantosLivros resto cliente


--OPERAÇÕES DE ATUALIZAÇÃO
--Função que atualiza a base da dados quando há um empréstimo
emprestimo :: BancodeDados -> Pessoa -> Livro -> BancodeDados
emprestimo banco cliente titulo = (cliente,titulo):banco

--Função que atualiza a base de dados quando há devolução
devolucao :: BancodeDados -> Pessoa -> Livro -> BancodeDados
devolucao [] cliente exemplar = error "Este livro não está emprestado"
devolucao ((inquilino,titulo):resto) cliente exemplar
    |cliente == inquilino && exemplar == titulo = resto
    |otherwise = (inquilino,titulo) : devolucao resto cliente exemplar
