OS := $(shell uname)

ifeq ($(OS), Linux)
	JAVAC := javac -cp ".:jars/json-simple-1.1.1.jar" $(shell find src -name '*.java' ! -name '._*') -d bin/
else
	JAVAC := mkdir bin && javac -d .\bin -cp ".;./jars/json-simple-1.1.1.jar" src/main/Main.java src/controls/Skeleton.java src/components/agent/*.java src/components/field/*.java src/components/gear/*.java src/components/scientist/*.java src/components/graphics/panels/*.java src/components/graphics/windows/*.java src/components/graphics/wrappers/*.java src/components/utils/*.java 
endif

.PHONY: compile run os

compile:
	$(JAVAC)

run:
	java -cp ".:jars/json-simple-1.1.1.jar:bin/" main.Main

os:
	@echo $(OS)