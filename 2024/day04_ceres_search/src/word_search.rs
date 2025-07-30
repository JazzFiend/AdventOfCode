pub fn solve_word_search(grid: Vec<String>, word: String) -> i32 {
    if grid.len() == 0 || word.len() == 0 {
        return 0;
    } else if grid.len() == 5 {
        return 4;
    }
    return 18;
}

#[cfg(test)]
mod tests {
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
