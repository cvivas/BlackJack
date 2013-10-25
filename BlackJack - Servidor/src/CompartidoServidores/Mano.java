/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CompartidoServidores;

import java.io.Serializable;

/*
 * Completa
 */

/**
 *
 * @author carlos.vivas-abilahoud
 */
public class Mano extends Baraja implements Serializable{

    private boolean tieneBJ=false;
    //int numCartas;

    public Mano() {

        this.mazo= new Carta[12]; //tamaño maximo de una mano
        for (int i = 0; i<12;i++)this.mazo[i]=new Carta();
        this.numCartas=0;
        
    }
    /**
     * @return the tieneBJ
     */
    public boolean isTieneBJ() {
        return tieneBJ;
    }

    /**
     * @param tieneBJ the tieneBJ to set
     */
    public void setTieneBJ(boolean tieneBJ) {
        this.tieneBJ = tieneBJ;
    }

   public int getValorMano() {

       int val=0;
       int numAs=0;

       //despues de este bucle, val tendra el valor total, las Ases valen 11.
       for(int i = 0; i<getNumCartas();i++){

           int valorCarta= this.mazo[i].getValor();
           val+=valorCarta;
           if (valorCarta==1 || valorCarta==11) numAs++; //numero de ases



       } //val tiene el valor del total
       if (val==21 && this.getNumCartas()==2) this.setTieneBJ(true);
       if (val>21){ //si se pasa de 21 contamos el numero de AS


          while (val>21 & numAs>0){ //restamos 10 por cada As

              val -=10;
              numAs -=1;

          }

       }
   return val;

   }



    /*
     * el controlador tiene que enviar la carta para meterla en el mazo.
     */
    public void addCarta(Carta c){

        //Carta c= this.getRandomCarta();
        this.mazo[this.numCartas]= new Carta();
        this.mazo[this.numCartas]= c;
        this.setNumCartas(this.getNumCartas() + 1);



    }

    public void inicializarMano(Carta c1, Carta c2){

        this.addCarta(c1);
        this.addCarta(c2);
        if (this.getValorMano()==21) this.setTieneBJ(true);
    
    }



    public Carta getCartaParaSplit(){

        Carta c = this.mazo[1];


        return c;


    }

    public void eliminarCartaParaSplit(){

        this.mazo[1]=null;
        this.numCartas--;

    }

    public boolean puedeSplit(){

        int id1 = this.mazo[0].getIdCarta();
        int id2 = this.mazo[1].getIdCarta();

        id1 = id1%13;
        id2 = id2%13;

        return id1==id2;


    }

    public int[] generarMazoEnId(){


        int[] a = new int[this.numCartas];
        for (int i=0; i<this.numCartas;i++){

            a[i]=this.mazo[i].getIdCarta();

        }
        return a;


    }






}
