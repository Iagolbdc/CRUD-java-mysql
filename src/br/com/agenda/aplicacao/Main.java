package br.com.agenda.aplicacao;

import java.util.Date;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Criando um novo contato
		Contato contato = new Contato();
		ContatoDAO contatoDao = new ContatoDAO();
		
		contato.setName("Iury Luan");
		contato.setIdade(14);
		contato.setDataCadastro(new Date());

		
		//contatoDao.save(contato);

//#####################################################################################
		
		//atualizar o contato
		
		Contato c1 = new Contato();
		c1.setName("Maria Eduarda Dias Orlando");
		c1.setIdade(37);
		c1.setDataCadastro(new Date());
		
		c1.setId(2);
		
		//contatoDao.update(c1);
		
//#####################################################################################		
		
		//Apaga o usuario
		
		//contatoDao.deleteById(1);

		
//#####################################################################################
		//visualização dos registros do banco de dados TODOS
		
		for(Contato c : contatoDao.getContatos()) {
			System.out.println("Contato: " + c.getName() + "\nIdade: " + c.getIdade() + "\nData de Cadastro: " + c.getDataCadastro() + "\n");
		}
		
	}

}
