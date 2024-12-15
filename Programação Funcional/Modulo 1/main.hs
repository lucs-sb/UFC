module Main where

{-
main :: IO()
main = do
    caracter <- getChar
    putChar caracter
-}
{-
main :: IO()
main = do
    putStrLn "Qual seu nome ?"
    nome <- getLine
    putStr nome
    putStrLn ", seja bem vindo(a)!"
-}
{-
main :: IO()
main = do
    putStrLn "Digite um numero"
    s1 <- readLn 
    putStrLn "Digite outro numero"
    s2 <- readLn
    putStr "Soma dos números digitados: "
    print (s1 + s2)
-}

main :: IO()
main = do
    putStrLn "Digite a nota do trabalho de laboratório: "
    notaLab <- readLn ::IO Float
    putStrLn "Digite a nota da avaliação semestral: "
    notaAv <- readLn ::IO Float
    putStrLn "Digite a nota do exame final: "
    notaEx <- readLn ::IO Float
    calculaConceito (calculaMedia notaLab notaAv notaEx)

calculaMedia :: Float -> Float -> Float -> Float
calculaMedia notaLab notaAv notaEx = (notaLab * 2 + notaAv * 3 + notaEx * 5)/(2 + 3 + 5)

calculaConceito :: Float -> IO()
calculaConceito media
    | media >= 8 = putStrLn "Conceito final: A"
    | media >= 7 = putStrLn "Conceito final: B"
    | media >= 6 = putStrLn "Conceito final: C"
    | media >= 5 = putStrLn "Conceito final: D"
    | otherwise = putStrLn "Conceito final: E"

