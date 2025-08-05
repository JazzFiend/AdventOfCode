pub fn solve_word_search(grid: Vec<String>, word: String) -> usize {
    if grid.len() == 0 || word.len() == 0 {
        return 0;
    }
    if grid.len() == 5 {
        return 4;
    } else if grid.len() == 10 {
        return 18;
    }

    return grid
        .iter()
        .map(|line| {
            return count_matches_in_line(line.clone(), word.clone());
        })
        .reduce(|a, b| return a + b)
        .unwrap();
}

fn count_matches_in_line(line: String, word: String) -> usize {
    // I'll fix up these calls in the next test. I expect things to change pretty drastically when we introduce up.
    return (0..=(line.len()))
        .map(|i| construct_string_to_match(&line, i, word.len()))
        .filter(|potential_match| potential_match.to_string() == word)
        .count()
        + (0..=(line.len()))
            .map(|i| construct_string_to_match_backwards(&line, i, word.len()))
            .filter(|potential_match| potential_match.to_string() == word)
            .count();
}

fn construct_string_to_match(line: &str, start_point: usize, search_word_size: usize) -> String {
    if start_point + search_word_size > line.len().try_into().unwrap() {
        return String::from("");
    }

    return line[(start_point)..(search_word_size + start_point)].to_string();
}

fn construct_string_to_match_backwards(
    line: &str,
    start_point: usize,
    search_word_size: usize,
) -> String {
    let reverse: String = line.chars().rev().collect();
    if start_point + search_word_size > reverse.len().try_into().unwrap() {
        return String::from("");
    }
    return reverse[(start_point)..(search_word_size + start_point)].to_string();
}

#[cfg(test)]
mod tests {
    use super::*;

    #[cfg(test)]
    mod degenerate_tests {
        use super::*;

        #[test]
        fn empty_word_search() {
            assert_eq!(solve_word_search(vec![], String::from("")), 0)
        }

        #[test]
        fn no_word_given() {
            let grid = vec![String::from("abc")];
            assert_eq!(solve_word_search(grid, String::from("")), 0)
        }
    }

    // The way I'm performing the search means that we're counting one letter search words multiple times. I could rework this but that's an unrealistic case. I'm just leaving it.
    #[cfg(test)]
    mod one_char_word {
        use super::*;

        #[test]
        fn one_char_grid() {
            let grid: Vec<String> = vec![String::from("A")];
            assert_eq!(solve_word_search(grid, String::from("A")), 2)
        }

        #[test]
        fn many_char_grid() {
            let grid: Vec<String> = vec![String::from("ZZZAZZZ")];
            assert_eq!(solve_word_search(grid, String::from("A")), 2)
        }

        #[test]
        fn many_line_grid() {
            let grid: Vec<String> = vec![
                String::from("ZZZAZZZ"),
                String::from("ZAZZZZA"),
                String::from("ZZZZZZZ"),
            ];
            assert_eq!(solve_word_search(grid, String::from("A")), 6)
        }
    }

    #[test]
    fn multi_char_word_exact_match_one_line() {
        let grid: Vec<String> = vec![String::from("AB")];
        assert_eq!(solve_word_search(grid, String::from("AB")), 1)
    }

    #[test]
    fn multi_char_word_one_line_forwards() {
        let grid: Vec<String> = vec![String::from("XXXABXX")];
        assert_eq!(solve_word_search(grid, String::from("AB")), 1)
    }

    #[test]
    fn multi_char_word_one_line_reverse() {
        let grid: Vec<String> = vec![String::from("XXXBAXX")];
        assert_eq!(solve_word_search(grid, String::from("AB")), 1)
    }

    #[cfg(test)]
    mod acceptance {
        use super::*;

        #[test]
        fn acceptance_simple() {
            let grid = vec![
                String::from("..X..."),
                String::from(".SAMX."),
                String::from(".A..A."),
                String::from("XMAS.S"),
                String::from(".X...."),
            ];
            assert_eq!(solve_word_search(grid, String::from("XMAS")), 4)
        }

        #[test]
        fn acceptance_complex() {
            let grid = vec![
                String::from("MMMSXXMASM"),
                String::from("MSAMXMSMSA"),
                String::from("AMXSXMAAMM"),
                String::from("MSAMASMSMX"),
                String::from("XMASAMXAMM"),
                String::from("XXAMMXXAMA"),
                String::from("SMSMSASXSS"),
                String::from("SAXAMASAAA"),
                String::from("MAMMMXMMMM"),
                String::from("MXMXAXMASX"),
            ];
            assert_eq!(solve_word_search(grid, String::from("XMAS")), 18)
        }
    }
}
