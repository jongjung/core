<%@page language="java" %>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import='java.util.*'%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Json Test</title>
<script type="text/javascript">
    var xmlHttp;
    
    function createXmlHttp(){
    	try{
    		xmlHttp = new XMLHttpRequest();
    		
    	}catch(trymicrosoft){
    		try{
    			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
    		
    		}catch(othermicrosoft){
    			try{
    				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    			
    			}catch(failed){
    				xmlHttp = false;
    			}
    		}
    	}
    	
    	if(!xmlHttp)
    	  alert("Error initializing XMLHttpRequest!");
   	}	
   	
   	function receiveXml(){
   		if(xmlHttp.readyState == 4){
   			document.getElementById("resultTextArea").value = xmlHttp.responseText;
   		}   	
   	}	
    
    function sendXml() {
    	document.getElementById("resultTextArea").value = "";
    	
    	createXmlHttp();
    	
    	xmlHttp.open("POST", document.getElementById("url").value+"", true);
    	xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    	xmlHttp.setRequestHeader("Accept","application/json");
    	
    	xmlHttp.onreadystatechange = receiveXml;
    	xmlHttp.send(document.getElementById("requestTextArea").value);
    	    
    }
   	
   	function host(){
   	
   		var host;
   		
   		host = location.href;
   		host = host.split("//");
   		host = host[1].substr(0,host[1].indexOf("/"));
   		document.getElementById("url").value = "http://" + host + "/core/smart.do";
   	}
</script>
</head>
<body onload="host()">
<form name="form1">
    <b>URL : </b>
    <input type="text" id="url" name="url" size="60" value="">
    <input type="button" onclick="sendXml()" value="Send Request Message"/>
    </p>
    <b>요청 전문(앞에 공백이 없어야 합니다. )</b>
    <br/>    
    <textarea id="requestTextArea" rows="20" cols="100">
{
  "commonData": {
    "guid": "111112222333",
    "trx_datetime": "2021111101012120000",
    "request_type": "S",
    "response_type": "HOLD",
    "system_code": "cor",
    "service_code": "SDPSMM0001"
  },
  "bizData": {
    "custom_id": "11111",
    "account_no": "1234-5678-22",
    "trans_start_date": "20210201",
    "trans_end_date": "20220201"
  }
}
    </textarea>
    <p/>
    <b>응답전문</b><br/>
    <textarea id="resultTextArea" rows="20" cols="100">
    </textarea>
</form>
</body>
</html>