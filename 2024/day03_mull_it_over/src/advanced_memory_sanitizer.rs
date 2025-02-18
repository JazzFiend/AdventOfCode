use crate::{memory_filter::filter_dont_commands, memory_sanitizer::{calculate_sum_of_products, fix_corrupted}};

pub fn evaulate_commands(corrupted_memory: Vec<String>) -> i32 {
    let filtered_memory = filter_dont_commands(corrupted_memory);
    let sanitized_memory = fix_corrupted(filtered_memory);
    return calculate_sum_of_products(sanitized_memory);
}

#[cfg(test)]
mod tests {
    use std::fs::File;
    use std::io::{self, BufRead, BufReader};
    use std::path::Path;
    use super::*;

    #[test]
    fn acceptance() {
        let corrupted_memory =
            vec!["xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))".to_string()];
        assert_eq!(evaulate_commands(corrupted_memory), 48);
    }

    #[test]
    fn puzzle_part_two() {
        let filename = "./src/input.txt";
        match read_lines(filename) {
            Ok(lines) => {
                let combined_lines = vec![lines.join("")];
                let total = evaulate_commands(combined_lines);
                assert_eq!(total, -1)
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
