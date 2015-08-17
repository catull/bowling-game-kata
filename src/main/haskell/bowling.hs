
import Data.List (intersperse)

import Test.HUnit.Base (Counts, Test, test, (~:), (~=?))
import Test.HUnit.Text (runTestTT)

-- Pins knocked down
type Pins = [Int]

-- Number of points scored
type Score = Int

scoreGame :: Pins -> Int -> Score
scoreGame [ x ]                  _ = x
scoreGame [ x, y ]               _ = x + y
scoreGame (x : y : z : xs) frame
     | 10 == x,     10 > frame = 10 + y + z + scoreGame (y : z : xs) n
     | 10 == x + y, 10 > frame = 10     + z + scoreGame     (z : xs) n
     | otherwise               =  x + y     + scoreGame     (z : xs) n
  where
     n = frame + 1

perfect :: Int -> Pins
perfect n = replicate n 10

gutter :: Int -> Pins
gutter n = replicate n 0

testData :: [ (Score, Pins) ]
testData = [
       (300, perfect 12), -- perfect game, 12 Strikes
       (290,  9 : 1 : perfect 11),
       (279,  8 : 1 : perfect 11),
       (269,  8 : 1 : 9 : 1 : perfect 10),
       (258,  7 : 2 : 6 : 3 : perfect 10),
       (290, perfect 9  ++  [10, 10,  0]),
       (285, perfect 9  ++  [10,  5,  5]),
       (280, perfect 9  ++  [10,  0, 10]),
       (270, perfect 9  ++  [10,  0,  0]),
       (274, perfect 9  ++  [ 9,  1,  5]),
       (267, perfect 9  ++  [ 9,  0]),
       (210, (concat $ replicate 5 [ 9, 1, 10 ]) ++ [ 10, 10 ]),
       (200, [ 10, 8, 2, 10, 9, 1, 10, 7, 3, 10, 6, 4, 10, 1, 9, 10 ]), -- Dutch
       (190, intersperse 1 $ replicate 11 9),
       (150, replicate 21 5),
       (129, [ 4, 5, 5, 3, 10, 6, 4, 0, 10, 7, 1, 9, 0, 8, 1, 3, 6, 10, 10, 10 ]),
       (101, [ 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 10, 9, 1 ]),
       ( 90, (intersperse 0 $ replicate 10 9) ++ [ 0 ]),
       ( 20, gutter 16  ++  [10,  2,  3]),
       ( 15, gutter 16  ++  [ 0,  0,  10,  2,  3]),
       (  0, gutter 20)         -- Missed all
    ]

-- Construct a unit test asserting that
-- we calculate the expected score
testFromData :: (Score, Pins) -> Test
testFromData (score, pins) = ("Scoring "  ++  show pins) ~: score ~=? scoreGame pins 1

-- Build a list of tests and run it
tests :: Test
tests = test (map testFromData testData)

main :: IO Counts
main = runTestTT tests
