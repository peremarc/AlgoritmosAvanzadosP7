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

public class Control extends Thread implements PerEsdeveniments {

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

    private void calcular() {

    }

    public void generarNumNXifres(int n) {
        String aux = "";
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int r = random.nextInt(10);
            if (i == 0 && r == 0) {
                while (r == 0) {
                    r = random.nextInt(10);
                }
            }
            int k = 48 + r;
            aux = aux + (char) (k);
        }
        prog.getModel().setNumNXifres(new BigInteger(aux));

    }

    public boolean esPrimo(String numero) {
        PrimoProbable primprob = new PrimoProbable(100);
        return primprob.esPrimo(numero);
    }

    public void generarNumFort() {
        BigInteger dos = new BigInteger("2");
        BigInteger mersen = new BigInteger("1");
        for (int i = 1; i <= 521; i++) {
            mersen = mersen.multiply(dos);
        }
        mersen = mersen.subtract(new BigInteger("1"));
        BigInteger duro = mersen.add(new BigInteger("0"));
        PrimoProbable primprob = new PrimoProbable(100);
        duro = duro.multiply(primprob.otroProbablePrimo(duro));
        prog.getModel().setNumDuro(duro);
    }

    @Override
    public void run() {
        factoritzarNumFort();
    }

    public void factoritzarNumFort() {
        factorizar(prog.getModel().getNumDuro().toString());
    }
    
    public void factorizar(String n){
        BigInteger veces;
        long temps = System.nanoTime();
        PrimoProbable primprob = new PrimoProbable(100);
        BigInteger num = new BigInteger(n);
        BigInteger zero = new BigInteger("0");
        BigInteger uno = new BigInteger("1");
        BigInteger dos = new BigInteger("2");
        BigInteger obj = num.subtract(uno);
        BigInteger test = new BigInteger("3");
        String aux = prog.getVista().getTextPanel().getTextArea().getText();
        aux += "\n\nVoy a factorizar " + num;
        prog.getVista().getTextPanel().getTextArea().setText(aux);
        if (num.remainder(dos).compareTo(zero) == 0) {
            veces = zero.add(zero);
            while (num.remainder(dos).compareTo(zero) == 0) {
                num = num.divide(dos);
                veces = veces.add(uno);
            }
            aux += "\n   factor -------> " + dos + "  (x" + veces + ")";
            prog.getVista().getTextPanel().getTextArea().setText(aux);
        }
        obj = sqrt(obj);
        int control = 0;
        while (test.compareTo(obj) <= 0) {
            if (primprob.esPrimo(num)) {
                aux += "\n   factor -------> " + num + " (x1)";
                prog.getVista().getTextPanel().getTextArea().setText(aux);
                test = obj.add(uno);
            } else if (primprob.esPrimo(test)) {
                if (num.remainder(test).compareTo(zero) == 0) {
                    veces = zero.add(zero);
                    while (num.remainder(test).compareTo(zero) == 0) {
                        num = num.divide(test);
                        veces = veces.add(uno);
                    }
                    aux += "\n   factor -------> " + test + "  (x" + veces + ")";
                    prog.getVista().getTextPanel().getTextArea().setText(aux);
                    if (num.compareTo(uno) == 0) {
                        test = obj.add(uno);
                    }
                }
            }
            test = test.add(dos);
            if (control++ == 10000) {
                aux += "\n" + test;
                prog.getVista().getTextPanel().getTextArea().setText(aux);
                control = 0;
            }
        }
        long tiempo = (System.nanoTime() - temps);
        prog.getModel().getTiempo().add(tiempo);
        aux += "\nHe tardado " + tiempo + "nanosec\n";
        prog.getVista().getTextPanel().getTextArea().setText(aux);
    }

    private BigInteger sqrt(BigInteger x) {
        BigInteger div = BigInteger.ZERO.setBit(x.bitLength() / 2);
        BigInteger div2 = div;
        // Loop until we hit the same value twice in a row, or wind
        // up alternating.
        for (;;) {
            BigInteger y = div.add(x.divide(div)).shiftRight(1);
            if (y.equals(div) || y.equals(div2)) {
                return y;
            }
            div2 = div;
            div = y;
        }
    }

}
