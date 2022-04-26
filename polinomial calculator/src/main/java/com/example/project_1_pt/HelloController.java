package com.example.project_1_pt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import logic.Operations;
import model.Polynomial;
import model.PolynomialException;
import utils.PolyUtil;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button addButton;

    @FXML
    private Label result;

    @FXML
    private TextField firstPolynomial;

    @FXML
    protected TextField secondPolynomial;

    @FXML
    protected void onResetButtonClick() {
        result.setText("");
        firstPolynomial.setText("");
        secondPolynomial.setText("");
    }

    @FXML
    protected void onAddButtonClick() {
        try {
            Polynomial firstPoly = new Polynomial(firstPolynomial.getText());
            Polynomial secondPoly = new Polynomial(secondPolynomial.getText());
            Operations operations = new Operations();
            PolyUtil polyUtil = new PolyUtil();

            Polynomial resultingPoly = operations.addPoly(firstPoly, secondPoly);

            String resPolyString = polyUtil.polyFormatter(resultingPoly, 'a');
            if (resPolyString.isEmpty()) {
                resPolyString = "0";
            }
            result.setText(resPolyString);
        } catch (PolynomialException e) {
            result.setText("ERROR: polynomial was not correctly introduced");
        }
    }

    @FXML
    protected void onSubstractButtonClick() {
        try {
            Polynomial firstPolly = new Polynomial(firstPolynomial.getText());
            Polynomial secondPolly = new Polynomial(secondPolynomial.getText());
            Operations operations = new Operations();
            PolyUtil polyUtil = new PolyUtil();

            Polynomial resultingPoly = operations.subPolly(firstPolly, secondPolly);

            String resPolyString = polyUtil.polyFormatter(resultingPoly, 'a');
            if (resPolyString.isEmpty()) {
                resPolyString = "0";
            }
            result.setText(resPolyString);
        } catch (PolynomialException e) {
            result.setText("ERROR: polynomial was not correctly introduced");
        }
    }

    @FXML
    protected void onDeriveButtonClick() {
        try {
            Polynomial polynomial = new Polynomial(firstPolynomial.getText());
            secondPolynomial.setText("");
            Operations operations = new Operations();
            PolyUtil polyUtil = new PolyUtil();

            operations.derivePolynomial(polynomial);

            String resPolyString = polyUtil.polyFormatter(polynomial, 'a');
            if (resPolyString.isEmpty()) {
                resPolyString = "0";
            }
            result.setText(resPolyString);
        } catch (PolynomialException e) {
            result.setText("ERROR: polynomial was not correctly introduced");
        }
    }

    @FXML
    protected void onIntegrateButtonClick() {
        try {
            Polynomial polynomial = new Polynomial(firstPolynomial.getText());
            secondPolynomial.setText("");
            Operations operations = new Operations();
            PolyUtil polyUtil = new PolyUtil();

            operations.integratePolynomial(polynomial);

            String resPolyString = polyUtil.polyFormatter(polynomial, 'd');
            if (resPolyString.isEmpty()) {
                resPolyString = "0";
            }
            result.setText(resPolyString);
        } catch (PolynomialException e) {
            result.setText("ERROR: polynomial was not correctly introduced");
        }
    }

    @FXML
    protected void onMultiplyButtonClick() {
        try {
            Polynomial firstPoly = new Polynomial(firstPolynomial.getText());
            Polynomial secondPoly = new Polynomial(secondPolynomial.getText());
            Operations operations = new Operations();
            PolyUtil polyUtil = new PolyUtil();

            Polynomial resultingPoly = operations.multiplyPolynomials(firstPoly, secondPoly);
            String resPolyString = polyUtil.polyFormatter(resultingPoly, 'a');
            if (resPolyString.isEmpty()) {
                resPolyString = "0";
            }
            result.setText(resPolyString);
        } catch (PolynomialException e) {
            result.setText("ERROR: polynomial was not correctly introduced");
        }
    }

    @FXML
    protected void onDivideButtonClick() {
        try {
            Polynomial firstPoly = new Polynomial(firstPolynomial.getText());
            Polynomial secondPoly = new Polynomial(secondPolynomial.getText());
            Operations operations = new Operations();
            PolyUtil polyUtil = new PolyUtil();

            Polynomial resultingPolys[] = operations.dividePolynomials(firstPoly, secondPoly);
            String quotient;
            String reminder;
            if (!resultingPolys[0].getMonomials().isEmpty()) {
                quotient = polyUtil.polyFormatter(resultingPolys[0], 'd');
            } else
                quotient = "0";

            if (!resultingPolys[1].getMonomials().isEmpty()) {
                reminder = polyUtil.polyFormatter(resultingPolys[1], 'd');
            } else
                reminder = "0";

            if (quotient.isEmpty()) {
                quotient = "0";
            }
            if (reminder.isEmpty()) {
                reminder = "0";
            }
            result.setText("Q: " + quotient + "  R: " + reminder);
        } catch (PolynomialException e) {
            result.setText("ERROR: polynomial was not correctly introduced");
        }
    }
}