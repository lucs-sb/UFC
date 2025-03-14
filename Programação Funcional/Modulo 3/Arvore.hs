module Arvore where

data BinTree a = Vazia | No a (BinTree a) (BinTree a)
                    deriving (Show,Eq, Ord)

a1 :: BinTree Int
a1 = No 20
        (No 7
            (No 3 Vazia Vazia)
            (No 14 Vazia Vazia))
        (No 90
            (No 80 Vazia Vazia)
            (No 500 Vazia Vazia))

--------------------------------------------------------------
--Função que retorna a quantidade de nós de uma árvore
btLength :: Num a => BinTree a -> Int
btLength Vazia = 0
btLength (No _ arvE arvD) = 1 + btLength arvE + btLength arvD

--------------------------------------------------------------
--Função que retorna a profundidade de uma árvore
btDepth :: (Num a,Eq a) => BinTree a -> Int
btDepth Vazia = 0
btDepth (No _ arvE arvD)
    |arvE == Vazia && arvD == Vazia = 0
    |otherwise = 1 + max (btDepth arvE) (btDepth arvD)

--------------------------------------------------------------
--Função verifica se um valor passado pertence à árvore
btElem :: (Num a, Ord a) => a -> BinTree a -> Bool
btElem _ Vazia = False
btElem valor (No v arvE arvD)
    |valor == v = True
    |valor < v = btElem valor arvE
    |otherwise = btElem valor arvD

--------------------------------------------------------------
--Função imprime os nós de uma árvore na ordem arvEsquerda-Raiz-arvDireita
btPrint :: (Num a, Show a) => BinTree a -> IO()
btPrint Vazia = putStrLn ""
btPrint (No v arvE arvD) = do
    btPrint arvE
    print v
    btPrint arvD

--------------------------------------------------------------
--verifica se uma árvore binária é vazia ou não.
btVazia :: (Num a) => BinTree a -> Bool
btVazia Vazia = True
btVazia _ = False

--------------------------------------------------------------
--recebe um valor e uma árvore binária e insere o valor na árvore binária mantendo-a a ordenada
btInsere :: (Num a, Ord a) => a -> BinTree a -> BinTree a
btInsere novo Vazia = No novo Vazia  Vazia
btInsere novo (No v arvE arvD)
    |novo <= v = No v (btInsere novo arvE) arvD
    |otherwise = No v arvE (btInsere novo arvD)

--------------------------------------------------------------
--recebe um valor e uma árvore binária e remove o valor na árvore binária mantendo-a a ordenada   
remove :: (Ord t) => t -> BinTree t -> BinTree t
remove x Vazia = Vazia
remove x (No n ae ad)
    |x < n = No n (remove x ae) ad
    |x > n = No n ae (remove x ad)
    |otherwise = junta ae ad

junta :: (Ord t) => BinTree t -> BinTree t -> BinTree t
junta Vazia ad = ad
junta ae Vazia = ae
junta (No x aee aed) (No y ade add) =
    No menor (No x aee aed) (remove menor (No y ade add))
        where menor = minArv (No y ade add)

minArv :: (Ord t) => BinTree t -> t
minArv (No x Vazia _) = x
minArv (No _ xt _) = minArv xt

--------------------------------------------------------------
--recebe uma árvore e transforma em uma lista ordenada
arvToLista :: (Ord a) => BinTree a -> [a]
arvToLista Vazia = []
arvToLista (No v arvE arvD) = arvToLista arvE ++ [v] ++ arvToLista arvD

----------------------------------------------------------------
-- recebe uma lista e transforma em uma árvore de busca
fazABB :: (Ord t) => [t] -> BinTree t
fazABB [] = Vazia
fazABB (x:xs) = No x (fazABB ys) (fazABB zs)
    where (ys, zs) = particao (<=x) xs

particao :: (t -> Bool) -> [t] -> ([t], [t])
particao p xs = (filter p xs, filter (not.p) xs)

------------------------------------------------------------------