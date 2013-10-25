/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CompartidoClientes;

/**
 *
 * @author carlos.vivas-abilahoud
 */
public class CONSTANTES {

    public static final int READY = 0;
    public static final int HIT=1;
    public static final int STAND=2;
    public static final int DOBLE = 3;
    public static final int SPLIT=4;
    public static final int ENTRARENJEGO = 5;
    public static final int BET=6;
    public static final int SALIR=7;
    public static final String IPPRIMARIA="localhost";
    public static final String IPSECUNDARIA="localhost";
    public static final int PUERTOPRIMARIOCLIENTE=4443;
    public static final int PUESTOSECUNDARIOCLIENTE=8443;
    public static final int PUERTOS1AS2=9000;
    public static final int PUERTOS2AS1=9001;
    public static final int PUERTOPINGPRIMARIO=9002;

    public static final String EXISTEUSUARIO="1"; //login
    public static final String CREARJUGADOR="2"; //cuando se une a la partida
    public static final String OBTENERCREDITO="3"; // para darle el credito disponible al cliente
    public static final String ENVIARESTADOACLIENTE="4"; //envia estado de la partida al cliente
    public static final String RECIBIRDATOSPARTIDA="5"; //recibe jugada del cliente
    public static final String CARGARMESA="6"; //lista las mesas disponibles
    public static final String RECIBIRESTADO="1"; //comunicacion entre 2 servidores
   



    public static boolean esConstanteJuego(int i){

        return (i>=0 && i<8);

    }

}
