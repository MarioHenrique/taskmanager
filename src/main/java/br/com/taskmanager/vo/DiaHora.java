package br.com.taskmanager.vo;

public class DiaHora {

	private String dia;
	private Integer horas;

	public DiaHora(String dia, Integer horas) {
		this.dia = dia;
		this.horas = horas;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public Integer getHoras() {
		return horas;
	}

	public void setHoras(Integer horas) {
		this.horas = horas;
	}

}
