/**
 * Practica 7 Algoritmos Avanzados - Ing Informática UIB
 *
 * @date 21/05/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url
 */
package mvc_esdeveniments.control;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.Random;
import mvc_esdeveniments.MVC_Esdeveniments;
import mvc_esdeveniments.PerEsdeveniments;

public class Control extends Thread implements PerEsdeveniments {

    private MVC_Esdeveniments prog;
    private String option;
    private boolean continua;
    private Long a;
    private int n, c;

    public Control(MVC_Esdeveniments p) {
        prog = p;
        continua = true;
        a = 400L;
        n = 0;
        c = 1;
    }

    @Override
    public void notificar(String s) {
        option = s;
        this.start();
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

    public Long generarNumNXifres2(int n) {
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

        return Long.valueOf(aux);
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
        switch (option) {
            case "Factoritzar num. fort":
                factoritzarNumFort();
                break;
            case "Graficar cost factorització":
                costeAsintotico();
                break;
        }
    }

    public void factoritzarNumFort() {
        // voy a factorizar
        factorizar("1236");
        // voy a factorizar
        factorizar("56754");
        // voy a factorizar
        factorizar("8281247744");
        // voy a factorizar un número duro
        String aux = prog.getVista().getTextPanel().getTextArea().getText();
        BigInteger num = prog.getModel().getNumDuro();
        aux += "\nLongitud del número duro:" + num.toString().length() + " cifras." + "\nVoy a tardar: " + 298073 * Math.exp(0.8981 * num.toString().length()) * 0.000000001 / (3600 * 24 * 365) + " años";
        prog.getVista().getTextPanel().getTextArea().setText(aux);
        factorizar(prog.getModel().getNumDuro().toString());
    }

    public long factorizar(String n) {
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
        aux += "\nVoy a factorizar " + num;
        prog.getVista().getTextPanel().getTextArea().setText(aux);
//        aux += "\n\nLongitud del número duro:" + num.toString().length() + " cifras." + "\nVoy a tardar: " + 298073 * Math.exp(0.8981 * num.toString().length()) * 0.000000001 / (3600 * 24 * 365) + " años" + "\nVoy a factorizar " + num;
//        prog.getVista().getTextPanel().getTextArea().setText(aux);
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
//        prog.getModel().getTiempo().add(tiempo);
        aux += "\nHe tardado " + tiempo + "nanosec\n";
        prog.getVista().getTextPanel().getTextArea().setText(aux);
        return tiempo;
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

    public void costeAsintotico() {
        while (continua) {
            a += 100;
//            if(n==0)n++;
//            if(c==8){
//                n++;
//                c=1;
//            }
//            Long candidat = generarNumNXifres2(n);
//            if(candidat==0 || candidat==1) candidat = generarNumNXifres2(n);
//            if (prog.getModel().getNumero().isEmpty()) {
//                prog.getModel().getNumero().add(candidat);
//            } else {
//                c++;
//                while (prog.getModel().getNumero().contains(candidat)|| candidat==0 || candidat==1) {
//                    candidat = generarNumNXifres2(n);
//                }
//                prog.getModel().getNumero().add(candidat);
//                prog.getModel().getNumero().sort(Comparator.naturalOrder());
//            }
            prog.getModel().getNumero().add(a);
            // Factorizamos el número generado y guardamos el tiempo
            long tiempo = factorizar(a.toString());
//            long tiempo = factorizar(candidat.toString());
//            int index = prog.getModel().getNumero().indexOf(candidat);
//            prog.getModel().getTiempo().add(index,tiempo);
            prog.getModel().getTiempo().add(tiempo);
            
            // Pintamos
            prog.getVista().notificar("pinta");

        }
    }

    public void aturarGrafica() {
        continua = false;
    }

    public void reprenGrafica() {
        continua = true;
        notificar("Graficar cost factorització");
    }

}
