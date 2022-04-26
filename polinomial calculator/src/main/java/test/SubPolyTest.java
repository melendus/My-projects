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


public class SubPolyTest {
    Operations operations = new Operations();
    PolyUtil polyUtil = new PolyUtil();

    @ParameterizedTest
    @MethodSource("provideInput")
    public void subPoly(String pol1, String poli2, String polRes) throws PolynomialException {
        Polynomial firstPoly = new Polynomial(pol1);
        Polynomial secondPoly = new Polynomial(poli2);
        Polynomial result = new Polynomial(polRes);
        Polynomial resTest;
        resTest = operations.subPolly(firstPoly, secondPoly);
        resTest.showMono();
        result.showMono();
        Assertions.assertEquals(resTest, result, "Substraction is bad");
    }
    private static List<Arguments> provideInput() {
        List<Arguments> argumentsList = new ArrayList<>();
        argumentsList.add(Arguments.of("+x^3-2x^2+6x-5", "+2x^2-1", "+x^3-4x^2+6x-4"));
        argumentsList.add(Arguments.of("+6x^3+10x^2+5", "+4x^2+2x+1", "+6x^3+6x^2-2x+4"));
        argumentsList.add(Arguments.of("-5x", "+5x", "+0"));
        argumentsList.add(Arguments.of("+5x+3x^2-2", "+4+2x^3-x", "+2x^3+3x^2+4x+2"));
        argumentsList.add(Arguments.of("", "", ""));
        argumentsList.add(Arguments.of("", "+5x", "+5x"));

        return argumentsList;
    }
}
