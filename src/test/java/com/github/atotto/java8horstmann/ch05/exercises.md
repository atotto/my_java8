## ex01

plusDaysを使わずにProgrammer’s Dayを計算する。

Programmer’s Day is the 256th day of the year. Here is how you can easily compute it:

```java
LocalDate programmersDay = LocalDate.of(2014, 1, 1).plusDays(255); 
// September 13, but in a leap year it would be September 12
```
