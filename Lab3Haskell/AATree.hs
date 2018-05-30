{-# OPTIONS -Wall #-}

---------------------------------------------------------------------------------------------------
-----------------------------------------------DATA------------------------------------------------
---------------------------------------------------------------------------------------------------

module AATree (
  AATree,        -- type of AA search trees
  emptyTree,     -- AATree a
  get,           -- Ord a => a -> AATree a -> Maybe a
  insert,        -- Ord a => a -> AATree a -> AATree a
  inorder,       -- AATree a -> [a]
  remove,        -- Ord a => a -> AATree a -> AATree a
  size,          -- AATree a -> Int
  height,        -- AATree a -> Int
  checkTree      -- Ord a => AATree a -> Bool
 ) where


data AATree a = Leaf
              | Content a Int (AATree a) (AATree a)
  deriving (Eq, Show, Read)


---------------------------------------------------------------------------------------------------
---------------------------------------------FUNCTIONS---------------------------------------------
---------------------------------------------------------------------------------------------------

-- Gives you an empty tree.                                                                    O(1)
emptyTree :: AATree a
emptyTree = Leaf


-- Gives you the value of a node in a tree.                                                O(log n)
get :: Ord a => a -> AATree a -> Maybe a
get _ Leaf = Nothing
get x (Content c _ left right) | x == c = Just c
                               | x < c = get x left
                               | otherwise = get x right


-- Raises a node in a tree.                                                                    O(1)
split :: AATree a -> AATree a 
split x@(Content c1 int1 left1 (Content c2 int2 left2 (Content c3 int3 left3 right3))) =
  if int1 == int2 && int2 == int3 then Content c2 (int2+1) (Content c1 int1 left1 left2) (Content c3 int3 left3 right3)
    else x
split x = x


-- Corrects a Tree by spinning it to the right.                                                O(1)
skew :: AATree a -> AATree a 
skew x@(Content c1 int1 (Content c2 int2 left2 right2) right1) = 
  if int1 == int2 then Content c2 int2 left2 (Content c1 int1 right2 right1)
    else x
skew x = x


-- Allows you to insert a node into a Tree.                                                O(log n)
insert :: Ord a => a -> AATree a -> AATree a
insert x Leaf = Content x 1 Leaf Leaf 
insert x (Content c int left right) | x == c    = (Content c int left right)
                                    | x > c     = split $ skew $ Content c int left (insert x right)
                                    | x < c     = split $ skew $ Content c int (insert x left) right
insert _ x = x
                                                                       

-- Gives you an ordered list of all the nodes in a Tree.                                       O(n)
inorder :: AATree a -> [a]
inorder Leaf = []
inorder (Content c _ Leaf Leaf)  = [c]
inorder (Content c _ left right) = inorder left ++ [c] ++ inorder right


-- Counts every node in a Tree.                                                          O(n)
size :: AATree a -> Int 
size Leaf = 0
size (Content _  _ left right) = 1 + size left + size right


-- Counts the height of a Tree.                                                            O(log n)
height :: Ord a => AATree a -> Int
height Leaf = 1
height (Content _ _ left right) | right /= Leaf = 1 + height right
                                | otherwise     = 1 + height left


-- Checks that an AA tree is ordered and obeys the AA invariants.                              O(n)     KOLLA UPP DET HÄR!!!
checkTree :: Ord a => AATree a -> Bool
checkTree root =
  isSorted (inorder root) &&
  all checkLevels (nodes root)
  where
    nodes x
      | isEmpty x = []
      | otherwise = x:nodes (leftSub x) ++ nodes (rightSub x)


-- True if the given list is ordered.                                                          O(n)
isSorted :: Ord a => [a] -> Bool 
isSorted []        = True
isSorted (_:[])    = True
isSorted (x:y:[])  | x < y     = True
isSorted (x:y:xys) | x < y     = isSorted xys
                   | otherwise = False


-- Checks if the invariant is true for a single AA node.                                       O(1)
checkLevels :: AATree a -> Bool
checkLevels Leaf = True
checkLevels node = leftChildOK node && rightChildOK node && rightGrandchildOK node


-- Checks if a nodes left child holds the invariant.                                           O(1)
leftChildOK :: AATree a -> Bool
leftChildOK Leaf = True
leftChildOK (Content _ _ Leaf _) = True
leftChildOK (Content _ int1 (Content _ int2 _ _) _) | int1 == (int2-1) = False                       
                                                    | otherwise        = True


-- Checks if a nodes right child holds the invariant.                                          O(1)
rightChildOK :: AATree a -> Bool
rightChildOK Leaf = True
rightChildOK (Content _ _ _ Leaf) = True
rightChildOK (Content _ int1 _ (Content _ int2 _ _)) | int1     == int2   = True
                                                     | (int1-1) == (int2) = True
                                                     | otherwise          = False

-- Checks if a nodes right grandchild holds the invariant.                                     O(1)
rightGrandchildOK :: AATree a -> Bool 
rightGrandchildOK Leaf = True
rightGrandchildOK (Content _ _ _ Leaf)                 = True
rightGrandchildOK (Content _ _ _ (Content _ _ _ Leaf)) = True
rightGrandchildOK (Content _ int1 _ (Content _ int2 _ (Content _ int3 _ _))) 
                        | int1 == int2 && int3 >= int2 = False
                        | otherwise                    = True


-- True if the tree is empty.                                                                O(1)
isEmpty :: AATree a -> Bool
isEmpty Leaf = True
isEmpty _    = False


-- Gives you the left subtree of a node.                                                       O(1)
leftSub :: AATree a -> AATree a 
leftSub Leaf = Leaf
leftSub (Content _ _ left _) = left


-- Gives you the right subtree of a node.                                                      O(1)
rightSub :: AATree a -> AATree a 
rightSub Leaf = Leaf
rightSub (Content _ _ _ right) = right

---------------------------------------------------------------------------------------------------
-----------------------------------------Optional Function-----------------------------------------
---------------------------------------------------------------------------------------------------


-- Merges two trees together.                                                                  O()
insertTree :: Ord a => AATree a -> AATree a -> AATree a
insertTree Leaf x = x
insertTree x Leaf = x
insertTree tree1 tree2 = foldl insert tree1 (inorder tree2)


-- Removes an element from a tree.                                                             O()
remove :: Ord a => a -> AATree a -> AATree a 
remove _ Leaf = Leaf    
remove x (Content c int Leaf right) | x == c = right
                                    | otherwise = (Content c int Leaf (remove x right))
remove x (Content c int left Leaf)  | x == c = left
                                    | otherwise = (Content c int (remove x left) Leaf)

remove x (Content c1 int1 (Content c2 int2 left2 right2) (Content c3 int3 left3 right3)) 
 | x == c1 = insertTree left3 (Content c3 int1 (Content c2 int2 left2 right2) right3)
 | x > c1 = (Content c1 int1 (Content c2 int2 left2 right2) (remove x (Content c3 int3 left3 right3)))
 | otherwise = (Content c1 int1 (remove x (Content c2 int2 left2 right2)) (Content c3 int3 left3 right3))


---------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------