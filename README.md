# Java Challenge

This is the implementation of a command-line Java scoring program for 10 Pin Bowling. You can check out the rules of the
game [here](https://en.wikipedia.org/wiki/Ten-pin_bowling#Rules_of_play)

The program reads a command line argument file path from which it gets a file containing the entries from several
players
bowling and as a result it outputs Scores and PinFalls for each player using 'X' for Strike rolls and '/' for Spare
ones.
Please take a look at files in `src/main/resources` to check out some example inputs

## How to use

Use [maven](https://maven.apache.org/) to build the project. Run all commands from the root project folder.

```bash
mvn clean install
```

Run the project

```bash
java -jar .\target\10-pin-bowling-scoring-1.0-SNAPSHOT.jar <your-file-path>
```

Run the project with existing example
```bash
java -jar .\target\10-pin-bowling-scoring-1.0-SNAPSHOT.jar src/main/resources/inputFile.txt
```


## Tests

Run Unit tests

```bash
mvn test
```

Run Integration tests

```bash
mvn verify
```

## Coding style

This project is develop under
the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
