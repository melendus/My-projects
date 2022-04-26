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

public class DividePolyTest {
    Operations operations = new Operations();
    PolyUtil polyUtil = new PolyUtil();

    @ParameterizedTest
    @MethodSource("provideInput")
    public void integrateTest(String pol1, String pol2, String result) throws PolynomialException {
        Polynomial firstPoly = new Polynomial(pol1);
        Polynomial secondPoly = new Polynomial(pol2);
        Polynomial[] resultDivision = operations.dividePolynomials(firstPoly, secondPoly);
        String quotient = polyUtil.polyFormatter(resultDivision[0], 'd');
        String reminder = polyUtil.polyFormatter(resultDivision[1], 'd');

        if (quotient.isEmpty()) {
            quotient = "0";
        }
        if (reminder.isEmpty()) {
            reminder = "0";
        }
        String finalResult = "Q: " + quotient  + "  R: " + reminder;

        Assertions.assertEquals(finalResult, result, "Division is bad");
    }

    private static List<Arguments> provideInput() {
        List<Arguments> argumentsList = new ArrayList<>();
        argumentsList.add(Arguments.of("+x^3-2x^2+6x-5", "+x^2-1", "Q: +x-2.0  R: +7.0x-7.0"));
        argumentsList.add(Arguments.of("+2x^3+2x^2+4x-10", "+x+1", "Q: +2.0x^2+4.0  R: -14.0"));
        argumentsList.add(Arguments.of("+2x^3-3x^2-5x-12", "+x+3", "Q: +2.0x^2-9.0x+22.0  R: -78.0"));

        return argumentsList;
    }
}
