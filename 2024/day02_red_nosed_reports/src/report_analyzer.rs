pub trait ReportAnalyzer {
    fn count_safe_reports(&self, reports: Vec<&str>) -> i32;

    fn convert_string_to_ints(&self, numbers: &str) -> Vec<i32> {
        numbers
            .split(" ")
            .map(|n| n.parse::<i32>().unwrap())
            .collect()
    }

    fn is_safe(&self, report: &Vec<i32>) -> bool {
        return self.all_decreasing(&report) || self.all_increasing(&report);
    }

    fn all_increasing(&self, vector: &Vec<i32>) -> bool {
        vector
            .iter()
            .zip(vector.iter().skip(1))
            .all(|(a, b)| (a < b) && (b - a <= 3))
    }

    fn all_decreasing(&self, vector: &Vec<i32>) -> bool {
        vector
            .iter()
            .zip(vector.iter().skip(1))
            .all(|(a, b)| (a > b) && (a - b <= 3))
    }
}
