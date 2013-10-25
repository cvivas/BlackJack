/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blackjackservidor2;

/**
 *
 * @author marc.sitges
 */
import java.net.*;
import java.io.*;
import CompartidoClientes.*;
import javax.net.ssl.*;
import java.security.*;
import java.security.cert.*;

public class ServerPrograma {

    private SSLServerSocket serverToApp;
    private SSLServerSocket serverToServer;
    private SSLServerSocket PINGServer;
    private SSLServerSocket PINGCliente;
    private boolean esPrimario;
    SSLServerSocketFactory sslserversocketfactory;
    SSLSocketFactory sslsocketfactory;
    final String[] enabledCipherSuites = { "SSL_DH_anon_WITH_RC4_128_MD5" };


    public ServerPrograma() throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, InvalidAlgorithmParameterException, CertPathValidatorException, GeneralSecurityException
    {
          //  kkp = new protocolosServidor();
            esPrimario = false;
          //  clientServSocket = new Socket();
            sslserversocketfactory =
                    (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            sslsocketfactory =
                    (SSLSocketFactory) SSLSocketFactory.getDefault();

           while(true)
            {

                esPrimario = esElPrimario();
                if(esPrimario)
                {
                                        System.out.println("Primario");

                    if(PINGServer==null)activarPingServer();
                    if(PINGCliente==null)activarPingCliente();
                    if(serverToApp==null)activarServidorACliente();
                    if(serverToServer==null)activarServidorAServidor();
                    new ThreadsClientes(serverToApp,serverToServer).start();
                    System.out.println(PINGServer.accept());

                }
                else
                {
                                       System.out.println("Segundario");
                    try {
                        new ThreadServidor((SSLSocket) sslsocketfactory.createSocket(CONSTANTES.IPPRIMARIA,CONSTANTES.PUERTOS1AS2)).start();

                    } catch (UnknownHostException ex) {
                       // Logger.getLogger(ServerPrograma.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                       // Logger.getLogger(ServerPrograma.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            /* Inicializar los datos:
             *
             * CASO 1:
             * Servidor inicia por primera vez
             *      Base de datos vacia
             *      Hace falta crear 5 mesas vacias
             *
             * Si el CASO 1 falla, el cliente se va al segundo servidor y entramos en el CASO 2
             *
             * CASO 2:
             * Un servidor ha petado y el otro sigue en pie. Este atiende todas las peticiones y
             * cuando el otro servidor vuelve a funcionar, le tiene enviar los datos que tiene
             * (Controladores de Dominio), nos apañaermos como podamos con el log.
             *
             * Si el segundo servidor falla, el usuario se jode y entramos en el CASO 3
             *
             * CASO 3:
             *      CASO 1 + Eliminar de la BBDD la info de las tablas:
             *          Jugador
             *          Mesa
             *
             */
    }

    private void activarPingServer() throws IOException {
              PINGServer = (SSLServerSocket) sslserversocketfactory.createServerSocket(9003);
              PINGServer.setEnabledCipherSuites(enabledCipherSuites);
    }

    private void activarPingCliente() throws IOException {
        System.out.println(PINGCliente);
              PINGCliente = (SSLServerSocket) sslserversocketfactory.createServerSocket(9005);
              PINGCliente.setEnabledCipherSuites(enabledCipherSuites);
    }

    private boolean esElPrimario() throws IOException
    {
        try {

            SSLSocket ping = (SSLSocket) sslsocketfactory.createSocket(CONSTANTES.IPPRIMARIA,9002);
            ping = null;
            return false;
        } catch (IOException ex) {
            System.out.println("-----------------");
            System.out.println(ex);
            System.out.println("-----------------");
            return true;
        }

    }

    private void activarServidorACliente() throws IOException {
        serverToApp = (SSLServerSocket) sslserversocketfactory.createServerSocket(CONSTANTES.PUESTOSECUNDARIOCLIENTE);
        serverToApp.setEnabledCipherSuites(enabledCipherSuites);
    }

    private void activarServidorAServidor() throws IOException {
        serverToServer = (SSLServerSocket) sslserversocketfactory.createServerSocket(CONSTANTES.PUERTOS2AS1);
        serverToServer.setEnabledCipherSuites(enabledCipherSuites);
    }

}

