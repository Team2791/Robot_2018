package org.usfirst.frc.team340.pathing;

import java.util.function.Function;

public class PathSegment {

	private Function<Double, Double> derivative;
    private double length;

    public PathSegment(Function<Double, Double> derivative, double length) {
        this.derivative = derivative;
        this.length = length;
    }

    public Function<Double, Double> getDerivative() {
        return derivative;
    }

    public double getLength() {
        return length;
    }

    
}
