pub trait WordMatcher {
    fn new() -> Self;
    // REFACTOR: Need to seperate the values so I can pass in word seach and word once.
    fn match_word(
        &self,
        word_search_line: &[String],
        word_search_line_index: usize,
        word: &[String],
        word_index: usize,
    ) -> i32 {
        if Self::is_not_match(word_search_line, word_search_line_index, word, word_index) {
            return 0;
        } else if Self::is_end_of_word(word, word_index) {
            return 1;
        } else if Self::is_edge_of_word_search(word_search_line, word_search_line_index) {
            return 0;
        } else {
            return Self::recursive(
                self,
                word_search_line,
                word_search_line_index,
                word,
                word_index,
            );
        }
    }

    fn is_end_of_word(word: &[String], word_index: usize) -> bool {
        word_index == word.len() - 1
    }

    fn is_not_match(
        word_search_line: &[String],
        word_search_line_index: usize,
        word: &[String],
        word_index: usize,
    ) -> bool {
        word_search_line[word_search_line_index] != word[word_index]
    }

    fn is_edge_of_word_search(word_search_line: &[String], word_search_index: usize) -> bool;
    // REFACTOR: Rename this
    fn recursive(
        &self,
        word_search_line: &[String],
        word_search_line_index: usize,
        word: &[String],
        word_index: usize,
    ) -> i32;
}

pub struct WordMatcherRight {}

impl WordMatcher for WordMatcherRight {
    fn new() -> Self {
        WordMatcherRight {}
    }

    fn is_edge_of_word_search(word_search_line: &[String], word_search_line_index: usize) -> bool {
        word_search_line_index == word_search_line.len() - 1
    }

    fn recursive(
        &self,
        word_search_line: &[String],
        word_search_line_index: usize,
        word: &[String],
        word_index: usize,
    ) -> i32 {
        return self.match_word(
            word_search_line,
            word_search_line_index + 1,
            word,
            word_index + 1,
        );
    }
}

pub struct WordMatcherLeft {}

impl WordMatcher for WordMatcherLeft {
    fn new() -> Self {
        WordMatcherLeft {}
    }

    fn is_edge_of_word_search(word_search_line: &[String], word_search_line_index: usize) -> bool {
        word_search_line_index == 0
    }

    fn recursive(
        &self,
        word_search_line: &[String],
        word_search_line_index: usize,
        word: &[String],
        word_index: usize,
    ) -> i32 {
        return self.match_word(
            word_search_line,
            word_search_line_index - 1,
            word,
            word_index + 1,
        );
    }
}
