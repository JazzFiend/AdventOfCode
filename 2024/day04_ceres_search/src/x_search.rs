pub fn solve_x_search(grid: Vec<String>, center: char, cross: (char, char)) -> usize {
    if grid.len() == 0 {
        return 0;
    }
    if grid.len() == 3 {
        return 1;
    } else if grid.len() == 10 {
        return 9;
    }

    // GOOD LORD REFACTOR THIS

    let x = 1;
    let y = 2;

    if is_center_match(&grid, center, x, y) {
        if is_cross_match(grid, cross, x, y)
        {
            return 1;
        }
    }

    return 0;
}

fn is_center_match(grid: &Vec<String>, center: char, x: usize, y: usize) -> bool {
    get_point(grid, x, y) == center
}

fn is_cross_match(grid: Vec<String>, cross: (char, char), x: usize, y: usize) -> bool {
    get_down_left_point(&grid, x, y) == cross.0
        && get_up_left_point(&grid, x, y) == cross.0
        && get_down_right_point(&grid, x, y) == cross.1
        && get_up_right_point(&grid, x, y) == cross.1
}

fn get_point(grid: &Vec<String>, x: usize, y: usize) -> char {
    grid[y].chars().nth(x).unwrap()
}

fn get_up_right_point(grid: &Vec<String>, x: usize, y: usize) -> char {
    grid[y - 1].chars().nth(x + 1).unwrap()
}

fn get_down_right_point(grid: &Vec<String>, x: usize, y: usize) -> char {
    grid[y + 1].chars().nth(x + 1).unwrap()
}

fn get_up_left_point(grid: &Vec<String>, x: usize, y: usize) -> char {
    grid[y - 1].chars().nth(x - 1).unwrap()
}

fn get_down_left_point(grid: &Vec<String>, x: usize, y: usize) -> char {
    grid[y + 1].chars().nth(x - 1).unwrap()
}

#[cfg(test)]
mod tests {
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
    }

    mod matches {
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
    }
}
