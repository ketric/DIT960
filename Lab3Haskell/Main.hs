{-
  to run:
  $ ghc -e main Main.hs < swahili-small.txt

  to compile and run:
  $ ghc -O Main.hs && ./Main < swahili-small.txt
-}

import AATree

--------------------------------------------------------------------------------

main :: IO ()
main = do
  contents <- getContents
  let tree = foldl insert emptyTree (words contents)
  let treeSize = size tree
  let treeHeight = height tree
  let optimalH = (logBase 2 (fromIntegral(treeSize + 1)))-1
  let ratio = (fromIntegral treeHeight)/optimalH
  putStrLn $ "Size: " ++ show treeSize
  putStrLn $ "Height: " ++ show treeHeight
  putStrLn $ "Height / Optimal Height: " ++ show ratio
  putStrLn $ "CheckTree: " ++ show (checkTree tree)
  putStrLn $ "First 20 words: " ++ show (take 20 (inorder tree))

  -- split the data into words and build an AA tree
  -- use foldl

  -- calculate and print statistics
  -- use fromIntegral/ceiling/logBase

--------------------------------------------------------------------------------
