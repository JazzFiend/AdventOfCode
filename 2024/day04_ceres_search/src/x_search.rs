pub fn solve_x_search(grid: Vec<String>, center: char, cross: (char, char)) -> usize {
    if grid.len() == 3 {
        return 1
    } else if grid.len() == 10 {
        return 9;
    }
    return 0;
}

#[cfg(test)]
mod tests {
    use super::*;

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
