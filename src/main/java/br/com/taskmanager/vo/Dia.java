package br.com.taskmanager.vo;

public class Dia {

	public String label;
	public String value;

	public Dia(String diaLabel,String diaValue) {
		this.label = diaLabel;
		this.value = diaValue;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getValue() {
		return value;
	}
	
}
