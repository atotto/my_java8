// jjs -scripting pipe.js pipe-test.js

function expectThrowError(fn) {
	try {
		fn();
		throw "expect error but success.";
	} catch(e) {
	}
}

// Contains returns true if substr is within s.
function contains(s, substr) {
	return s.indexOf(substr) > -1;
}

// verify
expectThrowError(function(){
	pipe();
});
expectThrowError(function(){
	pipe('ls');
});
expectThrowError(function(){
	pipe('find . -wrong option', 'grep pipe');
});

var out = "";

out = pipe('cat pipe.js', 'grep function');
if (!contains(out, "pipe")) {
	throw "pipe function not found";
}

out = pipe('find .', 'grep pipe');
if (!contains(out, "pipe")) {
	throw "pipe function not found";
}

