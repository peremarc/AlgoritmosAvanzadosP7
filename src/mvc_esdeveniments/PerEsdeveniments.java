package mvc_esdeveniments;

/**
 *      Interfícia de comunicació del patró per esdeveniments
 * 
 *      No és òptim però és molt visual acadèmicament per entendre el funcionament,
 *      emprar un String com entitat de comunicació.
 * 
 * @author mascport
 */
public interface PerEsdeveniments {
   public void notificar(String s); 
}
