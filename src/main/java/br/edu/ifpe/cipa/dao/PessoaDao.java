package br.edu.ifpe.cipa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.edu.ifpe.cipa.model.Pessoa;

public class PessoaDao {
	
	private Connection connection;
		
	 public PessoaDao() {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            String DATABASE_URL = "jdbc:mysql://localhost/ifpe_bd_cliente";
	            String usuario = "root";
	            String senha = "";
	            this.connection = DriverManager.getConnection(DATABASE_URL, usuario, senha);
	        } catch (ClassNotFoundException | SQLException ex) {
	            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	
	public List<Pessoa> listar() {
        String sql = "SELECT * FROM cliente";
        List<Pessoa> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Pessoa cliente = new Pessoa();
                cliente.setCodigo(resultado.getInt("codigo"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setEndereco(resultado.getString("endereco"));
                cliente.setCidade(resultado.getString("cidade"));
                cliente.setUf(resultado.getString("uf"));
                retorno.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public boolean inserir(Pessoa cliente) {
        String sql = "INSERT INTO cliente(nome, endereco, cidade, uf) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getCidade());
            stmt.setString(4, cliente.getUf());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Pessoa pessoa) {
        String sql = "UPDATE cliente SET nome=?, cidade=?, endereco=?, uf=? WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getCidade());
            stmt.setString(3, pessoa.getEndereco());
            stmt.setString(4, pessoa.getUf());
            stmt.setInt(5, pessoa.getCodigo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Integer codigo) {
        String sql = "DELETE FROM cliente WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
