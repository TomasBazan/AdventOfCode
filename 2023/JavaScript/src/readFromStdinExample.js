import * as readline from "readline";
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
});
console.log("======================");
rl.question("Please input something: ", (input) => {
    console.log("You entered:", input);
    rl.close();
});
