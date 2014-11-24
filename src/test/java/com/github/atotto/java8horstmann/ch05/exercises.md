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
