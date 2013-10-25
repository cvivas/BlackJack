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
public class Carta implements Serializable{

    private int idCarta;
    private int valor;

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    public Carta(int idCarta) {
        this.idCarta = idCarta;
        int val;
        int temp = idCarta % 13;
        if (temp == 0) val = 11;
        else if (temp>8) val = 10;
        else val = temp+1;

        this.valor = val;
    }

    public Carta() {
    }

    /**
     * @return the idCarta
     */
    public int getIdCarta() {
        return idCarta;
    }

    /**
     * @param idCarta the idCarta to set
     */
    public void setIdCarta(int idCarta) {
        this.idCarta = idCarta;
    }


    /*
     * devuelve el palo de la carta:
     * 1 : corazones
     * 2 : rombos
     * 3: trebol
     * 4: picas
     */
    public int getPalo(){

        int palo = this.idCarta%52;
        if (palo<13)palo =1;
        else if (palo<26) palo= 2;
        else if (palo<39) palo = 3;
        else if (palo<52) palo = 4;
        return palo;


    }



}
