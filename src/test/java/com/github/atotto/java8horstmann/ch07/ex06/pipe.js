// pipe takes a sequence of shell commands and pipes the output of one to the input of the next, returning the final output.
function pipe(/*arguments*/) {
	if (arguments.length < 2) {
		throw new Error("pipe: invalid arguments");
	}
	
	var output = "";
	for (var i=0; i<arguments.length; i++) {
		var next = arguments[i];
		output = $EXEC(next, output);
	}
	return output;
};
