package org.example;

import java.util.Scanner;

/**
 * @author lukas kurasinski
 * Program returns the name of a triangle, based on the input.
 * The input are 3 length, describing 3 sides of a triangle,
 * Sides can be set via constructors or by a method
 */
public class Triangle {
    public static enum TYPE {
        SCALENE("SCALENE"), ISOSCELES("ISOSCELES"), EQUILATERAL("EQUILATERAL");

        String thisType = null;

        TYPE(String string) {
            this.thisType = string;
        }
    }

    ;

    private TYPE current_type;
    int a, b, c;

    public Triangle(int a, int b, int c) {
        this.setCurrent_type(a, b, c);

    }

    public Triangle(String[] in) {
        if (in.length == 3) {
            try {
                this.setCurrent_type(Integer.parseInt(in[0]), Integer.parseInt(in[1]), Integer.parseInt(in[2]));
            } catch (NumberFormatException e) {
                this.current_type = null;
            }
        } else {
            this.current_type = null;
        }
    }

    public Triangle() {
    }

    //used with empty constructor
    public void getUserInput() {
        System.out.println("Enter the 3 triangle sides as int, separated by coma ',' \n Ex. 1,2,3");
        Scanner scnr = new Scanner(System.in);
        String[] in = scnr.nextLine().split(",");
        scnr.close();

        if (in.length == 3) {
            try {
                this.setCurrent_type(Integer.parseInt(in[0]), Integer.parseInt(in[1]), Integer.parseInt(in[2]));
            } catch (NumberFormatException e) {
                this.current_type = null;
            }
        } else {
            this.current_type = null;
        }
    }


    public void setCurrent_type(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
        // not a triangle if atleast one side <=0 or
        // sum of any two sides <= length of the third side
        if (a <= 0 || b <= 0 || c <= 0) {
            this.current_type = null;
        } else if (a == b && b == c) {// all sides equal
            this.current_type = TYPE.EQUILATERAL;
        } else if ((a + b <= c) || (a + c <= b) || (b + c <= a)) {
            this.current_type = null;
        } else if (a != b && a != c && b != c) {// none sides equal
            this.current_type = TYPE.SCALENE;
        } else if (a == b || a == c || b == c) {// 2 sides equal
            this.current_type = TYPE.ISOSCELES;
        }
    }

    public TYPE getCurrent_type() {
        this.toString();
        return current_type;
    }

    public String toString() {
        String temp = "";
        if (this.current_type == null) {
            temp = "" + this.a + ", " + this.b + ", " + this.c + ", This is not a triangle";
        } else if (this.current_type == TYPE.EQUILATERAL) {
            temp = "" + this.a + ", " + this.b + ", " + this.c + ", This is a Equilateral triangle";
        } else if (this.current_type == TYPE.SCALENE) {
            temp = "" + this.a + ", " + this.b + ", " + this.c + ", This is a Scalene triangle";
        } else if (this.current_type == TYPE.ISOSCELES) {
            temp = "" + this.a + ", " + this.b + ", " + this.c + ", This is a Isosceles triangle";
        }
        System.out.println(temp);
        return temp;
    }
}