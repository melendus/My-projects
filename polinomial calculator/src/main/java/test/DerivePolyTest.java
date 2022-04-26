package test;


import logic.Operations;
import model.Polynomial;
import model.PolynomialException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.PolyUtil;

import java.util.ArrayList;
import java.util.List;

public class DerivePolyTest {
    Operations operations = new Operations();
    PolyUtil polyUtil = new PolyUtil();

    @ParameterizedTest
    @MethodSource("provideInput")
    public void derivePoly(String pol1, String polRes) throws PolynomialException {
        Polynomial firstPoly = new Polynomial(pol1);
        Polynomial result = new Polynomial(polRes);
        Polynomial resTest = firstPoly;
        operations.derivePolynomial(firstPoly);
        System.out.print("poly introduced");
        resTest.showMono();
        System.out.print("poly after derivation");
        result.showMono();

        Assertions.assertEquals(firstPoly, result, "Derivation is bad");
    }
    private static List<Arguments> provideInput() {
        List<Arguments> argumentsList = new ArrayList<>();
        argumentsList.add(Arguments.of("+x^3-2x^2+6x-5", "+3x^2-4x+6"));
        argumentsList.add(Arguments.of("+6x^3+10x^2+5", "+18x^2+20x"));
        argumentsList.add(Arguments.of("-5x", "-5"));
        argumentsList.add(Arguments.of("+5x+3x^2-2", "+4+2x^3-x"));
        argumentsList.add(Arguments.of("", "-5"));
        //argumentsList.add(Arguments.of("", "+5"));

        return argumentsList;
    }
}
