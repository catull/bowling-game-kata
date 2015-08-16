{-# LANGUAGE TemplateHaskell #-}

import Data.List (intersperse)
import Test.QuickCheck

game_gutter :: [ Int ]
game_gutter = replicate 12 0

game_90 :: [ Int ]
game_90 = intersperse 0 $ replicate 10 9

game_92 :: [ Int ]
game_92 = [ 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 10, 9, 1 ]

game_129 :: [ Int ]
game_129 = [  4,  5,
          5,  3,
         10,
          6,  4,
          0, 10,
          7,  1,
          9,  0,
          8,  1,
          3,  6 ] ++ [ 10, 10, 10 ]

gamePerfect :: [ Int ]
gamePerfect = replicate 12 10

score :: [ Int ] -> Int -> Int
score [ x ]                  _ = x
score [ x, y ]               _ = x + y
score (x : y : z : xs)   frame
     | 10 == x,     10 > frame = 10 + y + z + score (y : z : xs) f
     | 10 == x + y, 10 > frame =  x + y + z + score     (z : xs) f
     | otherwise               =  x + y     + score     (z : xs) f
  where
     f = frame + 1

prop_game_gutter :: Bool
prop_game_gutter = 0 == score game_gutter 1

prop_game_90 :: Bool
prop_game_90 = 90 == score game_90 1

prop_game_92 :: Bool
prop_game_92 = 92 == score game_92 1

prop_game_perfect :: Bool
prop_game_perfect = 300 == score gamePerfect 1

return []
runTests = $quickCheckAll

main :: IO Bool
main = runTests
