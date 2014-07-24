package br.com.caelum.agiletickets.models;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public interface CalculaPeriodicidade {
	public int retornarNumeroDeSessoes(LocalDate inicio, LocalDate fim);
	public DateTime converterDataDeInicio(LocalDate inicio, LocalTime horario, int periodo);
}
