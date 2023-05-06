[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/coREwzrI)

### Compile:
```shell
javac -cp ".:jars/json-simple-1.1.1.jar" $(find src -name '*.java' ! -name '._*') -d bin/
```

### Run:
```shell
java -cp ".:jars/json-simple-1.1.1.jar:bin/" main.Main
```