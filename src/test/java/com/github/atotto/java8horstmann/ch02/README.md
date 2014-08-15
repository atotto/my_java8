## ex01

P.24の2.1節「イテレーションからストリーム操作へ」のforループの並列バージョンを書きなさい。
リストのセグメントごとに処理を行う別々のスレッドを多数生成し、処理が終わるごとに結果を合計するようにしなさい。
(みなさんは、単一カウンターを更新するためにスレッドを使用したくはないでしょう。なぜですか)

## ex02

ある文字数以上の長い単語のうち最初の5個だけ求める処理において、5番目の長い単語がいったん見つかったら、filterメソッドが呼び出されないことを検証しなさい。単純に、各メソッドの呼び出してログを出せばよいです。

## ex03

`stream`ではなく、`parallelStream`で長い単語を数えた場合の速度の違いを計りなさい。呼び出しの前後で`System.nanoTime`を呼び出して、差を表示しなさい。速いコンピュータを持っているのであれば、(「職争と平和」(War and Peace)などの)もっと大きなドキュメントで試しなさい。

## ex04

配列`int[] values = {1,4,9,16}`を持っていると想定してください。`Stream.of(values)`は、何になるでしょうか。代わりに、intのストリームをどうやって取得できるでしょうか。

## ex05

`Stream.iterate`を使用して、`Math.random`を呼び出すのではなく、線形合同生成器(linear congruential generator)を直接実装して、乱数の無限ストリームを作成しなさい。そのような生成器では x0 = seedで始めて、a,c,mの適切な値に対して、xn + 1 = (axn+c)%m を生成します。パラメータa,c,m,seedを受け取り、Stream<Long>を生成するメソッドを実装しなさい。a=25214903917,c = 11,m = 2^48を試してみなさい。

参考資料:

* [線形合同法](http://ja.wikipedia.org/wiki/%E7%B7%9A%E5%BD%A2%E5%90%88%E5%90%8C%E6%B3%95)
* [linear congruential generator](http://en.wikipedia.org/wiki/Linear_congruential_generator)

## ex06

P.27の2.3節「`filter`、`map`、`flatMap`メソッド」の`characterStream`メソッドは、最初に`ArrayList`を埋めて、それからそのリストをストリームに変換するという具合に、多少ぎこちないです。代わりに、ストリームを使用して1行で書きなさい̅。適切な方法は、`0`から`s.length()-1`までの`IntStream`を作成して、それを`s::charAt`メソッド参照でマップすることです。

```java
   public static Stream<Character> characterStream(String s) {
      List<Character> result = new ArrayList<>();
      for (char c : s.toCharArray()) result.add(c);
      return result.stream();
   }
```

参考資料:

* http://docs.oracle.com/javase/jp/8/api/java/util/stream/IntStream.html

## ex07

みなさんの上司が、メソッドとして`public static <T> boolean isFinite(Stream<T> stream)`を作成するように求めています。
それは、よくない考えでしょうか。まずは作成してから、考えてみなさい。

## ex08

`public static <T> Stream<T> zip(Stream<T> first, Stream<T> second)`メソッドを作成しなさい。
そのメソッドは、ストリームである`first`と`second`から要素を交互に取り出し、どちらかのストリームから要素がなくなったら停止します。

## ex09

`Stream<ArrayList<T>>`内のすべての要素を、1つの`ArrayList<T>`へまとめなさい。
具体的には、3つの形式のreduceを用いる方法を示しなさい。

## ex10

`Stream<Double>`の平均を計算するために使用できるreduceの呼び出しを書きなさい。
単純に合計を計算して、`count()`で割ることができないのはなぜですか。

## ex11

複数の`ArrayList`をマージする代わりに、単一の`ArrayList`においてストリームの結果を並行して収集できるようにすべきです。
ただし、その`ArrayList`がストリームの大きさで生成されている必要があります。なぜなら、互いに異なる位置へ並行して行う`set`操作であれば、スレッドセーフとなるからです。みなさんは、この収集をどうやって達成することができますか。


## ex12

P.47の2.13「並列ストリーム」で説明したように、`AtomicInteger`の配列を更新することで、並列な`Stream<String>`内の短い単語をすべて数えなさい。個々のカウントを安全に増やすためにアトミックである`getAndIncrement`メソッドを使用しなさい。

## ex13

前の問題に対し次の点を変更し、その問題を解いてください。変更点として、短い文字列はフィルターで取り出し、`Collectors.groupingBy`と`Collectors.counting`と一緒に`collect`メソッドを使用しなさい。
