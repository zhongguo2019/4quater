//昨天的时间
var day1 = new Date();
day1.setTime(day1.getTime()-24*60*60*1000);
 var yesterday = day1.getFullYear()+"-" + (day1.getMonth()+1) + "-" + day1.getDate();
//今天的时间
var day2 = new Date();
day2.setTime(day2.getTime());
var today = today2string(day2);
//明天的时间
var day3 = new Date();
day3.setTime(day3.getTime()+24*60*60*1000);
var tomorrow = tomorrow2string(day2);
 //拼接时间
function today2string(date){
	   var year = date.getFullYear();  
	   var month =(date.getMonth() + 1).toString();  
	   var day = (date.getDate()).toString();   
	   if (month.length == 1) {  
	       month = "0" + month;  
	   }  
	   if (day.length == 1) {  
	       day = "0" + day;  
	   } 
	   var dateTime = year + "-" + month + "-" + day;
	   return dateTime;  
	 }
	 
 function tomorrow2string(date){
	   var year = date.getFullYear();  
	   var month =(date.getMonth() + 1).toString();  
	   var day = (date.getDate())+1;
	  day = day.toString();
	   if (month.length == 1) {  
	       month = "0" + month;  
	   }  
	   if (day.length == 1) {  
	       day = "0" + day;  
	   } 
	   var dateTime = year + "-" + month + "-" + day;
	   return dateTime;  
	 }	
