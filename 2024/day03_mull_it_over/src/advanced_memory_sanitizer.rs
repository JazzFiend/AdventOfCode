use crate::memory_filter::filter_dont_commands;
use crate::memory_sanitizer::*;

pub fn evaulate_commands(corrupted_memory: Vec<&str>) -> i32 {
    let filtered_memory = filter_dont_commands(corrupted_memory);
    let sanitized_memory = fix_corrupted(filtered_memory);
    return calculate_sum_of_products(sanitized_memory);
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn acceptance() {
        let corrupted_memory =
            vec!["xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"];
        assert_eq!(evaulate_commands(corrupted_memory), 48);
    }
}
