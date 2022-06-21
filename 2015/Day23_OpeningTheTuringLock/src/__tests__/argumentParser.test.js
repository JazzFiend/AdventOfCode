const ArgumentParser = require('../argumentParser');

test('An empty array of arguments should result in all undefined entries', () => {
  const expected = {
    registerName: undefined,
    jumpOffset: undefined,
  };
  expect(ArgumentParser.parse([])).toEqual(expected);
});

test('Providing just a string should sub it into registerName', () => {
  const expected = {
    registerName: 'a',
    jumpOffset: undefined,
  };
  expect(ArgumentParser.parse(['a'])).toEqual(expected);
});

test('Providing just a number should sub it into jumpOffset', () => {
  const expected = {
    registerName: undefined,
    jumpOffset: -8,
  };
  expect(ArgumentParser.parse([-8])).toEqual(expected);
});

test('Providing a string followed by a number should put both of them in the output', () => {
  const expected = {
    registerName: 'b',
    jumpOffset: 15,
  };
  expect(ArgumentParser.parse(['b', 15])).toEqual(expected);
});

test('Providing two strings should use the first one, but leave the jump offset blank', () => {
  const expected = {
    registerName: 'b',
    jumpOffset: undefined,
  };
  expect(ArgumentParser.parse(['b', 'a'])).toEqual(expected);
});
