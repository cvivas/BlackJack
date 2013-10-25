/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blackjackservidor2;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import CompartidoServidores.*;
import CompartidoClientes.*;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author marc.sitges
 */
public class ThreadServidor extends Thread{

    private SSLSocket servidorSocket;
    private int accion;
    protocolosServidor kkp;
    final String[] enabledCipherSuites = { "SSL_DH_anon_WITH_RC4_128_MD5" };

    ThreadServidor(SSLSocket socket) {
        super("ServerPrograma");
        this.servidorSocket = socket;
        this.servidorSocket.setEnabledCipherSuites(enabledCipherSuites);
        kkp = new protocolosServidor();
    }

    public void run()
    {
        try {
            accion = escucharServidor();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThreadServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch(accion)
        {
            case 1: recibirEstado();break;
        }
    }

    private int escucharServidor() throws ClassNotFoundException {

            ObjectInputStream in = null;
            try {
                in = new ObjectInputStream(servidorSocket.getInputStream());
                String valor = (String) in.readObject();
                return Integer.parseInt(valor);
            } catch (IOException ex) {
                //Logger.getLogger(ServerPrograma.class.getName()).log(Level.SEVERE, null, ex);
            }
            return -1;
    }

    private void recibirEstado() {

        ObjectInputStream in = null;
        Object a,b,c,d;

        try {

            in = new ObjectInputStream(servidorSocket.getInputStream());
            a = in.readObject();

            in = new ObjectInputStream(servidorSocket.getInputStream());
            b = in.readObject();
            in = new ObjectInputStream(servidorSocket.getInputStream());
            c = in.readObject();
            Integer cc = (Integer) c;
            in = new ObjectInputStream(servidorSocket.getInputStream());
            d = in.readObject();

            if(a!=null &&b !=null && c!=null && d!=null)
            {
                CtrlJuego.setCJ((CtrlJugadores)a);
                CtrlJuego.setCM((CtrlMesas)b);
                CtrlJuego.setNumMesas(cc.intValue());
                CtrlJuego.setPartidas((Rondas[])d);
            }
        } catch (IOException ex) {
            Logger.getLogger(ThreadServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

