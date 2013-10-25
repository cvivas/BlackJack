/*/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package juegoblackjack;

import javax.swing.JOptionPane;

/**
 *
 * @author georgina.punsoda
 */
public class info extends JOptionPane{
    private static final long serialVersionUID = 1L;

    public void mostrar(String error) {
    /* Mostra una fistra modal amb el missatge d'error */
        String titol = "Informació";
        showMessageDialog(null, error, titol, JOptionPane.ERROR_MESSAGE);
    }
}
