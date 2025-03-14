module ArvoreDecisao where

data Decisao = Poscomp | IRA Double | Entrevista Double | Titulo Double
  deriving (Show, Eq)

data Resultado = Class | Elim
  deriving (Show, Eq)

data Arvore = NoDecisao Decisao Arvore Arvore
            | NoResultado Resultado
  deriving (Show, Eq)

arvore :: Arvore
arvore = NoDecisao Poscomp
          (NoDecisao (IRA 7)
            (NoDecisao (Entrevista 7)
              (NoDecisao (Titulo 5) (NoResultado Class) (NoResultado Elim))
              (NoDecisao (Titulo 7) (NoResultado Class) (NoResultado Elim))
            )
            (NoDecisao (Entrevista 9)
              (NoDecisao (Titulo 7) (NoResultado Class) (NoResultado Elim))
              (NoDecisao (Titulo 8) (NoResultado Class) (NoResultado Elim))
            )
          )
          (NoDecisao (IRA 8)
            (NoDecisao (Entrevista 8)
              (NoDecisao (Titulo 8) (NoResultado Class) (NoResultado Elim))
              (NoResultado Elim)
            )
            (NoResultado Elim)
          )

-- Funções auxiliares
satisfaz :: Double -> Decisao -> Bool
satisfaz valor (IRA limite) = valor >= limite
satisfaz valor (Entrevista limite) = valor >= limite
satisfaz valor (Titulo limite) = valor >= limite
satisfaz _ _ = False

navegar :: Arvore -> String -> Double -> Double -> Double -> Resultado
navegar (NoResultado resultado) _ _ _ _ = resultado
navegar (NoDecisao decisao esquerda direita) fezPoscomp ira entrevista titulo =
  case decisao of
    Poscomp -> if fezPoscomp == "SIM" then navegar esquerda fezPoscomp ira entrevista titulo else navegar direita fezPoscomp ira entrevista titulo
    IRA limite -> if satisfaz ira decisao then navegar esquerda fezPoscomp ira entrevista titulo else navegar direita fezPoscomp ira entrevista titulo
    Entrevista limite -> if satisfaz entrevista decisao then navegar esquerda fezPoscomp ira entrevista titulo else navegar direita fezPoscomp ira entrevista titulo
    Titulo limite -> if satisfaz titulo decisao then navegar esquerda fezPoscomp ira entrevista titulo else navegar direita fezPoscomp ira entrevista titulo

main :: IO ()
main = do
    putStrLn "Você fez o POSCOMP? (sim/nao)"
    fezPoscomp <- getLine
    
    putStrLn "Digite sua nota do IRA (0 a 10):"
    ira <- obterNota
    
    putStrLn "Digite sua nota da entrevista (0 a 10):"
    entrevista <- obterNota
    
    putStrLn "Digite sua nota da prova de títulos (0 a 10):"
    provaTitulos <- obterNota
    
    putStrLn "\nResumo das informações coletadas:"
    putStrLn $ "Fez POSCOMP: " ++ fezPoscomp
    putStrLn $ "Nota do IRA: " ++ show ira
    putStrLn $ "Nota da Entrevista: " ++ show entrevista
    putStrLn $ "Nota da Prova de Títulos: " ++ show provaTitulos

    let resultado = navegar arvore fezPoscomp ira entrevista provaTitulos
    putStrLn $ "\nResultado: " ++ show resultado

obterNota :: IO Double
obterNota = do
    input <- getLine
    let valoresValidos = [nota | (nota, "") <- reads input, nota >= 0, nota <= 10]
    case valoresValidos of
        (nota:_) -> return nota
        _ -> do
            putStrLn "Entrada inválida! Digite um número entre 0 e 10."
            obterNota
