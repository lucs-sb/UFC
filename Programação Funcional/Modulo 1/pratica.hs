module Pratica where

dobro :: Int -> Int
dobro x = x + x

quadruplo :: Int -> Int
quadruplo x = dobro (dobro x)

--Lista 3

areaTriangulo :: Float -> Float -> Float -> Float
areaTriangulo a b c = sqrt (s * (s-a) * (s-b) * (s-c))
    where
        s = (a + b + c)/2

--ehTriangulo :: Int -> Int -> Int -> Bool
--ehTriangulo a b c = if a + b > c && a + c > b && b + c > a then True else False

ehTriangulo :: Int -> Int -> Int -> Bool
ehTriangulo a b c 
    | a + b > c && a + c > b && b + c > a = True 
    | otherwise = False

precoRetrato :: Int -> String -> Double
precoRetrato pessoas dia
    | dia == "Domingo" || dia == "Sabado" = custoRetrato pessoas * 0.2 + custoRetrato pessoas
    | dia == "Segunda" = custoRetrato pessoas
    | dia == "Terca" = custoRetrato pessoas
    | dia == "Quarta"  = custoRetrato pessoas
    | dia == "Quinta"  = custoRetrato pessoas
    | otherwise = custoRetrato pessoas

custoRetrato :: Int -> Double
custoRetrato x 
    | x == 1 = 100.00
    | x == 2 = 130.00
    | x == 3 = 150.00
    | x == 4 = 165.00
    | x == 5 = 175.00
    | x == 6 = 180.00
    | otherwise = 185.00

-- Lista 4

todosIguais :: Int -> Int -> Int -> Bool
todosIguais a b c = (a == b) && (b == c)

todosDiferentes :: Int -> Int -> Int -> Bool
todosDiferentes a b c = (a /= b) && (a /= c) && (b /= c)

quantosIguais :: Int -> Int -> Int -> Int
quantosIguais a b c 
    | todosIguais a b c = 3
    | todosDiferentes a b c = 0
    | otherwise = 2

venda :: Int -> Int
venda x 
    | x == 0 = 3000
    | x == 1 = 150
    | x == 2 = 100
    | x == 3 = 1300
    | otherwise = 900

qtdVendas :: Int -> Int
qtdVendas n 
    | n > 0 = venda n + qtdVendas (n - 1)
    | otherwise = venda n 

--Lista 6
type Pessoa = (String, String, String)

lucas :: Pessoa
lucas = ("Lucas Soares", "Centro", "9999-9999")

nome :: Pessoa -> String
nome (x,_,_) = x

endereco :: Pessoa -> String
endereco (_,y,_) = y

fone :: Pessoa -> String
fone (_,_,z) = z

somaQuadrados :: Int -> Int -> Int
somaQuadrados n m = quadN + quadM
    where
        quadN = n * n
        quadM = m * m

somaQuadrados2 :: Int -> Int -> Int
somaQuadrados2 n m = quadrado n + quadrado m
    where
        quadrado x = x * x

emprestimo :: IO()
emprestimo = do
    putStrLn "Qual o salário bruto ?"
    salario <- readLn ::IO Float
    putStrLn "Qual o valor do empréstimo ?"
    valor <- readLn ::IO Float
    infoEmprestimo salario valor

infoEmprestimo :: Float -> Float -> IO()
infoEmprestimo salario valor
    | prestacao > prestacaoMax = 
        do
            putStrLn "Análise de crédito------------------------------------------"
            putStrLn "Salário bruto: "
            print salario 
            putStrLn "Valor da prestação: "
            print prestacao 
            putStrLn "O empréstimo não pode ser concedido"
    | otherwise = 
        do
            putStrLn "Análise de crédito------------------------------------------"
            print salario 
            putStrLn "Valor da prestação: "
            print prestacao
            putStrLn "O empréstimo pode ser concedido"
    where
        prestacao = calculaPrestacao valor
        prestacaoMax = calcula30Salario salario

calcula30Salario :: Float -> Float
calcula30Salario salario = salario * 0.3

calculaPrestacao :: Float -> Float
calculaPrestacao valor = valor / 60