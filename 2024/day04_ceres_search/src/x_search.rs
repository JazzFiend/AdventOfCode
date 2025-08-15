pub fn solve_x_search(grid: Vec<String>, center: char, cross: (char, char)) -> usize {
    if grid.len() == 0 {
        return 0;
    }

    let mut matches = 0;

    let y_range = 0..grid.len() - 1;
    let x_range = 0..grid.first().unwrap().len();

    for y in y_range.clone() {
        for x in x_range.clone() {
            if is_center_match(&grid, center, x, y) && is_cross_match(&grid, cross, x, y) {
                matches += 1;
            }
        }
    }
    return matches;
}

fn is_center_match(grid: &Vec<String>, center: char, x: usize, y: usize) -> bool {
    get_point(grid, x, y) == center
}

fn is_cross_match(grid: &Vec<String>, cross: (char, char), x: usize, y: usize) -> bool {
    is_match_negative_slope(grid, cross, x, y) && is_match_positive_slope(grid, cross, x, y)
}

fn is_match_positive_slope(grid: &Vec<String>, cross: (char, char), x: usize, y: usize) -> bool {
    (get_down_left_point(&grid, x, y).unwrap_or_default() == cross.0
        && get_up_right_point(&grid, x, y).unwrap_or_default() == cross.1)
        || (get_down_left_point(&grid, x, y).unwrap_or_default() == cross.1
            && get_up_right_point(&grid, x, y).unwrap_or_default() == cross.0)
}

fn is_match_negative_slope(grid: &Vec<String>, cross: (char, char), x: usize, y: usize) -> bool {
    (get_up_left_point(&grid, x, y).unwrap_or_default() == cross.0
        && get_down_right_point(&grid, x, y).unwrap_or_default() == cross.1)
        || (get_up_left_point(&grid, x, y).unwrap_or_default() == cross.1
            && get_down_right_point(&grid, x, y).unwrap_or_default() == cross.0)
}

fn get_point(grid: &Vec<String>, x: usize, y: usize) -> char {
    grid[y].chars().nth(x).unwrap()
}

fn get_up_right_point(grid: &Vec<String>, x: usize, y: usize) -> Option<char> {
    if y == 0 || x == grid[y - 1].len() - 1 {
        return None;
    }
    return Some(grid[y - 1].chars().nth(x + 1).unwrap());
}

fn get_down_right_point(grid: &Vec<String>, x: usize, y: usize) -> Option<char> {
    if y == grid.len() - 1 || x == grid[y - 1].len() - 1 {
        return None;
    }
    return Some(grid[y + 1].chars().nth(x + 1).unwrap());
}

fn get_up_left_point(grid: &Vec<String>, x: usize, y: usize) -> Option<char> {
    if y == 0 || x == 0 {
        return None;
    }
    return Some(grid[y - 1].chars().nth(x - 1).unwrap());
}

fn get_down_left_point(grid: &Vec<String>, x: usize, y: usize) -> Option<char> {
    if y == 0 || x == grid[y - 1].len() - 1 {
        return None;
    }
    return Some(grid[y + 1].chars().nth(x - 1).unwrap());
}

#[cfg(test)]
mod tests {
    use std::fs::File;
    use std::io::{self, BufRead, BufReader};
    use std::path::Path;

    use super::*;

    mod degenerate {
        use super::*;

        #[test]
        fn no_grid() {
            assert_eq!(solve_x_search(vec![], 'B', ('A', 'C')), 0);
        }

        #[test]
        fn no_matches() {
            let grid = vec![
                String::from("......."),
                String::from("......."),
                String::from("......."),
                String::from("......."),
                String::from("......."),
                String::from("......."),
            ];
            assert_eq!(solve_x_search(grid, 'B', ('A', 'C')), 0);
        }

        #[test]
        fn center_on_borders() {
            let grid = vec![
                String::from("BBB"),
                String::from("BBB"),
                String::from("BBB"),
            ];
            assert_eq!(solve_x_search(grid, 'B', ('A', 'C')), 0);
        }
    }

    mod one_match {
        use super::*;

        #[test]
        fn one_match_right() {
            let grid = vec![
                String::from("..."),
                String::from("A.C"),
                String::from(".B."),
                String::from("A.C"),
            ];
            assert_eq!(solve_x_search(grid, 'B', ('A', 'C')), 1);
        }

        #[test]
        fn one_match_left() {
            let grid = vec![
                String::from("..."),
                String::from("C.A"),
                String::from(".B."),
                String::from("C.A"),
            ];
            assert_eq!(solve_x_search(grid, 'B', ('A', 'C')), 1);
        }

        #[test]
        fn one_match_combo_1() {
            let grid = vec![
                String::from("..."),
                String::from("A.A"),
                String::from(".B."),
                String::from("C.C"),
            ];
            assert_eq!(solve_x_search(grid, 'B', ('A', 'C')), 1);
        }

        #[test]
        fn one_match_combo_2() {
            let grid = vec![
                String::from("..."),
                String::from("C.C"),
                String::from(".B."),
                String::from("A.A"),
            ];
            assert_eq!(solve_x_search(grid, 'B', ('A', 'C')), 1);
        }
    }

    mod many_matches {
        use super::*;

        #[test]
        fn many_matches() {
            let grid = vec![
                String::from(".....A.C."),
                String::from("C.C...B.."),
                String::from(".B.C.A.C."),
                String::from("A.A.B...."),
                String::from(".B.C.A..."),
                String::from("C.C......"),
            ];
            assert_eq!(solve_x_search(grid, 'B', ('A', 'C')), 4);
        }
    }

    mod acceptance {
        use super::*;

        #[test]
        fn simple() {
            let grid = vec![
                String::from("M.S"),
                String::from(".A."),
                String::from("M.S"),
            ];
            assert_eq!(solve_x_search(grid, 'A', ('M', 'S')), 1);
        }

        #[test]
        fn complex() {
            let grid = vec![
                String::from(".M.S......"),
                String::from("..A..MSMS."),
                String::from(".M.S.MAA.."),
                String::from("..A.ASMSM."),
                String::from(".M.S.M...."),
                String::from(".........."),
                String::from("S.S.S.S.S."),
                String::from(".A.A.A.A.."),
                String::from("M.M.M.M.M."),
                String::from(".........."),
            ];
            assert_eq!(solve_x_search(grid, 'A', ('M', 'S')), 9);
        }

        #[test]
        fn puzzle_part_two() {
            let filename = "./src/input.txt";
            match read_lines(filename) {
                Ok(grid) => {
                    assert_eq!(solve_x_search(grid, 'A', ('M', 'S')), 0)
                }
                Err(e) => {
                    println!("Error reading file: {}", e);
                    assert!(false, "An error occured")
                }
            }
        }

        fn read_lines<P>(filename: P) -> io::Result<Vec<String>>
        where
            P: AsRef<Path>,
        {
            let file = File::open(filename)?;
            let reader = BufReader::new(file);
            return reader.lines().collect();
        }
    }
}
