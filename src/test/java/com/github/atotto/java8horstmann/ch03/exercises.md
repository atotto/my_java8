## ex01

[Loggerパッケージ](http://docs.oracle.com/javase/jp/8/api/java/util/logging/Logger.html)を見るとSupplier<String>を引数に取るメソッドがjava8で導入されている。

課題のlogIfメソッドを作ってみたものの、ラムダ式に変更される可能性のある変数をそのまま入れられない＆配列の中身が並行して変更されると問題があるので、あまり使い勝手が良くない。

[Java8のlambda構文がどのようにクロージャーではないか](http://d.hatena.ne.jp/nowokay/20130522) で話があるように、問題になりやすい箇所を許さない設計になっているが配列で渡されるとイマイチな感じ。

ちなみにGoだと全然気にせずに書けてしまうので、この点を気をつけておく必要がある。

## ex02

[ReentrantLockクラス](http://docs.oracle.com/javase/jp/8/api/java/util/concurrent/locks/ReentrantLock.html))

## ex03

ここに記載がある: ["なぜアサーションの実装に、ライブラリの変更ではなく、言語の変更を採用したのですか。"](http://docs.oracle.com/javase/jp/7/technotes/guides/language/assert.html#design-faq)

アサーションを無効にした場合にランタイムの実行速度の低下を抑えるために言語の変更としている。ライブラリにした場合、実行時に条件式を評価する必要が出る。

ラムダ式で遅延評価ができるため、アサーションが無効の場合にその式を評価しないようなアサーションメソッドを作れば解決できる。

