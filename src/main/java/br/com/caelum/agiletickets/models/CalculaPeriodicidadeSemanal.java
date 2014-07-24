package br.com.caelum.agiletickets.models;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Weeks;

public class CalculaPeriodicidadeSemanal implements CalculaPeriodicidade {

	@Override
	public int retornarNumeroDeSessoes(LocalDate inicio, LocalDate fim) {
		return Weeks.weeksBetween(inicio, fim).getWeeks();
	}

	@Override
	public DateTime converterDataDeInicio(LocalDate inicio, LocalTime horario, int periodo) {
		return inicio.plusWeeks(periodo).toDateTime(horario);
	}
}
