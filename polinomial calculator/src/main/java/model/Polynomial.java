package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    private ArrayList<Monomial> monomials;

    public void addMonomial(Monomial monomial) {
        this.monomials.add(monomial);
        Collections.sort(monomials, Collections.reverseOrder());
    }

    public void removeMonomial(Monomial monomial) {
        monomials.remove(monomial);
    }

    public Monomial getMonomial(int power) {
        for (Monomial monomial : this.monomials) {
            if (monomial.getPower() == power)
                return monomial;
        }
        return null;
    }

    public Polynomial(String inputPoly) throws PolynomialException {
        this.monomials = new ArrayList<>();
        Pattern polynomialPatter = Pattern.compile("[+-](\\d+)?(x(\\^\\d+)?)?");
        Pattern monomialPatter = Pattern.compile("([+-]\\d*)(x?\\^?)(\\d*)");
        Matcher matcherP = polynomialPatter.matcher(inputPoly);
        String errorCheck = "";

        while (matcherP.find()) {
            //System.out.println(matcherP.group() + " ");
            String monomialString = matcherP.group();
            errorCheck = errorCheck.concat(monomialString);
            Matcher matcherM = monomialPatter.matcher(monomialString);

            while(matcherM.find()) {
                //System.out.println("groups: " +  matcherM.group(1) + " " + matcherM.group(2) + " " + matcherM.group(3));
                String coefficient = matcherM.group(1);
                String power = matcherM.group(3);
                if (power.isEmpty()) {
                    if (!matcherM.group(2).isEmpty()) {
                        power = "1";
                    } else
                        power = "0";
                }
                if (coefficient.equals("+"))
                    coefficient = "1";
                if (coefficient.equals("-"))
                    coefficient = "-1";
                //System.out.println("this is the coefficient " + Integer.parseInt(coefficient));
                Monomial monomial = new Monomial(Integer.parseInt(coefficient), Integer.parseInt(power));
                //System.out.println("final monomial: " + monomial.getCoefficient() + "x^" + monomial.getPower());
                this.monomials.add(monomial);
            }
            System.out.println();
        }
        //System.out.println(this.monomials.size());
        if (errorCheck.length() != inputPoly.length()) {
            throw new PolynomialException("error at the reading of the polynomial");
        }
    }

    public Polynomial() {
        this.monomials = new ArrayList<>();
    }

    public void showMono() {
        for (Monomial monomial : this.monomials) {
            System.out.print(monomial.getCoefficient() + "x^" + monomial.getPower() + " ");
        }
        System.out.println();
    }

    public void emptyPolynomial() {
        this.monomials.removeAll(monomials);
    }

    public ArrayList<Monomial> getMonomials() {
        return monomials;
    }

    public void setMonomials(ArrayList<Monomial> monomials) {
        this.monomials = monomials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Polynomial polynomial = (Polynomial) o;
        if(this.getMonomials().isEmpty() && !((Polynomial) o).getMonomials().isEmpty() ) {
            return false;
        }
        for (Monomial mono1 : this.monomials) {
            int i = 0;
            for (Monomial mono2 : ((Polynomial) o).getMonomials()) {
                if (mono2.equals(mono1))
                    i++;
            }
            if (i != 1)
                return false;
        }
        return true;
    }
}
