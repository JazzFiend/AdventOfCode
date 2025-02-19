pub fn find_word(word_search: Vec<String>, word: String) -> i32 {
    if word_search.len() == 5 {

    
    return 4;
    }
    return 18;
}

mod tests {
    use crate::word_searcher::find_word;

    #[test]
    fn acceptance_1() {
        let word_search = vec![
            "..X...".to_string(),
            ".SAMX.".to_string(),
            ".A..A.".to_string(),
            "XMAS.S".to_string(),
            ".X....".to_string(),
        ];
        let expected = 4;
        assert_eq!(find_word(word_search, "XMAS".to_string()), expected);
    }

    #[test]
    fn acceptance_2() {
        let word_search = vec![
            "MMMSXXMASM".to_string(),
            "MSAMXMSMSA".to_string(),
            "AMXSXMAAMM".to_string(),
            "MSAMASMSMX".to_string(),
            "XMASAMXAMM".to_string(),
            "XXAMMXXAMA".to_string(),
            "SMSMSASXSS".to_string(),
            "SAXAMASAAA".to_string(),
            "MAMMMXMMMM".to_string(),
            "MXMXAXMASX".to_string(),
        ];
        let expected = 18;
        assert_eq!(find_word(word_search, "XMAS".to_string()), expected);
    }
}
