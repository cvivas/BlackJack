/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CompartidoServidores;

/*
 * En principio completa. no es necesaria la tabla de jugadores en la BBDD.
 * pues accedemos directamente a los de usuario de credito y alias.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Carlos.vivas
 */
public class CtrlJugadores implements Serializable{

    protected ArrayList<Jugador> jugs;


    public CtrlJugadores() {

        jugs = new ArrayList<Jugador>();


    }


    public void addJugador(String alias){

        TestConnection t = new TestConnection();
        int c = t.obtenerCreditoUsuario(alias);
        Jugador j = new Jugador(alias, c);
        if(this.buscarJugador(alias)==null){
   
        this.jugs.add(j);
        }
        
       // System.out.println("insertamos jug "+alias+" con pasta de : "+c);
        //System.out.println("vamos a insertar el jugador :  "+j);
        //System.out.println("Hemos insertado :   "+this.jugs);
//        t.insertarJugador(j);


    }


    public int getNumJugadorsTotal (){

        return this.jugs.size();
    }

    public void eliminarJugador (String alias){

        Jugador j = buscarJugador (alias);
//        TestConnection t = new TestConnection();
//        t.eliminarJugador(alias);
        this.jugs.remove(j);


    }

    public Jugador buscarJugador (String alias){


        Jugador auxiliar=null;

        boolean encontrado=false;

                Iterator<Jugador> itl= jugs.iterator();
               // System.out.println("imprimimos jugs en buscarJugador: "+jugs);
                while (!encontrado && itl.hasNext()) {
                Jugador actual = itl.next();
                    if ( actual.sonIguales(alias)){
                        encontrado=true;
                        auxiliar=actual;
                    }//fin if
                //System.err.println(auxiliar);
                }//fin while
                //System.out.println(auxiliar);
        return auxiliar;
    }//fin operacion

    public void setNumMesa(String alias, int idm){

        Jugador j = this.buscarJugador(alias);
        j.setNumMesa(idm);
//        TestConnection t = new TestConnection();
//        t.updateJugador(j);

    }

    public void setJugando (String alias, boolean b){

        Jugador j = this.buscarJugador(alias);
        j.setJugando(b);

    }

    public void setEsperando (String alias, boolean b){

        Jugador j = this.buscarJugador(alias);
        j.setEsperando(b);
    }

    void addBancas(int n) {

              for(int i =0; i<n;i++){

                this.addBanca(i);


        }
    }

    public void addBanca(int num){

       String n = "banca";
       String num2 = String.valueOf(num);
       String n2 = n.concat(num2);
      // String nom = "banca"+num;
       Jugador j = new Jugador(n2);
       this.jugs.add(j);
    
    }

    Jugador getBanca(int idm) {

        return this.buscarJugador("banca"+idm);

    }

    void listarJugadores() {

        int n =this.getNumJugadorsTotal();
        System.out.println("Listado de los jugadores");System.out.println("");
        for (int i=0; i<n; i++){

            Jugador j =this.jugs.get(i);
            j.listar();




        }
    }

   













}
