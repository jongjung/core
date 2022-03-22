package core.fw.controller.vo;

public class CommonDataVo {
	
	 private String guid;
	 
	 private String trxDatetime;
	 
	 private String requestType;
	 
	 private String responseType;
	 
	 private String systemCode;
	    
	 private String service_code;

	 public String getGuid() {
	 	 return guid;
	 }

	 public void setGuid(String guid) {
		 this.guid = guid;
	 }

	 public String getTrxDatetime() {
		 return trxDatetime;
	 }

	 public void setTrxDatetime(String trxDatetime) {
		 this.trxDatetime = trxDatetime;
	 }

	 public String getRequestType() {
		 return requestType;
	 }

	 public void setRequestType(String requestType) {
		 this.requestType = requestType;
	 }

	 public String getResponseType() {
		 return responseType;
	 }

	 public void setResponseType(String responseType) {
		 this.responseType = responseType;
	 }

	 public String getSystemCode() {
		 return systemCode;
	 }

	 public void setSystemCode(String systemCode) {
		 this.systemCode = systemCode;
	 }

	 public String getService_code() {
		 return service_code;
	 }

	 public void setService_code(String service_code) {
		 this.service_code = service_code;
	 }

}
