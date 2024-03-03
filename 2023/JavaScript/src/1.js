/*
 *1abc2
 *pqr3stu8vwx
 *a1b2c3d4e5f
 *treb7uchet
 *
 *In this example, the calibration values of these four lines are 12, 38, 15, and 77. Adding these together produces 142.
 *
 */
import * as fs from "fs";
const inputData = fs.readFileSync("./src/1.txt", "utf8");
const lines = inputData.split("\n");
console.log(lines);
let acum = 0;
let firstNumber = "";
let secondNumber = "";
let isFirstNumberStored = false;
for (let line of lines) {
    console.log(line);
    const letters = line.split("");
    for (let letterIndex = 0; letterIndex < letters.length; letterIndex++) {
        const letter = letters[letterIndex];
        if (!isNaN(Number(letter))) {
            console.log(letter);
            if (!isFirstNumberStored) {
                firstNumber = letter;
                secondNumber = letter;
                isFirstNumberStored = true;
            }
            else {
                secondNumber = letter;
            }
            console.log(`First number:${firstNumber}, secondNumber ${secondNumber}`);
        }
        if (letterIndex === letters.length - 1) {
            const concatenatedNumbers = parseInt(firstNumber + secondNumber, 10);
            acum += concatenatedNumbers;
            console.log(acum);
            firstNumber = "";
            secondNumber = "";
            isFirstNumberStored = false;
        }
    }
}
