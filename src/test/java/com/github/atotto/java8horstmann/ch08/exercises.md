## ex01

int値と符号なし操作を使って、0〜2^32-1までの間の値を加算、減算、除算、比較する。

Integer.MAX_VALUEより大きい値を扱うときにマイナスでの扱いになるため、そのまま割り算を行なうと意図した結果にならない。
たとえば、`0xFFFFFFFF/0x00000001`を実行した場合、intで扱うと`-1/1`となってしまい、結果は`-1`である。
`divideUnsigned`を使うと内部でLongへ拡張して計算するため、正しく扱うことができる。

## ex02

`Math.negateExact`が例外をスローする整数値を1つ探す。

## ex03

[ユークリッドの互除法](http://ja.wikipedia.org/wiki/%E3%83%A6%E3%83%BC%E3%82%AF%E3%83%AA%E3%83%83%E3%83%89%E3%81%AE%E4%BA%92%E9%99%A4%E6%B3%95)

http://en.wikipedia.org/wiki/Euclidean_algorithm



