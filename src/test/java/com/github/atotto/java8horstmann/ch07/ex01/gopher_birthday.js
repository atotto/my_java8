var ChronoUnit = Java.type('java.time.temporal.ChronoUnit');
var LocalDate = Java.type('java.time.LocalDate');

function howManyDays(birthday) { return birthday.until(LocalDate.now(), ChronoUnit.DAYS); };

var gopher_birthday = LocalDate.of(2009, 11, 10);
var days = howManyDays(gopher_birthday);

print(days);
