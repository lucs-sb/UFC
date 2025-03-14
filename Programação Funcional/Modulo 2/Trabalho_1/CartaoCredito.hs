module CartaoCredito where

adicionaVerificador :: String -> String
adicionaVerificador s 
    | length s /= 12 = error "Entrada inválida"
    | otherwise = s ++ calculaVerificador s

calculaVerificador :: String -> String
calculaVerificador s = verificador
    where
        numeroCartao = [getDigito x | x <- s]
        (metade1, metade2) = splitAt 6 numeroCartao
        produto1 = product [d | d <- metade1, d /= 0]
        produto2 = product [d | d <- metade2, d /= 0]
        resultado = abs (produto1 - produto2)
        verificador = take 4 (show resultado ++ "0000")

getDigito :: Char -> Int
getDigito c
  | ehDigito c = read [c]
  | otherwise = error "Entrada inválida"

ehDigito :: Char -> Bool
ehDigito c = not (null [x | x <- ['0'..'9'], x == c])

validaCartao :: String -> Bool
validaCartao s 
    | length s /= 16 = error "Entrada inválida"
    | otherwise = validaVerificador s

validaVerificador :: String -> Bool
validaVerificador s = all id [x == y | (x, y) <- zip (calculaVerificador (take 12 s)) (drop 12 s)]