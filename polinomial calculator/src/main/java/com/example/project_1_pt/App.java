package com.example.project_1_pt;

import logic.Operations;
import model.Monomial;
import model.Polynomial;
import model.PolynomialException;
import utils.PolyUtil;

public class App {
    public static void main(String[] args) throws PolynomialException {
       HelloApplication.main(args);
/*        String test = "+x^2+x^2+5x+2x+2-3";
        Polynomial polynomial = new Polynomial(test);
        for (Monomial monomial : polynomial.getMonomials()) {
            System.out.print(monomial.getCoefficient() + "x^" + monomial.getPower() + "+ ");
        }
        PolyUtil polyUtil = new PolyUtil();
        polyUtil.combinePoly(polynomial);
        for (Monomial monomial : polynomial.getMonomials()) {
            System.out.print(monomial.getCoefficient() + "x^" + monomial.getPower() + "+ ");
        }*/
/*        String test1 = "-x-1";
        Polynomial polynomial1 = new Polynomial(test1);
        PolyUtil polyUtil = new PolyUtil();
        System.out.println(polyUtil.polyFormatter(polynomial1));*/

/*        String firstPoly = "+x^3+3x+7";
        String secondPoly = "+3x+5";

        Polynomial poly1 = new Polynomial(firstPoly);
        Polynomial poly2 = new Polynomial(secondPoly);
        Operations operations = new Operations();

        Polynomial result[] = new Polynomial[2];
        result = operations.dividePolynomials(poly1, poly2);

        System.out.println("First Poly: ");
        for (Monomial monomial : result[0].getMonomials()) {
            System.out.print(monomial.getCoefficient() + "x^" + monomial.getPower()+ " ");
        }

        System.out.println("\nSecond Poly: ");
        for (Monomial monomial : result[1].getMonomials()) {
            System.out.print(monomial.getCoefficient() + "x^" + monomial.getPower() + " ");
        }*/
        Operations operations = new Operations();

        Polynomial polynomial1 = new Polynomial("+2x^3-3x^2-5x-12");
        Polynomial polynomial2 = new Polynomial("+x-3");

        Polynomial[] result = operations.dividePolynomials(polynomial1, polynomial2);
    }
}
