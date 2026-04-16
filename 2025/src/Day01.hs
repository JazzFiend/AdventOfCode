module Day01 where

-- | Parse the raw puzzle input into a useful data structure.
parse :: String -> [Int]
parse = map read . lines

-- | Solve part 1.
part1 :: [Int] -> Int
part1 xs = undefined

-- | Solve part 2.
part2 :: [Int] -> Int
part2 xs = undefined

-- | Read input file and print both answers.
solve :: IO ()
solve = do
  input <- readFile "inputs/day01.txt"
  let xs = parse input
  putStrLn $ "Part 1: " ++ show (part1 xs)
  putStrLn $ "Part 2: " ++ show (part2 xs)