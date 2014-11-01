## ex01

テキストフィールドとラベルを持つプログラムを作成する。

* "Hello, FX"を100ptのフォントで表示
* テキストフィールドを同じ文字列で初期化する
* ユーザがテキストフィールドを編集したらラベルを更新する

## ex02

JavaFXプロパティを持つクラスを作成する。

プロパティにはリスナーが登録されないことがほとんどなので、各プロパティのプロパティオブジェクトを持つのは無駄になる。
したがって、以下のように設計する:

* プロパティの値を保持するために、最初は普通のフィールドに保持する
* xxxProperty()メソッドが最初に呼び出された

## ex03

デフォルトから変化しないようなJavaFXプロパティを保持するクラスを考える。
以下の場合にプロパティオブジェクトを生成するよう設計する：

* デフォルトではない値を設定された
* xxxProperty()メソッドが最初に呼び出された

## ex04

円が少なくとも2点で縁に接触するようにする。

## ex05

以下を作る:

```java
public static <T, R> ObservableValue<R> observe(Function<T, R> f, ObservableValue<T> t)
public static <T, U, R> ObservableValue<R> observe(BiFunction<T, U, R> f,
                                                   ObservableValue<T> t, ObservableValue<U> u)
```

that return observable values whose getValue method returns the value of the lambda expression, and whose invalidation and change listeners are fired when any of the inputs become invalid or change. 

* getValueメソッド: ラムダ式の値を返す
* invalidationとchangeのlistenerが、入力で無効か変更になったときに実行するようにする

For example:

```java
larger.disableProperty().bind(observe(t -> t >= 100, gauge.widthProperty()));
```
