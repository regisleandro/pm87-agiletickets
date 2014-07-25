package br.com.caelum.agiletickets.acceptance;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ReservaTest {
	private WebDriver browser;
	
	@Before
	public void setUp(){
		browser = new FirefoxDriver();
	}
	
	@After
	public void tearDown(){
		browser.close();
	}
	
	@Test
	public void deveReservarIngressoComAdicaoDe10PorcentoAoPrecoOriginal(){
		String url = "http://localhost:8080/";
		browser.get(url);
		
		WebElement tabela = browser.findElement(By.id("sessoes"));
		List<WebElement> linhas = tabela.findElements(By.tagName("li"));
		WebElement linha = linhas.get(4);
		WebElement link = linha.findElement(By.tagName("a"));
		String urlLink = link.getAttribute("href");
		
		browser.get(urlLink);
		WebElement inputQuantidade = browser.findElement(By.id("qtde"));
		inputQuantidade.sendKeys("1");
		inputQuantidade.submit();
		
		WebElement mensagem = browser.findElement(By.id("message"));
		String mensagemSucesso = mensagem.getText();
	    assertEquals("Sessão reservada com sucesso por R$ 55,00", mensagemSucesso);
		
	}

}
