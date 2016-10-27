# H2O Jol

Report size of H2O key infrastructure.

## Build
```
./gradlew build
```

## Run
```
java -Djol.tryWithSudo=true -jar build/libs/jol-h2o-all.jar
```

or try

```
java -Djol.tryWithSudo=true -XX:-UseCompressedOops -jar build/libs/jol-h2o-all.jar
```
