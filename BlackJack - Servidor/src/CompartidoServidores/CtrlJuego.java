/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CompartidoServidores;

import CompartidoClientes.CONSTANTES;
import CompartidoClientes.Tcom;
import CompartidoClientes.Tjugada;
import CompartidoServidores.*;
import CompartidoClientes.*;

/**
 *
 * @author carlos.vivas
 */
public class CtrlJuego{
    private static Rondas[] partidas;
    private static int numMesas;
    private static CtrlMesas CM;
    private static CtrlJugadores CJ;

    /**
     * @return the CM
     */
    public static CtrlMesas getCM() {
        return CM;
    }

    /**
     * @return the CJ
     */
    public static CtrlJugadores getCJ() {
        return CJ;
    }

    /**
     * @return the partidas
     */
    public static Rondas[] getPartidas() {
        return partidas;
    }

    /**
     * @param aPartidas the partidas to set
     */
    public static void setPartidas(Rondas[] aPartidas) {
        partidas = aPartidas;
    }

    /**
     * @return the numMesas
     */
    public static int getNumMesas() {
        return numMesas;
    }

    /**
     * @param aNumMesas the numMesas to set
     */
    public static void setNumMesas(int aNumMesas) {
        numMesas = aNumMesas;
    }

    /**
     * @param aCM the CM to set
     */
    public static void setCM(CtrlMesas aCM) {
        CM = aCM;
    }

    /**
     * @param aCJ the CJ to set
     */
    public static void setCJ(CtrlJugadores aCJ) {
        CJ = aCJ;
    }

    public CtrlJuego() {

    }
    //ctrl jug


    public CtrlJuego(int a) {


        this.initPrincipio();
        
    }

    public void initPrincipio(){

        /*ver si hay mesas creadas. si las hay, cargarlas. */
        /*crear la base de jugadores*/
        /*si el otro servidor esta encendido, no crear las partidas sino pedirlas*
         *en caso contrario crearlas*/
        if (getNumMesas()<1){
            String banca = "banca";
            setNumMesas(5);
            setCM(new CtrlMesas());
            getCM().initMesas(5);
            setCJ(new CtrlJugadores());
            setPartidas(new Rondas[5]);
            getCJ().addBancas(5);
            for (int j=0;j<5;j++){
                partidas[j]=new Rondas(j);
                String nomBanca = banca.concat(String.valueOf(j));
                Jugador b = getCJ().buscarJugador(nomBanca);
                if (b!=null) System.out.println("obtenemos la banca para insertar "+b.getAlias());
                partidas [j].addBanca(b);

            }

        }
        

    }

    public void addMesa(){



        int idm=getCM().addMesa();
        Rondas r = new Rondas(idm);
        getPartidas()[numMesas] =  new Rondas();


    }

    public void addRonda(int idm){

        
        getPartidas()[idm] = new Rondas(idm);
        Jugador j = getCJ().getBanca(idm);
        getPartidas()[idm].addBanca(j);



    }

    public Rondas getRonda(int idm){

        Rondas r = getPartidas()[idm];

    return r;

    }

    public Rondas nuevaPartida(int idm){

        Rondas r = this.getRonda(idm);
        r.nuevaPartida();
        return r;

    }

    public void addJugador(String alias){

        getCJ().addJugador(alias);


    }

    public void addJugador(String alias, int idm){

        Rondas r = this.getRonda(idm);
        Jugador j = getCJ().buscarJugador(alias);

     boolean ins = false;
        if (j==null || (j!=null && !j.isJugando())) {ins = r.addJugador(j);}
        if (ins){
        getCJ().setNumMesa(alias, idm);
        getCM().addJugador(idm);
        System.out.println("HEMOS INSERTADO CON HEXITO EL JUGADOR !! en la posicion : "+r.obtenerPosJug(j));

        }
        else{
            System.out.println("JUGADOR NO INSERTADO");


        }

    }

    public Jugador delJugadorMesa(String alias, int idm){

        Rondas r = this.getRonda(idm);
        Jugador j = getCJ().buscarJugador(alias);

        int pos = r.eliminarJugador(j);

        getCJ().setNumMesa(alias, -5);
        if (pos==r.getTurno()/2){r.siguienteTurno();}

        r.listarPartidas();
        return j;

    }



    public boolean delJugadorSistema(String alias){

        Jugador j = getCJ().buscarJugador(alias);
        boolean eliminat = false;
        if (j != null) {
            eliminat = true;
            int idm = j.getNumMesa();
            if (idm >= 0) {
                System.out.println("VAMOS A ELIMINARLO DE LA MESA:" + idm + " !!");
                this.delJugadorMesa(alias, idm);
                this.getCM().eliminarJugador(idm);

            }
            getCJ().eliminarJugador(alias);
        }
        return eliminat;

    }


    public void pedirCarta(int idm){

        Rondas r = this.getRonda(idm);
        r.pedirCarta();
        /*enviarlas al cliente*/
    }

    public void pedirCartasIniciales(int idm){

        Rondas r = this.getRonda(idm);
        r.repartirCartasIniciales();
        /*enviar al cliente*/


    }

    public void hacerDobles(int idm){

        Rondas r = this.getRonda(idm);
        r.haceDobleTurnosCompleto();


    }

    public void hacerSplit(int idm){

    Rondas r = this.getRonda(idm);
    r.hacerSplitCompleto();

    }

    public void cambiarFase (int idm){

        Rondas r = this.getRonda(idm);
        r.cambiarFase();

    }

    public Tcom generarTcom(int idm){


        Rondas r = this.getRonda(idm);

        Tcom t = r.generarCom();
        return t;

    }

    public Tcom generarTcomAntes(int idm){

        Rondas r = this.getRonda(idm);
        return r.getAnterior();


    }

    public void enviarTcom(int idm){

        Tcom t = this.generarTcom(idm);
        /*enviarle el tcom a todos los clientes de la mesa idm. */

    }

    public Jugador buscarJugador(String nom){

        return getCJ().buscarJugador(nom);

    }

    public void jugada(Tjugada t){

        /*hacer la jugada correspondiente a lo que indique Tjugada. */

        int a = t.getAccion();
        int idm = t.getIdm();
        String nom = t.getAlias();
        //System.out.println("el cliente: "+nom+" nos ha dicho de realizar la jugada numero:  "+a+" en la mesa numero : "+idm);
        Rondas r = this.getRonda(idm);
        Jugador j = this.buscarJugador(nom);
        /*estar atento al cambio de turnos*/
        if (r!=null){
              //  System.out.println("Entramos en el switch ");
            switch (a){
                case CONSTANTES.READY:
                    if (j!=null) {
                      //  System.out.println("Ahora se va a setear la jugada y se va a pasar al sig turno");
                        r.quiereJugar(j);

                        r.siguienteTurno();
                      //  System.out.println("SE HA CAMBIADO EL TURNO EN EL SISTEMA Y AHORA ES : "+r.getTurno());
                    }
                    break;

                case CONSTANTES.HIT:
                    boolean b = r.pedirCarta();
                    
                    if (b){
                   // System.out.println("hemos hecho hit y ahora pasamos de turno");
                        r.siguienteTurno();

                    }
                    break;

                case CONSTANTES.STAND:
                    r.plantarse();
                    break;

                case CONSTANTES.DOBLE:
                    r.haceDobleTurnosCompleto();
                    break;

                case CONSTANTES.SPLIT:
                    r.hacerSplitCompleto();
                    break;

                case CONSTANTES.BET:
                    int bets=t.getBet();
                    //System.out.println("SE VA A REALIZAR UNA APUESTA DE : "+bets);
                    if (bets>0){
                        boolean bu = r.addApuestaTurnos(bets);
                        //System.out.println("se ha realizado la apuesta ?"+bu);
                        r.siguienteTurno();
                    }
                    break;
                case CONSTANTES.SALIR:
                        //System.out.println("SALIMOS EN EL SERVER !!");
                        this.delJugadorSistema(nom);
                   // r.addJugador(j);
                    break;

                    
                default: break;

            }
        }
       // this.enviarTcom(idm);


    }

    public void imprimirTodo(){

        System.out.println("listando todo: numero de mesas totales: "+numMesas);
        System.out.println("");
        CM.listarMesas();
        CJ.listarJugadores();
        System.out.println("printing rondas:  ");

        CtrlJuego.partidas[0].listarPartidas();


    }




    








}
