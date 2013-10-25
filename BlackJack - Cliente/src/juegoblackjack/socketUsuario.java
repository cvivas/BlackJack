/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package juegoblackjack;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import CompartidoClientes.*;
import javax.net.ssl.*;
//import servidor.Mesa;

/**
 *
 * @author marc.sitges
 */
public class socketUsuario implements Serializable{

    SSLSocketFactory sslsocketfactory;
    private SSLSocket socketServ;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    final String[] enabledCipherSuites = { "SSL_DH_anon_WITH_RC4_128_MD5" };

    public socketUsuario()
    {
        sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        if(!segundarioActivo())
        {
            try {
                socketServ = (SSLSocket) sslsocketfactory.createSocket(CONSTANTES.IPPRIMARIA,CONSTANTES.PUERTOPRIMARIOCLIENTE);
                socketServ.setEnabledCipherSuites(enabledCipherSuites);
            } catch (IOException ex) {
                Logger.getLogger(socketUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            try {
                System.out.println("LEFGA");
                socketServ = (SSLSocket) sslsocketfactory.createSocket(CONSTANTES.IPSECUNDARIA,CONSTANTES.PUESTOSECUNDARIOCLIENTE);
                socketServ.setEnabledCipherSuites(enabledCipherSuites);
                System.out.println("HFJDGHJASDHASDGSDFSDJ");
            } catch (IOException ex) {
                Logger.getLogger(socketUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
    }

    public boolean existeUsuario(String Alias, String Pass)throws ClassNotFoundException, IOException
    {
        String res ="";
            out = new ObjectOutputStream(socketServ.getOutputStream());
            out.writeObject(Alias);
            out = new ObjectOutputStream(socketServ.getOutputStream());
            out.writeObject(Pass);
            in = new ObjectInputStream(socketServ.getInputStream());
            res = (String) in.readObject();

            if(res.equals("false"))return false;
            else return true;
    }

    public void crearJugador(String Alias, int idm) throws IOException
    {
            out = new ObjectOutputStream(socketServ.getOutputStream());
            out.writeObject(Alias);

            out = new ObjectOutputStream(socketServ.getOutputStream());
            out.writeObject(new Integer(idm));


    }

   public int obtenerCredito(String Alias) throws IOException, ClassNotFoundException{

               String res ="";
               int credito;

               out = new ObjectOutputStream(socketServ.getOutputStream());
               out.writeObject(Alias);
               in = new ObjectInputStream(socketServ.getInputStream());
               res = (String) in.readObject();
               credito= Integer.parseInt(res);

               return credito;

    }
    public void avisarAccion(String accion) throws IOException{

                out = new ObjectOutputStream(socketServ.getOutputStream());
                out.writeObject(accion);
    }

    public ArrayList<Mesa> obtenerMesas() {

       ArrayList<Mesa> lista = null;

       try {

            in= new ObjectInputStream(socketServ.getInputStream());
             lista = (ArrayList<Mesa>) in.readObject();


        } catch (IOException ex) {
            Logger.getLogger(socketUsuario.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            //Logger.getLogger(socketUsuario.class.getName()).log(Level.SEVERE, null, ex);

        }
        return lista;

    }

    public Tcom pedirEstadoPartida(int idm) {
       ObjectOutputStream out = null;
       ObjectInputStream in;
       Tcom t = null;
       String temp;

       try {

            String a = String.valueOf(idm);
            System.out.println("escibimos id mesa:   "+a);
            out = new ObjectOutputStream(socketServ.getOutputStream());
            out.writeObject(a);
            in = new ObjectInputStream(socketServ.getInputStream());
            t= (Tcom) in.readObject();



        } catch (IOException ex) {
            System.out.println("entramos en la 1era EXCEPTION");
            Logger.getLogger(socketUsuario.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            System.out.println("entramos en la sec EXCEPTION");
            Logger.getLogger(socketUsuario.class.getName()).log(Level.SEVERE, null, ex);

        }





       return t;

    }

    public void enviarJugada(Tjugada tj) throws IOException{
        out = new ObjectOutputStream(socketServ.getOutputStream());
        out.writeObject(tj);
    }

    private boolean segundarioActivo() {

        if(socketServ != null && socketServ.isConnected())return true;
        try {
            socketServ = (SSLSocket) sslsocketfactory.createSocket();
            InetSocketAddress endPoint = new InetSocketAddress( CONSTANTES.IPSECUNDARIA,9005  );
            socketServ.connect(endPoint, 100);
            socketServ = null;
            System.out.println("HOLA");
            return true;
        } catch (IOException ex) {
            System.out.println(ex);
            return false;
        }
    }
}


