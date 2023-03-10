package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.mysql.jdbc.PreparedStatement;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {

	public void save(Contato contato) {
		String sql = "INSERT INTO contatos (nome,idade,datacadastro) VALUES (?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		
		try {
			//cria uma conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMysql();
			
			//cria uma PreparedStatement para executar uma query
	
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			//adicionar valores que são esperados pela query
			pstm.setString(1, contato.getName());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			
			//executar a query
			pstm.execute();
			System.out.println("Contato salvo com sucesso");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			// fechar as conexões
			try {
				if(pstm != null) {
					pstm.close();
				}
				
				if(conn != null) {
					conn.close();
					
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Contato> getContatos(){
		String sql = "SELECT * FROM contatos";
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//classe que vai recuperar os dados do banco *****SELECT****
		ResultSet rst = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMysql();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rst = pstm.executeQuery();
			
			
			while (rst.next()) {
				Contato contato = new Contato();
				
				//recuperar o id
				contato.setId(rst.getInt("Id"));
				contato.setName(rst.getString("nome"));
				contato.setIdade(rst.getInt("Idade"));
				contato.setDataCadastro(rst.getDate("dataCadastro"));
				
				contatos.add(contato);
			}
		}catch (Exception e){
			e.printStackTrace();
				
		}finally {
			try{
				if(rst != null) {
				
					rst.close();
				}
			
			if(pstm != null) {
				pstm.close();
			}
			
			if(conn != null) {
				conn.close();
			}
			}catch(Exception e) {
				
			}
			
		}
		return contatos;
	}

	public void update(Contato contato){
		
		String sql = "UPDATE contatos SET nome = ?, idade = ?, dataCadastro = ? WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//criar conexão com o banco
			conn = ConnectionFactory.createConnectionToMysql();
			
			//criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//adicionar os valores para atualizar
			
			pstm.setString(1, contato.getName());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			
			//qual o ID do registro que deseja atualizar
			pstm.setInt(4, contato.getId());
			
			//executar a query
			pstm.execute();			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(pstm != null) {
					pstm.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	public void deleteById(int id) {
		//criação da query
		String sql = "DELETE FROM contatos WHERE id = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
			//criar conexão com o banco
			conn = ConnectionFactory.createConnectionToMysql();
			
			//criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//adicionar o valor para deletar
			pstm.setInt(1, id);
			
			pstm.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}	
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
	}
}

