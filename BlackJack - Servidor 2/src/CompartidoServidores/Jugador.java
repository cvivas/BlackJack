/*
 * To change this template, choose Tools | Templates
   * and open the template in the editor.
 */

package CompartidoServidores;

import java.io.Serializable;

/*
 * En principio completa eliminando tabla jugador de bbdd
 *quizas es mejor quitar el jugador de la BBDD y solo hacer el acceso al credito
 */
/**
 *
 * @author carlos.vivas-abilahoud
 */
public class Jugador implements Serializable{

    protected String Aliasj;
    protected int numMesa;
    private boolean esperando;//esta en una partida en curso y no juega
    protected boolean jugando; //esta jugando en una partida en curso
    private int credito;
    //protected int numPartida;
    //protected boolean aceptarSeguro;
    //protected boolean haceDoble;
    //protected boolean pasarse;
    //protected boolean plantarse;
    //protected boolean tieneBlackJack;
    //protected double apuesta;
    //protected boolean haceSplit;
    //protected boolean haceSplitDoble;
    //protected boolean splitPasarse;
    //protected boolean splitPlantarse;
    //protected boolean tieneSplitBlackJack;
    //protected double splitApuesta;

    public Jugador(String a, int c) {

        this.Aliasj = a;
        this.jugando = false;
        this.credito=c;
        this.esperando=false;
        this.numMesa=-5;


    }

    public Jugador (String a){

        this.Aliasj=a;
        this.jugando=true;
        this.credito=-5;
        this.esperando=false;
        this.numMesa=-5;



    }

    public Jugador() {
    }

    /**
     * @return the alias
     */
    public String getAlias() {
        return Aliasj;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.Aliasj = alias;
    }

    /**
     * @return the numMesa
     */
    public int getNumMesa() {
        return numMesa;
    }

    /**
     * @param numMesa the numMesa to set
     */
    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;

    }

    /**
     * @return the numPartida
     */
//    public int getNumPartida() {
//        return numPartida;
//    }
//
//    /**
//     * @param numPartida the numPartida to set
//     */
//    public void setNumPartida(int numPartida) {
//        this.numPartida = numPartida;
//    }

    /**
     * @return the jugando
     */
    public boolean isJugando() {
        return jugando;
    }

    /**
     * @param jugando the jugando to set
     */
    public void setJugando(boolean jugando) {
        this.jugando = jugando;
    }


    public boolean sonIguales (String a){

        if(a == null)return false;
        //System.out.println("Jugador: imprimimos el alias:  "+this.getAlias());
        return a.equalsIgnoreCase(this.getAlias());

    }

    /**
     * @return the credito
     */
    public int getCredito() {
        return credito;
    }

    /**
     * @param credito the credito to set
     */
    public void setCredito(int credito) {
        this.credito = credito;
        TestConnection t = new TestConnection();
        t.insertarGananciaUsuario(Aliasj, this.credito);
    }

    public void sumarCredito(int c){

        this.credito+=c;
         TestConnection t = new TestConnection();
        t.insertarGananciaUsuario(Aliasj, this.credito);
    }

    public void restarCredito(int c){

        /*if (c>0)*/ this.credito=this.credito-c;
        ///if(c<0) this.credito= this.credito+c;
         TestConnection t = new TestConnection();
        t.insertarGananciaUsuario(Aliasj, this.credito);
    }

    /**
     * @return the esperando
     */
    public boolean isEsperando() {
        return esperando;
    }

    /**
     * @param esperando the esperando to set
     */
    public void setEsperando(boolean esperando) {
        this.esperando = esperando;
    }

    void listar() {
        System.out.println("Jugador :");
        System.out.print(this.Aliasj+"  credito: "+this.credito+"  mesa: "+this.numMesa+"  jug:"+this.jugando+"  esp:"+this.esperando);
    }


    public boolean isEmpty(){

        return this.Aliasj==null;

    }

    




//    /**
//     * @return the preparadoParaJugar
//     */
//    public boolean isPreparadoParaJugar() {
//        return preparadoParaJugar;
//    }
//
//    /**
//     * @param preparadoParaJugar the preparadoParaJugar to set
//     */
//    public void setPreparadoParaJugar(boolean preparadoParaJugar) {
//        this.preparadoParaJugar = preparadoParaJugar;
//    }
//
//    /**
//     * @return the aceptarSeguro
//     */
//    public boolean isAceptarSeguro() {
//        return aceptarSeguro;
//    }
//
//    /**
//     * @param aceptarSeguro the aceptarSeguro to set
//     */
//    public void setAceptarSeguro(boolean aceptarSeguro) {
//        this.aceptarSeguro = aceptarSeguro;
//    }
//
//    /**
//     * @return the haceDoble
//     */
//    public boolean isHaceDoble() {
//        return haceDoble;
//    }
//
//    /**
//     * @param haceDoble the haceDoble to set
//     */
//    public void setHaceDoble(boolean haceDoble) {
//        this.haceDoble = haceDoble;
//    }
//
//    /**
//     * @return the pasarse
//     */
//    public boolean isPasarse() {
//        return pasarse;
//    }
//
//    /**
//     * @param pasarse the pasarse to set
//     */
//    public void setPasarse(boolean pasarse) {
//        this.pasarse = pasarse;
//    }
//
//    /**
//     * @return the plantarse
//     */
//    public boolean isPlantarse() {
//        return plantarse;
//    }
//
//    /**
//     * @param plantarse the plantarse to set
//     */
//    public void setPlantarse(boolean plantarse) {
//        this.plantarse = plantarse;
//    }
//
//    /**
//     * @return the tieneBlackJack
//     */
//    public boolean isTieneBlackJack() {
//        return tieneBlackJack;
//    }
//
//    /**
//     * @param tieneBlackJack the tieneBlackJack to set
//     */
//    public void setTieneBlackJack(boolean tieneBlackJack) {
//        this.tieneBlackJack = tieneBlackJack;
//    }
//
//    /**
//     * @return the apuesta
//     */
//    public double getApuesta() {
//        return apuesta;
//    }
//
//    /**
//     * @param apuesta the apuesta to set
//     */
//    public void setApuesta(double apuesta) {
//        this.apuesta = apuesta;
//    }
//
//    /**
//     * @return the haceSplit
//     */
//    public boolean isHaceSplit() {
//        return haceSplit;
//    }
//
//    /**
//     * @param haceSplit the haceSplit to set
//     */
//    public void setHaceSplit(boolean haceSplit) {
//        this.haceSplit = haceSplit;
//    }
//
//    /**
//     * @return the haceSplitDoble
//     */
//    public boolean isHaceSplitDoble() {
//        return haceSplitDoble;
//    }
//
//    /**
//     * @param haceSplitDoble the haceSplitDoble to set
//     */
//    public void setHaceSplitDoble(boolean haceSplitDoble) {
//        this.haceSplitDoble = haceSplitDoble;
//    }
//
//    /**
//     * @return the splitPasarse
//     */
//    public boolean isSplitPasarse() {
//        return splitPasarse;
//    }
//
//    /**
//     * @param splitPasarse the splitPasarse to set
//     */
//    public void setSplitPasarse(boolean splitPasarse) {
//        this.splitPasarse = splitPasarse;
//    }
//
//    /**
//     * @return the splitPlantarse
//     */
//    public boolean isSplitPlantarse() {
//        return splitPlantarse;
//    }
//
//    /**
//     * @param splitPlantarse the splitPlantarse to set
//     */
//    public void setSplitPlantarse(boolean splitPlantarse) {
//        this.splitPlantarse = splitPlantarse;
//    }
//
//    /**
//     * @return the tieneSplitBlackJack
//     */
//    public boolean isTieneSplitBlackJack() {
//        return tieneSplitBlackJack;
//    }
//
//    /**
//     * @param tieneSplitBlackJack the tieneSplitBlackJack to set
//     */
//    public void setTieneSplitBlackJack(boolean tieneSplitBlackJack) {
//        this.tieneSplitBlackJack = tieneSplitBlackJack;
//    }
//
//    /**
//     * @return the splitApuesta
//     */
//    public double getSplitApuesta() {
//        return splitApuesta;
//    }
//
//    /**
//     * @param splitApuesta the splitApuesta to set
//     */
//    public void setSplitApuesta(double splitApuesta) {
//        this.splitApuesta = splitApuesta;
//    }
}
