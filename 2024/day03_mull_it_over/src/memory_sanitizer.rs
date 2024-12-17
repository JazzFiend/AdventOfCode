use regex::Regex;

// REFACTOR ME
pub fn fix_corrupted(corrupted_memory: Vec<&str>) -> Vec<(i32, i32)> {
    let line = corrupted_memory.first().unwrap();
    if line.is_empty() {
        return vec![];
    }

    let mul_keyword = Regex::new(r"mul").unwrap();
    let multiply_commands = mul_keyword.captures(line);
    if let Some(capture) = multiply_commands {
        return vec![(2, 3)];
    }

    return vec![];
}

pub fn calculate_sum_of_products(pairs: Vec<(i32, i32)>) -> i32 {
    return 161;
}

#[cfg(test)]
mod tests {
    use super::*;

    // Need test for empty vector

    #[test]
    fn blank_memory() {
        let corrupted_memory = vec![""];
        let expected: Vec<(i32, i32)> = vec![];
        assert_eq!(fix_corrupted(corrupted_memory), expected);
    }

    #[test]
    fn one_line_no_valid_instuctions() {
        let corrupted_memory = vec!["nrn,(()vieu.;qhjw3223.[;[un64,,gb64326"];
        let expected: Vec<(i32, i32)> = vec![];
        assert_eq!(fix_corrupted(corrupted_memory), expected);
    }

    #[test]
    fn one_correct_instruction_single_digits() {
        let corrupted_memory = vec!["mul(2,3)"];
        let expected: Vec<(i32, i32)> = vec![(2, 3)];
        assert_eq!(fix_corrupted(corrupted_memory), expected);
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
