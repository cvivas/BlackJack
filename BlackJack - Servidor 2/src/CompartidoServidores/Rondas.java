/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CompartidoServidores;
import CompartidoClientes.TcomManos;
import CompartidoClientes.Tcom;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author carlos.vivas-abilahoud
 */


/*  falta revisar y falta comprovar que funciona. */


public class Rondas implements Serializable{

    //clau = idmesa + fechaRonda
   // private int idRonda;
    private int idMesa;
    private Calendar fechaRonda;
    private Jugador[] jugadoresRonda;
    private Baraja deck;

    /*Sin Split*/
    private int[] apuestas;
    private Mano[] manos;
    private boolean[] plantarse;
    private boolean[] pasarse;
    private boolean[] tieneBJ;
    private boolean[] dobles;

    /*Con Split*/
    private int[] apuestasSplit;
    private Mano[] splitManos;
    private boolean[] splitPlantarse;
    private boolean[] splitPasarse;
    private boolean[] tieneSplitBJ;
    private boolean[] splitDobles;

    private int turno;
    private int numJugadores; //maximo 3 por mesa + banca
    private int fase;

    private boolean rondaEspera; //esperando todo
    private int quierenJugar;
    private boolean pasadoTiempoEspera;

    private Tcom anterior;

    public Rondas() {
    }
    /*
     * fase 0 : esperando jugadores : solo esta la banca confirmada.
     * fase:1: apuestas iniciales
     * fase 2: apuestas iniciales realizadas: repartir 2 cartas
     * fase 3: jugadas por turnos
     *
     */
    

    public Rondas(int idm) {
        
       // this.idRonda=idr;
        this.idMesa=idm;
        this.fechaRonda= Calendar.getInstance();
        this.jugadoresRonda = new Jugador[4];
       // System.out.println("Acaso es nulo los jugadores ronda despues de crearlos:   "+this.jugadoresRonda);
        this.apuestas = new int [4];
        this.apuestasSplit = new int [4];
        this.deck = new Baraja();
        this.manos = new Mano[4];
        this.splitManos = new Mano[4];
        this.plantarse = new boolean[4];
        this.splitPlantarse = new boolean[4];
        this.pasarse = new boolean[4];
        this.splitPasarse = new boolean[4];
        this.tieneBJ = new boolean[4];
        this.tieneSplitBJ = new boolean[4];
               
        this.fase=0;
        this.turno = 0;
        this.numJugadores=0;
        int i ;
        for ( i =0; i<4;i++){

            this.jugadoresRonda[i]= new Jugador();
            this.manos[i]= new Mano();
            this.splitManos[i]= new Mano();
            this.pasarse[i]=false;
            this.plantarse[i]=false;
            this.splitPasarse[i]=false;
            this.splitPlantarse[i]=false;
            this.tieneBJ[i]=false;
            this.tieneSplitBJ[i]=false;
            this.apuestas[i]=-5;
            this.apuestasSplit[i]=-5;

        }


        this.rondaEspera=true;
        this.quierenJugar = 0 ;
        this.pasadoTiempoEspera = false;
        anterior = null;


    }

    public void nuevaPartida(){

        this.todosEnEspera();
        this.fechaRonda= Calendar.getInstance();
        //this.renovarDeck();
        this.apuestas = new int [4];
        this.apuestasSplit = new int [4];
        this.deck = new Baraja();

        this.manos = new Mano[4];
        this.splitManos = new Mano[4];
        this.plantarse = new boolean[4];
        this.splitPlantarse = new boolean[4];
        this.pasarse = new boolean[4];
        this.splitPasarse = new boolean[4];
        this.tieneBJ = new boolean[4];
        this.tieneSplitBJ = new boolean[4];

        this.fase=0;
        this.turno = 0;
        for ( int i =0; i<4;i++){


//            this.pasarse[i]=false;
//            this.plantarse[i]=false;
//            this.splitPasarse[i]=false;
//            this.splitPlantarse[i]=false;
//            this.tieneBJ[i]=false;
//            this.tieneSplitBJ[i]=false;
//            this.apuestas[i]=-5;
//            this.apuestasSplit[i]=-5;
//
            this.manos[i]= new Mano();
            this.splitManos[i]= new Mano();
            this.pasarse[i]=false;
            this.plantarse[i]=false;
            this.splitPasarse[i]=false;
            this.splitPlantarse[i]=false;
            this.tieneBJ[i]=false;
            this.tieneSplitBJ[i]=false;
            this.apuestas[i]=-5;
            this.apuestasSplit[i]=-5;

        }



    }



    public void cambiarFase() {

        if (!this.isRondaEspera()/*|| this.getFase()==2*/)this.setFase(this.getFase() + 1);
        setFase(getFase() % 4);
        System.out.println("Cambiando fase: esta fase vale : "+this.getFase());
        System.out.println("EL numero de jugadores totales de la mesa es : "+this.getNumJugadores());
        switch (getFase()){

            case 0:
                cambiarFaseAEspera();
                break;
            case 1:
                this.cambiarFaseAApuestas();
                break;
            case 2 :
                this.cambiarFaseARepartirCartasIniciales();
                break;
            case 3:
                this.cambiarFaseAJuego();
                break;
            default: break;


        }

    }

    public void cambiarFaseNuevaPartida(){

        this.nuevaPartida();
        this.cambiarFase();

    }



    public void quiereJugar(Jugador j){

        int i = this.obtenerPosJug(j);
        if (i<4){
            this.jugadoresRonda[i].setJugando(true);
            this.jugadoresRonda[i].setEsperando(false);
            this.quierenJugar++;
        }




    }

    public void cambiarFaseAEspera() {
        /*esperando jugadores o no hay jugadores,
         cambiaremos de fase cuando alguien este dispuesto a jugar*/
            System.out.println("ENTRAMOS EN CAMBIAR A FASE DE ESPERA");
        if (this.getNumJugadores()<=1){
            
            this.setRondaEspera(true);
            this.fase=0;
            /*alguna forma de dejar la mesa inactiva hasta que entre algun jugador*/
            
        }
        else{
             /*comprobar uno a uno los jugadores que quieran volver a jugar*/
            /*si se ha pasado un tiempo X de espera, se empezara la partida*/

            if (this.isPasadoTiempoEspera()&& this.getQuierenJugar()==0) {
                this.setPasadoTiempoEspera(false);
                    
                    //volver a iniciar la partida
            
                }

                System.out.println("Quiern jugar vale: "+this.getQuierenJugar()+" y el num de jugadores es : "+this.getNumJugadores());
            /*al menos un jugador que quiera jugar y se ha pasado el tiempo de espera, o todos quieren jugar*/
            if (this.getQuierenJugar() == (this.getNumJugadores()-1) || this.isPasadoTiempoEspera()){

                this.setRondaEspera(false);
                this.cambiarFase();

            }
  
        }



    }
    
    public int numJugandoNoEspera(){
    
        int n = 0;
        
        for (int i = 0; i<3;i++){
        
            if ( this.jugadoresRonda[i]!=null && !this.jugadoresRonda[i].isEsperando() && this.jugadoresRonda[i].jugando){
            
                n++;
            
            }
        
        }
        
        
        return n ;
    }


    public boolean comprobarQueTodosApuesten(){
    
        boolean b = true;
        int i = 0;
        int j = 0;
        //System.out.println("Hemos de entrar aqui si o si");
        while(i<3 &&b){
      
               if (this.jugadoresRonda[i]!=null && this.apuestas[i]>0 && !this.jugadoresRonda[i].isEsperando()){
                   
                  j++;             

               }
               i++;
              
        }
        System.out.println("comprobarquetodosapuestan devuelve: "+j+"y el num no espera devuelve : "+this.numJugandoNoEspera());
        /*retorna cierto si el numero de jugadores no esperando es igual al numero de jugadores que han apostado*/
        b = j==this.numJugandoNoEspera();
        return b;
    }
    
    public int ultimoJugador(){
    
        int pos =0;
        int i = 0 ;
        while(i<4){
            if(this.jugadoresRonda[i]!=null){
            
                pos = i;
                
            }
            i++;
        
        }
        return pos;
    }
    
    public void timeout(Jugador j){
    
        
        int i = this.obtenerPosJug(j);
        if (i<4){
            
            if (fase ==0){
                /*entrada al juego*/
                if (i == ultimoJugador()) this.pasadoTiempoEspera= true;
            
            }
            if (fase == 1){
                /*apuestas si entramos aqui es que no ha apostado*/
            this.jugadoresRonda[i].setEsperando(true);
            /*comunicarle al cliente que hay un timeout para este jugador*/
            }
            if (fase == 3){
            
                
                
            }
        }
    
    }

    public void cambiarFaseAApuestas(){

        /*estamos en la zona de apuestas*/
        /*no debemos irnos de esta fase mientras no se hayan exo todas las apuestas. */
        System.out.println("FASE DE APUESTAS INICIADA. ");
        this.pasadoTiempoEspera=false;

        /*normalmente nunca entrara aqui*/

      //  this.turno=0;



    }

    public void cambiarFaseARepartirCartasIniciales(){

        /*una vez hechas todas las apuestas, pasar a esta fase*/
        System.out.println("entrasmo en cambiarfase a repartir cartas iniciales");
        /*aqui comprobamos que todos los que estan en no esperando han apostado, sino volvemos a apostar*/
        if (!this.comprobarQueTodosApuesten()) this.fase--;
        System.out.println("AHORA SI QUE LAS REPARTIMOS");
       Mano[] man = this.repartirCartasIniciales();
       System.out.println("ya las hemos repartido");

       /*enviar las cartas al cliente*/
       /*immediatamente pasamos a la siguiente fase*/

       this.cambiarFase();



    }

    public void cambiarFaseAJuego(){

        turno=0;
        /*desarrollo de la partida*/
        /*aqui no hemos de hacer nada a priori pero por si acaso, la dejo. */


    }



    public int obtenerPosJug(Jugador j){

    int i =0;
        boolean trobat = false;
    //        System.out.println("PRINTING DE JUGADORES a buscar "+j);
   //     System.out.println("Imprimimos los jugadores de la ronda "+this.jugadoresRonda);
    //    System.out.println("imprimimos la primera posicion:  "+this.jugadoresRonda[1]);
        while (i<4 && !trobat){
       //     System.out.println("PRINTING DE JUGADORES RONDA DE "+i+" a ver q sale "+jugadoresRonda[i]);
            if (this.jugadoresRonda[i]!=null && j!=null) {
             //       System.out.println("emos entrado en el primer if y vale el alias "+jugadoresRonda[i].getAlias());
                if (this.jugadoresRonda[i].sonIguales(j.getAlias())){
                //    System.out.println("emos entrado en el true; ");
                trobat = true;}
                else {i++;}
            }
            else i++;
        }
     return i;

    }

    public boolean addJugador(Jugador j){

        /*hace falta ver si el juego esta en espera,
         y si lo esta y es el unico, iniciar las partidas*/
        int i =0;
        boolean trobat = false;
        boolean insertado=false;
        TJugPos t = new TJugPos();

        if (this.obtenerPosJug(j)>3){
            while (i<3 && !trobat){

                if (this.getJugadoresRonda()[i].isEmpty()) {

                    this.jugadoresRonda[i]= j;
                    this.apuestas[i]=-1;
                    this.apuestasSplit[i]=-1;
                    this.numJugadores++;
                    trobat = true;
                    j.setEsperando(true);
                    j.setJugando(true);
                    j.setNumMesa(idMesa);
                    this.getPasarse()[i]=false;
                    this.getPlantarse()[i]=false;
                    this.getSplitPasarse()[i]=false;
                    this.getSplitPlantarse()[i]=false;
                    this.getTieneBJ()[i]=false;
                    this.getTieneSplitBJ()[i]=false;
                    t.j=j;
                    t.pos=i;
                    insertado=true;

                }

                i++;

            }
            if (this.numJugadores==2){

              //  this.iniciarPartidaInicial();

            }
        }
        if (!trobat) t=null;
        
        System.out.println("insertamos el jugador y ahora numJugadores vale : "+this.getNumJugadores());


    return insertado;
    }

    public void iniciarPartidaInicial(){

        turno =-1;
        this.siguienteTurno();


    }

    public void addBanca(Jugador b){

        this.getJugadoresRonda()[3]=b;
        this.apuestas[3]=-1;
        this.apuestasSplit[3]=-1;
        b.setEsperando(false);
        b.setJugando(true);
        b.setNumMesa(idMesa);
        numJugadores++;
        TJugPos t = new TJugPos();
        t.j=b;
        t.pos=3;
        System.out.println("insertamos la banca y ahora numJugadores vale : "+this.getNumJugadores());


    }


    public int eliminarJugador(Jugador j){

        int i = this.obtenerPosJug(j);
        if (i<3) {
            j.setEsperando(false);
            j.setJugando(false);
            j.setNumMesa(-1);
            if (!this.jugadoresRonda[i].isEsperando()) this.quierenJugar--;
            this.jugadoresRonda[i] = null;
            this.apuestas[i]=-5;
            this.apuestasSplit[i]=-5;
            this.numJugadores--;
            this.getPasarse()[i]=false;
            this.getPlantarse()[i]=false;
            this.getSplitPasarse()[i]=false;
            this.getSplitPlantarse()[i]=false;
            this.getTieneBJ()[i]=false;
            this.getTieneSplitBJ()[i]=false;
            
           // return true;


        }
        return i;

    }

    public void restarApuesta(Jugador j, int a){

        j.restarCredito(a);
       // return j.getCredito();


    }

//    public int addApuesta (Jugador j, int a){
//
//        int i = this.obtenerPosJug(j);
//        if (i<3){
//            this.apuestas[i]=a;
//            this.restarApuesta(j, a);
//            //return true;
//        }
//        return i;
//        }
//
    public int addApuestaSplit (Jugador j){

        int i = this.obtenerPosJug(j);
        if (i<3){

            this.apuestasSplit[i]=this.getApuestas()[i];
            this.restarApuesta(j, this.apuestasSplit[i]);
            //return true;

        }
        return i;

    }

    public boolean addApuestaTurnos(int a){

        if (getFase() != 1 )return false;
        int i = turno%2;
        int pos=turno/2;
        if(this.jugadoresRonda[pos]==null)return false;

        if (i==0){
            this.apuestas[pos]=a;

        }
        else{

            this.apuestasSplit[pos]=a;


        }
        this.restarApuesta(this.jugadoresRonda[pos], a);
        return true;


    }


//    public int haceDoble(Jugador j){
//
//        int i = this.obtenerPosJug(j);
//        if (i<3)
//        {
//            this.restarApuesta(j, this.apuestas[i]);
//            this.apuestas[i]+=this.getApuestas()[i];
//            //return true;
//        }
//        return i;
//
//    }
//
//    public int haceDobleSplit(Jugador j){
//
//        int i = this.obtenerPosJug(j);
//        if (i<3) {
//
//            this.restarApuesta(j, this.apuestasSplit[i]);
//            this.apuestasSplit[i]+=this.getApuestasSplit()[i];
//          //  return true;
//        }
//
//        return i;
//    }

       public boolean hacerDobleTurnos(){

       // int i = turno%2;
        int pos=turno/2;
        if(this.jugadoresRonda[pos]==null)return false;


        if (!this.esSplit()){
            if (this.jugadoresRonda[pos].getCredito()<this.apuestas[pos])return false;
            this.restarApuesta(this.jugadoresRonda[pos],this.apuestas[pos] );
            this.apuestas[pos]+=this.apuestas[pos];

        }
        else{
            if (this.jugadoresRonda[pos].getCredito()<this.apuestasSplit[pos])return false;
            this.restarApuesta(this.jugadoresRonda[pos],this.apuestasSplit[pos] );
            this.apuestasSplit[pos]+=this.apuestasSplit[pos];


        }
        return true;


    }
       //esta se tiene que implementar de forma separada en el cliente
       public void haceDobleTurnosCompleto(){
        if (getFase() ==3){
           boolean b = this.hacerDobleTurnos(); //a nivel de apuesta
           if (b){

               this.pedirCarta();
               int val=0;
               if (!this.esSplit()){

                   val = this.manos[turno/2].getValorMano();
                       this.getPlantarse()[turno/2]=true;
                   if (val>21) {
                        this.getPasarse()[turno/2]=true;

                   }

               }
               else{
                   val = this.splitManos[turno/2].getValorMano();
                       this.getSplitPlantarse()[turno/2]=true;
                   if (val>21) {
                        this.getSplitPasarse()[turno/2]=true;

               }


           }


          }
           //se ha de enviar al cliente la mano que ha pedido el doble o la carta.
        }
       }


       public boolean comprobarSplit(){

          int pos = turno/2;
          boolean b=false ;
            if (this.jugadoresRonda[pos].getCredito()<this.apuestas[pos])return false;
           // if (this.splitManos[pos]!=null)return false;
            if (this.manos[pos].getNumCartas()!=2) return false;
            b = this.manos[pos].puedeSplit();
          return b;


       }

       public void hacerSplitCompleto(){

           if (this.comprobarSplit()){
            System.out.println("Geo es un pringao, y una tambien");
               int pos = turno/2;
               Jugador j = this.jugadoresRonda[pos];
               this.addApuestaSplit(j);

               this.splitManos[pos]= new Mano();
               Carta c = this.manos[pos].getCartaParaSplit();
               this.splitManos[pos].addCarta(c);

               this.manos[pos].eliminarCartaParaSplit();
              // this.manos[pos].setNumCartas(this.manos[pos].getNumCartas()-1);
               this.pedirCarta();
               Carta c2 = this.deck.getRandomCartaRetirando();
               this.splitManos[pos].addCarta(c2);
               System.out.println("Emos HEXO HEL HEPLI!");
             //  this.


           }



       }
    

//    public int obtenerApuesta(Jugador j){
//
//        int a = -1;
//        int i = this.obtenerPosJug(j);
//        if (i<3) a=this.getApuestas()[i];
//
//        return a;
//
//    }
//
//    public int obtenerApuestaSplit(Jugador j){
//
//
//        int a = -1;
//        int i = this.obtenerPosJug(j);
//        if (i<3) a=this.getApuestasSplit()[i];
//
//        return a;
//
//    }

        public int obtenerApuestaTurnos(){

        int i = turno%2;
        int pos=turno/2;
        if(this.jugadoresRonda[pos]==null)return -1;


        if (i==0){

            return this.apuestas[pos];

        }
        else{

           return this.apuestasSplit[pos];


        }


    }


    public Baraja renovarDeck(){

        this.setDeck(new Baraja());
        return this.deck;

    }

    /**
     * @return the jugadoresRonda
     */
    public Jugador[] getJugadoresRonda() {
        return jugadoresRonda;
    }

    public void siguienteTurno(){

        boolean encontrado=false;
        if (this.getNumJugadores()==1) {
            this.turno=0;
            setFase(0) ;
            this.cambiarFaseAEspera();
        }
        if (this.isRondaEspera()){

            this.setTurno(0);
            System.out.println("Ahora vamos a cambiar la fase para empezar");
            this.cambiarFase();
        }


        else{
            if (turno >= 6) {


                this.turno=0;
                this.cambiarFase();


            }
            else {
                while (this.turno<6 && !encontrado){

                    turno++;
                    if (turno!=6){


                        if (this.esSplit()){

                            if (this.splitManos[turno/2]!=null && this.apuestasSplit[turno/2]>0){encontrado = true; }

                        }
                        else{
                           // if (this.manos[turno/2]!=null) encontrado=true;
                            if ((this.jugadoresRonda[turno/2]!=null )&& (this.apuestas[turno/2]>0)) encontrado=true;
                        }

                    }
                    if (turno ==6  ) {

                        if (this.getFase()==1){
                            System.out.println("PASAMOS A REPARTIR LAS CARTAS INICIALES");
                            encontrado=true;
                            this.cambiarFase();

                        }

                        else if (this.getFase()==3){
                        encontrado=true;
                        this.turnoBanca();
                        anterior = this.generarCom();
                        System.out.println("por si no lo lee:  "+anterior.getNicks()[0]+""+anterior.getPlantado()[0]+""+anterior.getManos()[0].valMano+" el servidor: "+anterior.getManos()[3].valMano);

                        this.cambiarFaseNuevaPartida();
                        }
                    }


                }
            }
        }
   // return this.turno;
    }



    public Mano[] repartirCartasIniciales(){

        Carta c1;
        Carta c2;
        int j=0;
        //solo se reparten si estan hechas todas las apuestas.
        if (getFase()==2){

            /*geneerar para dividir*/
                if (j==0){
                c1=this.deck.getRandomCartaRetirando();
                c2=this.deck.getRandomCarta();
                while(c1.getIdCarta()%13!=c2.getIdCarta()%13){
                c2=this.deck.getRandomCarta();

                }
                this.deck.scarCarta(c2);
                this.manos[0]=new Mano();
                this.manos[0].inicializarMano(c1, c2);


                }
                j=1;
            while (j<4){

               // int i = turno/2;


                if (this.apuestas[j]>0 || j==3){

            System.out.println("la fase es 2 y repartimos !!");

                    c1 = this.deck.getRandomCartaRetirando();
                    c2 = this.deck.getRandomCartaRetirando();
                    this.manos[j]= new Mano();
                    this.manos[j].inicializarMano(c1, c2);
                    if (this.manos[j].isTieneBJ()) this.getTieneBJ()[j]=true;


                }
                j++;

            }
            System.out.println("ahora tienen que estar repartidas");

        }

      //  this.siguienteTurno();

        
        return this.manos;

    }

    //retorna cierto si se ha pasado.
    public boolean pedirCarta(){

        Carta c = this.deck.getRandomCartaRetirando();
        if (turno%2==0){

            System.out.println("VALOR DE LA MANO Antes de pedir carta: "+this.manos[turno/2].getValorMano());
            this.manos[turno/2].addCarta(c);

            int val = this.manos[turno/2].getValorMano();
            System.out.println("VALOR DE LA MANO CUANDO HA PEDIDO CARTA: "+val);
            if (val>21) {
            this.pasarse[turno/2]=true;
            return true;
            }

        }
        else{

            this.splitManos[turno/2].addCarta(c);
            int val = this.splitManos[turno/2].getValorMano();
            if (val>21) {
            this.splitPasarse[turno/2]=true;
            return true;
            }

        }
        return false;


    }


    
    public int plantarse(){

        if (!this.esSplit()){

            this.getPlantarse()[turno/2]=true;
        }
        else{

            this.getPlantarse()[turno/2]=true;

        }
        this.siguienteTurno();

        return this.turno;

    }

    


    public boolean esSplit(){

        return turno%2==1;

    }

    public Mano turnoBanca(){

        //hacer turnobanca
        while (this.manos[3].getValorMano()<17){

            this.pedirCarta();

        }
        this.comprobarPagar();

        return this.manos[3];

    }

    public void comprobarPagar(){

        int i = 0;

        while (i<(this.getNumJugadores()-1)){

            if(this.manos[i]!=null && this.apuestas[i]>0){
                this.pagar(i);

            }
            if (this.splitManos[i]!=null && this.apuestas[i]>0){

                this.splitPagar(i);
            }
            i++;

        }

        this.todosEnEspera();

    }

    public void pagar(int i){


        int valb = this.manos[3].getValorMano();
        int valj = this.manos[i].getValorMano();
        int credit = this.jugadoresRonda[i].getCredito();
        int aposta = this.apuestas[i];
        if (valj>valb){
            if(this.getTieneBJ()[i]){

                this.jugadoresRonda[i].sumarCredito(aposta+aposta+aposta);
            }
            else{

                this.jugadoresRonda[i].sumarCredito(aposta+aposta);
            }
        }
        else if (valj==valb){

            //jugador bj y banca no
            if (this.getTieneBJ()[i]&&!this.getTieneBJ()[3])
            {
                this.jugadoresRonda[i].sumarCredito(aposta+aposta+aposta);



            }
            else{

                this.jugadoresRonda[i].sumarCredito(aposta);

            }


        }



    }

    public void splitPagar(int i){


        int valb = this.manos[3].getValorMano();
        int valj = this.splitManos[i].getValorMano();
        int credit = this.jugadoresRonda[i].getCredito();
        int aposta = this.apuestasSplit[i];
        if (valj>valb){
            if(this.getTieneSplitBJ()[i]){
                this.jugadoresRonda[i].sumarCredito(aposta+aposta+aposta);
            }
            else{
                this.jugadoresRonda[i].sumarCredito(aposta+aposta);
            }
        }
        else if (valj==valb){

            //judaro bj y banca no
            if (this.getTieneSplitBJ()[i]&&!this.getTieneBJ()[3])
            {
                this.jugadoresRonda[i].sumarCredito(aposta+aposta+aposta);


            }
            else{

                this.jugadoresRonda[i].sumarCredito(aposta);

            }


        }



    }

/*genera el Tcom de la partida que se va a comunicar a los clientes y asi guardaran el estado
 * de la partida en curso pero sin tener acceso a ninguna variable importante.
 */
    public Tcom generarCom(){


     String[] nicks = this.generarNicks();
     int[] creditos = this.generarCreditos();
     TcomManos[] manosCom= this.generarManos();
     int[] apuestasCom = this.generarApuestas();
     boolean[] plantado = this.generarPlantado();
     boolean[] pasado= this.generarPasado();
     boolean[] blackJack= this.generarBlackJack();
     boolean[] doble= this.generarDoble();
     TcomManos[] splitManosCom = this.generarSplitManos();
     boolean[] hechoSplits = this.generarHechoSplits();
     int[] splitApuestas= this.generarSplitApuestas();
     boolean[] splitDoble = this.generarSplitDoble();
     boolean[] splitPlantarseCom=this.generarSplitPlantarse();
     boolean[] splitPasarseCom = this.generarSplitPasarse();
     boolean[] splitBlackJack= this.generarSplitBlackJack();
     int turnoCom = this.turno;
     int numJugadoresCom= this.numJugadores;
     int faseCom=this.fase;
     boolean rondaEsperaCom= this.rondaEspera;
     int idm=this.idMesa;
     Calendar fechaPartida=this.getFechaRonda();

     Tcom t = new Tcom(nicks,creditos,manosCom,apuestasCom, plantado, pasado, blackJack, doble, splitManosCom, hechoSplits, splitApuestas, splitDoble, splitPlantarseCom,splitPasarseCom, splitBlackJack, turnoCom,numJugadoresCom,  faseCom,  rondaEsperaCom,  idm, fechaPartida);



        return t;


    }


    public String[] generarNicks(){

        String nicks[] = new String[4];
        for (int i=0; i<4;i++){

            if (this.jugadoresRonda[i]!=null) nicks[i]=this.jugadoresRonda[i].getAlias();
            else nicks[i]=null;

        }

        return nicks;
    }

    public int[] generarCreditos(){

        int a[] = new int[4];
        for (int i=0; i<4;i++){

            if (this.jugadoresRonda[i]!=null) a[i]=this.jugadoresRonda[i].getCredito();
            else a[i]=-5;

        }
        return a;

    }

    public TcomManos[] generarManos(){

        TcomManos a[] = new TcomManos[4];
        for (int i=0; i<4;i++){
            a[i]=new TcomManos(null, -5, -5);
            if (this.jugadoresRonda[i]!=null&& this.manos[i]!=null){
                a[i].numCartas = this.manos[i].getNumCartas();
                a[i].valMano=this.manos[i].getValorMano();
                a[i].manos = this.manos[i].generarMazoEnId();

            }
            else a[i]=null;

        }
        return a;

    }

    public int[] generarApuestas () {return this.apuestas;}

    public boolean[] generarPlantado(){return this.plantarse;}

    public boolean[] generarPasado(){return this.pasarse;}

    public boolean[] generarBlackJack(){

        return this.tieneBJ;
    }

    public boolean[] generarDoble() { return this.dobles;}

    public TcomManos[] generarSplitManos(){

        TcomManos a[] = new TcomManos[4];
        for (int i=0; i<4;i++){
            a[i] = new TcomManos(null, -5,-5);
            if (this.jugadoresRonda[i]!=null&& this.splitManos[i]!=null){
                a[i].numCartas = this.splitManos[i].getNumCartas();
                a[i].valMano=this.splitManos[i].getValorMano();
                a[i].manos = this.splitManos[i].generarMazoEnId();

            }
            else a[i]=null;

        }
        return a;

    }

    public boolean[] generarHechoSplits(){

    boolean b[] = new boolean[3];

    for (int i=0; i<3;i++){

        if(this.jugadoresRonda[i]!=null && this.apuestasSplit[i]>0){

            b[i]=true;

        }
        else {b[i]=false;}

    }
    return b;

    }

    public int[] generarSplitApuestas(){return this.apuestasSplit;}

    public boolean[] generarSplitDoble(){return this.splitDobles;}

    public boolean[] generarSplitPlantarse() {return this.splitPlantarse;}

    public boolean[] generarSplitPasarse(){return this.splitPasarse;}

    public boolean[] generarSplitBlackJack() {return this.tieneSplitBJ;}



    


    

//    /**
//     * @return the idRonda
//     */
//    public int getIdRonda() {
//        return idRonda;
//    }
//
//    /**
//     * @param idRonda the idRonda to set
//     */
//    public void setIdRonda(int idRonda) {
//        this.idRonda = idRonda;
//    }

    /**
     * @return the idMesa
     */
    public int getIdMesa() {
        return idMesa;
    }

    /**
     * @return the fechaRonda
     */
    public Calendar getFechaRonda() {
        return fechaRonda;
    }

    /**
     * @return the apuestas
     */
    public int[] getApuestas() {
        return apuestas;
    }

    /**
     * @return the apuestasSplit
     */
    public int[] getApuestasSplit() {
        return apuestasSplit;
    }

    /**
     * @return the deck
     */
    public Baraja getDeck() {
        return deck;
    }

    /**
     * @return the manos
     */
    public Mano[] getManos() {
        return manos;
    }

    /**
     * @return the splitManos
     */
    public Mano[] getSplitManos() {
        return splitManos;
    }

    /**
     * @return the turno
     */
    public int getTurno() {
        return turno;
    }

    /**
     * @return the fase
     */
 /*   public int getFase() {
        return fase;
    }*/

    /**
     * @param idMesa the idMesa to set
     */
    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    /**
     * @param fechaRonda the fechaRonda to set
     */
    public void setFechaRonda(Calendar fechaRonda) {
        this.fechaRonda = fechaRonda;
    }

    /**
     * @param jugadoresRonda the jugadoresRonda to set
     */
    public void setJugadoresRonda(Jugador[] jugadoresRonda) {
        this.setJugadoresRonda(jugadoresRonda);
    }

    /**
     * @param apuestas the apuestas to set
     */
    public void setApuestas(int[] apuestas) {
        this.apuestas = apuestas;
    }

    /**
     * @param apuestasSplit the apuestasSplit to set
     */
    public void setApuestasSplit(int[] apuestasSplit) {
        this.apuestasSplit = apuestasSplit;
    }

    /**
     * @param deck the deck to set
     */
    public void setDeck(Baraja deck) {
        this.deck = deck;
    }

    /**
     * @param manos the manos to set
     */
    public void setManos(Mano[] manos) {
        this.manos = manos;
    }

    /**
     * @param splitManos the splitManos to set
     */
    public void setSplitManos(Mano[] splitManos) {
        this.splitManos = splitManos;
    }

    /**
     * @param turno the turno to set
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }

    /**
     * @param fase the fase to set
     */
   /* public void setFase(int fase) {
        this.fase = fase;
    }*/

    /**
     * @param numJugadores the numJugadores to set
     */
    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }

    public void todosEnEspera() {

        for (int i = 0 ; i<3; i++){

            if (this.jugadoresRonda[i]!=null) this.jugadoresRonda[i].setEsperando(true);


        }
        this.setRondaEspera(true);
        this.setQuierenJugar(0) ;



    }



    /**
     * @return the plantarse
     */
    public boolean[] getPlantarse() {
        return plantarse;
    }

    /**
     * @param plantarse the plantarse to set
     */
    public void setPlantarse(boolean[] plantarse) {
        this.plantarse = plantarse;
    }

    /**
     * @return the pasarse
     */
    public boolean[] getPasarse() {
        return pasarse;
    }

    /**
     * @param pasarse the pasarse to set
     */
    public void setPasarse(boolean[] pasarse) {
        this.pasarse = pasarse;
    }

    /**
     * @return the tieneBJ
     */
    public boolean[] getTieneBJ() {
        return tieneBJ;
    }

    /**
     * @param tieneBJ the tieneBJ to set
     */
    public void setTieneBJ(boolean[] tieneBJ) {
        this.tieneBJ = tieneBJ;
    }

    /**
     * @return the splitPlantarse
     */
    public boolean[] getSplitPlantarse() {
        return splitPlantarse;
    }

    /**
     * @param splitPlantarse the splitPlantarse to set
     */
    public void setSplitPlantarse(boolean[] splitPlantarse) {
        this.splitPlantarse = splitPlantarse;
    }

    /**
     * @return the splitPasarse
     */
    public boolean[] getSplitPasarse() {
        return splitPasarse;
    }

    /**
     * @param splitPasarse the splitPasarse to set
     */
    public void setSplitPasarse(boolean[] splitPasarse) {
        this.splitPasarse = splitPasarse;
    }

    /**
     * @return the tieneSplitBJ
     */
    public boolean[] getTieneSplitBJ() {
        return tieneSplitBJ;
    }

    /**
     * @param tieneSplitBJ the tieneSplitBJ to set
     */
    public void setTieneSplitBJ(boolean[] tieneSplitBJ) {
        this.tieneSplitBJ = tieneSplitBJ;
    }

    /**
     * @return the numJugadores
     */
    public int getNumJugadores() {
        return numJugadores;
    }

    /**
     * @return the fase
     */
    public int getFase() {
        return fase;
    }

    /**
     * @param fase the fase to set
     */
    public void setFase(int fase) {
        this.fase = fase;
    }

    /**
     * @return the rondaEspera
     */
    public boolean isRondaEspera() {
        return rondaEspera;
    }

    /**
     * @param rondaEspera the rondaEspera to set
     */
    public void setRondaEspera(boolean rondaEspera) {
        this.rondaEspera = rondaEspera;
    }

    /**
     * @return the quierenJugar
     */
    public int getQuierenJugar() {
        return quierenJugar;
    }

    /**
     * @param quierenJugar the quierenJugar to set
     */
    public void setQuierenJugar(int quierenJugar) {
        this.quierenJugar = quierenJugar;
    }

    /**
     * @return the pasadoTiempoEspera
     */
    public boolean isPasadoTiempoEspera() {
        return pasadoTiempoEspera;
    }

    /**
     * @param pasadoTiempoEspera the pasadoTiempoEspera to set
     */
    public void setPasadoTiempoEspera(boolean pasadoTiempoEspera) {
        this.pasadoTiempoEspera = pasadoTiempoEspera;
    }

    public void listarPartidas(){

    Tcom t = this.generarCom();
    t.listarTcom();

    }

    /**
     * @return the anterior
     */
    public Tcom getAnterior() {
        return anterior;
    }

    











}
