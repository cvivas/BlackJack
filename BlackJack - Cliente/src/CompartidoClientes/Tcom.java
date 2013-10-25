/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CompartidoClientes;

import java.util.Calendar;
import java.io.*;

/**
 *
 * @author carlos.vivas-abilahoud
 */
public class Tcom implements Serializable{
     private static final long serialVersionUID = 1234;

    private String[] nicks;
    private int[] creditos;
    private TcomManos[] manos;
    private int[] apuestas;
    private boolean[] plantado;
    private boolean[] pasado;
    private boolean[] blackJack;
    private boolean[] doble;

    private TcomManos[] splitManos;
    private boolean[] hechoSplits;
    private int[] splitApuestas;
    private boolean[] splitDoble;
    private boolean[] splitPlantarse;
    private boolean[] splitPasarse;
    private boolean[] splitBlackJack;
    private int turno;
    private int numJugadores;
    private int fase;
    private boolean rondaEspera;
    private int idm;
    private Calendar fechaPartida;
    private int ultimo;

    public Tcom() {
    }

    public Tcom(String[] nicks, int[] creditos, TcomManos[] manos, int[] apuestas, boolean[] plantado, boolean[] pasado, boolean[] blackJack, boolean[] doble, TcomManos[] splitManos, boolean[] hechoSplits, int[] splitapuestas, boolean[] splitdoble, boolean[] splitPlantarse, boolean[] splitPasarse, boolean[] splitBlackJack, int turno, int numJugadores, int fase, boolean rondaEspera, int idm, Calendar fechaPartida,int ultim) {
        this.nicks = nicks;
        this.creditos = creditos;
        this.manos = manos;
        this.apuestas = apuestas;
        this.plantado = plantado;
        this.pasado = pasado;
        this.blackJack = blackJack;
        this.doble = doble;
        this.splitManos = splitManos;
        this.hechoSplits = hechoSplits;
        this.splitApuestas = splitapuestas;
        this.splitDoble = splitdoble;
        this.splitPlantarse = splitPlantarse;
        this.splitPasarse = splitPasarse;
        this.splitBlackJack = splitBlackJack;
        this.turno = turno;
        this.numJugadores = numJugadores;
        this.fase = fase;
        this.rondaEspera = rondaEspera;
        this.idm = idm;
        this.fechaPartida = fechaPartida;
        this.ultimo=ultim;
    }

    /**
     * @return the nicks
     */
    public String[] getNicks() {
        return nicks;
    }

    public void listarTcom() {
        System.out.println("imprimiendo ronda:");

        System.out.println("nombres:");
        System.out.print(this.nicks);
        System.out.println("idmesa: "+this.idm);
        System.out.println("turno...:"+this.turno);
        System.out.println("fase  : "+this.fase);





    }

    /**
     * @param nicks the nicks to set
     */
    public void setNicks(String[] nicks) {
        this.nicks = nicks;
    }

    /**
     * @return the creditos
     */
    public int[] getCreditos() {
        return creditos;
    }

    /**
     * @param creditos the creditos to set
     */
    public void setCreditos(int[] creditos) {
        this.creditos = creditos;
    }

    /**
     * @return the manos
     */
    public TcomManos[] getManos() {
        return manos;
    }

    /**
     * @param manos the manos to set
     */
    public void setManos(TcomManos[] manos) {
        this.manos = manos;
    }

    /**
     * @return the apuestas
     */
    public int[] getApuestas() {
        return apuestas;
    }

    /**
     * @param apuestas the apuestas to set
     */
    public void setApuestas(int[] apuestas) {
        this.apuestas = apuestas;
    }

    /**
     * @return the plantado
     */
    public boolean[] getPlantado() {
        return plantado;
    }

    /**
     * @param plantado the plantado to set
     */
    public void setPlantado(boolean[] plantado) {
        this.plantado = plantado;
    }

    /**
     * @return the pasado
     */
    public boolean[] getPasado() {
        return pasado;
    }

    /**
     * @param pasado the pasado to set
     */
    public void setPasado(boolean[] pasado) {
        this.pasado = pasado;
    }

    /**
     * @return the blackJack
     */
    public boolean[] getBlackJack() {
        return blackJack;
    }

    /**
     * @param blackJack the blackJack to set
     */
    public void setBlackJack(boolean[] blackJack) {
        this.blackJack = blackJack;
    }

    /**
     * @return the doble
     */
    public boolean[] getDoble() {
        return doble;
    }

    /**
     * @param doble the doble to set
     */
    public void setDoble(boolean[] doble) {
        this.doble = doble;
    }

    /**
     * @return the splitManos
     */
    public TcomManos[] getSplitManos() {
        return splitManos;
    }

    /**
     * @param splitManos the splitManos to set
     */
    public void setSplitManos(TcomManos[] splitManos) {
        this.splitManos = splitManos;
    }

    /**
     * @return the hechoSplits
     */
    public boolean[] getHechoSplits() {
        return hechoSplits;
    }

    /**
     * @param hechoSplits the hechoSplits to set
     */
    public void setHechoSplits(boolean[] hechoSplits) {
        this.hechoSplits = hechoSplits;
    }

    /**
     * @return the splitapuestas
     */
    public int[] getSplitapuestas() {
        return splitApuestas;
    }

    /**
     * @param splitapuestas the splitapuestas to set
     */
    public void setSplitapuestas(int[] splitapuestas) {
        this.splitApuestas = splitapuestas;
    }

    /**
     * @return the splitdoble
     */
    public boolean[] getSplitdoble() {
        return splitDoble;
    }

    /**
     * @param splitdoble the splitdoble to set
     */
    public void setSplitdoble(boolean[] splitdoble) {
        this.splitDoble = splitdoble;
    }

    /**
     * @return the splitPlantarse
     */
    public boolean[] getSplitPlantarse() {
        return splitPlantarse;
    }

    /**
     * @param splitPlantarse the splitPlantarse to set
     */
    public void setSplitPlantarse(boolean[] splitPlantarse) {
        this.splitPlantarse = splitPlantarse;
    }

    /**
     * @return the splitBlackJack
     */
    public boolean[] getSplitBlackJack() {
        return splitBlackJack;
    }

    /**
     * @param splitBlackJack the splitBlackJack to set
     */
    public void setSplitBlackJack(boolean[] splitBlackJack) {
        this.splitBlackJack = splitBlackJack;
    }

    /**
     * @return the turno
     */
    public int getTurno() {
        return turno;
    }

    /**
     * @param turno the turno to set
     */
    public void setTurno(int turno) {
        this.turno = turno;
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
    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }

    /**
     * @return the fase
     */
    public int getFase() {
        return fase;
    }

    /**
     * @param fase the fase to set
     */
    public void setFase(int fase) {
        this.fase = fase;
    }

    /**
     * @return the rondaEspera
     */
    public boolean getRondaEspera() {
        return isRondaEspera();
    }

    /**
     * @param rondaEspera the rondaEspera to set
     */
    public void setRondaEspera(boolean rondaEspera) {
        this.rondaEspera = rondaEspera;
    }

    /**
     * @return the idm
     */
    public int getIdm() {
        return idm;
    }

    /**
     * @param idm the idm to set
     */
    public void setIdm(int idm) {
        this.idm = idm;
    }

    /**
     * @return the fechaPartida
     */
    public Calendar getFechaPartida() {
        return fechaPartida;
    }

    /**
     * @param fechaPartida the fechaPartida to set
     */
    public void setFechaPartida(Calendar fechaPartida) {
        this.fechaPartida = fechaPartida;
    }

    /**
     * @return the rondaEspera
     */
    public boolean isRondaEspera() {
        return rondaEspera;
    }

    /**
     * @return the ultimo
     */
    public int getUltimo() {
        return ultimo;
    }

    /**
     * @param ultimo the ultimo to set
     */
    public void setUltimo(int ultimo) {
        this.ultimo = ultimo;
    }

    /**
     * @return the splitPasarse
     */
    public boolean[] getSplitPasarse() {
        return splitPasarse;
    }

    /**
     * @param splitPasarse the splitPasarse to set
     */
    public void setSplitPasarse(boolean[] splitPasarse) {
        this.splitPasarse = splitPasarse;
    }










}
