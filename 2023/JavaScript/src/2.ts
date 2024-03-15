import * as fs from "fs";

const inputData: string = fs.readFileSync("./src/2.txt", "utf8");
const lines: string[] = inputData.split("\n");
lines.pop();
const regexIdNumber: RegExp = new RegExp(/(\d+)/);
const possibleGames: number[] = [];
const regexRed: RegExp = new RegExp(/(\d+) red/);
const regexGreen: RegExp = new RegExp(/(\d+) green/);
const regexBlue: RegExp = new RegExp(/(\d+) blue/);
const MAX_RED = 12;
const MAX_GREEN = 13;
const MAX_BLUE = 14;

const checkColorNumber = (set: string): boolean => {
  const matchRegexRed: RegExpMatchArray | null = set.match(regexRed);
  const redNumber: number | null = matchRegexRed
    ? parseInt(matchRegexRed[1], 10)
    : 0;
  const matchRegexGreen: RegExpMatchArray | null = set.match(regexGreen);
  const greenNumber: number | null = matchRegexGreen
    ? parseInt(matchRegexGreen[1], 10)
    : 0;
  const matchRegexBlue: RegExpMatchArray | null = set.match(regexBlue);
  const blueNumber: number | null = matchRegexBlue
    ? parseInt(matchRegexBlue[1], 10)
    : 0;
  const result: boolean =
    redNumber > MAX_RED || greenNumber > MAX_GREEN || blueNumber > MAX_BLUE;
  return result;
};

for (let line of lines) {
  const gameSets = line.split(":");
  const matchRegex: RegExpMatchArray | null = gameSets[0].match(regexIdNumber);
  const gameId: number | null = matchRegex ? parseInt(matchRegex[0], 10) : null;
  const setsOfGames = gameSets[1].split(";");
  let validSet = true;

  for (let set of setsOfGames) {
    validSet = validSet && !checkColorNumber(set);
  }

  if (validSet) {
    if (gameId === null) console.log("WTF! game is valid and null id");
    if (gameId !== null) possibleGames.push(gameId);
  }
}

const sumPossibleIdGames = possibleGames.reduce(
  (accumulator, currentValue) => (accumulator += currentValue),
  0,
);
console.log(sumPossibleIdGames);

// The complexity is O(nm) where n is the number of games and m is the number of Sets inf the game (take the max)
