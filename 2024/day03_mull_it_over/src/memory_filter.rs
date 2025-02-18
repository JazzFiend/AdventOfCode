use regex::Regex;

pub fn filter_dont_commands(corrupted_memory: Vec<String>) -> Vec<String> {
    return corrupted_memory.iter().map(|line| {
        let pairs_removed = remove_regex(line.to_string(), Regex::new(r"don't\(\).*?do\(\)").unwrap());
        let donts_removed = remove_regex(pairs_removed, Regex::new(r"don't\(\).*").unwrap());
        let dos_removed = remove_regex(donts_removed, Regex::new(r"do\(\)").unwrap());
        return dos_removed;
    }).collect();
}

fn remove_regex(text: String, regex: Regex) -> String {
    return remove_ranges(&text, &find_all_matches(regex, &text));
}

fn find_all_matches(regex: Regex, text: &str) -> Vec<(usize, usize)> {
    return regex
        .find_iter(text)
        .map(|mat| (mat.start(), mat.end()))
        .collect();
}

fn remove_ranges(text: &str, ranges: &[(usize, usize)]) -> String {
    if ranges.is_empty() {
        return text.to_string();
    }

    let ranges_removed = ranges
        .iter()
        .fold((String::new(), 0), |(acc, last_end), &(start, end)| {
            return add_next_range(text.to_owned(), acc, (last_end, start), end);
        })
        .0;
    return add_remaining(ranges_removed, text.to_owned(), ranges);
}

fn add_next_range(
    full_text: String,
    accumulator: String,
    boundary: (usize, usize),
    next_start: usize,
) -> (String, usize) {
    return (
        accumulator + (&full_text[boundary.0..boundary.1]),
        next_start,
    );
}

fn add_remaining(result: String, full_text: String, ranges: &[(usize, usize)]) -> String {
    return result + &full_text[ranges.last().unwrap().1..full_text.len()];
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
        let expected = vec!["".to_string()];
        assert_eq!(filter_dont_commands(corrupted_memory), expected);
    }

    #[test]
    fn do_with_other_chars() {
        let corrupted_memory = vec!["hfwiudo()vnmul()njk".to_string()];
        let expected = vec!["hfwiuvnmul()njk".to_string()];
        assert_eq!(filter_dont_commands(corrupted_memory), expected);
    }

    #[test]
    fn only_dont_command() {
        let corrupted_memory = vec!["don't()".to_string()];
        let expected = vec!["".to_string()];
        assert_eq!(filter_dont_commands(corrupted_memory), expected);
    }

    #[test]
    fn remove_everything_after_dont() {
        let corrupted_memory = vec!["frqgrdon't()huireuyverbyvuewrb".to_string()];
        let expected = vec!["frqgr".to_string()];
        assert_eq!(filter_dont_commands(corrupted_memory), expected);
    }

    #[test]
    fn remove_between_do_and_dont() {
        let corrupted_memory = vec!["cniuwnidon't()dnhewjfnwuiwfryudo()jdewid".to_string()];
        let expected = vec!["cniuwnijdewid".to_string()];
        assert_eq!(filter_dont_commands(corrupted_memory), expected);
    }

    #[test]
    fn dont_do_dont() {
        let corrupted_memory = vec!["fnirdon't()rbgegvvdo()bnefwdon't()eloep".to_string()];
        let expected = vec!["fnirbnefw".to_string()];
        assert_eq!(filter_dont_commands(corrupted_memory), expected);
    }

    #[test]
    fn bunch_of_dos_and_donts() {
        let corrupted_memory = vec!["1don't()2do()3don't()4do()5".to_string()];
        let expected = vec!["135".to_string()];
        assert_eq!(filter_dont_commands(corrupted_memory), expected);
    }

    #[test]
    fn out_of_order_commands() {
        let corrupted_memory = vec!["1do()2don't()3don't()4do()5do()6don't()7don't()8".to_string()];
        let expected = vec!["1256".to_string()];
        assert_eq!(filter_dont_commands(corrupted_memory), expected);
    }

    #[test]
    fn bunch_of_memory_commands() {
        let corrupted_memory = vec![
            "don't()".to_string(),
            "12don't()345".to_string(),
            "1don't()2do()3".to_string()
        ];
        let expected = vec![
            "",
            "12",
            "13"
        ];
        assert_eq!(filter_dont_commands(corrupted_memory), expected);
    }
}
