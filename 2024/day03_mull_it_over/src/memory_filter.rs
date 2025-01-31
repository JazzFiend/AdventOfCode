use regex::Regex;

pub fn filter_dont_commands(corrupted_memory: Vec<String>) -> Vec<String> {
    if corrupted_memory.is_empty() {
        return vec![];
    }

    let line = corrupted_memory.first().unwrap().to_string();
    let dos = find_dos(line.clone());
    let donts = find_donts(line.clone());

    if donts.is_empty() {
        return vec![line]
    }
    let remove_sections = calculate_remove_sections(dos, donts, line.len());

    return vec![remove_chunk(line, remove_sections.0, remove_sections.1)];
}

fn find_donts(line: String) -> Vec<(usize, usize)> {
    let dont_regex = Regex::new(r"don't\(\)").unwrap();
    return find_command_locations(line.to_string(), dont_regex.clone());
}

fn find_dos(line: String) -> Vec<(usize, usize)> {
    let do_regex = Regex::new(r"do\(\)").unwrap();
    return find_command_locations(line.to_string(), do_regex.clone());
}

fn find_command_locations(line: String, command: Regex) -> Vec<(usize, usize)> {
    if let Some(mat) = command.find(&line) {
        return vec![(mat.start(), mat.end())];
    }
    return vec![];
}

fn calculate_remove_sections(do_matches: Vec<(usize, usize)>, dont_matches: Vec<(usize, usize)>, string_length: usize) -> (usize, usize) {
    if do_matches.is_empty() {
        (dont_matches.first().unwrap().1, string_length)
    } else {
        return (dont_matches.first().unwrap().1, do_matches.first().unwrap().0);
    }
}

fn remove_chunk(s: String, start: usize, end: usize) -> String {
    format!("{}{}", &s[..start], &s[end..])
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
        let expected = vec!["do()".to_string()];
        assert_eq!(filter_dont_commands(corrupted_memory), expected);
    }

    #[test]
    fn do_with_other_chars() {
        let corrupted_memory = vec!["hfwiudo()vnmul()njk".to_string()];
        let expected = vec!["hfwiudo()vnmul()njk".to_string()];
        assert_eq!(filter_dont_commands(corrupted_memory), expected);
    }

    #[test]
    fn only_dont_command() {
        let corrupted_memory = vec!["don't()".to_string()];
        let expected = vec!["don't()".to_string()];
        assert_eq!(filter_dont_commands(corrupted_memory), expected);
    }

    #[test]
    fn remove_everything_after_dont() {
        let corrupted_memory = vec!["frqgrdon't()huireuyverbyvuewrb".to_string()];
        let expected = vec!["frqgrdon't()".to_string()];
        assert_eq!(filter_dont_commands(corrupted_memory), expected);
    }

    #[test]
    fn remove_between_do_and_dont() {
        let corrupted_memory = vec!["cniuwnidon't()dnhewjfnwuiwfryudo()jdewid".to_string()];
        let expected = vec!["cniuwnidon't()do()jdewid".to_string()];
        assert_eq!(filter_dont_commands(corrupted_memory), expected);
    }
}
