package org.firstinspires.ftc.teamcode.encoder_example;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Position;

@TeleOp
public class getEachEncoderPosition extends LinearOpMode {
    //TODO: cambiar el valor de la siguiente variable a lo que sea dependiendo del motor/relacion de engranes/radio de la llanta
    private final double ticksPerInches = 1;
    //se crean las variables
    private DcMotor rightFront, rightRear, leftFront, leftRear;

    //            ⠀   ⢀⣀⣀⣀⡀⢀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⡀⢀⣀⣀⣀⡀
    //                ⢸⡏⠉⢹⡇⢸⡏⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⢹⡇⢸⡏⠉⢹⡇
    //    leftFront-> ⢸⡇ ⢸⡇⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⢸⡇ ⢸⡇ <-rightFront
    //                ⢸⡇⠀⢸⡇⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⢸⡇⠀⢸⡇
    //                ⢸⣇⣀⣸⡇⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⢸⣇⣀⣸⡇
    //                ⠈⠉⠉⠉⠁⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠈⠉⠉⠉⠁
    //                      ⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀
    //                      ⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀
    //                      ⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀
    //                ⢀⣀⣀⣀⡀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⢀⣀⣀⣀⡀⠀
    //                ⢸⡏⠉⢹⡇⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⢸⡏⠉⢹⡇
    //     leftRear-> ⢸⡇⠀⢸⡇⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⢸⡇⠀⢸⡇ <-rightRear
    //                ⢸⡇⠀⢸⡇⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⢸⡇⠀⢸⡇
    //                ⢸⣇⣀⣸⡇⢸⣇⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣸⡇⢸⣇⣀⣸⡇
    //                ⠈⠉⠉⠉⠁⠈⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠁⠈⠉⠉⠉⠁

    public void runOpMode(){
        //se conectan las variables a lo que le hayan puesto en el driver station
        //TODO: renombrar todos los "deviceNames" a lo que tengan dentro del driver station
        rightFront = hardwareMap.get(DcMotor.class, "rF");
        leftFront = hardwareMap.get(DcMotor.class, "lF");
        rightRear = hardwareMap.get(DcMotor.class, "rR");
        leftRear = hardwareMap.get(DcMotor.class, "lR");

        waitForStart();
        while(!isStopRequested()) {
            move(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

            //se puede obtener los valores de cada motor haciendo las siguientes lineas de código:
            telemetry.addData("posición de encoder rightFront", rightFront.getCurrentPosition());
            telemetry.addData("posición de encoder leftFront", leftFront.getCurrentPosition());
            telemetry.addData("posición de encoder rightRear", rightRear.getCurrentPosition());
            telemetry.addData("posición de encoder leftRear", leftRear.getCurrentPosition());

            //o se puede obtener el "x" y "y" del robot analizando cada uno de los encoders:
            telemetry.update();
        }
    }

    //Esta funcion se puede mejorar si se usa el Imu, aunque se puede saber la posicion del robot o
    // si el mismo esta dando vuelta con el movimiento de los encoders:

    /**si va para enfrente los valores serían los siguientes:**/
    //(si va para atás solamente la variable power se convierte en negativo)
    //  leftFront = power
    //  leftRear = power
    //  rightFront = -power
    //  rightRear = -power

    /**si da vuelta los valores serían los siguientes:**/
    //(si se quiere dar vuelta al otro lado la variable power se convierte en negativo)
    //  leftFront = power
    //  leftRear = power
    //  rightFront = power
    //  rightRear = power

    /**si hace un desliz (en caso de ser chassis mecanum) los valores serían los siguientes:**/
    //(si se quiere ir al otro lado la variable power se convierte en negativo)
    //  leftFront = -power
    //  leftRear = power
    //  rightFront = power
    //  rightRear = -power

    /**FUNCION EJEMPLO DE MOVIMIENTO CON CONTROL**/
    //No es necesario que usen esta misma función, puede ser que tengan otra para el
    //movimiento con el control que funcione igual de fluido que esta.
    public void move(double forwardPower, double leftPower, double turnPower){
        //ly = enfrente, lx = desliz, rx = vuelta
        double leftY;
        double leftX;
        double rightX;

        //Aqui las variables se comparan con un "error" que se pueda obtener del rango del control, si es menor a eso se convierte
        //en 0 para no deteriorar los motores
        if(forwardPower < 0.1 && forwardPower > -0.1){
            leftY = 0;
        }else{
            leftY = forwardPower;
        }
        if(leftPower < 0.1 && leftPower > -0.1){
            leftX = 0;
        }else{
            leftX = leftPower;
        }
        if(turnPower < 0.1 && turnPower > -0.1){
            rightX = 0;
        }else{
            rightX = turnPower;
        }

        //en esta parte solamente se obtiene los valores que se da del control, y se saca el promedio de cada poder en cada motor.
        //la primera variable (leftY) es para que vaya enfrente
        //la segunda variable (leftX) es para que haga un desliz (en caso de ser chassis mecanum)
        //la tercera variable (rightX) es para dar vuelta
        if(leftX != 0 && leftY != 0 && rightX != 0){
            rightFront.setPower((leftY - leftX - rightX)/3);
            rightRear.setPower((leftY + leftX - rightX)/3);
            leftFront.setPower((-leftY + leftX - rightX)/3);
            leftRear.setPower((-leftY - leftX - rightX)/3);
        }else if((leftX != 0  && leftY != 0 && rightX == 0)
                || (leftX != 0  && leftY == 0 && rightX != 0)
                || (leftX == 0  && leftY != 0 && rightX != 0)){
            rightFront.setPower((leftY - leftX - rightX)/2);
            rightRear.setPower((leftY + leftX - rightX)/2);
            leftFront.setPower((-leftY + leftX - rightX)/2);
            leftRear.setPower((-leftY - leftX - rightX)/2);
        }else if((leftX != 0  && leftY == 0 && rightX == 0)
                || (leftX == 0  && leftY != 0 && rightX == 0)
                || (leftX == 0  && leftY == 0 && rightX != 0)){
            rightFront.setPower(leftY - leftX - rightX);
            rightRear.setPower(leftY + leftX - rightX);
            leftFront.setPower(-leftY + leftX - rightX);
            leftRear.setPower(-leftY - leftX - rightX);
        }else{
            rightFront.setPower(0);
            rightRear.setPower(0);
            leftFront.setPower(0);
            leftRear.setPower(0);
        }
    }

    /**FUNCION PARA OBTENER LA ORIENTACIÓN Y LA POSICION DEL ROBOT**/
    public Position getCurrentPositionTank(){
        //creamos una posición
        Position temp = new Position(0,0,0);
        
        //sacamos el promedio de cada uno de los encoders de cada lado
        double avLeft = ((leftFront.getCurrentPosition() + leftRear.getCurrentPosition())/2.0) * ticksPerInches;
        double avRight = ((leftFront.getCurrentPosition() + leftRear.getCurrentPosition())/2.0) * ticksPerInches;

        //igualamos la variable a el cambio que ha tenido el encoder

        return(temp);
    }
}
