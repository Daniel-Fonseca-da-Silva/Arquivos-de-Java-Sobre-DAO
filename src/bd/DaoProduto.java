package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import negocio.Produto;

public class DaoProduto {
	
	private Connection con = null;
	private Statement st = null;
	private ResultSet rs = null;
	
	public DaoProduto() throws Exception // Trata essa exceção na classe Tela
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/alunos", "root", "123");
		st = con.createStatement();
		
	}
	
	// Metódos que jogam os valores para o SGBD
	
	public void incluir(Produto produto) throws Exception
	{
		st.executeUpdate("INSERT INTO mercado.produtos VALUES('"+ produto.getCodigo() +"', '"+ produto.getDescricao()+ "', "+ produto.getValorUnit() +", "+ produto.getQtEstoque() +")");
	}
	
	public void excluir(Produto produto) throws Exception
	{
		st.executeUpdate("DELETE FROM mercado.produtos WHERE codigo = '"+ produto.getCodigo() +"' ");
	}
	public void alterar(Produto produto) throws Exception
	{
		st.executeUpdate("UPDATE mercado.produtos SET descricao = '"+produto.getDescricao()+"',valorUnit = "+produto.getValorUnit()+",qtEstoque = "+produto.getQtEstoque()+" WHERE codigo = '"+produto.getCodigo()+"'");
	}
	public void consultar(Produto produto) throws Exception
	{
		rs = st.executeQuery("SELECT * FROM mercado.produtos WHERE codigo = '"+ produto.getCodigo() +"' ");
		
		if(rs.next())
		{
			produto.setCodigo(rs.getString(1));
			produto.setDescricao(rs.getString(2));
			produto.setValorUnit(rs.getDouble(3));
			produto.setQtEstoque(rs.getInt(4));
			
		}
	}
	
}
