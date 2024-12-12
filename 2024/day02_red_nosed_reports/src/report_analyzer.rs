pub fn count_safe_reports(reports: Vec<&str>) -> i32 {
    if reports.len() == 0 { return 0; }

    return reports
        .iter()
        .map(|report| convert_string_to_ints(&report))
        .filter(|report_numbers| {
            is_safe(report_numbers.to_vec())
        })
        .count()
        .try_into()
        .unwrap();
}

fn convert_string_to_ints(vector: &str) -> Vec<i32> {
    vector
        .split(" ")
        .map(|n| n.parse::<i32>().unwrap())
        .collect()
}

fn is_safe(report: Vec<i32>) -> bool {
    return all_decreasing(report.clone()) || all_increasing(report.clone());
}

fn all_increasing(vector: Vec<i32>) -> bool {
    vector
        .iter()
        .zip(vector.iter().skip(1))
        .all(|(a, b)| (a < b) && (b - a <= 3))
}

fn all_decreasing(vector: Vec<i32>) -> bool {
    vector
        .iter()
        .zip(vector.iter().skip(1))
        .all(|(a, b)| (a > b) && (a - b <= 3))
}

#[cfg(test)]
mod tests {
    use std::fs::File;
    use std::io::{self, BufRead, BufReader};
    use std::path::Path;

    use super::*;

    #[test]
    fn no_reports() {
        assert_eq!(count_safe_reports(vec![]), 0);
    }

    mod one_report {
        use super::*;

        #[test]
        fn one_value() {
            let reports: Vec<&str> = vec!["3"];
            assert_eq!(count_safe_reports(reports), 1);
        }

        mod safe_report {
            use super::*;

            #[test]
            fn decreasing() {
                let reports: Vec<&str> = vec!["10 9 8 7 6 5 4 3 2 1"];
                assert_eq!(count_safe_reports(reports), 1);
            }

            #[test]
            fn increasing() {
                let reports: Vec<&str> = vec!["1 2 3 4 5 6 7 8 9 10"];
                assert_eq!(count_safe_reports(reports), 1);
            }

            #[test]
            fn no_more_than_three() {
                let reports: Vec<&str> = vec!["0 1 3 6 9 12 15"];
                assert_eq!(count_safe_reports(reports), 1);
            }
        }

        mod unsafe_report {
            use super::*;

            #[test]
            fn increasing_and_decreasing() {
                let reports: Vec<&str> = vec!["5 4 6 3 7 2 8 1 9 0 10"];
                assert_eq!(count_safe_reports(reports), 0);
            }
        }
    }

    mod many_reports {
        use super::*;

        #[test]
        fn multiple_reports_safe() {
            let reports: Vec<&str> = vec!["10 9 8", "0 2 4 6 8"];
            assert_eq!(count_safe_reports(reports), 2);
        }

        #[test]
        fn unsafe_more_than_three() {
            let reports: Vec<&str> = vec!["0 1 3 6 10", "20 19 18 1"];
            assert_eq!(count_safe_reports(reports), 0);
        }
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

    #[test]
    fn puzzle_part_one() {
        let filename = "./src/input.txt";
        match read_lines(filename) {
            Ok(lines) => {
                let reports = lines.iter().map(|l| l.as_str()).collect();
                assert_eq!(count_safe_reports(reports), 279)
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
        reader.lines().collect()
    }
}
