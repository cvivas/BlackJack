/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*GEO, HAS DE COJER ESTA CLASE DE AQUI Y LA DE CONSTANTES, JUNTO CON LA DE TCOM Y TCOMMANOS*/
package CompartidoClientes;

import java.io.Serializable;

/**
 *
 * @author carlos.vivas-abilahoud
 */
public class Tjugada implements Serializable {
     private static final long serialVersionUID = 123456;
    private int idm;
    private String alias;
    private int accion;
    private int bet;

    public Tjugada(int idm, String alias) {
        this.idm=idm;
        this.alias=alias;
        this.accion=-5;
        this.bet=-5;
    }
    
    public boolean setAccion(int i){

        if (CONSTANTES.esConstanteJuego(i)){

            this.accion=i;
            return true;

        }
        return false;
        
    }
    public boolean setBet(int ac, int aposta){


        if (ac==CONSTANTES.BET && aposta>0){

            this.accion=CONSTANTES.BET;
            this.bet=aposta;
            return true;

        }
        return false;

    }

    /**
     * @return the accion
     */
    public int getAccion() {
        return accion;
    }

    /**
     * @return the bet
     */
    public int getBet() {
        return bet;
    }

    /**
     * @return the idm
     */
    public int getIdm() {
        return idm;
    }

    /**
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    





    





}
