package npi.contatos.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonTableResult<T> {
	
	@JsonProperty("Result")
	private String result;
	
	@JsonProperty("Message")
	private String message;
	
	@JsonProperty("Record")
	private Object record;
	
	public JsonTableResult(String result, Object record ) {
		super();
		this.result = result;
		this.record = record;
	}
	public JsonTableResult(String result, String message ) {
		super();
		this.result = result;
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getRecord() {
		return record;
	}

	public void setRecord(Object record) {
		this.record = record;
	}
	

}
