package testing.mathlib;

import mathlib.Mat3x3;
import mathlib.Normal3;
import mathlib.Point3;
import mathlib.Vector3;

/**
 * TestsMathLibFunctions is a class to test all
 * functions of mathlib classes with defined mathematical
 * calculation tests
 *
 * @author group raspi, CG1, Beuth-Hochschule
 * @version 1.0
 */
public class TestsMathLibFunctions {

    /**
     * Main Class, tests functions from package mathlib
     *
     * @param args
     */
    public static void main(String[] args) {
        Vector3 v1 = new Vector3(8, 8, 8);
        Mat3x3 m = new Mat3x3(1, 2, 3, 4, 5, 6, 7, 8, 9);

        //Schema A
        System.out.println("Zeigen Sie, dass (1 2 3) * 0.5 = (0.5 1 1.5) ist, wobei (1 2 3) eine Normale ist!");
        System.out.println(new Normal3(1, 2, 3).mul(0.5) + "\n\n");

        System.out.println("Zeigen Sie, dass (1 2 3) + (3 2 1) = (4 4 4) ist, wobei (1 2 3) und (3 2 1) Normalen sind!");
        System.out.println(new Normal3(1, 2, 3).add(new Normal3(3, 2, 1)));

        System.out.println("Zeigen Sie für Sie, sowohl für die Skalarprodukte von:\n" +
                "              Normale und Vektor,\n" +
                "              Vektor mit Normale und\n" +
                "              Vektor mit Vektor, dass\n" +
                "              (1 0 0) * (1 0 0) = 1 und\n" +
                "              (1 0 0) * (0 1 0) = 0 ist.");

        System.out.println("Normale mit Vektor");
        System.out.println(new Normal3(1, 0, 0).dot(new Vector3(1, 0, 0)));
        System.out.println(new Normal3(1, 0, 0).dot(new Vector3(0, 1, 0)) + "\n");
        System.out.println("Vektor mit Normale");
        System.out.println(new Vector3(1, 0, 0).dot(new Normal3(1, 0, 0)));
        System.out.println(new Vector3(1, 0, 0).dot(new Normal3(0, 1, 0)) + "\n");
        System.out.println("Vektor mit Vektor");
        System.out.println(new Vector3(1, 0, 0).dot(new Vector3(1, 0, 0)));
        System.out.println(new Vector3(1, 0, 0).dot(new Vector3(0, 1, 0)) + "\n\n");

        System.out.println("Zeigen Sie für den Punkt, dass (1 1 1) - (2 2 0) den Vektor (-1 -1 1) ergibt, wenn (2 2 0) ein Punkt ist.");
        System.out.println(new Point3(1, 1, 1).sub(new Point3(2, 2, 0)) + "\n\n");

        System.out.println("Zeigen Sie für den Punkt, dass (1 1 1) - (4 3 2) den Vektor (-3 -2 -1) ergibt, wenn (4 3 2) ein Vektor ist.");
        System.out.println(new Point3(1, 1, 1).sub(new Vector3(4, 3, 2)) + "\n\n");

        System.out.println("Zeigen Sie für den Punkt, dass (1 1 1) + (4 3 2) den Vektor (5 4 3) ergibt, wenn (4 3 2) ein Vektor ist.");
        System.out.println(new Point3(1, 1, 1).add(new Vector3(4, 3, 2)) + "\n\n");

        System.out.println("Zeigen Sie, dass |(1 1 1)| = Math.sqrt(3) ist!");
        Vector3 v_magnitude = new Vector3(1, 1, 1);
        System.out.println("magnitude of " + v_magnitude + "\nmagnitude^2 = " + Math.pow(v_magnitude.magnitude, 2) + "\n\n");

        //Schema B
        System.out.println("Zeigen Sie, dass (1 2 3) * 0.5 = (0.5 1 1.5) ist, wobei (1 2 3) ein Vector ist!");
        System.out.println(new Vector3(1, 2, 3).mul(0.5) + "\n\n");

        System.out.println("Zeigen Sie, dass (1 2 3) + (3 2 1) = (4 4 4) ist, wobei (1 2 3) und (3 2 1) Vektoren sind!");
        System.out.println(new Vector3(1, 2, 3).add(new Vector3(3, 2, 1)) + "\n\n");

        System.out.println("Zeigen Sie für den Vektor, dass (1 1 1) - (2 2 0) den Vektor (-1 -1 1) ergibt, wenn (2 2 0) ein Vektor ist.");
        System.out.println(new Vector3(1, 1, 1).sub(new Vector3(2, 2, 0)) + "\n\n");

        System.out.println("Zeigen Sie für den Vektor, dass (1 1 1) - (4 3 2) den Vektor (-3 -2 -1) ergibt, wenn (4 3 2) ein Vektor ist.");
        System.out.println(new Vector3(1, 1, 1).sub(new Vector3(4, 3, 2)) + "\n\n");

        System.out.println("Zeigen Sie für den Vektor, dass (1 1 1) + (4 3 2) den Vektor (5 4 3) ergibt, wenn (4 3 2) ein Vektor ist.");
        System.out.println(new Vector3(1, 1, 1).add(new Vector3(4, 3, 2)) + "\n\n");


        System.out.println("Zeigen Sie, dass der Vektor (-0.707 0.707 0) reflektiert an der Normalen (0 1 0) den Vektor (0.707 0.707 0) ergibt!");
        System.out.println(new Vector3(-0.707, 0.707, 0).reflectedOn(new Normal3(0, 1, 0)).toString() + "\n\n");

        System.out.println("Zeigen Sie, dass der Vektor (0.707 0.707 0) reflektiert an der Normalen (1 0 0) den Vektor (0.707 -0.707 0) ergibt!");
        System.out.println(new Vector3(0.707, 0.707, 0).reflectedOn(new Normal3(1, 0, 0)) + "\n\n");

        System.out.println("Zeigen Sie jeweils für einen Punkt und einen Vektor, dass die Rechnung (1 0 0 0 1 0 0 0 1)* (3 2 1) = (3 2 1) stimmt!");
        System.out.println("Matrix mit Punkt:");
        System.out.println(new Mat3x3(1, 0, 0, 0, 1, 0, 0, 0, 1).mul(new Point3(3, 2, 1)) + "\n");
        System.out.println("Matrix mit Vektor:");
        System.out.println(new Mat3x3(1, 0, 0, 0, 1, 0, 0, 0, 1).mul(new Vector3(3, 2, 1)) + "\n\n");

        System.out.println("Zeigen Sie, dass die Rechnung (1 2 3 4 5 6 7 8 9) * (1 0 0 0 1 0 0 0 1) = (1 2 3 4 5 6 7 8 9) stimmt!");
        System.out.println(new Mat3x3(1, 2, 3, 4, 5, 6, 7, 8, 9).mul(new Mat3x3(1, 0, 0, 0, 1, 0, 0, 0, 1)) + "\n\n");

        System.out.println("Zeigen Sie, dass die Rechnung (1 2 3 4 5 6 7 8 9) * (0 0 1 0 1 0 1 0 0) = (3 2 1 6 5 4 9 8 7) stimmt!");
        System.out.println(new Mat3x3(1, 2, 3, 4, 5, 6, 7, 8, 9).mul(new Mat3x3(0, 0, 1, 0, 1, 0, 1, 0, 0)) + "\n\n");

        System.out.println("Nehmen Sie die Matrix (1 2 3 4 5 6 7 8 9) und den Vektor (8 8 8) und zeigen Sie, dass die Spalten \n" +
                "durch die Methoden changeCol1, changeCol2 und changeCol3 richtig ausgetauscht werden.");
        System.out.println("changeCol1: ");
        System.out.println(m.changeCol1(v1) + "\n");
        System.out.println("changeCol2: ");
        System.out.println(m.changeCol2(v1) + "\n");
        System.out.println("changeCol3: ");
        System.out.println(m.changeCol3(v1));
    }
}