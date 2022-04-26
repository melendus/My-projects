package logic;

import model.Monomial;
import model.Polynomial;
import utils.PolyUtil;

public class Operations {

    public Polynomial addPoly(Polynomial firstPolynomial, Polynomial secondPolynomial) {
        Polynomial resultingPoly = new Polynomial();

        for (Monomial monomial1 : firstPolynomial.getMonomials()) {
            resultingPoly.addMonomial(monomial1);
        }

        for (Monomial monomial2 : secondPolynomial.getMonomials()) {
            int OK = 1;
            for (Monomial monomial1 : firstPolynomial.getMonomials()) {
                if (monomial2.getPower() == monomial1.getPower()) {
                    float coeff = monomial1.getCoefficient().floatValue() + monomial2.getCoefficient().floatValue();
                    if (coeff == 0)
                        resultingPoly.removeMonomial(monomial1);
                    else
                        resultingPoly.getMonomial(monomial2.getPower()).setCoefficient(coeff);
                    OK = 0;
                }
            }
            if (OK == 1)
                resultingPoly.addMonomial(monomial2);
        }
        return resultingPoly;
    }

    public Polynomial subPolly(Polynomial firstPolynomial, Polynomial secondPolynomial) {
        Polynomial resPoly = new Polynomial();

        for (Monomial monomial : secondPolynomial.getMonomials()) {
            monomial.setCoefficient(-monomial.getCoefficient().floatValue());
        }
        resPoly = addPoly(firstPolynomial, secondPolynomial);
        return resPoly;
    }

    public void derivePolynomial(Polynomial polynomial) {
        PolyUtil polyUtil = new PolyUtil();
        for (Monomial monomial : polynomial.getMonomials()) {
            polyUtil.deriveMonomial(monomial);
        }
        Polynomial polyToBeRemoved = new Polynomial();
        for (Monomial monomial : polynomial.getMonomials()) {
            if (monomial.getCoefficient().floatValue() == 0)
                polyToBeRemoved.addMonomial(monomial);
        }
        polynomial.getMonomials().removeAll(polyToBeRemoved.getMonomials());
    }

    public void integratePolynomial(Polynomial polynomial) {
        PolyUtil polyUtil = new PolyUtil();
        for (Monomial monomial : polynomial.getMonomials()) {
            polyUtil.integrateMonomial(monomial);
        }
    }

    public Polynomial multiplyPolynomials(Polynomial firstPolynomial, Polynomial secondPolynomial) {
        Polynomial result = new Polynomial();
        PolyUtil polyUtil = new PolyUtil();
        for (Monomial monomial1 : firstPolynomial.getMonomials()) {
            for (Monomial monomial2 : secondPolynomial.getMonomials()) {
                float coeff = 0;
                int power = 0;
                if (monomial1 != monomial2) {
                    coeff = monomial1.getCoefficient().floatValue() * monomial2.getCoefficient().floatValue();
                    power = monomial1.getPower() + monomial2.getPower();
                }
                Monomial newMonomial = new Monomial(coeff, power);
                result.addMonomial(newMonomial);
            }
        }
        polyUtil.combinePoly(result);
        return result;
    }

    public Polynomial[] dividePolynomials(Polynomial firstPolynomial, Polynomial secondPolynomial) {
        Polynomial quotient = new Polynomial();
        Polynomial reminder = new Polynomial();
        Polynomial result[] = new Polynomial[2];

        Polynomial pPolynomial = new Polynomial();
        Polynomial qPolynomial = new Polynomial();
        PolyUtil polyUtil = new PolyUtil();

        if (firstPolynomial.getMonomials().get(0).getPower() > secondPolynomial.getMonomials().get(0).getPower()) {
            pPolynomial = firstPolynomial;
            qPolynomial = secondPolynomial;
        } else {
            pPolynomial = secondPolynomial;
            qPolynomial = firstPolynomial;
        }

        Monomial pMonomial = firstPolynomial.getMonomials().get(0);
        Monomial qMonomial = secondPolynomial.getMonomials().get(0);

        pPolynomial.showMono();
        qPolynomial.showMono();

        System.out.println(pMonomial.getCoefficient() + "x^" + pMonomial.getPower() + " ");
        System.out.println(qMonomial.getCoefficient() + "x^" + qMonomial.getPower() + " ");

        while (pMonomial.getPower() >= qMonomial.getPower()) {
            Polynomial intermediate = new Polynomial();
            int power = pMonomial.getPower() - qMonomial.getPower();
            float coeff = pMonomial.getCoefficient().floatValue() / qMonomial.getCoefficient().floatValue();
            Monomial monomial = new Monomial(coeff, power);
            quotient.addMonomial(monomial);
            //polyUtil.combinePoly(quotient);
            intermediate.addMonomial(monomial);
            intermediate = multiplyPolynomials(intermediate, qPolynomial);
            pPolynomial = subPolly(pPolynomial, intermediate);
            if (pPolynomial.getMonomials().isEmpty())
                break;
            pMonomial = pPolynomial.getMonomials().get(0);
            qMonomial = qPolynomial.getMonomials().get(0);
        }
        if (!pPolynomial.getMonomials().isEmpty()) {
            reminder = pPolynomial;
        }
        result[0] = quotient;
        result[1] = reminder;
        return result;
    }
}
