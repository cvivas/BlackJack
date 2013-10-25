/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CompartidoClientes;

import java.io.Serializable;

/**
 *
 * @author carlos.vivas-abilahoud
 */
public class TcomManos implements Serializable {
     private static final long serialVersionUID = 12345;

    public int[] manos;
    public int numCartas;
    public int valMano;
   

    /*
     * se ha de pasar un vector de enteros los id de cartas
     */
    public TcomManos(int[] manos, int numCartas, int valMano) {
        this.manos = manos;
        this.numCartas = numCartas;
        this.valMano = valMano;
    }







}
