package br.com.taskmanager.util;

import java.util.ArrayList;
import java.util.List;

import br.com.taskmanager.vo.Dia;

public class DiasDaSemanaUtil {

	private static List<Dia> dias = new ArrayList<Dia>();
	
	static{
		dias.add(new Dia("Domingo","DOMINGO"));
		dias.add(new Dia("Segunda","SEGUNDA"));
		dias.add(new Dia("Terça","TERCA"));
		dias.add(new Dia("Quarta","QUARTA"));
		dias.add(new Dia("Quinta","QUINTA"));
		dias.add(new Dia("Sexta","SEXTA"));
		dias.add(new Dia("Sabado","SABADO"));
	}
	
	public static List<Dia> getDiasDaSemana(){
		return dias;
	}
	
}
