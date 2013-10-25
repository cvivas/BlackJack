package juegoblackjack;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.JOptionPane;

/**
 *
 * @author georgina.punsoda
 */
public class errorAposta extends JOptionPane{
    private static final long serialVersionUID = 1L;

    public void mostrar(String msg) {
    /* Mostra una fistra modal amb el missatge d'error */
        String titol = "Advertencia!";
        showMessageDialog(null, msg, titol, JOptionPane.WARNING_MESSAGE);
    }

    public void mostrarInfo(String msg) {
    /* Mostra una fistra modal amb el missatge d'error */
        String titol = "Informacio";
        showMessageDialog(null, msg, titol, JOptionPane.INFORMATION_MESSAGE);
    }
}
