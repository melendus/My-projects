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

public class IntegratePolyTest {
    Operations operations = new Operations();
    PolyUtil polyUtil = new PolyUtil();

    @ParameterizedTest
    @MethodSource("provideInput")
    public void integrateTest(String pol1, String result) throws PolynomialException {
        Polynomial firstPoly = new Polynomial(pol1);
        operations.integratePolynomial(firstPoly);
        String stringResult = polyUtil.polyFormatter(firstPoly, 'd');
        Assertions.assertEquals(stringResult, result, "Integration is bad");
    }

    private static List<Arguments> provideInput() {
        List<Arguments> argumentsList = new ArrayList<>();
        argumentsList.add(Arguments.of("+x^3-2x^2+6x-5", "+0.25x^4-0.6666667x^3+3.0x^2-5.0x"));
        argumentsList.add(Arguments.of("+6x^3+10x^2+5", "+1.5x^4+3.3333333x^3+5.0x"));
        argumentsList.add(Arguments.of("-5x", "-2.5x^2"));
        argumentsList.add(Arguments.of("+5x+3x^2-2", "+2.5x^2+x^3-2.0x"));
        argumentsList.add(Arguments.of("", ""));
        //argumentsList.add(Arguments.of("+3", "+5"));
        return argumentsList;
    }
}
