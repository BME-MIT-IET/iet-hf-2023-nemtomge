[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/coREwzrI)

### Compile:
#### Linux/MacOS
```shell
javac -cp ".:jars/json-simple-1.1.1.jar" $(find src -name '*.java' ! -name '._*') -d bin/
```
#### Windows
```shell
mkdir bin
javac -d .\bin -cp ".;./jars/json-simple-1.1.1.jar" src/main/Main.java src/controls/Skeleton.java src/components/agent/*.java src/components/field/*.java src/components/gear/*.java src/components/scientist/*.java src/components/graphics/panels/*.java src/components/graphics/windows/*.java src/components/graphics/wrappers/*.java src/components/utils/*.java 
```

### Run:
```shell
java -cp ".:jars/json-simple-1.1.1.jar:bin/" main.Main
```