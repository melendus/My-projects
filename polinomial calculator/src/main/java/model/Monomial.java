package model;

import java.util.Objects;

public class Monomial implements Comparable<Monomial> {

    private Number coefficient; // change to NUMBER
    private int power;

    public Monomial(Number coefficient, int power) {
        if (power == -1)
            this.power = 0;
        else
            this.power = power;
            this.coefficient = coefficient;
    }

    public Monomial() {

    }

    public Number getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Number coefficient) {
        this.coefficient = coefficient;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public int compareTo(Monomial o) {
        if (this.getPower() > o.getPower())
            return 1;
        else if (this.getPower() < o.getPower())
            return -1;
        else return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Monomial monomial = (Monomial) o;
        return Objects.equals(this.coefficient.floatValue(), monomial.getCoefficient().floatValue()) && this.power == monomial.getPower();
    }

    @Override
    public int hashCode() {
        String hashCode = this.coefficient + String.valueOf(this.power);
        return hashCode.hashCode();
    }
}
