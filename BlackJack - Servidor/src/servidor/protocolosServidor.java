/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servidor;

/**
 *
 * @author marc.sitges
 */
import CompartidoServidores.TestConnection;
import CompartidoServidores.Jugador;
import CompartidoServidores.CtrlJuego;
import CompartidoClientes.Mesa;
import CompartidoClientes.Tcom;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import CompartidoServidores.*;
import CompartidoClientes.*;

public class protocolosServidor {

    private TestConnection t;

    public boolean existeUsuario(String alias, String password)
    {
        t = new TestConnection();
        return t.existeUsuario(alias, password);
    }

    public ArrayList<Mesa> buscarPartidas() {


        

        return CtrlJuego.getCM().getMesas();
     
    }

    public Jugador crearJugador(String Alias, int idm)
    {

        CtrlJuego CJ= new CtrlJuego();
        CJ.addJugador(Alias);
        CJ.addJugador(Alias, idm);
        System.out.println("TENEMOS INSERTADOS: "+CJ.getCM().getMesas().get(idm).getNumJugadores());
        return CJ.buscarJugador(Alias);

        
    }

    public int obtenerCredito(String Alias){
         t = new TestConnection();
         return t.obtenerCreditoUsuario(Alias);
    }

    Tcom obtenerTcom(int idm) {
        CtrlJuego CJ = new CtrlJuego();
        return CJ.generarTcom(idm);
    }
    
    Tcom obtenerTcomAntes(int idm) {
        CtrlJuego CJ = new CtrlJuego();
        return CJ.generarTcomAntes(idm);
    }


}

