## ex01

plusDaysを使わずにProgrammer’s Dayを計算する。

Programmer’s Day is the 256th day of the year. Here is how you can easily compute it:

```java
LocalDate programmersDay = LocalDate.of(2014, 1, 1).plusDays(255); 
// September 13, but in a leap year it would be September 12
```

## ex02

`LocalDate.of(2000, 2, 29)`に:

* 1年を加算すると？
* 4年を加算すると？
* 1年を4回加算すると？

memo:

4年を加算すると2004/2/29になるが、4回加算すると2/29の日が落ちて2004/2/28になる。


## ex03

`public static TemporalAdjuster next(Predicate<LocalDate> condition)` を実装する。

* [`TemporalAdjuster`](http://docs.oracle.com/javase/8/docs/api/java/time/temporal/TemporalAdjuster.html) を返す
* nextに渡したPredicateを満足する翌日の日付を返す
* `today.with(next(w -> getDayOfWeek().getValue() < 6))` で、明日以降で最初に平日となる日を返す

## ex04

unixのcalのようなプログラムをつくる

cal 3 2013

```
             1  2  3
 4  5  6  7  8  9 10
11 12 13 14 15 16 17
18 19 20 21 22 23 24
25 26 27 28 29 30 31
```

## ex05

いま何日目か表示するプログラムをつくる

## ex06

20世紀の13日の金曜日をすべて出力する

## ex07

時刻のインターバルを表すTimeIntervalクラスを実装する。
2つのインターバルが重なっているかどうかを判定するメソッドを用意する。

## ex08

サポートしているすべてのタイムゾーンのオフセット（UTCからの差）を取得する。

ZoneId.getAvailableIdsをストリームにして実装する。

## ex09

UTCから1時間以内のタイムゾーンを取得する。

