package br.com.caelum.agiletickets.integration.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.agiletickets.models.Estabelecimento;
import br.com.caelum.agiletickets.persistencia.JPAEstabelecimentoDao;

public class JPAEstabelecimentoDaoTest {
	private static EntityManagerFactory emf;
	private EntityManager manager;
	private JPAEstabelecimentoDao dao;
	
	@BeforeClass
	public static void beforeClass(){
		emf = Persistence.createEntityManagerFactory("tests");
	}
	
	@Before
	public void before(){
		this.manager = emf.createEntityManager();
		this.manager.getTransaction().begin();
		this.dao = new JPAEstabelecimentoDao(manager);
	}
	
	@After
	public void after(){
		this.manager.getTransaction().rollback();
		this.manager.close();
	}
	
	@AfterClass
	public static void afterClass(){
		emf.close();
	}
	
	@Test
	public void deveAdicionarUmEstabelecimento(){
		Estabelecimento novo = new Estabelecimento();
		novo.setNome("Novo Estabelecimento de testes");
		novo.setEndereco("Endereço do Estabelecimento de Testes");
		novo.setTemEstacionamento(true);
		
		dao.adiciona(novo);
		
		Estabelecimento adicionado = manager.find(Estabelecimento.class, novo.getId());
		assertEquals(adicionado, novo);
	}
	
	@Test
	public void deveListarTodosOsEstabelecimentosCadastrados(){
		Estabelecimento primeiro = criarEstabelecimento("Estabelecimento de Testes 1", 
														"Endereco de Teste 1", true);	
		Estabelecimento segundo = criarEstabelecimento("Estabelecimento de Testes 2", 
				"Endereco de Teste 2", true);	
		Estabelecimento terceiro = criarEstabelecimento("Estabelecimento de Testes 3", 
				"Endereco de Teste 3", false);	
		
		List<Estabelecimento> todos = dao.todos();
		assertEquals(3, todos.size());
		assertTrue(todos.containsAll(Arrays.asList(primeiro, segundo, terceiro)));
	}
	
	private Estabelecimento criarEstabelecimento(String nome, String endereco, boolean temEstacionamento){
		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setNome(nome);
		estabelecimento.setEndereco(endereco);
		estabelecimento.setTemEstacionamento(temEstacionamento);
		
		this.manager.persist(estabelecimento);
		return estabelecimento;
	}

}
