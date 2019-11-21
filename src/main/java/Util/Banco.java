package Util;

public class Banco 
{
    static private Conexao con;
    static public Conexao getCon(){ return con;}

    public Banco() 
    {
        
    }
    
    static public boolean conectar()
    {
       con=new Conexao();
       return con.conectar("jdbc:postgresql://localhost:5433/","usrDB","postgres","postgres123");
    }   
}
