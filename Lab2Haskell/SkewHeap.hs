module SkewHeap where

-- |A SkewHeap implementation with the min heap property.
-- Useful for priority queues.
data SkewHeap a = Null
                | Node a (SkewHeap a) (SkewHeap a)
                deriving (Eq, Show)

-- |Returns root of heap.
-- Complexity: O(1)
root :: SkewHeap a -> Maybe a
root Null = Nothing
root (Node a _ _) = Just a

-- |Merge two SkewHeaps.
-- Complexity: O(lg n)
merge :: Ord v => SkewHeap v -> SkewHeap v -> SkewHeap v
merge Null rhs = rhs
merge lhs Null = lhs
merge a@(Node a_val a_left a_right) b@(Node b_val b_left b_right)
    | a_val <= b_val = Node a_val (merge a_left b) a_right
    | otherwise      = Node b_val (merge b_left a) b_right


-- |Insert a value into a SkewHeap.
-- Complexity: O(lg n)
insert :: Ord v => v -> SkewHeap v -> SkewHeap v
insert val heap = merge (Node val Null Null) heap

-- |Remove and return the minimum value from a heap.
-- Complexity: O(lg n)
removeMin :: Ord a => SkewHeap a -> Maybe (a, SkewHeap a)
removeMin Null = Nothing
removeMin (Node min left right) = Just (min, merge left right)

-- |Remove element by value regardless of location.
-- Complexity: O(n lg n)
remove :: Ord a => a -> SkewHeap a -> SkewHeap a
remove v Null = Null
remove v (Node root left right)
    | v == root = merge left right
    | otherwise = Node root (remove v left) (remove v right)

-- |Update a value by replacing it with a new one
-- Complexity: O(n lg n)
update :: Ord a => a -> a -> SkewHeap a -> SkewHeap a
update old new heap = insert new (remove old heap)

-- |Return a comma separated string of the SkewHeap
-- Complexity: O(n)
commaSeparatedStr :: (Show a) => SkewHeap a -> String
commaSeparatedStr Null                  = ""
commaSeparatedStr (Node val left right) =
    show val ++ ", " ++ commaSeparatedStr left ++ commaSeparatedStr right