--------------------------------------------------------------------------------
-- Função que armazena a quantidade de vendas por semana -----------------------
venda :: Int -> Int
venda 1 = 3000
venda 2 = 150
venda 3 = 100
venda 4 = 13000
venda 5 = 0
venda 6 = 900

--------------------------------------------------------------------------------
-- Qual o total de vendas desde a semana 1 até a semana n? ---------------------
-- Exemplo de chamada: > totalVendas 6 -----------------------------------------
totalVendas :: Int -> Int
totalVendas n
    |n == 1 = venda 1
    |n > 1 = venda n + totalVendas (n-1)
    |otherwise = -1

-- Forma alternativa para totalVendas------------------------------------------
{- totalVendas n |n<=0 = -1
totalVendas 1 = venda 1
totalVendas n = venda n + totalVendas (n-1) -}


-------------------------------------------------------------------------------
-- Qual a maior venda semanal entre as semanas 1 e n?--------------------------
maiorVenda :: Int -> Int
maiorVenda n
    |n == 1 = venda 1
    |n > 1 = max (venda n) (maiorVenda (n-1))   --uso da função max da bib padrão
    |otherwise = -1 

{- alternativa para a função maiorVenda
maiorVenda :: Int -> Int
maiorVenda n
  |n==1 = venda 1
  |n<1 = -1
  |n>1 && venda n > maiorVenda(n-1) = venda n
  |n>1 && venda n < maiorVenda(n-1) = maiorVenda(n-1) -}


-----------------------------------------------------------------------------
-- Em que semana ocorreu a maior venda?--------------------------------------
--exemplo de chamada da função: > queSemana 5 (maiorVenda 5)
queSemana :: Int -> Int -> Int
queSemana n x
    |n <= 0 = -1
    |venda n == x = n
    |otherwise = queSemana (n-1) x

-----------------------------------------------------------------------------
-- Forma alternativa --------------------------------------
--exemplo de chamada da função: > qualSemana 5
qualSemana :: Int -> Int
qualSemana n
    |n <= 0 = -1
    |venda n == maior = n
    |otherwise = qualSemana (n-1)
    where
        maior = maiorVenda n

------------------------------------------------------------------------------
--Média de vendas entre as semanas 1 e n--------------------------------------
mediaVendas :: Int -> Float
mediaVendas n = vendas / quantidade
    where
        vendas = fromIntegral (totalVendas n) :: Float --função que converte Int para Float
        quantidade = fromIntegral n :: Float

------------------------------------------------------------------------------
-- Existe alguma semana que nada foi vendido----------------------------------
semVenda :: Int -> Bool
semVenda n
  |venda n == 0 = True
  |n > 1 = semVenda (n-1)
  |otherwise = False



--forma alternativa para função semVenda
{- semVenda :: Int -> Bool
semVenda 1 = venda 1 == 0 
semVenda n = venda n == 0 || semVenda (n-1) -}



-------------------------------------------------------------------------------
-- Em qual semana não houve venda (para quando encontra a primeira semana zerada)
semanaSemVenda :: Int -> Int
semanaSemVenda 1 = if venda 1 == 0 then 1 else -1
semanaSemVenda n
    |venda n == 0 = n
    |otherwise = semanaSemVenda (n-1)


----------------------------------------------------------------------------
-------------Funções que imprimem uma tabela de vendas----------------------

-- variável que define a largura da coluna da tabela------------------------------
largura :: Int
largura = 8

-----------------------------------------------------------------------------------
-- Imprime a primeira linha da tabela ---------------------------------------------
cabecalho :: String
cabecalho = 
    "---------------------\n"++
    "||" ++ centralizado largura "Semana" ++ "|" ++ centralizado largura "Vendas" ++ "||\n"++
    "---------------------\n"


------------------------------------------------------------------------------------
-- Imprime todas as vendas da semana de 1 a n na tabela-----------------------------
imprimeSemanas :: Int -> String
imprimeSemanas 1 = imprimeUmaSemana 1
imprimeSemanas n = imprimeSemanas (n-1) ++ imprimeUmaSemana n


-----------------------------------------------------------------------------------
-- Imprime uma linha da tabela com a venda da semana n-----------------------------
imprimeUmaSemana :: Int -> String
imprimeUmaSemana n =
    "||" ++ 
    centralizado largura (show n) ++ 
    "|" ++ 
    centralizado largura (show(venda n)) ++ "||\n"


----------------------------------------------------------------------------------
-- Imprime uma linha da tabela com o total de vendas de 1 a n---------------------
imprimeTotal :: Int -> String
imprimeTotal n = 
    "---------------------\n"++
    "||" ++ centralizado largura "Total" ++ "|"
     ++ centralizado largura (show(totalVendas n)) ++ 
     "||\n"++
     "---------------------\n"


---------------------------------------------------------------------------------
--Imprime uma linha da tabela com a média de vendas na tabela--------------------
imprimeMedia :: Int -> String
imprimeMedia n = 
    "---------------------\n"++
    "||" ++ centralizado largura "Média" ++ "|"
     ++ centralizado largura (show(mediaVendas n)) ++ 
     "||\n"++
     "---------------------\n"


----------------------------------------------------------------------------------
-- Função que chama todas as funções que imprimem uma tabela com as vendas de 1 a n
imprimeTabela :: Int -> IO()
imprimeTabela n = do
    putStr cabecalho
    putStr(imprimeSemanas n)
    putStr(imprimeTotal n)
    putStr(imprimeMedia n)


-----------------------------------------------------------------------------------
-- Função auxiliar de alinhamento que imprime n espaços em branco------------------
imprimeEspacos :: Int -> String
imprimeEspacos 0 = ""
imprimeEspacos n = " " ++ imprimeEspacos (n-1)


-----------------------------------------------------------------------------------
-- Faz o alinhamento à direta de um texto em um espaço de tamanho n----------------
alinhamentoDireita :: Int -> String -> String
alinhamentoDireita n texto
    |n > length texto = imprimeEspacos (n - length texto) ++ texto
    |n == length texto = texto
    |otherwise = "Erro: o texto nao cabe no intervalo dado"


----------------------------------------------------------------------------------
-- Faz o alinhamento à esquerda de um texto em um espaço de tamanho n-------------
alinhamentoEsquerda :: Int -> String -> String
alinhamentoEsquerda n texto
    |n > length texto = texto ++ imprimeEspacos (n - length texto)
    |n == length texto = texto
    |otherwise = "Erro: o texto nao cabe no intervalo dado"


-----------------------------------------------------------------------------------
-- Faz o alinhamento centralizado de um texto em um espaço de tamanho n------------
centralizado :: Int -> String -> String
centralizado n texto
    |n > length texto && tamanhoPar texto = imprimeEspacos metade ++ texto ++ imprimeEspacos metade
    |n > length texto && not (tamanhoPar texto) = imprimeEspacos (metade + 1) ++ texto ++ imprimeEspacos metade --se o tamanho do texto é impar um espaço adicional é incluído para o alinhamento se ajustar
    |n == length texto = texto
    |otherwise = "Erro: o texto nao cabe no intervalo dado"
        where 
            metade = div (n - length texto) 2
            tamanhoPar :: String -> Bool --verifica se o tamanho do texto é par
            tamanhoPar txt
                |even (length txt) = True
                |otherwise = False

-----------------------------------------------------------------------------------
-- Função principal que é chamada quando o código compilado é executado------------
main :: IO ()
main = imprimeTabela 6