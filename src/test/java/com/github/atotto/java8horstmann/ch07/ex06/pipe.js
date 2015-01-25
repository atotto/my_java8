// pipe takes a sequence of shell commands and pipes the output of one to the input of the next, returning the final output.
function pipe(/*arguments*/) {
	if (arguments.length < 2) {
		throw new Error("pipe: invalid arguments");
	}
	
	var output = $EXEC(arguments[0]);
	checkErr(arguments[0]);
	for (var i=1; i<arguments.length; i++) {
		var next = arguments[i];
		output = $EXEC(next, output);
		checkErr(arguments[i]);
	}
	return output;
};

function checkErr(cmd) {
	if ($EXIT != 0) {
		throw cmd+": exit status " + $EXIT + "\n"+ $ERR;
	}
}
