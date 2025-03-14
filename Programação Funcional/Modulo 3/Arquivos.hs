module Main where
import System.Environment (getArgs)
import System.Exit (exitFailure)
import qualified Distribution.Types.InstalledPackageInfo as Arquivos

main :: IO ()
main = do 
    argumentos <- getArgs
    case argumentos of 
        [entrada, saida] -> do 
            conteudo <- readFile entrada
            writeFile saida (processa conteudo)
        _-> exitFailure

processa :: String-> String
processa = unlines . map processaAluno . lines

processaAluno :: String-> String
processaAluno linha =
    case words linha of
        [mat, nome, n1, n2]-> let media = (read n1 + read n2)/2
                                  situacao | media < 3 = "reprovado"
                                           | media < 7 = "exame especial"
                                           | otherwise = "aprovado"
                              in unwords [mat, nome, n1, n2, show media, situacao]
        _-> error "dados não estão no formato esperado"

------------------------------------------------------------------------------------------
{- para executar o código acima, ele deve ser compilado e depois executado com o nome 
do arquivo de entrada e o nome do arquivo de saída porque o programa escreve ao final em um arquivo.
O arquivo de entrada deve estar no mesmo nível do arquivo do código-fonte-}
-- > ghc -make Arquivos.hs
-- > .\Arquivos.exe entrada.txt saida.txt
