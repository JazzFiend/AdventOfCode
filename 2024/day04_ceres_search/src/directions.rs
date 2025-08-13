pub type DirectionFn = fn(&[String], usize, usize, &str) -> Option<String>;

pub fn construct_string_to_match_right(
    grid: &[String],
    x: usize,
    y: usize,
    search_word: &str,
) -> Option<String> {
    if out_of_bounds_right(x, search_word.len(), grid[y].len()) {
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
    if out_of_bounds_left(x, search_word.len()) {
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
    if out_of_bounds_up(y, search_word.len()) {
        return None;
    }

    return Some(
        relevant_range_up(grid, y, search_word.len())
            .iter()
            .rev()
            .map(|line| line.chars().nth(x).unwrap().to_string())
            .collect(),
    );
}

pub fn construct_string_to_match_down(
    grid: &[String],
    x: usize,
    y: usize,
    search_word: &str,
) -> Option<String> {
    if out_of_bounds_down(y, search_word.len(), grid.len()) {
        return None;
    }

    return Some(
        relevant_range_down(grid, y, search_word.len())
            .iter()
            .map(|line| line.chars().nth(x).unwrap().to_string())
            .collect(),
    );
}

pub fn construct_string_to_match_up_right(
    grid: &[String],
    x: usize,
    y: usize,
    search_word: &str,
) -> Option<String> {
    // Extract these guys into functions
    if out_of_bounds_up(y, search_word.len()) || out_of_bounds_right(x, search_word.len(), grid[y].len()) {
        return None;
    }

    let to_return = Some(
        relevant_range_up(grid, y, search_word.len())
            .iter()
            .rev()
            .enumerate()
            .map(|(i, line)| line.chars().nth(x + i).unwrap().to_string())
            .collect(),
    );
    return to_return;
}

fn out_of_bounds_up(y: usize, search_word_len: usize) -> bool {
    y < search_word_len - 1
}

fn out_of_bounds_down(y: usize, search_word_length: usize, grid_length: usize) -> bool {
    y + search_word_length > grid_length
}

fn out_of_bounds_right(x: usize, search_word_length: usize, grid_horizontal_length: usize) -> bool {
    x + search_word_length > grid_horizontal_length
}

fn out_of_bounds_left(x: usize, search_word_length: usize) -> bool {
    x < search_word_length - 1
}

fn relevant_range_up(grid: &[String], y: usize, search_word_length: usize) -> &[String] {
    &grid[(1 + y - search_word_length)..=y]
}

fn relevant_range_down(grid: &[String], y: usize, search_word_length: usize) -> &[String] {
    &grid[y..=(y + search_word_length - 1)]
}