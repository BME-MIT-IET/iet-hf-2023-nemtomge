## A projekt leírása

A projekt a 2021/2022-es félév Szoftver projekt laboratórium tárgya során a vadászeprek csapat által készített játék. A szoftver Java nyelven készült a Java Swing GUI keretrendszer használatával. Külső függősége még a json-simple jar file, ami a kódhoz mellékelt, a `/jars` mappában.

A játékban vak varázslók keresnek genetikai kódokat. Az nyer, aki először megszerzi az összeset, de közben a varázslók tárgyak és ágensek segítségével egymást hátráltathatják. A játék részletes leírása a `/docs` mappában található.

Szintén a `/docs` mappában található a játék különböző komponseinek dokumentációja, valamint a tesztesetek bemeneti nyelvének leírása. Ezek a tesztek a `/test` mappában találhatóak. Az `/assets` mappában a grafikus felület erőforrásai találhatóak, mint például a tárgyak és a térkép képe. A `/maps.json` fájl a játékteret tartalmazza.

## A projekt fordítása

### Linux/MacOS
```shell
javac -cp ".:jars/json-simple-1.1.1.jar" $(find src -name '*.java' ! -name '._*') -d bin/
```
### Windows
```shell
mkdir bin
javac -d .\bin -cp ".;./jars/json-simple-1.1.1.jar" src/main/Main.java src/controls/Skeleton.java src/components/agent/*.java src/components/field/*.java src/components/gear/*.java src/components/scientist/*.java src/components/graphics/panels/*.java src/components/graphics/windows/*.java src/components/graphics/wrappers/*.java src/components/utils/*.java
```

## A projekt futtatása

```shell
java -cp ".:jars/json-simple-1.1.1.jar:bin/" main.Main
```