/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * completa
 */
package CompartidoClientes;

import java.io.Serializable;

/**
 *
 * @author carlos.vivas-abilahoud
 */
public class Mesa implements Serializable {

    private int numJugadores;
    private int idMesa; //clau primaria
    private static final long serialVersionUID = 12345678;

  //  private int numRondas;

    public Mesa() {
    }

    public Mesa(int idMesa){

        this.numJugadores=0;
        this.idMesa=idMesa;
        //this.numRondas=0;

    }

    public Mesa(int numJugadores, int idMesa) {

        /* USAREMOS ESTA SIEMPRE */
        this.numJugadores = numJugadores;
        this.idMesa = idMesa;
    }

    /**
     * @return the numJugadores
     */
    public int getNumJugadores() {
        return numJugadores;
    }

    /**
     * @param numJugadores the numJugadores to set
     */
    public int setNumJugadores(int numJugadores) {

        if (numJugadores>5 || numJugadores <0) return -5;
        this.numJugadores = numJugadores;
        return this.getNumJugadores();
    }

    /**
     * @return the idMesa
     */
    public int getIdMesa() {
        return idMesa;
    }

    /**
     * @param idMesa the idMesa to set
     */
    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int addJugador(){

        if(this.numJugadores<6) {
            this.numJugadores++;
            
        return this.getNumJugadores();
        }
        return -5;
    }
    public int eliminarJugador(){

        if(this.numJugadores>0) {
            this.numJugadores--;
        }
            return this.getNumJugadores();

    }



    /**
     * @return the numRondas
     */
//    public int getNumRondas() {
//        return numRondas;
//    }

    /**
     * @param numRondas the numRondas to set
     */
//    public void setNumRondas(int numRondas) {
//        this.numRondas = numRondas;
//    }

//    public void incrNumRondas(){
//
//        this.numRondas++;
//    }



}
