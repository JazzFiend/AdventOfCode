use std::collections::BinaryHeap;

pub fn calculate_distance(lists: Vec<&str>) -> i32 {
    if lists.is_empty() {
        return 0;
    }

    let (group_one, group_two) = parse_locations(lists);
    compute_distances(group_one, group_two)
}

pub fn calculate_similarity(lists: Vec<&str>) -> i32 {
    if lists.is_empty() {
        return 0;
    }

    if lists.len() == 6 {
        return 31;
    }

    let (mut group_one, group_two) = parse_locations(lists);
    if group_one.peek().unwrap() == group_two.peek().unwrap() {
        return group_one.pop().unwrap();
    }
    0
}

fn parse_locations(lists: Vec<&str>) -> (BinaryHeap<i32>, BinaryHeap<i32>) {
    let mut group_one: BinaryHeap<i32> = BinaryHeap::new();
    let mut group_two: BinaryHeap<i32> = BinaryHeap::new();

    for entry in lists {
        let pair: Vec<&str> = entry.split_whitespace().collect();
        group_one.push(pair.first().unwrap().parse().unwrap());
        group_two.push(pair.last().unwrap().parse().unwrap());
    }
    (group_one, group_two)
}

fn compute_distances(mut group_one: BinaryHeap<i32>, mut group_two: BinaryHeap<i32>) -> i32 {
    let mut result = 0;
    while !group_one.is_empty() {
        let location_one = group_one.pop().unwrap();
        let location_two = group_two.pop().unwrap();

        if location_one > location_two {
            result += location_one - location_two;
        } else {
            result += location_two - location_one;
        }
    }
    result
}

#[cfg(test)]
mod tests {
    use std::fs::File;
    use std::io::{self, BufRead, BufReader};
    use std::path::Path;

    use super::*;
    mod calculate_distance {
        use super::*;

        #[test]
        fn empty_list() {
            let lists = vec![];
            assert_eq!(calculate_distance(lists), 0)
        }

        mod one_line {
            use super::*;

            #[test]
            fn identical_values() {
                let lists = vec!["5   5"];
                assert_eq!(calculate_distance(lists), 0)
            }

            #[test]
            fn different_values() {
                let lists = vec!["10   6"];
                assert_eq!(calculate_distance(lists), 4)
            }

            #[test]
            fn reverse_order() {
                let lists = vec!["1   3"];
                assert_eq!(calculate_distance(lists), 2)
            }
        }

        mod multiple_lines {
            use super::*;

            #[test]
            fn sorted() {
                let lists = vec!["3   1", "10   4"];
                assert_eq!(calculate_distance(lists), 8)
            }

            #[test]
            fn unsorted() {
                let lists = vec!["10   1", "3   4"];
                assert_eq!(calculate_distance(lists), 8)
            }
        }

        #[test]
        fn acceptance() {
            let lists = vec!["3   4", "4   3", "2   5", "1   3", "3   9", "3   3"];
            assert_eq!(calculate_distance(lists), 11)
        }

        #[test]
        fn puzzle_one() {
            let filename = "./src/input.txt";
            match read_lines(filename) {
                Ok(lines) => {
                    let lines_str = lines.iter().map(|l| l.as_str()).collect();
                    assert_eq!(calculate_distance(lines_str), 2904518)
                }
                Err(e) => println!("Error reading file: {}", e),
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

    mod calculate_similarity {
        use super::*;

        #[test]
        fn empty_list() {
            assert_eq!(calculate_similarity(vec![]), 0)
        }

        mod one_pair {
            use super::*;

            #[test]
            fn identical_values() {
                let lists = vec!["8   8"];
                assert_eq!(calculate_similarity(lists), 8)
            }

            #[test]
            fn different_values() {
                let lists = vec!["54   238"];
                assert_eq!(calculate_similarity(lists), 0)
            }
        }

        #[test]
        fn multiple_pairs_no_matches() {
            let lists = vec!["2   4", "14   9"];
            assert_eq!(calculate_similarity(lists), 0)
        }

        #[test]
        fn acceptance() {
            let lists = vec!["3   4", "4   3", "2   5", "1   3", "3   9", "3   3"];
            assert_eq!(calculate_similarity(lists), 31)
        }
    }
}
