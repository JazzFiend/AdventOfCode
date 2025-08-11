pub type DirectionFn = fn(&[String], usize, usize, &str) -> Option<String>;

pub fn construct_string_to_match_right(
    grid: &[String],
    x: usize,
    y: usize,
    search_word: &str,
) -> Option<String> {
    if x + search_word.len() > grid[y].len() {
        return None;
    }

    let this_line = &grid[y];
    return Some((&this_line[x..x + search_word.len()]).to_string());
}

pub fn construct_string_to_match_left(
    grid: &[String],
    x: usize,
    y: usize,
    search_word: &str,
) -> Option<String> {
    if x < search_word.len() - 1 {
        return None;
    }

    let this_line = &grid[y];
    return Some(
        (&this_line[(1 + x - search_word.len())..=x])
            .chars()
            .rev()
            .collect(),
    );
}

pub fn construct_string_to_match_up(
    grid: &[String],
    x: usize,
    y: usize,
    search_word: &str,
) -> Option<String> {
    if y < search_word.len() - 1 {
        return None;
    }

    return Some(
        grid[(1 + y - search_word.len())..=y]
            .iter()
            .rev()
            .map(|line| line.chars().nth(x).unwrap().to_string())
            .collect(),
    );
}