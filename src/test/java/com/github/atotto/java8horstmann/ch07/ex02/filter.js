var String = Java.type('java.lang.String');

var StandardCharsets = Java.type('java.nio.charset.StandardCharsets');
var Files = Java.type('java.nio.file.Files');
var Paths = Java.type('java.nio.file.Paths');
var Arrays = Java.type('java.util.Arrays');
var List = Java.type('java.util.List');

var path = "../../../../../../../resources/alice.txt";
var contents = new String(Files.readAllBytes(Paths.get(path)),
						  StandardCharsets.UTF_8);
var words = Arrays.asList(contents.split(/[^\w]/));
words.stream().sequential().filter(function(w) { 
	return w.length() > 12; 
}).sorted().distinct().forEach(function(w) {
	print(w);
});
