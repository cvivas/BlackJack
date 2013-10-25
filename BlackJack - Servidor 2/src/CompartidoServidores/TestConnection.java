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

    /*
     * MODO DE EMPLEO:
     * Crear TestConnection t;
     * hacer t.loquesea. Y COMENTARLO (EN ARAMEO O EN CANADIENSE)
     * avisar a marc.
     */




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

//    public int obtenerCreditoJugador(String Alias)
//	{
//		Connection conn = null;
//		try
//		{
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(url, login, password);
//
//			if (conn != null)
//			{
//                 Statement stmt = conn.createStatement();
//				 ResultSet rs = stmt.executeQuery("SELECT * FROM jugador");
//
//				 while(rs.next())
//				 {
//					 String Al = rs.getString("Alias");
//					 if(Al.equals(Alias)) return rs.getInt("credito");
//				 }
//				conn.close();
//			}
//            else return -1;
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
//        return -1;
//	}

//    public Jugador obtenerJugador(String Alias)
//	{
//        Jugador jug = new Jugador();
//		Connection conn = null;
//		try
//		{
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(url, login, password);
//
//			if (conn != null)
//			{
//                 Statement stmt = conn.createStatement();
//				 ResultSet rs = stmt.executeQuery("SELECT * FROM jugador");
//
//				 while(rs.next())
//				 {
//					 String Al = rs.getString("Alias");
//					 if(Al.equals(Alias))
//                     {
//                         jug.setAlias(Alias);
//                         jug.setCredito(rs.getInt("credito"));
//                         int aux = rs.getInt("Jugando");
//                         if(aux==0)jug.setJugando(false);
//                         else jug.setJugando(true);
//                         aux = rs.getInt("Preparado");
//                         if(aux==0)jug.setEsperando(false);
//                         else jug.setEsperando(true);
//                         jug.setNumMesa(rs.getInt("Num Mesa"));
//                         return jug;
//                     }
//				 }
//				conn.close();
//			}
//            else return null;
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
//        return null;
//	}

//    public void insertarGananciaJugador(String Alias, int ganancia)
//	{
//		Connection conn = null;
//		try
//		{
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(url, login, password);
//
//			if (conn != null)
//			{
//                Statement stmt = conn.createStatement();
//                String Update = "update jugador set credito = "+ganancia+" where Alias = '"+Alias+"'";
//				stmt.executeUpdate(Update);
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
//	}

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

//    public void insertarGananciaAmbos(String Alias, int ganancia)
//	{
//		Connection conn = null;
//		try
//		{
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(url, login, password);
//
//			if (conn != null)
//			{
//                Statement stmt = conn.createStatement();
//                String UpdateJ = "update usuario set credito = "+ganancia+" where Alias = '"+Alias+"'";
//				stmt.executeUpdate(UpdateJ);
//                String UpdateU = "update jugador set Credito = "+ganancia+" where Alias = '"+Alias+"'";
//				stmt.executeUpdate(UpdateU);
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
//	}

//    public void insertarJugador(Jugador jug)
//	{
//		Connection conn = null;
//		try
//		{
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(url, login, password);
//
//            int juega;
//            int preparado;
//
//            if(jug.isEsperando())preparado=1;
//            else preparado = 0;
//
//            if(jug.isJugando())juega=1;
//            else juega=0;
//
//			if (conn != null)
//			{
//                Statement stmt = conn.createStatement();
//                stmt.executeUpdate("INSERT INTO jugador " + "VALUES" +
//                    " ('"+jug.getAlias()+"'," +
//                    " "+jug.getNumMesa()+"," +
//                    " "+juega+"," +
//                    " "+preparado+"," +
//                    " "+jug.getCredito()+")");
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
//	}

/*
    public Usuario obtenerUsuario(String Alias) {

        Connection conn = null;
        Usuario u = new Usuario();
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
                         u.setAlias(Alias);
                         u.setApellido1(rs.getString("Apellido1"));
                         u.setApellido2(rs.getString("Apellido2"));
                         u.setCP(rs.getInt("CP"));
                         u.setCalle(rs.getString("Calle"));
                         u.setCredito(rs.getInt("Credito"));

                         int aux = rs.getInt("Logged");
                         if(aux==0)u.setLogged(false);
                         else u.setLogged(true);
                         aux = rs.getInt("Jugando");
                         if(aux==0)u.setJugando(false);
                         else u.setJugando(true);

                        // u.setMail(rs.getString("mail"));
                         u.setNombre(rs.getString("Nombre"));
                         u.setPais(rs.getString("Pais"));
                         u.setPassword(rs.getString("Password"));
                         u.setPoblacion(rs.getString("Poblacion"));
                         u.setProvincia(rs.getString("Provincia"));

                         //falta por insertar los dos parametros de fechas
                         return u;
                     }
                     else return null;
				 }
                 conn.close();
            }
            else return null;
        }
        catch(SQLException ex) {
            System.out.println("Hubo un problema al intentar conectarse con la base de datos "+url);
            System.out.println(ex);
        }
        catch(ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return null;
    }*/
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
