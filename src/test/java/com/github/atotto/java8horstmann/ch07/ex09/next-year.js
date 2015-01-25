// nextYear obtains the age of the user and then prints Next year, you will be ..., adding 1 to the input. The age can be specified on the command line or the AGE environment variable. If neither are present, prompt the user.
function nextYear(year) {
	var next = Number(year) + 1;
	return next;
}

var age = -1;

switch (true) {
	case arguments.length == 1:
		age = arguments[0];
		break;
	case $ENV.AGE != undefined:
		age = $ENV.AGE;
		break;
	default:
		age = readLine('Please input your age: ');
		break;
}

if (isNaN(age)) {
	print(age + " is not a number.");
	exit(1);
}

var nextAge = nextYear(age);

print("Next year, you will be "+ nextAge);
