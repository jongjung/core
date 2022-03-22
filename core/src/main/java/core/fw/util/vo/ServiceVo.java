package core.fw.util.vo;

public class ServiceVo {
	
	private String className;
	
	private String methodName;
	
	private String inputVoClassName;
	
	private String outputVoClassName;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getInputVoClassName() {
		return inputVoClassName;
	}

	public void setInputVoClassName(String inputVoClassName) {
		this.inputVoClassName = inputVoClassName;
	}

	public String getOutputVoClassName() {
		return outputVoClassName;
	}

	public void setOutputVoClassName(String outputVoClassName) {
		this.outputVoClassName = outputVoClassName;
	}

}
