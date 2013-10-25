package CompartidoServidores;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marc
 */
public class TestConnection {


	public String bd = "BDpxc06";
	public String login = "pxc06";
	public String password = "e1Fg06PeY";
	public String url = "jdbc:mysql://mysqlfib.fib.upc.es:3306/" + bd;

    public void connect()
	{
        Connection conn = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, login, password);
            System.out.println("tuto benne");
            conn.close();
        }
        catch (SQLException ex)
		{
			System.out.println("Hubo un problema al intentar conectarse con la base de datos " + url);
			System.out.println(ex);
		}
		catch (ClassNotFoundException ex)
		{
			System.out.println(ex);
		}

    }
    public int obtenerCreditoUsuario(String Alias)
	{
		Connection conn = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, login, password);

			if (conn != null)
			{
                 Statement stmt = conn.createStatement();
				 ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");

				 while(rs.next())
				 {
					 String Al = rs.getString("Alias");
					 if(Al.equals(Alias)) return rs.getInt("credito");
				 }
				conn.close();
			}
            else return -1;
		}
		catch (SQLException ex)
		{
			System.out.println("Hubo un problema al intentar conectarse con la base de datos " + url);
			System.out.println(ex);
		}
		catch (ClassNotFoundException ex)
		{
			System.out.println(ex);
		}
        return -1;
	}



    public void insertarGananciaUsuario(String Alias, int ganancia)
	{
        /*es un update del credito*/
		Connection conn = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, login, password);
            System.out.println("ACTUALIZANDO PASTA DE  :"+Alias+" Con la ganancia : "+ganancia);
			if (conn != null)
			{
                Statement stmt = conn.createStatement();
                String Update = "update usuario set Credito = "+ganancia+" where Alias = '"+Alias+"'";
				stmt.executeUpdate(Update);
				conn.close();
			}
		}
		catch (SQLException ex)
		{
			System.out.println("Hubo un problema al intentar conectarse con la base de datos " + url);
			System.out.println(ex);
		}
		catch (ClassNotFoundException ex)
		{
			System.out.println(ex);
		}
	}


     public boolean existeUsuario(String Alias, String pass) {

        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);


            if (conn != null)
            {
                 Statement stmt = conn.createStatement();
				 ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
                 while(rs.next())
				 {
					 String Al = rs.getString("Alias");
					 if(Al.equals(Alias))
                     {
                         String p = rs.getString("Password");
                         if(pass.equals(p))
                         {
                             conn.close();
                             return true;
                         }
                         else
                         {
                             conn.close();
                             return false;
                         }
                     }
				 }
                 conn.close();
            }
            else return false;
        }
        catch(SQLException ex) {
            System.out.println("Hubo un problema al intentar conectarse con la base de datos "+url);
            System.out.println(ex);
        }
        catch(ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return false;
    }

//    public void eliminarJugador(String alias)
//    {
//		Connection conn = null;
//		try
//		{
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(url, login, password);
//
//			if (conn != null)
//			{
//                 Statement stmt = conn.createStatement();
//                 String query = "DELETE FROM jugador WHERE Alias = '"+alias+"'";
//				 ResultSet rs = stmt.executeQuery(query);
//
//				conn.close();
//			}
//		}
//		catch (SQLException ex)
//		{
//			System.out.println("Hubo un problema al intentar conectarse con la base de datos " + url);
//			System.out.println(ex);
//		}
//		catch (ClassNotFoundException ex)
//		{
//			System.out.println(ex);
//		}
//    }
//
//    void addJugadorAMesa(int nj, int idm) {
//        throw new UnsupportedOperationException("Not yet implemented");
//        /*
//         * te paso el numero de jugadores de la mesa con id idm, y has de actualizar
//         * en las tablas el num de jugadores de la mesa idmesa con el valor nj
//         */
//    }

//    void insertarMesas(Vector<Mesa> listaPartidas)
//    {
//		Connection conn = null;
//		try
//		{
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(url, login, password);
//
//			if (conn != null)
//			{
//                 Statement stmt = conn.createStatement();
//                 ResultSet rs = stmt.executeQuery("SELECT * FROM mesa");
//                 Mesa me;
//
//                /* while(rs.next())
//				 {
//                    listaPartidas.add(new Mesa(rs.getInt("Num jugadores"),rs.getInt("idMesa")));
//				 }*/
//				conn.close();
//			}
//		}
//        catch (SQLException ex)
//		{
//			System.out.println("Hubo un problema al intentar conectarse con la base de datos " + url);
//			System.out.println(ex);
//		}
//		catch (ClassNotFoundException ex)
//		{
//			System.out.println(ex);
//		}
//    }
//
//    public void insertarMesas(Mesa m) {
//        /*inserta la mesa m en la BBDD*/
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
//
//    void updateJugador(Jugador j) {
//
//        /*hace update de todos los campos del jugador j.alias --> seguramente sera innecesaria*/
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
}
