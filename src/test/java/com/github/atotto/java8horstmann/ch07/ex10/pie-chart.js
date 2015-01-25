if (arguments.length != 1) {
	print("data file not set.");
	exit(1);
}

var path = java.nio.file.Paths.get(arguments[0]);
var contents;
try {
	contents = new java.lang.String(
		java.nio.file.Files.readAllBytes(path), 
		java.nio.charset.StandardCharsets.UTF_8);
} catch(e) {
	print("No such file: " + path);
	exit(1);
}

var title = path.getFileName().toString().replaceAll('_',' ').split(/\.(?=[^.]+$)/)[0];
var lines = contents.split(/[\n]/);

var pieChartData = javafx.collections.FXCollections.observableArrayList();
for (var i=0; i<lines.length; i++) {
	if (lines[i] == "") {
		break;
	}
	var set = lines[i].split(/[,]/);
	print(set);
	pieChartData.add(new javafx.scene.chart.PieChart.Data(set[0], set[1]));
}
var chart = new javafx.scene.chart.PieChart(pieChartData);
chart.setTitle(title);

$STAGE.scene = new javafx.scene.Scene(chart);
