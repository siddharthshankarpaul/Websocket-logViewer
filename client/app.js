 function webSocket() {
	var table =$("#table").logTable(); 	 
	 window.WebSocket = window.WebSocket || window.MozWebSocket;
	 var ws = new WebSocket("ws://127.0.0.1:8085");
	   ws.onopen = function() {
		  //alert("Open...");
	   };				
	   ws.onmessage = function (evt) { 
		var json =JSON.parse(evt.data);			 
		  table.addRow(json);
	   };			
	 ws.onclose = function() {console.log("Connection is closed...");};			   
 }
 
 
 $.fn.logTable = function () {
	 
	 this.addRow = function (json) {
		 var rowClass = getRowClass(json.level);
		 var localTime=new Date(json.timeStamp).toLocaleString();
		 var row = "<tr class=\""+rowClass +"\"><td>"+localTime+"</td><td>"+json.threadName+"</td><td>"+json.level+"</td><td>"+json.message+"</td></tr>"; 
		 this.find("tbody").prepend(row);
		 var rowCount = this.find("tr").length;
		if(rowCount > 50) {				
		  $('#table tr:last').remove();
		  }
	 };
	 function getRowClass(level){
		 var rowClass="";
		 if(level == "ERROR"){
			 rowClass ="danger";
		 } else if (level == "WARN"){
			 rowClass ="warning";
		 } else if (level == "INFO"){
			 rowClass ="info";
		 }
		 return rowClass;
	 }
	return this; 
 };
