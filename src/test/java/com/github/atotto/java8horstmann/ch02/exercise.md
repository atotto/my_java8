## ex01

forループでの並列バージョンはThreadを駆使する必要があり、とても複雑になる。
今回はThread内部でカウント処理をおこなったが、もし親のThreadでカウント処理をおこなう場合はカウント処理をアトミックにする必要がある。

## ex02

streamでlimitをするとそれ以降のfilterメソッドは呼ばれない。

## ex03

すこし`go test -bench`っぽくした。

`stream` vs `parallelStream`

## ex04

```java
int[] values = { 1, 4, 9, 16 };
Stream<int[]> istream = Stream.of(values);
IntStream istream = Arrays.stream(values);
IntStream istream = IntStream.of(values);
```

## ex05

* 引数
  * a, c, m, seed
* 戻り値
  * Stream<Long>

参考資料:

* [線形合同法](http://ja.wikipedia.org/wiki/%E7%B7%9A%E5%BD%A2%E5%90%88%E5%90%8C%E6%B3%95)
* [linear congruential generator](http://en.wikipedia.org/wiki/Linear_congruential_generator)

## ex06

```java
   public static Stream<Character> characterStream(String s) {
      List<Character> result = new ArrayList<>();
      for (char c : s.toCharArray()) result.add(c);
      return result.stream();
   }
```

```java
	public static Stream<Character> characterStream(String s) {
		return IntStream.rangeClosed(0, s.length() - 1).mapToObj(s::charAt);
	}
```

参考資料:

* http://docs.oracle.com/javase/jp/8/api/java/util/stream/IntStream.html

## ex07

```java
public static <T> boolean isFinite(Stream<T> stream)
```

有限かどうかを判定するメソッド。

無限を判定できないので、実装できないのでは？

## ex08

ストリーム`first`と`second`から要素を交互に取り出し、どちらかのストリームから要素がなくなったら停止する`public static <T> Stream<T> zip(Stream<T> first, Stream<T> second)`メソッドを作成する。

## ex09

`Stream<ArrayList<T>>`内のすべての要素を1つの`ArrayList<T>`へまとめる。

reduceの3つの形式を用いる:

* `T reduce(T identity, BinaryOperator<T> accumulator)`
* `Optional<T> reduce(BinaryOperator<T> accumulator)`
* `<U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)`

## ex10

`Stream<Double>`の平均を計算する。

`reduce`で合計を計算して、`count()`で割ることができないのは一度使ったストリームを再利用できないため。

## ex11

ex09と関連。単純にreduceするのではなく、並行して`ArrayList`を収集できるようにする。

`ArrayList`のストリームなので、いくつArrayListが放り込まれるのかわからない。
なので、全体を簡単に知ることができない。
また、`ArrayList`はサイズを指定して生成するものではない。。

というか、`ArrayList`の`set`メソッドは同時に呼ばれると不正な動作をすると思う。。

## ex12

`AtomicInteger`を使わないと並行してインクリメントする際に不整合が生じる。

参考資料:

* http://docs.oracle.com/javase/jp/8/api/java/util/concurrent/atomic/AtomicInteger.html

## ex13

短い文字を`filter`し、`collect`メソッドを`Collectors.groupingBy`と`Collectors.counting`と一緒に使う。

参考資料:

* http://docs.oracle.com/javase/jp/8/api/java/util/stream/Collectors.html#groupingBy-java.util.function.Function-java.util.stream.Collector-
* http://docs.oracle.com/javase/jp/8/api/java/util/stream/Collectors.html#counting--
