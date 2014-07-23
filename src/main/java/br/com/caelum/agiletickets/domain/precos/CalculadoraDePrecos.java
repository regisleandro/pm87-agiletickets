package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		
		preco = calculaPrecoDoIngresso(sessao);

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}
	
	private static BigDecimal retornaAcrescimo(Sessao sessao){
		BigDecimal percentualDeAcrescimo = BigDecimal.valueOf(0);
		switch (sessao.getEspetaculo().getTipo()){
			case CINEMA:
			case SHOW: 
				if (sessao.ehPrimeiros()){
					percentualDeAcrescimo = BigDecimal.valueOf(0.10);
				}
				break;
			case BALLET:
			case ORQUESTRA:
				if (sessao.ehPrimeiros()){
					percentualDeAcrescimo = BigDecimal.valueOf(0.20);
				}
				if (sessao.getDuracaoEmMinutos() > 60){
					percentualDeAcrescimo = percentualDeAcrescimo.add(BigDecimal.valueOf(0.10));
				}
				break;
				
		}
		
		return percentualDeAcrescimo;
		
	}

	private static BigDecimal calculaPrecoDoIngresso(Sessao sessao) {
		BigDecimal preco;
		preco = sessao.getPreco().add(sessao.getPreco().multiply(retornaAcrescimo(sessao)));
		return preco;
	}

}