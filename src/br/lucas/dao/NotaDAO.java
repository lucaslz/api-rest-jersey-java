package br.lucas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.lucas.config.DataBaseConnectionConfig;
import br.lucas.entidade.Nota;

public class NotaDAO {
	
	public List<Nota> sltNotas() throws Exception {
		List<Nota> lista = new ArrayList<>();
		
		Connection conexao = DataBaseConnectionConfig.getConnection();
		
		String sql = "SELECT * FROM nota";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
	
		while(rs.next()) {
			Nota nota = new Nota();
			nota.setId(rs.getInt("id"));
			nota.setTitulo(rs.getString("titulo"));
			nota.setDescricao(rs.getString("descricao"));
			
			lista.add(nota);
		}
		
		return lista;
	}
	
	public Nota getNotaPorId(int idNota) throws Exception {
		Nota nota = null;
		
		Connection conexao = DataBaseConnectionConfig.getConnection();
		
		String sql = "SELECT * FROM nota WHERE id = ?";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, idNota);
		ResultSet rs = statement.executeQuery();
		
		if (rs.next()) {
			nota = new Nota();
			nota.setId(rs.getInt("id"));
			nota.setTitulo(rs.getString("titulo"));
			nota.setDescricao(rs.getString("descricao"));
		}
		
		return nota;
	}
	
	public int insNota(Nota nota) throws Exception {
		int id = 0;
		
		Connection conexao = DataBaseConnectionConfig.getConnection();
		
		String sql = "INSERT INTO nota(titulo, descricao) VALUES(?, ?)";
		
		PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, nota.getTitulo());
		statement.setString(2, nota.getDescricao());
		statement.execute();
		
		ResultSet rs = statement.getGeneratedKeys();
		
		if (rs.next()) id = rs.getInt(1);
		
		return id;
	}
	
	public void updNota(Nota nota, int idNota) throws Exception {
		Connection conexao = DataBaseConnectionConfig.getConnection();
		
		String sql = "UPDATE nota SET titulo = ?, descricao = ? WHERE id = ?";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, nota.getTitulo());
		statement.setString(2, nota.getDescricao());
		statement.setInt(3, idNota);
		statement.execute();
	}
	
	public void dlNota(int idNota) throws Exception {
		Connection conexao = DataBaseConnectionConfig.getConnection();
		
		String sql = "DELETE FROM nota WHERE id = ?";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, idNota);
		statement.execute();
	}
}
