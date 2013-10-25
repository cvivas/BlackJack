/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 * Completa
 */

package CompartidoServidores;
import CompartidoClientes.Mesa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author carlos.vivas
 */
public class CtrlMesas implements Serializable{

    private ArrayList <Mesa> mesas;
    int numMesas;
    private static final long serialVersionUID = 87654321;

    public CtrlMesas() {

        this.mesas= new ArrayList<Mesa> ();
        this.numMesas=0;

    }

    public void initMesas(int n){

        for(int i =0; i<n;i++){

            this.addMesa();


        }

      }


    public int addMesa(){

        TestConnection t = new TestConnection();
        Mesa m = new Mesa(0,this.numMesas);
        this.getMesas().add(m);
        this.numMesas++;
//        t.insertarMesa(m);
        return m.getIdMesa();

    }

    public int addJugador(int idm){

        Mesa m = this.getMesas().get(idm);
        int nj = m.addJugador();
//        if (nj>0){
//            TestConnection t = new TestConnection();
//            t.addJugadorAMesa(nj,idm);
//        }

            return nj;

    }

    public int eliminarJugador(int idm){

        Mesa m = this.getMesas().get(idm);
        int nj = m.eliminarJugador();
//        if (nj>0){
//            TestConnection t = new TestConnection();
//            t.addJugadorAMesa(nj,idm);
//        }

            return nj;

    }

    public int setNumJugadores (int idm, int nj){

        Mesa m = this.getMesas().get(idm);
        int n = m.setNumJugadores(nj);
//        if (n>0){
//            TestConnection t = new TestConnection();
//            t.addJugadorAMesa(n,idm);
//        }

            return n;

    }

    void listarMesas() {

        System.out.println(this.mesas);
            System.out.println("LISTANDO MESAS");
        for (int i = 0; i<5;i++){

            System.out.print(this.mesas.get(i).getIdMesa());
            System.out.print("   :    ");

        }

    }

//    public int getNumRondas(int id){
//
//        Mesa m = this.buscarMesa(id);
//        int nr = m.getNumRondas();
//        return nr;
//
//    }


    private Mesa buscarMesa(int id){
    /* Operacion buscarMesa devuelve una Mesa si existe, sino devuelve null */
        Mesa auxiliar=null;
        boolean encontrado=false;

                Iterator<Mesa> itl= this.getMesas().iterator();
                while (!encontrado && itl.hasNext()) {
                Mesa actual = itl.next();
                    if ( actual.getIdMesa()==id){
                        encontrado=true;
                        auxiliar=actual;
                    }//fin if
                }//fin while
        return auxiliar;
    }//fin operacion

    public void addMesasDeBD(){



    }

    /**
     * @return the mesas
     */
    public ArrayList<Mesa> getMesas() {
        return mesas;
    }

//    public ArrayList getMesasAL(){
//
//        List list = new ArrayList
//
//
//
//    }


}
