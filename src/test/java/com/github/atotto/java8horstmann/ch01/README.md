## ex01

1. Arrays.sortメソッド内で呼び出されるコンパレータのコードは、sortメソッドを呼び出したスレッドて実行されるでしょうか。それとも、別のスレッドで実行されるでしょうか。

## ex02

1. java.io.FileクラスのlistFiles(FileFilter)メソッドとisDirectoryメソッドを使用して、指定されたディレクトリの下のすべてのサブディレクトリを返すメソッドを書きなさい。
2. FileFilterオブジェクトではなく、ラムダ式を使用しなさい。
3. 同じことを、メソッド参照を用いて行いなさい。

## ex03

1. java.io.Fileクラスのlist(FilenameFilter)を使用して、指定されたディレクトリの下の指定された拡張子を持つ、すべてのファイルを返すメソッドを書きなさい。
2. FilenameFilterではなく、ラムダ式を使用しなさい。
3. エンクロージングスコープからキャプチャされる変数はどれですか。

## ex04

1. Fileオブジェクトの配列が与えられたとします。その配列をソートして、ファイルの前にディレクトリが来るようにし、ファイルとディレクトリのそれぞれのグループではパス名でソートされるようにしなさい。Comparatorではなく、ラムダ式を使用しなさい。

## ex05

1. みなさんのプロジェクトの1つから、ActionListenerやRunnableといったインタフェースを多数使用しているファイルを1つ選んでください。それらのインタフェースの使用をラムダ式で置き換えなさい。
2. 置き換えた結果、コードが何行短くなりましたか。
3. コードは読みやすくなりましたか。
4. メソッド参照を使用することができましたか。

## ex06

1. Runnable内でチェックされる例外を処理しなければならないことが、いつも面倒だと思っていませんか。チェックされるすべての例外をキャッチし、それをチェックされない例外へ変えるuncheckメソッドを書きなさい。

例:

```java
new Thread(uncheck(() -> { System.out.println("Zzz"); Thread.sleep(1000); })).start();
// catch (InterruptedException) は必要ありません
```

ヒント: どのような例外でもスローできるrunメソッドを持つRunnableExインタフェースを定義します。そして、public static Runnable uncheck(RunnableEx runner)を実装します。uncheck関数内でラムダ式を使用します。なぜ、RunnableExの代わりにCallable<Void>を使用できないのでしょうか。

## ex07

1. 2つのRunnableインスタンスをパラメータとして受け取り、最初のRunnableを実行した後に2つ目のRunnableを実行するRunnableを返すように、staticメソッドandThenを書きなさい。mainメソッドでは、andThenへの呼び出しに2つのラムダ式を渡して、返されたインスタンスを実行しなさい。

## ex08

1. ラムダ式が次のような拡張forループ内の値をキャプチャした場合には、どうなりますか。

```java
String[] names = {"Peter", "Paul", "Mary"};
List<Runnable> runners = new ArrayList<>();
for (String name : names)
    runners.add(() -> System.out.println(name));
```

これは、正当なコードでしょうか。各ラムダ式は異なる値をキャプチャするのでしょうか。それとも、すべてのラムダ式が最後の値をキャプチャするのでしょうか。
2. 従来の`for (int i = 0; i < names.length; i++)`ループを使用すると、どうなるでしょうか。
