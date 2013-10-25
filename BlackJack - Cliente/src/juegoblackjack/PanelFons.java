package juegoblackjack;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelFons.java
 *
 * Created on 30-abr-2010, 12:46:30
 */



import CompartidoClientes.Tcom;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;


/**
 *
 * @author georgina.punsoda
 */
public class PanelFons extends javax.swing.JPanel {
    private Image imagen, cart, cartB;
    int cartasj, cartasjSplit, jugs, posJ, valCartes, valCartesSplit;
    String jugadors[];
    boolean div, primerSplit, segonSplit, banca, jPrinc, init, borraPuntet;
    boolean win, gameOver, empat, w1, w2, gO1, gO2, e1, e2;

    //vectors de cartes del jugador (principal i split)
    int jugador[]= new int[20];
    int y2[]= new int[20];
    int jugadorSplit[]= new int[20];
    int y2Split[]= new int[20];

    //Vectors de cartes de la banca

    int numCarBanca[]= new int[20];
    int paloBanca[]= new int[20];

    /*VARIABLES DE TRATARINFO()*/
    int t;
    int punts[]= new int[6];
    int cartesBanca;
    int valBanca;

    Tcom info2;
    
    /** Creates new form PanelFons */
    public PanelFons() {
        
    }
    public PanelFons(String nom) {
        if (nom != null) {
            imagen = new ImageIcon(getClass().getResource(nom)).getImage();
        }
    }

    public PanelFons(Image imIni) {
        if (imIni != null) {
            imagen = imIni;
        }
    }

    public void setNoms(int num, int pos, String noms[]){
        jugs= num-1; //li restem la banca
        posJ= pos;
        jugadors= noms;
        jPrinc= false;
        banca= false;
        init= true;
        
        primerSplit= false;
        segonSplit= false;

        win= false;
        empat= false;
        gameOver= false;
        w1= false;
        w2= false;
        gO1= false;
        gO2= false;
        e1= false;
        e2= false;

        repaint();
    }

    public void setImagen(String nom) {
        if(nom != null){
            imagen = new ImageIcon(getClass().getResource(nom)).getImage();
        } else {
            imagen = null;
        }
        cartasj= 0;
        div=false;
        
        repaint();
    }

    public void divide(){
        primerSplit= true;  //ma esquerra
        segonSplit= false;
    }

    public void divide2(){
        primerSplit= false;
        segonSplit= true;
        repaint();
    }

    public void noDivide(){
        segonSplit= false;
    }

    public void setImagen(Image nuevaImagen) {
        imagen = nuevaImagen;
        repaint();
    }

    public void reset(){
        init= true;
        win= false;
        gameOver= false;
        empat= false;
        w1= false;
        w2= false;
        gO1= false;
        gO2= false;
        e1= false;
        e2= false;
        borraPuntet= false;

    }

    public void noReset(){
        init= false;
    }

    public void perdut(Tcom ant){
        if(segonSplit){
            gO2= true;
            if(((ant.getManos()[posJ].valMano > ant.getManos()[3].valMano) && !ant.getPasado()[posJ]) || ((ant.getManos()[posJ].valMano < ant.getManos()[3].valMano)&& ant.getPasado()[3])){
               w1= true;
            }else if((ant.getPasado()[posJ] || (ant.getManos()[posJ].valMano < ant.getManos()[3].valMano)) || ((ant.getManos()[posJ].valMano == ant.getManos()[3].valMano) && (ant.getManos()[3].numCartas==2))){
                gO1= true;
            }else{
                e1= true;
            }
        }else{
            gameOver= true;
        }

        repaint();
    }

    public void guanyat(Tcom ant){
         if(segonSplit){
            w2= true;
            if(((ant.getManos()[posJ].valMano > ant.getManos()[3].valMano) && !ant.getPasado()[posJ]) || ((ant.getManos()[posJ].valMano < ant.getManos()[3].valMano)&& ant.getPasado()[3])){
               w1= true;
            }else if((ant.getPasado()[posJ] || (ant.getManos()[posJ].valMano < ant.getManos()[3].valMano)) || ((ant.getManos()[posJ].valMano == ant.getManos()[3].valMano) && (ant.getManos()[3].numCartas==2))){
                gO1= true;
            }else{
                e1= true;
            }
        }else{
            win= true;
        }

        repaint();
    }

    public void empat(Tcom ant){
         if(segonSplit){
            e2= true;
            if(((ant.getManos()[posJ].valMano > ant.getManos()[3].valMano) && !ant.getPasado()[posJ]) || ((ant.getManos()[posJ].valMano < ant.getManos()[3].valMano)&& ant.getPasado()[3])){
               w1= true;
            }else if((ant.getPasado()[posJ] || (ant.getManos()[posJ].valMano < ant.getManos()[3].valMano)) || ((ant.getManos()[posJ].valMano == ant.getManos()[3].valMano) && (ant.getManos()[3].numCartas==2))){
                gO1= true;
            }else{
                e1= true;
            }
        }else{
            empat= true;
        }

        repaint();
    }

    public void borrarPuntet(){
        borraPuntet= true;
    }

    public void tratarInfo(Tcom info){
        info2= info;
        System.out.println("tratar infoooo");
        t= info.getTurno();

        //if(((t/2)!=posJ) && ((t/2)!=3)){
        /*AGAFEM TOTA LA INFO DELS ALTRES JUGADORS (si n'hi ha)*/
        if(jugs>1){
            System.out.println("el numero de jugadores que posee tratar info es : "+jugs);
            for(int k=0; k<jugs; k++){
                System.out.println("jugador en pos "+k+" amb alias "+jugadors[k]);
                if(k!=posJ){
                    if(info.getManos()!=null){
                        punts[k*2]= info.getManos()[k].valMano;
                    }
                    if(info.getHechoSplits()!=null){
                        if(info.getHechoSplits()[k]){
                            punts[k*2+1]= info.getSplitManos()[k].valMano;
                        }
                    }
                }
            }
            System.out.println("punts jugadors--> "+punts[0]+" "+punts[1]+" "+punts[2]+" "+punts[3]);
        }

        /*AGAFEM TOTA LA INFO DEL NOSTRE JUGADOR*/
        cart= new ImageIcon(getClass().getResource("cartes.png")).getImage();
            //ES EL NOSTRE JUGADOR!!!
            if(info.getHechoSplits()[posJ]){ //Ha fet split
                System.out.println("TRATARINFO split");
                if(info.getSplitManos()!=null){
                    System.out.println("split1");
                    cartasjSplit= info.getSplitManos()[posJ].numCartas;
                    valCartesSplit= info.getSplitManos()[posJ].valMano;

                    for(int i=0; i<cartasjSplit; i++){
                        jugadorSplit[i]= info.getSplitManos()[posJ].manos[i]%13;
                        y2Split[i]= info.getSplitManos()[posJ].manos[i]/13;
                        System.out.println("valorSplit= "+ jugadorSplit[i]+" paloSplit= "+y2Split[i]);
                    }
                }
            }

            if(info.getManos()!= null){
                cartasj= info.getManos()[posJ].numCartas;

                if(cartasj!=0){
                    cart = new ImageIcon(getClass().getResource("cartes.png")).getImage();
                
                    valCartes= info.getManos()[posJ].valMano;
                    for(int i=0; i<cartasj; i++){
                        jugador[i]= info.getManos()[posJ].manos[i]%13;
                        y2[i]= info.getManos()[posJ].manos[i]/13;
                        System.out.println("valor= "+ jugador[i]+" palo= "+y2[i]);
                    }
                    System.out.println("TRATARINFO num cartas---> "+cartasj);
                    jPrinc= true;
                }
            }

            /*AGAFEM TOTA LA INFO DE LA BANCA!*/
            if(info.getManos()!=null){
                cartesBanca= info.getManos()[3].numCartas;
                System.out.println("cartasBanca "+cartesBanca);
                if(cartesBanca!=0) cartB = new ImageIcon(getClass().getResource("cartes.png")).getImage();

                valBanca= info.getManos()[3].valMano;
                for(int i=0; i<cartesBanca; i++){
                    numCarBanca[i]= info.getManos()[3].manos[i]%13;
                    paloBanca[i]= info.getManos()[3].manos[i]/13;
                }
                banca= true;
            }
        System.out.println("TRATARINFO abans de cridar a pintar jPrinc--> "+jPrinc);
        repaint();
    }

    /*
     * @paint carrega el fons de la pantalla cada cop que es fa repaint()
     * si s'ha demanat carta, les dibuixa on li correspon al jugador
     * si s'ha fet DIVIDE mostra les cartes en pilons diferents
    */
    @Override
    public void paint(Graphics g) {
        int j= 0;
        int d= 1;
        int i;
        String punt;
        //int valorC2= 0;
System.out.println("A PINTAAAAAAAAAAAAAAAAAAR");
        /*PINTEM EL FONS*/
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), 970,this);
            setOpaque(false);
        } else {
            setOpaque(true);
        }
        g.setFont(new Font("Britannic Bold", Font.BOLD, 100));
        g.setColor(Color.white);
        g.drawString("PXC BlackJack", 100, 150);


        /*PINTEM ELS NOMS DELS JUGADORS*/
        g.setFont(new Font("Calibri",Font.BOLD,30));
        g.setColor(Color.white);

        d=1;
        j=0;
        for(i=0; i<jugs; i++){
            if(i!=posJ){
                if (jugadors!=null && jugadors[i]!=null){
                    g.drawString(jugadors[i], d*300+j*350, 500);
                }else{
                    g.drawString("", d*300+j*350, 500);
                }
                d++;
                j++;
            }else{
                if (jugadors!=null && jugadors[i]!=null){
                    g.drawString(jugadors[i], 610, 650);
                }else{
                    g.drawString("", 600, 650);
                }
            }
        }
        g.drawString("Banca", 580, 450);

        /*PINTEM VALORS DE LES JUGADES DE TOTS*/
        if(!init){
            j=0;
            g.setFont(new Font("Calibri", Font.BOLD, 20));
            if(jugs>1){//mes jugadors a part del nostre (vector de 4!)
                for(i=1; i<6; i++){
                    if(info2.getHechoSplits()[i/2] && (i/2)!=posJ) g.drawString("/", i*150+j, 550);
                    System.out.println("anem a pintar punts de la pos "+i);
                    if(punts[i-1]!=0){
                        System.out.println("pintem punts "+punts[i-1]);
                        punt= String.valueOf(punts[i-1]);
                        g.drawString(punt, i*100+j, 550);
                    }
                    if(i>2)j=550;
                }
            }
            System.out.println("punts jugadors--> "+punts[0]+" "+punts[1]+" "+punts[2]+" "+punts[3]);
        }

        /*PINTEM CARTES*/
        if(cart != null){
            //CARTES JUGADOR
            if(jPrinc){
                j=0;
                div = info2.getHechoSplits()[posJ];
                if(!div){
                    System.out.println("PINTAMEEEEE");
                    /*Sense split*/
                    for (i=0;i<cartasj;i++){
                        g.drawImage(cart,450+(98*i)-(45*j),700,523+(98*i)-(45*j),798,jugador[i]*73,y2[i]*98,jugador[i]*73+73,y2[i]*98+98,this);
                        j++;
                    }
                    if(!init){
                        g.setFont(new Font("Calibri",Font.BOLD,20));
                        g.setColor(Color.white);
                        g.drawString(String.valueOf(valCartes), 420, 720);
                    }
                    
                }else{ /*Amb split*/
//                    if(primerSplit){
//                        System.out.println("hola nene PAINT");
//                        for (i=0;i<2;i++){
//                            if(jugador[i]!=0){
//                                g.drawImage(cart,450+(98*i),700,523+(98*i),798,jugador[i]*73,y2[i]*98,jugador[i]*73+73,y2[i]*98+98,this);
//                            }
//                        }
//                    }else{
                        //ESQUERRA
                        j=0;
                        for (i=0;i<cartasj;i++){
                            g.drawImage(cart,350+(98*i)-(45*j),700,423+(98*i)-(45*j),798,jugador[i]*73,y2[i]*98,jugador[i]*73+73,y2[i]*98+98,this);
                            j++;
                        }

                        if(!init){
                            g.setFont(new Font("Calibri",Font.BOLD,20));
                            g.setColor(Color.white);
                            g.drawString(String.valueOf(valCartes), 320, 720);
                        }

                        if(primerSplit){
                            g.setColor(Color.RED);
                            g.fillOval(400, 810, 30, 30);
                        }

                        //DRETA
                        j=0;
                        for (i=0;i<cartasjSplit;i++){
                            g.drawImage(cart,750+(98*i)-(45*j),700,823+(98*i)-(45*j),798,jugadorSplit[i]*73,y2Split[i]*98,jugadorSplit[i]*73+73,y2Split[i]*98+98,this);
                            j++;
                        }

                        if(!init){
                            g.setFont(new Font("Calibri",Font.BOLD,20));
                            g.setColor(Color.white);
                            g.drawString(String.valueOf(valCartesSplit), 720, 720);
                        }

                        if(segonSplit && !borraPuntet){
                            g.setColor(Color.RED);
                            g.fillOval(800, 810, 30, 30);
                        }
//                    }
                }
            }

            //CARTES BANCA 
            if(banca){
                j=0;
                    for (i=0;i<cartesBanca;i++){
                        g.drawImage(cart,450+(98*i)-(45*j),500,523+(98*i)-(45*j),598,numCarBanca[i]*73,paloBanca[i]*98,numCarBanca[i]*73+73,paloBanca[i]*98+98,this);
                        j++;
                    }

                    if(!init){
                        g.setFont(new Font("Calibri",Font.BOLD,20));
                        g.setColor(Color.white);
                        g.drawString(String.valueOf(valBanca), 420, 520);
                    }
            }
        }


        if(!div){
            /*Pintem si ha guanyat, perdut o empatat*/
            if(gameOver){
                g.setFont(new Font("Calibri", Font.BOLD, 50));
                g.setColor(Color.RED);
                g.drawString("HAS PERDUT!", 400, 500);
            }
            if(win){
                g.setFont(new Font("Calibri", Font.BOLD, 50));
                g.setColor(Color.GREEN);
                g.drawString("HAS GUANYAT!", 400, 500);
            }
            if(empat){
                g.setFont(new Font("Calibri", Font.BOLD, 50));
                g.setColor(Color.YELLOW);
                g.drawString("EMPAT AMB LA BANCA!", 400, 500);
            }
        }else{
            /*Guanyat, perdut o empat MA ESQUERRA*/
            if(w1){
                g.setFont(new Font("Calibri", Font.BOLD, 50));
                g.setColor(Color.GREEN);
                g.drawString("HAS GUANYAT!", 300, 850);
            }else if(gO1){
                g.setFont(new Font("Calibri", Font.BOLD, 50));
                g.setColor(Color.RED);
                g.drawString("HAS PERDUT!", 300, 850);
            }else if (e1){
                g.setFont(new Font("Calibri", Font.BOLD, 50));
                g.setColor(Color.YELLOW);
                g.drawString("EMPAT!", 330, 850);
            }

            /*Guanyat, perdut o empat MA DRETA*/
            if(gO2){
                g.setFont(new Font("Calibri", Font.BOLD, 50));
                g.setColor(Color.RED);
                g.drawString("HAS PERDUT!", 700, 850);
            }
            if(w2){
                g.setFont(new Font("Calibri", Font.BOLD, 50));
                g.setColor(Color.GREEN);
                g.drawString("HAS GUANYAT!", 700, 850);
            }
            if(e2){
                g.setFont(new Font("Calibri", Font.BOLD, 50));
                g.setColor(Color.YELLOW);
                g.drawString("EMPAT!", 730, 850);
            }
        }

        super.paint(g);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
