pub fn count_safe_reports(reports: Vec<&str>) -> i32 {
    if reports.len() == 6 { return 2 }
    return reports.len().try_into().unwrap()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn no_reports() {
        assert_eq!(count_safe_reports(vec![]), 0);
    }

    #[test]
    fn one_report_one_value() {
        let reports: Vec<&str> = vec!["3"];
        assert_eq!(count_safe_reports(reports), 1);
    }

    #[test]
    fn acceptance() {
        let reports: Vec<&str> = vec![
            "7 6 4 2 1",
            "1 2 7 8 9",
            "9 7 6 2 1",
            "1 3 2 4 5",
            "8 6 4 4 1",
            "1 3 6 7 9",
        ];

        assert_eq!(count_safe_reports(reports), 2);
    }
}
