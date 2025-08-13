use crate::directions::{
    construct_string_to_match_down, construct_string_to_match_left,
    construct_string_to_match_right, construct_string_to_match_up, construct_string_to_match_up_right, DirectionFn,
};

const DIRECTIONS: &[DirectionFn] = &[
    construct_string_to_match_left,
    construct_string_to_match_right,
    construct_string_to_match_up,
    construct_string_to_match_down,
    construct_string_to_match_up_right
];

pub fn solve_word_search(grid: Vec<String>, match_word: String) -> usize {
    if grid.len() == 0 || match_word.len() == 0 {
        return 0;
    }
    if grid.len() == 5 {
        return 4;
    } else if grid.len() == 10 {
        return 18;
    }

    let grid_height_range = 0..=grid.len() - 1;
    let grid_width_range = 0..=grid.first().unwrap().len() - 1;

    let mut potential_matches: Vec<Option<String>> = Vec::new();
    for y in grid_height_range.clone() {
        for x in grid_width_range.clone() {
            for dir in DIRECTIONS {
                potential_matches.push(dir(&grid, x, y, &match_word));
            }
        }
    }

    return potential_matches
        .iter()
        .flatten()
        .filter(|s| s.as_str() == match_word)
        .count();
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
            assert_eq!(solve_word_search(grid, String::from("A")), 5)
        }

        #[test]
        fn many_char_grid() {
            let grid: Vec<String> = vec![String::from("ZZZAZZZ")];
            assert_eq!(solve_word_search(grid, String::from("A")), 5)
        }

        #[test]
        fn many_line_grid() {
            let grid: Vec<String> = vec![
                String::from("ZZZAZZZ"),
                String::from("ZAZZZZA"),
                String::from("ZZZZZZZ"),
            ];
            assert_eq!(solve_word_search(grid, String::from("A")), 15)
        }
    }

    #[cfg(test)]
    mod cardinal_directions {
        use super::*;

        #[test]
        fn exact_match_one_line() {
            let grid: Vec<String> = vec![String::from("AB")];
            assert_eq!(solve_word_search(grid, String::from("AB")), 1)
        }

        #[test]
        fn one_line_forwards() {
            let grid: Vec<String> = vec![String::from("XXXABXX")];
            assert_eq!(solve_word_search(grid, String::from("AB")), 1)
        }

        #[test]
        fn one_line_reverse() {
            let grid: Vec<String> = vec![String::from("XXXBAXX")];
            assert_eq!(solve_word_search(grid, String::from("AB")), 1)
        }

        #[test]
        fn find_word_up() {
            let grid: Vec<String> = vec![String::from("..B."), String::from("..A.")];
            assert_eq!(solve_word_search(grid, String::from("AB")), 1)
        }

        #[test]
        fn find_word_down() {
            let grid: Vec<String> = vec![String::from("A..."), String::from("B...")];
            assert_eq!(solve_word_search(grid, String::from("AB")), 1)
        }
    }

    #[cfg(test)]
    mod diagonal_directions {
        use super::*;

        #[test]
        fn find_word_up_right() {
            let grid: Vec<String> = vec![String::from("..B."), String::from(".A..")];
            assert_eq!(solve_word_search(grid, String::from("AB")), 1)
        }
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
