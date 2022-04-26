package utils;

import model.Monomial;
import model.Polynomial;

import java.util.ArrayList;

public class PolyUtil {
    public String monoFormatter(Monomial monomial, char type) {
        //System.out.println("Monomial: " + monomial.getCoefficient() + "x^" + monomial.getPower());
        String resMono = "";
        String coefficient = "";
        if (type == 'd') {
            coefficient = String.valueOf(monomial.getCoefficient().floatValue());
        } else {
            coefficient = String.valueOf(monomial.getCoefficient().intValue());
        }
        if (monomial.getCoefficient().floatValue() > 0) {
            resMono = resMono.concat("+");
        }
        if (monomial.getCoefficient().floatValue() == 0){
            return "";
        } else if (monomial.getCoefficient().floatValue() == -1) {
            resMono = resMono.concat("-");
        } else if (monomial.getCoefficient().floatValue() == 1 && monomial.getPower() == 0) {
            resMono = resMono.concat(coefficient);
        }
        else if (monomial.getCoefficient().floatValue() != 1) {
            resMono = resMono.concat(coefficient);
        }
        if (monomial.getPower() == 0)
        {
            if (monomial.getCoefficient().floatValue() == -1) {
                resMono = resMono.concat("1");
                return resMono;
            }
        }
        else if (monomial.getPower() == 1) {
             resMono = resMono.concat("x");
        } else {
            resMono = resMono.concat("x^" + monomial.getPower());
        }
        return resMono;
    }

    public String polyFormatter(Polynomial polynomial, char type) {
        String resPolly = "";
        for(Monomial monomial : polynomial.getMonomials()) {
            String mono = monoFormatter(monomial, type);
            resPolly = resPolly.concat(mono);
        }
        return resPolly;
    }

    public void deriveMonomial(Monomial monomial) {
        int coeff = monomial.getCoefficient().intValue();
        int power = monomial.getPower();

        if (power == 0){
            coeff = 0;
        } else {
            coeff = coeff * power;
            power -= 1;
        }
        monomial.setCoefficient(coeff);
        monomial.setPower(power);

        //return new Monomial(coeff, power);
    }

    public void integrateMonomial(Monomial monomial) {
        float coeff = monomial.getCoefficient().floatValue();
        int power = monomial.getPower();

        coeff = coeff / (power + 1);
        power++;

        monomial.setPower(power);
        monomial.setCoefficient(coeff);
    }

    public void combinePoly(Polynomial polynomial) {
        Polynomial toRemove = new Polynomial();

        for(Monomial monomial : polynomial.getMonomials()) {
            float coefficient = monomial.getCoefficient().floatValue();
            for (Monomial monomial1 : polynomial.getMonomials()) {
                if (monomial.getPower() == monomial1.getPower() && monomial != monomial1) {
                    coefficient += monomial1.getCoefficient().intValue();
                }
            }
            if (coefficient != 0) {
                Monomial newMonomial = new Monomial(coefficient, monomial.getPower());
                if (!toRemove.getMonomials().contains(newMonomial))
                toRemove.addMonomial(newMonomial);
            }
        }
        polynomial.emptyPolynomial();
        System.out.println(toRemove.getMonomials().size());
        for (Monomial monomial : toRemove.getMonomials()) {
            polynomial.addMonomial(monomial);
        }
    }
}
