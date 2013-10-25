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

        System.out.println("restando pasta: tenia : "+this.credito+" y le vamos a restar : "+c+" a ver cuanto da el restultado  "+(credito-c));
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

 
}
