use std::vec;

pub fn fix_corrupted(corrupted_memory: Vec<&str>) -> Vec<(i32, i32)> {
    return vec![];
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn blank_memory() {
        let corrupted_memory = vec![""];
        let expected: Vec<(i32, i32)> = vec![];
        assert_eq!(fix_corrupted(corrupted_memory), expected);
    }
}
