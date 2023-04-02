# Readme
- [Summary](#Readme)
    * [Inscrutions](#instructions)
    * [Description](#description)
    * [Install](#install)
    * [Run](#run)
    * [Stop game](#stop-game)
    * [Versions](#versions)

# Instructions
This Kata goal is to implement a simple tennis score computer.

## Description
The scoring system consist in one game, divided by points :

Each player starts a game with 0 point.
If the player wins the 1st ball, he will have 15 points. 2nd balls won : 30 points. 3rd ball won : 40points.
If a player have 40 points and wins the ball, he wins the game, however there are special rules.
If both players have 40 points the players are “deuce”.
If the game is in deuce, the winner of the ball will have advantage
If the player with advantage wins the ball he wins the game
If the player without advantage wins the ball they are back at “deuce”.
You can found more details about the rules here : (http://en.wikipedia.org/wiki/Tennis#Scoring )

Here we want you to develop a java method that will take a String as input containing the character ‘A’ or ‘B’.
The character ‘A’ corresponding to “player A won the ball”, and ‘B’ corresponding to “player B won the ball”.
The java method should print the score after each won ball (for example : “Player A : 15 / Player B : 30”) and print the winner of the game.

**Example**:
For example the following input “ABABAA” should print :
```
 “Player A : 15 / Player B : 0”
 “Player A : 15 / Player B : 15”
 “Player A : 30 / Player B : 15”
 “Player A : 30 / Player B : 30”
 “Player A : 40 / Player B : 30”
 “Player A wins the game
```

For example the following input “ABABABABBB” should print :
```
 “Player A : 15 / Player B : 0”
 “Player A : 15 / Player B : 15”
 “Player A : 30 / Player B : 15”
 “Player A : 30 / Player B : 30”
 “Player A : 40 / Player B : 30”
 “deuce”
 “Player A : Advantage / Player B : 40”
 “deuce”
 “Player A : 40 / Player B : Advantage”
 “Player B wins the game
```

## Install
`mvn package`

## Run
`java -jar target/tennis-score-computer-0.0.1-SNAPSHOT.jar`

## Stop game
type `exit`

## Versions
```
└-(> java -version
openjdk 17.0.3 2022-04-19
OpenJDK Runtime Environment Temurin-17.0.3+7 (build 17.0.3+7)
OpendJDK 64-Bit Server VM Temerin-17.0.3+7 (build 17.0.3+7, mixed mode, sharing)

```

```
└-(> mvn --version
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: C:\Program Files\apache-maven-3.6.3
Java version: 17.0.3, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-17.0.3+7
Default locale: fr_FR, platform encoding: UTF-8
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```