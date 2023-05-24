/**
 * Practica 7 Algoritmos Avanzados - Ing Informática UIB
 *
 * @date 21/05/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url 
 */
package mvc_esdeveniments.control;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Random;
import mvc_esdeveniments.MVC_Esdeveniments;
import mvc_esdeveniments.PerEsdeveniments;

public class Control implements PerEsdeveniments {

    private MVC_Esdeveniments prog;

    public Control(MVC_Esdeveniments p) {
        prog = p;
    }

    @Override
    public void notificar(String s) {
        if (s.startsWith("Calcular")) {
            this.calcular();
        }
    }
    
    private void calcular(){
        
    }
    
    public void generarNumNXifres(int n){
        String aux = "";
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int r = random.nextInt(10);
            if(i==0 && r==0){
                while(r==0){
                    r = random.nextInt(10);
                }
            }
            int k = 48 + r;
            aux = aux + (char) (k);
        }
        prog.getModel().setNumNXifres(new BigInteger(aux));
        
    }

}
