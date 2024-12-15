module Aula6 where

type Pessoa = (String, String, String)
simone :: Pessoa
simone = ("Simone Santos",
    "Avenida Professora Machadinha Lima, S/N, Príncipe Imperial, Crateús-CE", "9999-9999")
antonio :: Pessoa
antonio = ("Antonio Barreto",
    "Avenida sargento hermínio, 10000, São Vicente, Crateús-CE", "8888-8888")

nome :: Pessoa -> String
nome (x,_,_) = x

endereco :: Pessoa -> String
endereco (_,y,_) = y

fone :: Pessoa -> String
fone (_,_,z) = z

------------------------------------------------------------------------------------------------
--Função usando declaração de variáveis locais
somaQuadradosVar :: Int -> Int -> Int
somaQuadradosVar n m = quadN + quadM
    where
        quadN = n * n
        quadM = m * m

--Função usando declaração de função local
somaQuadradosFun :: Int -> Int -> Int
somaQuadradosFun n m = quad n + quad m
    where
        quad :: Int -> Int
        quad valor = valor * valor

----------------------------------------------------------------------------------------------
--Função que calcua a área de um triângulo qualquer usando fórmulas trigonométricas
areaTriangulo :: Float -> Float -> Float -> Float
areaTriangulo a b c 
    |ehTriangulo a b c = (c*h)/2
    |otherwise = 0
    where
        h = b * sinAlpha
        sinAlpha = sqrt (1 - cosAlpha^2)
        cosAlpha = (b^2 + c^2 - a^2)/(2*b*c) 

----------------------------------------------------------------------------------------------
--Função que verifica se 3 lados formam um triângulo
ehTriangulo :: Float -> Float -> Float -> Bool
ehTriangulo a b c
    |(a+b>c) && (b+c>a) && (a+c>b) = True
    |otherwise = False


----------------------------------------------------------------------------------------------
constanteG :: Double
constanteG = 6.67e-11

forcaG :: Double -> Double -> Double -> Double
forcaG m1 m2 distancia = constanteG*((m1*m2)/distancia^2)

--massa da Terra em quilos
massaTerra :: Double
massaTerra = 6e24

--massa da Lua em quilos
massaLua :: Double
massaLua = 1e23

--distância entre a Terra e a Lua em Km
dTL :: Double
dTL = 4e5

dTLMetros :: Double
dTLMetros = dTL * 1000

----------------------------------------------------------------------------------------------
numRaizes :: Float -> Float -> Float -> Int
numRaizes a b c
    |delta > 0 = 2
    |delta == 0 = 1
    |otherwise = 0
    where
        delta = b^2 - 4*a*c


----------------------------------------------------------------------------------------------
--Função que retorna o segundo elemento de uma lista
second :: [a] -> a
second xs = head(tail xs)

-- Função que retorna o primeiro parâmetro
const2 :: p1 -> p2 -> p1
const2 x y = x

-- Função que recebe uma tupla e inverte as posições dos elementos da tupla
swap :: (b, a) -> (a, b)
swap (x,y) = (y,x)

--Função que recebe uma função unaria e um parâmetro e aplica o parâmetro à função passada como parâmetro
apply :: (t1 -> t2) -> t1 -> t2
apply f x = f x

--Função que recebe uma função binaria e dois parâmetros e aplica os parâmetro à função passada como parâmetro
flip2 :: (t1 -> t2 -> t3) -> t2 -> t1 -> t3
flip2 f x y = f y x

--Função que recebe dois parâmetros e transforma em uma dupla com os parâmetros
pair :: a -> b -> (a, b)
pair x y = (x,y)

--Função que verifica se uma lista passada é igual ao seu reverso
palindrome :: Eq a => [a] -> Bool
palindrome xs = reverse xs == xs

--Função que recebe uma função unária e um parâmetro e aplica uma função duas vezes ao seu parâmetro
twice :: (t -> t) -> t -> t
twice f x = f (f x)

--Função que forma uma frase com os parâmetros passados
mostra :: Show a => ([Char], a) -> [Char]
mostra (nome,idade) = "Nome: "++ nome ++ ", idade: "++ show idade

----------------------------------------------------------------------------------------------
ultimo :: [a] -> a
ultimo lista = head (reverse lista)
--ultimo lista = head (drop (length lista - 1) lista)
--ultimo [] = []
--ultimo lista = lista !! max 0 (length lista - 1)

----------------------------------------------------------------------------------------------
primeiros :: [a] -> [a]
primeiros lista = reverse (drop 1 (reverse lista))

primeiros2 [] = []
primeiros2 [x] = []
primeiros2 (x:xs) = x: primeiros2 xs

----------------------------------------------------------------------------------------------
metade :: [a] -> ([a],[a])
metade xs = splitAt (div (length xs) 2) xs

----------------------------------------------------------------------------------------------
funcao :: Num a => [a] -> a --define a como um tipo numérico
funcao (x:y:_) = x + y
funcao [x] = x
funcao _ = 0

----------------------------------------------------------------------------------------------
--Função que imprime o reverso de uma String 
imprime :: String -> IO()
imprime [] = putStrLn " " 
imprime (x:xs) = do
    imprime xs
    putChar x 

----------------------------------------------------------------------------------------------
contaCurtas :: [String] -> Int
contaCurtas [] = 0
contaCurtas (x:xs)
    |length x < 5 = 1 + contaCurtas xs
    |otherwise = contaCurtas xs

----------------------------------------------------------------------------------------------
numera :: [String] -> [(Int,String)]
numera [] = []
numera lista = zip [1..length lista] lista
