module Lista8 where

verificaPosMap :: [Int] -> Bool
verificaPosMap lista = foldr (&&) True (map (>0) lista)

verificaPos :: [Int] -> Bool
verificaPos = foldr ((&&).(>0)) True

ehPositivo :: Int -> Bool
ehPositivo valor = valor > 0

verificaPosAlt :: [Int] -> Bool
verificaPosAlt = foldr ((&&).ehPositivo) True

--usando acumulador
verificaPosAcc :: [Int] -> Bool
verificaPosAcc = foldl (\acc x -> acc && (x > 0)) True


somaTripla :: (Int,Int,Int) -> Int
somaTripla (x,y,z) = x+y+z

somaTriplas :: [(Int,Int,Int)] -> [Int]
somaTriplas = map somaTripla


membro :: [Int] -> Int -> Bool
membro lista valor = not (null [x | x <- lista, x == valor])


-- função que recebe um inteiro positivo e retorna a lista com os divisores
divisores :: Int -> [Int]
divisores n = [x | x <- [1..(n-1)], mod n x == 0]

ehPerfeito :: Int -> Bool
ehPerfeito n = sum (divisores n) == n

numerosPerfeitos :: Int -> [Int]
numerosPerfeitos n = filter ehPerfeito [1..n]

-- Defina uma função que gere uma lista de todos os números pares maiores que 113 e menores ou iguais a 1000, e que sejam perfeitos
listaParesPerfeitos :: [Int]
listaParesPerfeitos = [x | x <- [114 .. 1000], ehPerfeito x]

--Uma função que incremente os elementos de uma lista em uma unidade e mostre apenas os positivos
incrementaPositivos :: [Int] -> [Int]
--incrementaPositivos lista = filter (>0) (map (+1) lista)
incrementaPositivos lista = filter ((>0).(+1)) lista

--Uma função que pega apenas os elementos pares de uma lista
pegaPares :: [Int] -> [Int]
pegaPares lista = filter ((==0).(`mod` 2)) lista

pegaPares2 :: [Int] -> [Int]
pegaPares2 lista = filter (even) lista
