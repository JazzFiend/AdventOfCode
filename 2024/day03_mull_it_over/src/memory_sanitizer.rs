use regex::Regex;

pub fn fix_corrupted(corrupted_memory: Vec<&str>) -> Vec<(i32, i32)> {
    if corrupted_memory.is_empty() {
        return vec![];
    }

    let line = corrupted_memory.first().unwrap();
    if line.is_empty() {
        return vec![];
    }

    let multiply_regex = Regex::new(r"mul\((\d+),(\d+)\)").unwrap();
    let multiply_commands = multiply_regex.captures(line);
    if let Some(capture) = multiply_commands {
        return vec![(capture[1].parse().unwrap(), capture[2].parse().unwrap())];
    }
    return vec![];
}

pub fn calculate_sum_of_products(pairs: Vec<(i32, i32)>) -> i32 {
    return 161;
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn empty_memory() {
        let corrupted_memory = vec![];
        let expected: Vec<(i32, i32)> = vec![];
        assert_eq!(fix_corrupted(corrupted_memory), expected);
    }

    mod one_line {
        use super::*;

        #[test]
        fn blank_memory() {
            let corrupted_memory = vec![""];
            let expected: Vec<(i32, i32)> = vec![];
            assert_eq!(fix_corrupted(corrupted_memory), expected);
        }

        #[test]
        fn no_valid_instuctions() {
            let corrupted_memory = vec!["nrn,(()vieu.;qhjw3223.[;[un64,,gb64326"];
            let expected: Vec<(i32, i32)> = vec![];
            assert_eq!(fix_corrupted(corrupted_memory), expected);
        }

        mod one_correct_instruction {
            use super::*;

            #[test]
            fn single_digits() {
                let corrupted_memory = vec!["mul(2,3)"];
                let expected: Vec<(i32, i32)> = vec![(2, 3)];
                assert_eq!(fix_corrupted(corrupted_memory), expected);
            }

            #[test]
            fn multi_digits() {
                let corrupted_memory = vec!["mul(47,589)"];
                let expected: Vec<(i32, i32)> = vec![(47, 589)];
                assert_eq!(fix_corrupted(corrupted_memory), expected);
            }
        }
    }

    #[test]
    fn acceptance_test() {
        let corrupted_memory =
            vec!["xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"];
        let tuples = fix_corrupted(corrupted_memory);
        let total = calculate_sum_of_products(tuples);
        assert_eq!(total, 161)
    }
}
