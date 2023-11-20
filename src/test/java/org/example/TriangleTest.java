package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    // Tests for a valid scalene triangle with different side lengths.
    @ParameterizedTest
    @MethodSource("scaleneTriangleSidesProvider")
    @DisplayName("Test for a valid scalene triangle with different sides")
    void testValidScaleneTriangle(int[] sides) {
        Triangle triangle = new Triangle(sides[0], sides[1], sides[2]);
        assertEquals(Triangle.TYPE.SCALENE, triangle.getCurrent_type(), "Triangle with sides " + sides[0] + ", " + sides[1] + ", " + sides[2] + " should be SCALENE");
    }

    // Provides sets of sides for scalene triangles.
    private static Stream<int[]> scaleneTriangleSidesProvider() {
        return Stream.of(
                new int[]{3, 4, 5},
                new int[]{4, 5, 3},
                new int[]{5, 3, 4}
        );
    }

    // Tests for a valid isosceles triangle with two equal sides.
    @ParameterizedTest
    @MethodSource("isoscelesTriangleSidesProvider")
    @DisplayName("Test for a valid isosceles triangle with two equal sides")
    void testValidIsoscelesTriangle(int[] sides) {
        Triangle triangle = new Triangle(sides[0], sides[1], sides[2]);
        assertEquals(Triangle.TYPE.ISOSCELES, triangle.getCurrent_type(), "Triangle with sides " + sides[0] + ", " + sides[1] + ", " + sides[2] + " should be ISOSCELES");
    }

    // Provides sets of sides for isosceles triangles.
    private static Stream<int[]> isoscelesTriangleSidesProvider() {
        return Stream.of(
                new int[]{2, 2, 3},
                new int[]{2, 3, 2},
                new int[]{3, 2, 2}
        );
    }

    // Tests for a valid equilateral triangle with all sides equal.
    @Test
    @DisplayName("Output test for equilateral triangle")
    void testValidEquilateralTriangle() {
        Triangle triangle = new Triangle(3, 3, 3);
        String expectedOutput = "3, 3, 3, This is a Equilateral triangle";
        assertEquals(expectedOutput, triangle.toString(), "Output not matching for equilateral triangle");
    }

    // Tests for an invalid triangle with all sides zero.
    @Test
    @DisplayName("Test for invalid triangle with zeroes")
    void testInvalidTriangleWithZeroNumbers() {
        Triangle triangle = new Triangle(0, 0, 0);
        assertNull(triangle.getCurrent_type(), "Invalid triangle should return null type");
    }

    // Tests for an invalid triangle with negative side lengths.
    @Test
    @DisplayName("Test for invalid triangle with negative numbers")
    void testInvalidTriangleNegativeNumbers() {
        Triangle triangle = new Triangle(-1, -1, -1);
        assertNull(triangle.getCurrent_type(), "Invalid triangle should return null type");
    }

    // Tests for handling non-numeric inputs in the constructor.
    @Test
    @DisplayName("Test for handling of non-numeric inputs")
    void testNonNumericInputHandling() {
        Triangle triangle = new Triangle(new String[]{"a", "b", "c"});
        assertNull(triangle.getCurrent_type(), "Triangle with non-numeric inputs should have null type");
    }

    // Tests for handling maximum integer values as sides.
    @Test
    @DisplayName("Data type test with maximum integer values")
    void testMaxIntValues() {
        Triangle triangle = new Triangle(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertEquals(Triangle.TYPE.EQUILATERAL, triangle.getCurrent_type(), "Triangle type should be EQUILATERAL for maximum integer values");
    }

    // Tests for handling insufficient number of sides provided as an array.
    @Test
    @DisplayName("Boundary test for array input with too few values")
    void testInvalidArrayInputLow() {
        Triangle triangle = new Triangle(new String[]{"1", "2"}); // Two sides provided
        assertNull(triangle.getCurrent_type(), "Triangle with insufficient sides should have null type");
    }

    // Tests for handling excessive number of sides provided as an array.
    @Test
    @DisplayName("Boundary test for array input with too many values")
    void testInvalidArrayInputHigh() {
        Triangle triangle = new Triangle(new String[]{"1", "2", "3", "4"}); // Four sides provided
        assertNull(triangle.getCurrent_type(), "Triangle with excess sides should have null type");
    }

    // Tests the empty constructor with valid user input.
    @Test
    @DisplayName("Test empty constructor with valid user input")
    void testEmptyConstructorWithValidInput() {
        String validData = "3,4,5\n";
        InputStream originalStdin = System.in; // Backup the original System.in

        try {
            System.setIn(new ByteArrayInputStream(validData.getBytes()));
            Triangle triangle = new Triangle();
            triangle.getUserInput();

            assertNotNull(triangle.getCurrent_type(), "Triangle type should not be null for valid input");
        } finally {
            System.setIn(originalStdin); // Restore original System.in
        }
    }

    // Tests the empty constructor with invalid user input.
    @Test
    @DisplayName("Test empty constructor with invalid user input")
    void testEmptyConstructorWithInvalidInput() {
        String invalidData = "1,2,3\n";
        InputStream originalStdin = System.in; // Backup the original System.in

        try {
            System.setIn(new ByteArrayInputStream(invalidData.getBytes()));
            Triangle triangle = new Triangle();
            triangle.getUserInput();

            assertNull(triangle.getCurrent_type(), "Triangle type should be null for invalid input");
        } finally {
            System.setIn(originalStdin); // Restore original System.in
        }
    }

    // Tests the empty constructor with non-numeric user input.
    @Test
    @DisplayName("Test empty constructor with non-numeric user input")
    void testEmptyConstructorWithNonNumericInput() {
        String nonNumericData = "a,b,c\n";
        InputStream originalStdin = System.in; // Backup the original System.in

        try {
            System.setIn(new ByteArrayInputStream(nonNumericData.getBytes()));
            Triangle triangle = new Triangle();
            triangle.getUserInput();

            assertNull(triangle.getCurrent_type(), "Triangle type should be null for non-numeric input");
        } finally {
            System.setIn(originalStdin); // Restore original System.in
        }
    }

    // Tests the empty constructor with insufficient number of inputs.
    @Test
    @DisplayName("Test empty constructor with insufficient number of inputs")
    void testEmptyConstructorWithInsufficientNumberOfInputs() {
        String insufficientData = "1,2\n"; // Only two sides provided
        InputStream originalStdin = System.in; // Backup the original System.in

        try {
            System.setIn(new ByteArrayInputStream(insufficientData.getBytes()));
            Triangle triangle = new Triangle();
            triangle.getUserInput();

            assertNull(triangle.getCurrent_type(), "Triangle type should be null for insufficient input");
        } finally {
            System.setIn(originalStdin); // Restore original System.in
        }
    }

    // Tests the empty constructor with excessive number of inputs.
    @Test
    @DisplayName("Test empty constructor with excessive number of inputs")
    void testEmptyConstructorWithExcessiveNumberOfInputs() {
        String excessiveData = "1,2,3,4\n"; // Four sides provided
        InputStream originalStdin = System.in; // Backup the original System.in

        try {
            System.setIn(new ByteArrayInputStream(excessiveData.getBytes()));
            Triangle triangle = new Triangle();
            triangle.getUserInput();

            assertNull(triangle.getCurrent_type(), "Triangle type should be null for excessive input");
        } finally {
            System.setIn(originalStdin); // Restore original System.in
        }
    }

}
