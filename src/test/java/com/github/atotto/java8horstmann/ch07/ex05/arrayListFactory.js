function arrayListFactory(){ 
	var arr = new (Java.extend(java.util.ArrayList)) {
		add: function(x){
			print('Adding ' + x);
			return Java.super(arr).add(x);
		}
	};
	return arr;
}

var l1 = arrayListFactory();
var l2 = arrayListFactory();

l1.add("1");
l2.add("a");
l1.add("2");
l2.add("b");

// for test
function fail(/*arguments*/) {
	var v = "";
	for (var i=0; i<arguments.length; i++) {
		v += arguments[i];
	}
	print(v);
	exit(1);
}

// verify
if (l1.contains("a") || l1.contains("b") || l2.contains("1") || l2.contains("2")) {
	fail("l1 or l2 have wrong object:", l1, l2);
}

if (l1.size() != 2) {
	fail("l1: want 2, got " + l1.size());
}

if (l2.size() != 2) {
	fail("l2: want 2, got " + l2.size());
}

