package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	@Test
	public void deveCriar1SessaoParaDatasIguaisEPeridiodicidadeDiaria(){
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio;
		LocalTime horario = new LocalTime();
		Periodicidade diaria = Periodicidade.DIARIA;
		
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> lista = espetaculo.criaSessoes(inicio, fim, horario, diaria);
		assertEquals(1, lista.size());
		assertEquals(inicio.toDateTime(horario), lista.get(0).getInicio());
	}
	
	@Test
	public void deveCriar7SessoesParaPeriodicidadeDiaria(){
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.plusDays(7);
		LocalTime horario = new LocalTime();
		Periodicidade diaria = Periodicidade.DIARIA;
		
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> lista = espetaculo.criaSessoes(inicio, fim, horario, diaria);
		assertEquals(7, lista.size());
		for (Sessao s : lista){
			assertEquals(inicio.toDateTime(horario), s.getInicio());
			inicio = inicio.plusDays(1);
		}
	}
	
	@Test
	public void deveCriar1SessaoParaDatasIguaisEPeriodicidadeSemanal(){
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio;
		LocalTime horario = new LocalTime();
		Periodicidade semanal = Periodicidade.SEMANAL;
		
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> lista = espetaculo.criaSessoes(inicio, fim, horario, semanal);
		assertEquals(1, lista.size());
		assertEquals(inicio.toDateTime(horario), lista.get(0).getInicio());		
	}
	
	@Test
	public void deveCriar5SessoesParaPeriodicidadeSemanal(){
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.plusWeeks(5);
		LocalTime horario = new LocalTime();
		Periodicidade diaria = Periodicidade.SEMANAL;
		
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> lista = espetaculo.criaSessoes(inicio, fim, horario, diaria);
		assertEquals(5, lista.size());
		for (Sessao s : lista){
			assertEquals(inicio.toDateTime(horario), s.getInicio());
			inicio = inicio.plusWeeks(1);
		}
	}
	
	@Test
	public void deveCriar1SessaoParaPeriodoMenorQue1SemanaEPeriodicidadeSemanal(){
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.plusDays(3);
		LocalTime horario = new LocalTime();
		Periodicidade semanal = Periodicidade.SEMANAL;
		
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> lista = espetaculo.criaSessoes(inicio, fim, horario, semanal);
		assertEquals(1, lista.size());
		assertEquals(inicio.toDateTime(horario), lista.get(0).getInicio());		
	}
}
