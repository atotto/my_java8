// jjs -scripting env.js

function printEnv() {
	var env = $ENV;

	for (var key in env){
		print(key+"="+env[key]);
	}
}

printEnv();
