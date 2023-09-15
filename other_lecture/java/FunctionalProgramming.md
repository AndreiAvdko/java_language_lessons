## Функциональное программирование Java

### Функциональный интерфейс
- Интерфейс (не абстрактный класс), 
содержащий единственный абстрактный метод (single abstract method)
- Может быть аннотирован как @FunctionalInterface
- Методы, определённые в классе Object не учитываются в функциональном интерфейсе

__Пример функционального интерфейса__:
```java
@FunctionalInterface
public interface Runnable {
    public abstract void run();
}
```
__Второй пример функционального интерфейса:__
```java
@FunctionalInterface
public interface Functional<T, R> {
    R apply(T t);
    
    default <V> Function<V, R> compose(Function <? super V, ? extends T> before) {
        OObjects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }
    
    default <V> Function<T, V> andThen(Function<& super R, & extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }
    
    static <T> Function<T, T> identity() {
        return t -> t;
    }
}
```
### Функциональное выражение в Java
- Отображается на функциональный интерфейс
- Не имеет состояния
- Конкретный тип устанавливается по контексту
- Либо лямбда-выражение ((a, b)-> a+b), либо ссылка на метод (Integer::sum)
- Не гарантируется identity
- Не гарантируется getClass() identity

### Лямбда-выражения
- Аргументы
  - (Type1 name1, Type2 name2)
  - (name1, name2)
  - Name
- Стрелочка ->
- Тело:
  - Выражение System.out.println() 
  - Блок { System.out.println(); }

#### Возвращение значений
- Void-compatible (void SAM):
  - Блок: каждый return не содержит выражения
  - Выражение: допустимое в утверждении (вызов метода, присваивание и тд.)
- Value-compatible (non-void SAM)
  - Блок: каждый return содержит выражение и нормальное завершение невозможно
  - Выражение: имеет значение не void

#### Захват (capture) значений
- Локальная переменная - должна быть effectively final и инициализирована к месту использования лямбды, 
берётся её значение
- Поле текущего класса - захватывается this, значение из поля читается при выполнении
- Closure (замыкание) - лямбда выражение с захваченными переменными
Так можно:
```java
int x = 5;
IntSupplier l1 = () -> x*x;
```
А так нельзя:
```java
int x = 5;
x++;
IntSupplier l1 = () -> x*x;
```

#### Ссылка на метод (method reference)
- Cтатический метод:
`IntBinaryOperator sum = Integer::sum;`
- Нестатический метод(первый параметр SAM-метода превращается в квалификатор):
`Function<String, String> trimmer = String::trim; // res = trimmer.apply(" Hello ")`
- Нестатический метод привязанный к экземпляру (instance-bound):
```java
Predicate<String> isFoo = "foo"::equals;
Consumer<Object> printer = System.out::println;
```
- Конструктор:
`Supplier<List<String>> listFactory = ArrayList::new;`
- Конструирование массива:
`IntFunction<String[]> arrayFactory = String[]::new;`

### Примитивы функционального программирования
- Композиция функций: h = f * g; h(x) = f(g(x))
```java
default <V> Function<V, R> compose (Function<? super V, ? extends T> before) {
    Objects.requireNonNull(before);
    return (V v) -> apply(before.apply(v));
}
```
- Привязка аргумента (bind):
```java
static <A,B,C> Function<B,C> bind(BiFunction<A,B,C> fn,A a) {
    Objects.requireNonNull(fn);
    return b -> fn.apply(a,b);
}

Function<Integer, Integer> inc = bind(Integer::sum, 1);
System.out.println(inc.apply(10));
```
- Карирование (curry)
```java
static <A,B,C>
Function<A, Function<B,C>> curry(BiFunction<A,B,C> fn) {
    return a -> b -> fn.apply(a,b);
}
```
