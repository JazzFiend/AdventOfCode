module Day01 where

import Day01SecretEntrance.CombinationLock (runCombinationCountNumber)

-- | Parse the raw puzzle input into a useful data structure.
parse :: String -> [String]
parse = lines

-- | Solve part 1.
part1 :: [String] -> Int
part1 moves = runCombinationCountNumber moves 0

-- | Solve part 2.
-- part2 :: [String] -> Int
-- part2 moves = runCombinationCountNumber moves 0

-- | Read input file and print both answers.
solve :: IO ()
solve = do
  input <- readFile "inputs/day01.txt"
  let xs = parse input
  putStrLn $ "Part 1: " ++ show (part1 xs)

-- putStrLn $ "Part 2: " ++ show (part2 xs)