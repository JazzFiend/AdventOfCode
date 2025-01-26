use regex::Regex;

pub fn filter_dont_commands(corrupted_memory: Vec<String>) -> Vec<String> {
    if corrupted_memory.is_empty() {
        return vec![];
    }

    let do_regex = Regex::new(r"do\(\)").unwrap();
    let line = corrupted_memory.first().unwrap();

    if do_regex.captures(line).is_some() {
        let do_chunks: Vec<&str> = line.split("do()").collect();
        let dos_removed: String = do_chunks.iter().map(|s| *s).collect();
        return vec![dos_removed]
    }

    return corrupted_memory;
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn empty_memory() {
        let corrupted_memory = vec![];
        assert_eq!(
            filter_dont_commands(corrupted_memory.clone()),
            corrupted_memory
        );
    }

    #[test]
    fn empty_line() {
        let corrupted_memory = vec!["".to_string()];
        assert_eq!(
            filter_dont_commands(corrupted_memory.clone()),
            corrupted_memory
        );
    }

    #[test]
    fn no_do_or_dont_commands() {
        let corrupted_memory = vec!["wfjnewfwekmul(3,4)mul(242,7434)fnhewuiihfuew".to_string()];
        assert_eq!(
            filter_dont_commands(corrupted_memory.clone()),
            corrupted_memory
        );
    }

    #[test]
    fn only_do_command() {
        let corrupted_memory = vec!["do()".to_string()];
        let expected = vec![""];
        assert_eq!(filter_dont_commands(corrupted_memory), expected);
    }

    #[test]
    fn do_with_other_chars() {
        let corrupted_memory = vec!["hfwiudo()vnmul()njk".to_string()];
        let expected = vec!["hfwiuvnmul()njk"];
        assert_eq!(filter_dont_commands(corrupted_memory), expected);
    }
}
