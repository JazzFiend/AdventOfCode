pub fn filter_dont_commands(corrupted_memory: Vec<&str>) -> Vec<&str> {
    return corrupted_memory
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn empty_memory() {
        let corrupted_memory = vec![];
        assert_eq!(filter_dont_commands(corrupted_memory.clone()), corrupted_memory);
    }
}