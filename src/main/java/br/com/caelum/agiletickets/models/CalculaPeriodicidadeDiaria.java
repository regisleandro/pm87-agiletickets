package br.com.caelum.agiletickets.models;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class CalculaPeriodicidadeDiaria implements CalculaPeriodicidade {

	@Override
	public int retornarNumeroDeSessoes(LocalDate inicio, LocalDate fim) {
		return Days.daysBetween(inicio, fim).getDays();
	}

	@Override
	public DateTime converterDataDeInicio(LocalDate inicio, LocalTime horario, int periodo) {
		return inicio.plusDays(periodo).toDateTime(horario);
	}
}
