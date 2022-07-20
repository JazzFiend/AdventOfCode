const CopyProtectionCodeGenerator = require("../main/CopyProtectionCodeGenerator");

describe('CopyProtectionCodeGenerator', () => {
  test('Location 0,0 should be undefined', () => {
    expect(CopyProtectionCodeGenerator.generateCode(0, 0)).toEqual(undefined);
  });

  test('All entries in the zeroth row should be undefined', () => {
    expect(CopyProtectionCodeGenerator.generateCode(0, 1)).toEqual(undefined);
    expect(CopyProtectionCodeGenerator.generateCode(0, 5)).toEqual(undefined);
    expect(CopyProtectionCodeGenerator.generateCode(0, 45)).toEqual(undefined);
    expect(CopyProtectionCodeGenerator.generateCode(0, 284)).toEqual(undefined);
  });

  test('All entries in the zeroth column should be undefined', () => {
    expect(CopyProtectionCodeGenerator.generateCode(1, 0)).toEqual(undefined);
    expect(CopyProtectionCodeGenerator.generateCode(9, 0)).toEqual(undefined);
    expect(CopyProtectionCodeGenerator.generateCode(74, 0)).toEqual(undefined);
    expect(CopyProtectionCodeGenerator.generateCode(947, 0)).toEqual(undefined);
  });

  test('Location 1,1 should have the initial value', () => {
    expect(CopyProtectionCodeGenerator.generateCode(1, 1)).toEqual(20151125);
  });

  test('Location 1,2 should advance the hash once.', () => {
    expect(CopyProtectionCodeGenerator.generateCode(1, 2)).toEqual(31916031);
  });

  test('Location 2,1 should advance the hash twice.', () => {
    expect(CopyProtectionCodeGenerator.generateCode(2, 1)).toEqual(18749137);
  });

  test('Test Location 3,4', () => {
    expect(CopyProtectionCodeGenerator.generateCode(3, 4)).toEqual(21345942);
  });

  test('Test Location 6,6', () => {
    expect(CopyProtectionCodeGenerator.generateCode(6, 6)).toEqual(27995004);
  });

  test('Puzzle 1', () => {
    expect(CopyProtectionCodeGenerator.generateCode(3083, 2978)).toEqual(2650453);
  });
});
