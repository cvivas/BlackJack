/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CompartidoServidores;
import CompartidoClientes.Mesa;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author carlos.vivas-abilahoud
 */
public class CtrlRondas implements Serializable{




    protected Jugador[] jugadoresRonda;
    protected double[] apuestas;
    protected double[] apuestasSplit;
    protected Baraja deck;
    protected Mesa mesa;

    public CtrlRondas() {
        this.jugadoresRonda = new Jugador[5];
        this.apuestas = new double [5];
        this.apuestasSplit = new double [5];
        this.deck = new Baraja();
        mesa = new Mesa();
    }

    private int obtenerPosJug(Jugador j){

    int i =0;
        boolean trobat = false;

        while (i<5 && !trobat){

            if (this.jugadoresRonda[i]!=null) {

                this.jugadoresRonda[i]= j;
                trobat = true;

            }

            else i++;

        }
     return i;

    }
    public boolean addJugador(Jugador j){

        int i =0;
        boolean trobat = false;

        while (i<5 && !trobat){

            if (this.jugadoresRonda[i]!=null) {

                this.jugadoresRonda[i]= j;
                this.apuestas[i]=-1;
                this.apuestasSplit[i]=-1;
                this.mesa.addJugador();
                trobat = true;

            }

            i++;

        }

    return trobat;
    }

    public boolean eliminarJugador(Jugador j){

        int i = this.obtenerPosJug(j);
        if (i<5) {
            this.jugadoresRonda[i] = null;
            this.apuestas[i]=0;
            this.apuestasSplit[i]=0;
            this.mesa.eliminarJugador();
            return true;


        }
        return false;

    }

    public void addApuesta (Jugador j, double a){

        int i = this.obtenerPosJug(j);
        if (i<5) this.apuestas[i]=a;


    }

    public void addApuestaSplit (Jugador j){

        int i = this.obtenerPosJug(j);
        if (i<5) this.apuestasSplit[i]=this.apuestas[i];


    }

    public void haceDoble(Jugador j){

        int i = this.obtenerPosJug(j);
        if (i<5) this.apuestas[i]+=this.apuestas[i];

    }

    public void haceDobleSplit(Jugador j){

        int i = this.obtenerPosJug(j);
        if (i<5) this.apuestasSplit[i]+=this.apuestasSplit[i];

    }

    public double obtenerApuesta(Jugador j){

        double a = -1;
        int i = this.obtenerPosJug(j);
        if (i<5) a=this.apuestas[i];

        return a;

    }

    public double obtenerApuestaSplit(Jugador j){


        double a = -1;
        int i = this.obtenerPosJug(j);
        if (i<5) a=this.apuestasSplit[i];

        return a;

    }

    public void renovarDeck(){

        this.deck = new Baraja();

    }











}
