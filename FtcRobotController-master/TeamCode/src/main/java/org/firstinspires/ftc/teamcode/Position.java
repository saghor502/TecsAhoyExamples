package org.firstinspires.ftc.teamcode;

//Aqui se crea una clase de Posicion
public class Position {

    //el objeto posicion tiene "X", "Y" y "Orientacion"
    private static double xPosition;
    private static double yPosition;
    private static int orientation;

    //aqui podemos crear en nuestro codigo posiciones para quiarnos más facilmente
    public Position(double xPosition, double yPosition, int orientation) {
        Position.xPosition = xPosition;
        Position.yPosition = yPosition;
        Position.orientation = orientation;
    }

    //aqui podemos obtener los valores de "X", "Y" o "Orientacion" de la posicion
    public static double getXPosition(){
        return xPosition;
    }
    public static double getYPosition(){
        return yPosition;
    }
    public static int getOrientation(){
        return orientation;
    }

    //aqui podemos cambiar los valores de "X", "Y" o "Orientacion" de la posicion creada previamente
    public static void setXPosition(double xposition){Position.xPosition = xposition;}
    public static void setYPosition(double yposition){Position.yPosition = yposition;}
    public static void setOrientation(int orientation){Position.orientation = orientation;}
}
