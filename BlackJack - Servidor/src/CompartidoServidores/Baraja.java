/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CompartidoServidores;
import java.io.Serializable;
import java.util.Random;

/*
 * Completa
 */

/**
 *
 * @author carlos.vivas-abilahoud
 */
/*contiene:
 * int tamBaraja()
 * Carta getRandomCarta()
 * void sacarCarta (Carta c)

 */

public class Baraja implements Serializable{

    protected Carta[] mazo ;
    protected int numCartas;

   
    
   public Baraja() {

        mazo = new Carta[52];
        for (int i=0;i<52;i++){

            mazo[i] = new Carta(i);

        }
        numCartas=52;

    }

   /*
    * retira la carta C del mazo en caso de que este presente.
    */
    public void scarCarta(Carta c){


           if(this.mazo[c.getIdCarta()]!=null){

           this.mazo[c.getIdCarta()]=null;
           setNumCartas(getNumCartas() - 1);
           }
    }

    /*
     * devuelve una carta aleatoria de las restantes en la baraja
     * pero no la retira del mazo
     */
    public Carta getRandomCarta(){

        Random r = new Random();
        boolean b= false;
        int rand=-1;
        while (!b){

            rand = r.nextInt(52);
            if (this.mazo[rand]!=null) b=true;

        }
        Carta C = this.mazo[rand];
        return C;

    }

    /*
     * devuelve una carta aleatoria de las restantes en la baraja
     * retirandola de la baraja
     */

    public Carta getRandomCartaRetirando(){

        Random r = new Random();
        boolean b= false;
        int rand=-1;
        while (!b){

            rand = r.nextInt(52);
            if (this.mazo[rand]!=null) b=true;

        }
        Carta C = this.mazo[rand];
        this.scarCarta(C);
        return C;

    }

    /**
     * @return the mazo
     */
    public Carta[] getMazo() {
        return mazo;
    }


    /**
     * @param mazo the mazo to set
     */
    public void setMazo(Carta[] mazo) {
        this.mazo = mazo;
    }

    /**
     * @return the numCartas
     */
    public int getNumCartas() {
        return numCartas;
    }

    /**
     * @param numCartas the numCartas to set
     */
    public void setNumCartas(int numCartas) {
        this.numCartas = numCartas;
    }



}
