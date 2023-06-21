/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_esdeveniments.control;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author mascport
 */
public class PrimoProbable implements Serializable{

    private int tope = 100;
    private Random random;

    public PrimoProbable() {
        tope = 100;
        random = new Random();
    }

    public PrimoProbable(int p) {
        tope = p;
        random = new Random();
    }

    public boolean esPrimo(BigInteger num) {
        return esPrimo(num.toString());
    }

    public boolean esPrimo(String num) {
        BigInteger n = new BigInteger(num);
        boolean esprimo = true;
        if (n.compareTo(new BigInteger("2")) == 0) {
            esprimo = true;
        } else if (n.remainder(new BigInteger("2")).compareTo(new BigInteger("0")) == 0) {  // numero par
            esprimo = false;
        } else if (n.compareTo(new BigInteger("3")) == 0) {
            esprimo = true;
        } else {
            for (int i = 0; (i < tope) && (esprimo); i++) {
                esprimo = Miller_Rabin(n);
            }
        }
        return esprimo;
    }

    private boolean Miller_Rabin(BigInteger n) {
        // n > 4 e impar
        BigInteger a;
        BigInteger dos = new BigInteger("2");
        a = random(dos, n.subtract(dos));
        return B(a, n);
    }

    private BigInteger random(BigInteger a, BigInteger b) {
        // random entre 2 y n-2. En este caso entre a y b
        BigInteger res = null;
        String aux = "";
        int n = b.toString().length();
        for (int i = 0; i < n; i++) {
            int k = 48 + random.nextInt(10);
            aux = aux + (char) (k);
        }
        res = new BigInteger(aux);
        if (res.compareTo(a) == -1) {  // menor que la base
            res = random(a, b);
        } else if (res.compareTo(b) == 1) {  // mayor que el tope
            res = random(a, b); // llamada recursiva poco probable
        }
        return res;
    }

    private boolean B(BigInteger a, BigInteger n) {
        boolean res = true;
        BigInteger s, t, x, i;
        BigInteger uno = new BigInteger("1");
        BigInteger dos = new BigInteger("2");
        boolean stop;
        s = new BigInteger("0");  // s = 0
        t = n.subtract(uno);   // t = n - 1
        do {
            s = s.add(uno);     // s++
            t = t.divide(dos);          // t = t / 2
        } while ((t.remainder(dos).compareTo(uno)) != 0);   // no puede ser módulo 1
        x = a.modPow(t, n);   // a elevado a t módulo n
        if ((x.compareTo(uno) == 0) || (x.compareTo(n.subtract(uno)) == 0)) {    // si es 1 ó n-1
            res = true;
        } else {
            stop = false;
            for (i = new BigInteger("0"); (i.compareTo(s.subtract(uno)) == -1) && (!stop); i = i.add(uno)) {
                x = x.multiply(x);
                x = x.remainder(n);
                if (x.compareTo(n.subtract(uno)) == 0) {
                    stop = true;
                }
            }
            res = stop;
        }
        return res;
    }

    public BigInteger otroProbablePrimo(BigInteger prim) {
        BigInteger res = random(
                prim.divide(new BigInteger("10")),
                prim.subtract(new BigInteger("1")));
        while (!esPrimo(res)) {
            res = random(
                    prim.divide(new BigInteger("10")),
                    prim.subtract(new BigInteger("1")));
        }
        return res;
    }
}
