module ListasMais where

--lista com inteiros de 1 a 5
lista :: [Int]
lista = [x^2 | x <- [1 .. 5]]

-- função que recebe uma lista de inteiros e retorna um nova lista com os valores dobrados
dobraLista :: [Int]-> [Int]
dobraLista lista = [2*a | a <- lista]

--lista de tuplas de inteiros
listaTupla :: [(Int,Int)]
listaTupla = [(x,y) | x <- [1,2,3], y <- [4,5]]

-- função que transforma uma lista de listas em uma lista simples 
conc :: Num a => [[a]] -> [a]
conc xss = [x | xs <- xss, x <- xs]

-- função que recebe um inteiro positivo e retorna os divisores
divisores :: Int -> [Int]
divisores n = [x | x <- [1..n], mod n x == 0]

-- função que verifica se um inteiro positivo é primo 
primo :: Int -> Bool
primo n = divisores n == [1,n]

-- função que recebe um inteiro positivo e retorna a lista de todos os números primos positivos até o valor passado
listaPrimos :: Int -> [Int]
listaPrimos n = [x | x <- [1..n], primo x]

-- função que recebe um nome e retorna a tupla formada pelo nome e pelo tamanho no nome
criaTupla :: String -> (String,Int)
criaTupla s = (s,length s)

-- função que recebe uma lista de string e retorna uma lista de tuplas
criaListaTupla :: [String] -> [(String,Int)]
criaListaTupla [] = []
criaListaTupla (x:xs) = criaTupla x : criaListaTupla xs

-- mesma função acima usando compreensões
criaListaTuplaZF :: [String] -> [(String,Int)]
criaListaTuplaZF lista = 

-- mesma função acima usando mapeamento
criaListaTuplaMap :: [String] -> [(String,Int)]
criaListaTuplaMap = map criaTupla

-- mesma função acima usando mapeamento e função anônima sem função auxiliar externa
criaListaTuplaAnonima :: [String] -> [(String,Int)]
criaListaTuplaAnonima = map (\s -> (s,length s))

-- função que recebe uma lista de inteiros e retorne a lista dos quadrados dos elementos de lista
listaQuadrados :: [Int] -> [Int]
listaQuadrados = map (\x -> x*x)

-- função que recebe uma lista de inteiros e retorne a soma dos quadrados dos elementos 
somaListaQuadrados :: [Int] -> Int
somaListaQuadrados lista = sum (listaQuadrados lista)

-- função que recebe uma lista de inteiros e verifique se todos os elementos da lista são, ou não, positivos
pegaPositivos :: [Int] -> [Int]
pegaPositivos lista = [x | x <- lista, x > 0]

saoPositivos :: [Int] -> Bool
saoPositivos lista = lista == pegaPositivos lista

-----------------------------------------------------------------------------------
-----------------------------------------------------------------------------------
--funções da biblioteca
type Pessoa = String
type Livro = String
type BancodeDados = [(Pessoa, Livro)]

-- base de dados para testes
base :: BancodeDados
base = [("Francisco","Fogo e Sangue"),("Victor","A Sociedade do Anel"), 
    ("Victor","As duas Torres"), ("Simone","O Retorno do Rei"), 
    ("Alana","A tormenta das espadas"), ("Luis","Fogo e Sangue")]

--função original com recursão
--função que informa os livros que uma determinada pessoa tomou emprestado
livrosEmprestados :: BancodeDados-> Pessoa-> [Livro]
livrosEmprestados [] _ = []
livrosEmprestados ((inquilino,titulo) : resto) cliente
    |inquilino == cliente = titulo : livrosEmprestados resto cliente
    |otherwise = livrosEmprestados resto cliente

--função 'livrosEmprestados' usando compreensões
livrosEmprestadosZF :: BancodeDados-> Pessoa-> [Livro]
livrosEmprestadosZF base cliente = [titulo | (inquilino,titulo) <- base, inquilino == cliente]


--Função que informa todas as pessoas que tomaram emprestado um determinado livro
{- listaPessoasPorLivro :: BancodeDados -> Livro -> [Pessoa]
listaPessoasPorLivro [] _ = []
listaPessoasPorLivro ((inquilino,titulo):resto) livro
    |titulo == livro = inquilino : listaPessoasPorLivro resto livro
    |otherwise = listaPessoasPorLivro resto livro -}

listaPessoasPorLivro :: BancodeDados -> Livro -> [Pessoa]
listaPessoasPorLivro base livro = [inquilino | (inquilino,titulo) <- base, livro == titulo]


--Função que informa se um determinado livro está ou não emprestado
{- estaEmprestado :: BancodeDados -> Livro -> Bool
estaEmprestado [] _ = False
estaEmprestado ((inquilino,titulo):resto) livro
    |titulo == livro = True
    |otherwise = estaEmprestado resto livro -}

estaEmprestado :: BancodeDados -> Livro -> Bool
estaEmprestado base livro = not (null [titulo | (inquilino,titulo) <- base, titulo == livro])

-- Função que informa a quantidade de livros que uma determinada pessoa tomou emprestado
{- quantosLivros :: BancodeDados -> Pessoa -> Int
quantosLivros [] _ = 0
quantosLivros ((inquilino,titulo):resto) cliente
    |inquilino == cliente = 1 + quantosLivros resto cliente
    |otherwise = quantosLivros resto cliente -}

quantosLivros :: BancodeDados -> Pessoa -> Int
quantosLivros base cliente = length (livrosEmprestadosZF base cliente)

