package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

    private Connection connect;
    private String erro = "";

    public Conexao() {
        this("org.postgresql.Driver", "jdbc:postgresql://localhost:5433/", "usrDB", "postgres", "postgres123");
    }

    public Conexao(String driver, String local, String banco, String usuario, String senha) {
        try {
            Class.forName(driver); //"org.postgresql.Driver");
            String url = local + banco; //"jdbc:postgresql://localhost/"+banco;
            connect = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException cnfex) {
            erro = "Falha ao ler o driver JDBC: " + cnfex.toString();
        } catch (SQLException sqlex) {
            erro = "Impossivel conectar com a base de dados: " + sqlex.toString();
        } catch (Exception ex) {
            erro = "Outro erro: " + ex.toString();
        }
    }

    public String getMensagemErro() {
        return erro;
    }

    public boolean getEstadoConexao() {
        if (connect == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean manipular(String sql) // inserir, alterar,excluir
    {
        try {
            Statement statement = connect.createStatement();
            int result = statement.executeUpdate(sql);
            statement.close();
            if (result >= 1) {
                return true;
            }
        } catch (SQLException sqlex) {
            erro = "Erro: " + sqlex.toString();
            return false;
        }
        return false;
    }

    public ResultSet consultar(String sql) {
        ResultSet rs = null;
        try {
            Statement statement = connect.createStatement();
            //ResultSet.TYPE_SCROLL_INSENSITIVE,
            //ResultSet.CONCUR_READ_ONLY);
            rs = statement.executeQuery(sql);
            //statement.close();
        } catch (SQLException sqlex) {
            erro = "Erro: " + sqlex.toString();
            return null;
        }
        return rs;
    }

    public int getMaxPK(String tabela, String chave) {
        String sql = "select max(" + chave + ") from " + tabela;
        int max = 0;
        ResultSet rs = consultar(sql);
        try {
            if (rs.next()) {
                max = rs.getInt(1);
            }
        } catch (SQLException sqlex) {
            erro = "Erro: " + sqlex.toString();
            return -1;
        }
        return max;
    }
}
