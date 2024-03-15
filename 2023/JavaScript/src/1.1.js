import * as fs from "fs";
// six1mpffbnbnnlxthree -> 63
// 4eight3one92-> 42
// 9nine2xnhvjtjlzj48-> 98
// result : 203
const numberStrings = new Map([
    ["zero", "0"],
    ["one", "1"],
    ["two", "2"],
    ["three", "3"],
    ["four", "4"],
    ["five", "5"],
    ["six", "6"],
    ["seven", "7"],
    ["eight", "8"],
    ["nine", "9"],
]);
const checkNumberString = (line, letterIndex) => {
    for (let [key, value] of numberStrings) {
        if (line.startsWith(key, letterIndex)) {
            return [true, key, value];
        }
    }
    return [false, "", ""];
};
const inputData = fs.readFileSync("./src/1.1.txt", "utf8");
const lines = inputData.split("\n");
lines.pop();
let acum = 0;
let firstNumber = "";
let secondNumber = "";
let isFirstNumberStored = false;
for (let line of lines) {
    const letters = line.split("");
    for (let letterIndex = 0; letterIndex < letters.length; letterIndex++) {
        const [isStringNumber, numberKey, numberValue] = checkNumberString(line, letterIndex);
        const letter = letters[letterIndex];
        if (!isNaN(Number(letter))) {
            if (!isFirstNumberStored) {
                firstNumber = letter;
                secondNumber = letter;
                isFirstNumberStored = true;
            }
            else {
                secondNumber = letter;
            }
        }
        else if (isStringNumber) {
            if (!isFirstNumberStored) {
                firstNumber = numberValue;
                secondNumber = firstNumber;
                isFirstNumberStored = true;
            }
            else {
                secondNumber = numberValue;
            }
        }
        else {
            continue;
        }
    }
    const concatenatedNumbers = parseInt(firstNumber + secondNumber, 10);
    acum += concatenatedNumbers;
    console.log(acum);
    firstNumber = "";
    secondNumber = "";
    isFirstNumberStored = false;
}
console.log(acum);
