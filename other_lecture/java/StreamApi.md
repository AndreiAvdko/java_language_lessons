## Optioanl & Stream API

### Optional<T>
- Может хранить либо значение (не null), либо его отсутствие

Методы:
- Optional.of(obj)
- Optional.ofNullable(obj)
- Optional.empty()

Примеры:
- filter(Predicate<T>)
```java
    boolean isFooEqualsBar = Optional.of("foo").filter("bar"::equals).isPresent();
```
- < U > map(Function < T, U >)
```java
    String trimmed = Optional.of(" foo ").map(String::trim).get();
```
- < U > flatMap(Function<T, Optional< U >> )

```java
    Integer num = Optional.of("1234").flatMap(x -> toInteger(x)).orElse(-1);
```

- orElseGet(Supplier<T>)

```java
    Double random = Optional.<Double>empty().orElseGet(Math::random);
```
- < X extends Throwable> orElseThrow(Supplier<T>) throws X
```java
    Double random = Optional.<Double>empty().orElseThrow(Exception::new);
```
- or(upplier <? extends Optional<? extends T>>)
```java
    getOneOptional().or(() -> getAnotherOptional());
```

## Stream API 
- Поток однотипных данных для однотипной обработки
- Источник создаёт ленивый стрим
- Промежуточные операции описывают рецепт обработки, но ничего не делают
- Вся раобта производится при вызове терминальной операции
- Может потребить не все элементы
- Может быть бесконечным и завершиться за конечное время

### Источники
- Stream.empty()
- Stream.of(x,y,z)
- Stream.ofNullable(x) - Java9
- Stream.generate(Math::random)
- Stream.iterate(val, x -> x+1)
- Stream.iterate(0, x-> x < 100, x-> x+1) - Java 9
- collection.stream()
- Arrays.stream(array)(int/long/double/Object)
- Random.ints()/longs()/doubles()
- String.chars()
- Pattern.splitAsStream()

### Промежуточные операции
- map(mapToInt/mapToLong/mapToDouble/mapToObj)
- filter
- flatMap (flatMapToInt/flatMapToLong/flatMapToDouble)
- mapMulti (mapMultiToInt/mapMultiToLong/mapMultiToDouble)
- distinct
- sorted
- limit
- skip
- peek (применить некоторую функцию к каждому элементу стрима и подглядеть какие элементы проходят)
- takeWhile (брать элементы, которые удовлетворяют условию)
- dropWhile (игнорировать элементы, пока они удовлетворяют указанному предикату)
- boxed/asLongStream/asDoubleStream (примитивы)

### Порядок операций в стриме важен:
Этот код выполнится корректно:
```java
IntStream.iterate(1,x -> x*2)
        .limit(10)
        .sorted()
        .forEach(System.out::println)
```
А этот код упадёт с ошибкой:
```java
IntStream.iterate(1,x -> x*2)
        .sorted()
        .limit(10)
        .forEach(System.out::println)
```


