module Senha where

validaSenha :: String -> Bool
validaSenha s =
    length s >= 6 && length s <= 10 &&
    any ehMaiusculo s &&
    any ehDigito s

ehDigito :: Char -> Bool
ehDigito c = not (null [x | x <- ['0'..'9'], x == c])

ehMaiusculo :: Char -> Bool
ehMaiusculo c = not (null [x | x <- ['A'..'Z'], x == c])

buscarSenhasValidas :: [String] -> [String]
buscarSenhasValidas lista = filter validaSenha lista