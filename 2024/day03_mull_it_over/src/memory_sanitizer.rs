use regex::Regex;

pub fn fix_corrupted(corrupted_memory: Vec<&str>) -> Vec<(i32, i32)> {
    let multiply_regex = Regex::new(r"mul\((\d+),(\d+)\)").unwrap();

    return corrupted_memory
        .iter()
        .flat_map(|line| {
            multiply_regex.captures_iter(line).map(|command| {
                (
                    command[1].parse::<i32>().unwrap(),
                    command[2].parse::<i32>().unwrap(),
                )
            })
        })
        .collect();
}

pub fn calculate_sum_of_products(pairs: Vec<(i32, i32)>) -> i32 {
    return pairs
        .iter()
        .map(|pair| pair.0 * pair.1)
        .reduce(|a, b| a + b)
        .unwrap();
}

#[cfg(test)]
mod tests {
    use super::*;

    mod fix_corrupted {
        use super::*;

        #[test]
        fn empty_memory() {
            let corrupted_memory = vec![];
            let expected: Vec<(i32, i32)> = vec![];
            assert_eq!(fix_corrupted(corrupted_memory), expected);
        }

        mod one_line {
            use super::*;

            #[test]
            fn blank_memory() {
                let corrupted_memory = vec![""];
                let expected: Vec<(i32, i32)> = vec![];
                assert_eq!(fix_corrupted(corrupted_memory), expected);
            }

            #[test]
            fn no_valid_instuctions() {
                let corrupted_memory = vec!["nrn,(()vieu.;qhjw3223.[;[un64,,gb64326"];
                let expected: Vec<(i32, i32)> = vec![];
                assert_eq!(fix_corrupted(corrupted_memory), expected);
            }

            mod one_correct_instruction {
                use super::*;

                #[test]
                fn single_digits() {
                    let corrupted_memory = vec!["mul(2,3)"];
                    let expected: Vec<(i32, i32)> = vec![(2, 3)];
                    assert_eq!(fix_corrupted(corrupted_memory), expected);
                }

                #[test]
                fn multi_digits() {
                    let corrupted_memory = vec!["mul(47,589)"];
                    let expected: Vec<(i32, i32)> = vec![(47, 589)];
                    assert_eq!(fix_corrupted(corrupted_memory), expected);
                }
            }

            #[test]
            fn multiple_correct_instructions() {
                let corrupted_memory = vec!["qqewdrmul(2,29)hgyuiymul(55,5)njjnrjrei"];
                let expected: Vec<(i32, i32)> = vec![(2, 29), (55, 5)];
                assert_eq!(fix_corrupted(corrupted_memory), expected);
            }
        }

        #[test]
        fn multiple_lines() {
            let corrupted_memory = vec!["mul(2,3)", "mul)(mul(2,44)(5,9)mul(345)"];
            let expected: Vec<(i32, i32)> = vec![(2, 3), (2, 44)];
            assert_eq!(fix_corrupted(corrupted_memory), expected);
        }
    }

    mod calculate_sum_of_products {
        use super::*;

        #[test]
        fn sum_of_products() {
            assert_eq!(
                calculate_sum_of_products(vec![(2, 5), (10, 20), (1, 0), (11, 234)]),
                (2 * 5) + (10 * 20) + (1 * 0) + (11 * 234)
            )
        }
    }

    #[test]
    fn acceptance_test() {
        let corrupted_memory =
            vec!["xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"];
        let tuples = fix_corrupted(corrupted_memory);
        let total = calculate_sum_of_products(tuples);
        assert_eq!(total, 161)
    }
}
