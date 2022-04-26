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

public class MultiplyPolyTest {
    Operations operations = new Operations();
    PolyUtil polyUtil = new PolyUtil();

    @ParameterizedTest
    @MethodSource("provideInput")
    public void integrateTest(String pol1, String pol2, String result) throws PolynomialException {
        Polynomial firstPoly = new Polynomial(pol1);
        Polynomial secondPoly = new Polynomial(pol2);
        Polynomial resultMultiplication =  operations.multiplyPolynomials(firstPoly, secondPoly);
        Polynomial resultPoly = new Polynomial(result);
        Assertions.assertEquals(resultMultiplication, resultPoly, "Multiplication is bad");
    }

    private static List<Arguments> provideInput() {
        List<Arguments> argumentsList = new ArrayList<>();
        argumentsList.add(Arguments.of("+x^3-2x^2+6x-5", "+2x^2-1", "+2x^5-4x^4+11x^3-8x^2-6x+5"));
        argumentsList.add(Arguments.of("+3x^2-x+1", "+x-2", "+3x^3-7x^2+3x-2"));
        argumentsList.add(Arguments.of("-5x", "-2x^2","+10x^3"));
        argumentsList.add(Arguments.of("", "",""));
        return argumentsList;
    }
}
