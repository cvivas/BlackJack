/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blackjackservidor2;

import CompartidoServidores.CtrlJuego;
import CompartidoClientes.Mesa;
import CompartidoClientes.Tcom;
import CompartidoClientes.Tjugada;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import CompartidoServidores.*;
import CompartidoClientes.*;
import javax.net.ssl.*;

/**
 *
 * @author marc.sitges
 */
public class ThreadsClientes extends Thread implements Serializable {

    private SSLSocket clienteSocket;
    private SSLSocket clienteServSocket;
    private SSLServerSocket serverToApp;
    private SSLServerSocket serverToServer;
    private protocolosServidor kkp;
    private int accion;

    public ThreadsClientes(SSLServerSocket socket, SSLServerSocket StS) {
        super("ServerPrograma");
        serverToApp = socket;
        serverToServer = StS;
        kkp = new protocolosServidor();
    }

    public void run() {

        while(true)
        {
            try {
                clienteSocket = (SSLSocket) serverToApp.accept();
            } catch (IOException ex) {
                Logger.getLogger(ThreadsClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                accion = escucharCliente();
            } catch (ClassNotFoundException ex) {
                //Logger.getLogger(ThreadsClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
            switch(accion)
            {
                case 1:existeUsuario();break;
                case 2:crearJugador();break;
                case 3:obtenerCredito();break;
                case 4:
                {System.out.println("VAMOS A ENVIARLE EL ESTADO AL CLIENTE");enviarEstadoACliente();break;}
                case 5:recibirDatosPartida();break;
                case 6:cargarMesas();break;
                case 7:enviarEstadoAClienteAnterior();break;
                default: break;
            }
            if(EstaSegundoActivo())enviarEstadoASegundoServidor();
            try {
              //  serverToServer.close();
                clienteSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(ThreadsClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private boolean EstaSegundoActivo() {

        boolean activo = false;
        try {
                serverToServer.setSoTimeout(200);
                clienteServSocket = (SSLSocket) serverToServer.accept();
                activo = true;
            } catch (UnknownHostException ex) {
                activo = false;
            } catch (IOException ex) {
                activo = false;
            }
        try {
            serverToServer.setSoTimeout(0);
        } catch (SocketException ex) {
            //Logger.getLogger(ThreadsClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
                Logger.getLogger(ThreadsClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(activo);
            return activo;
    }

    private void avisarAccion(String valor) {

        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(clienteServSocket.getOutputStream());
            out.writeObject(valor);
        } catch (IOException ex) {
           // Logger.getLogger(ServerPrograma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarMesas() {
        ObjectOutputStream out;
        ArrayList<Mesa> listaMesas = null;
        listaMesas = kkp.buscarPartidas();
        

        try {
            Mesa temp = new Mesa(1,1);
            System.out.println("id de la mesa que pasamos : "+temp.getIdMesa());
            listaMesas= CtrlJuego.getCM().getMesas();
            out = new ObjectOutputStream(clienteSocket.getOutputStream());
            out.writeObject(listaMesas);
            //out.writeObject(temp);

            out.flush();

        } catch (IOException ex) {
            Logger.getLogger(ServerPrograma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void crearJugador() {

        ObjectOutputStream out;
        ObjectInputStream in;
        String Alias = "";
        int idm=-5;

        try {

            in = new ObjectInputStream(clienteSocket.getInputStream());
            Alias = (String) in.readObject();
            in = new ObjectInputStream(clienteSocket.getInputStream());
            idm = ((Integer) in.readObject()).intValue();
          //  System.out.println("HELLO WORLD");
            kkp.crearJugador(Alias, idm);

        } catch (IOException ex) {
            Logger.getLogger(ServerPrograma.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerPrograma.class.getName()).log(Level.SEVERE, null, ex);

        }
      /*  if(EstaSegundoActivo())
        {
            try {

            clienteServSocket = serverToServer.accept();
            avisarAccion("1");

            out = new ObjectOutputStream(clienteServSocket.getOutputStream());
            out.writeObject(Alias);
            clienteServSocket.close();
            } catch (UnknownHostException ex) {
                      //  Logger.getLogger(ServerPrograma.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                      //  Logger.getLogger(ServerPrograma.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
    }

    private void enviarEstadoACliente() {
       ObjectOutputStream out = null;
       ObjectInputStream in;
       Tcom t = null;
       String temp;
       int idm = -1;
       try {

            in = new ObjectInputStream(clienteSocket.getInputStream());
            temp = (String) in.readObject();
            idm = Integer.parseInt(temp);
            t=kkp.obtenerTcom(idm);
            out = new ObjectOutputStream(clienteSocket.getOutputStream());
            out.writeObject(t);


        } catch (IOException ex) {
            Logger.getLogger(ServerPrograma.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerPrograma.class.getName()).log(Level.SEVERE, null, ex);

        }


    }


    private void enviarEstadoAClienteAnterior() {
       ObjectOutputStream out = null;
       ObjectInputStream in;
       Tcom t = null;
       String temp;
       int idm = -1;
       try {

            in = new ObjectInputStream(clienteSocket.getInputStream());
            temp = (String) in.readObject();
            idm = Integer.parseInt(temp);
            t=kkp.obtenerTcomAntes(idm);
            out = new ObjectOutputStream(clienteSocket.getOutputStream());
            out.writeObject(t);


        } catch (IOException ex) {
            Logger.getLogger(ServerPrograma.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerPrograma.class.getName()).log(Level.SEVERE, null, ex);

        }


    }




    private void enviarEstadoASegundoServidor() {

        ObjectOutputStream out = null;
        try {

            clienteServSocket = (SSLSocket) serverToServer.accept();
            avisarAccion(CONSTANTES.RECIBIRESTADO); //recibir datos del primer servidor

            out = new ObjectOutputStream(clienteServSocket.getOutputStream());
            out.writeObject(CtrlJuego.getCJ()); //Ctrl Jugadores
            out = new ObjectOutputStream(clienteServSocket.getOutputStream());
            out.writeObject(CtrlJuego.getCM()); //Ctrl Mesas
            out = new ObjectOutputStream(clienteServSocket.getOutputStream());
            out.writeObject(new Integer(CtrlJuego.getNumMesas())); //Num mesas
            out = new ObjectOutputStream(clienteServSocket.getOutputStream());
            out.writeObject(CtrlJuego.getPartidas()); //Num Rondas
            
            clienteServSocket.close();
        } catch (UnknownHostException ex) {
                      //  Logger.getLogger(ServerPrograma.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
                      //  Logger.getLogger(ServerPrograma.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private int escucharCliente() throws ClassNotFoundException {

       ObjectInputStream in;
            try {
                in = new ObjectInputStream(clienteSocket.getInputStream());
                String valor = (String) in.readObject();
                return Integer.parseInt(valor);
            } catch (IOException ex) {
                //Logger.getLogger(ServerPrograma.class.getName()).log(Level.SEVERE, null, ex);
            }
       return -1;
    }

    private void existeUsuario() {

        ObjectInputStream in;
        boolean res = false;
        ObjectOutputStream out = null;

        try {
            in = new ObjectInputStream(clienteSocket.getInputStream());
            String Alias = (String) in.readObject();

            in = new ObjectInputStream(clienteSocket.getInputStream());
            String Password = (String) in.readObject();

            System.out.println(Alias+Password);

            res = kkp.existeUsuario(Alias, Password);
            out = new ObjectOutputStream(clienteSocket.getOutputStream());

        } catch (IOException ex) {
             //   Logger.getLogger(ThreadsClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
             //   Logger.getLogger(ThreadsClientes.class.getName()).log(Level.SEVERE, null, ex);
        }


        try {
            if(res) out.writeObject("existeUsuario de Thread Clientes = true");
            else out.writeObject("existeUsuario de Thread Clientes = false");
        } catch (IOException ex) {
           // Logger.getLogger(ThreadsClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    private void obtenerCredito() {
        ObjectInputStream in;
        int credito = 0;
        ObjectOutputStream out = null;
        
        try {
            in = new ObjectInputStream(clienteSocket.getInputStream());
            String Alias = (String) in.readObject();

            System.out.println(Alias);

            credito = kkp.obtenerCredito(Alias);
            out = new ObjectOutputStream(clienteSocket.getOutputStream());
            out.writeObject(String.valueOf(credito));

        } catch (IOException ex) {
             //   Logger.getLogger(ThreadsClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
             //   Logger.getLogger(ThreadsClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void recibirDatosPartida() {

        ObjectInputStream in;
        Tjugada t = null;
        ObjectOutputStream out = null;
        try {
            in = new ObjectInputStream(clienteSocket.getInputStream());
            t = (Tjugada) in.readObject();
            CtrlJuego CJ = new CtrlJuego();
            CJ.jugada(t);
            /*enviarlo a dominio para que lo trate*/


        } catch (IOException ex) {
             //   Logger.getLogger(ThreadsClientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
             //   Logger.getLogger(ThreadsClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
