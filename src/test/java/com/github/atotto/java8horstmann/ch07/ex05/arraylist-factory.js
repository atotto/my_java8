function arrayListFactory(){ 
	var arr = new (Java.extend(java.util.ArrayList)) {
		add: function(x){
			print('Adding ' + x);
			return Java.super(arr).add(x);
		}
	};
	return arr;
}

