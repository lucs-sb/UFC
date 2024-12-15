module Main where
import System.IO (stdout, hSetBuffering, BufferMode (NoBuffering))

-----------------------------------------------------------------------------------
analiseCredito :: Float -> Float -> String
analiseCredito salario emprestimo
    |verificaPrestacao salario emprestimo = "O emprestimo pode ser concedido."
    |otherwise = "O emprestimo não pode ser concedido."


verificaPrestacao :: Float -> Float -> Bool
verificaPrestacao salario emprestimo
    |valorPrestacao emprestimo < salario*0.3 = True
    |otherwise = False

valorPrestacao :: Float -> Float
valorPrestacao emprestimo = emprestimo/60

main :: IO ()
main = do
    hSetBuffering stdout NoBuffering
    putStrLn "Análise de crédito-----------------------------------------"
    putStrLn "Digite o valor do salário: "
    salario <- readLn :: IO Float                    -- obs o uso de readLn para valor
    putStrLn "Digite o valor do emprestimo pretendido: "
    emprestimo <- readLn :: IO Float 
    putStr "Salario bruto: "
    print salario
    putStr "Valor da prestação: "
    print (valorPrestacao emprestimo)
    putStrLn (analiseCredito salario emprestimo)
    
