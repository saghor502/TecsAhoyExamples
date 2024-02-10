package org.firstinspires.ftc.teamcode.encoder_example;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class MovimientoNavojoa extends LinearOpMode {
    private DcMotor leftFront, leftRear, rightFront, rightRear; //Chassis

    @Override
    public void runOpMode() throws InterruptedException {
        initializeMotors();

        waitForStart();
        while (opModeIsActive()) {
            if(gamepad1.left_trigger > 0.2){
                //tankDrive(gamepad1.left_stick_y * 0.25, gamepad1.right_stick_y * 0.25);
                mecanumDrive(gamepad1.left_stick_y, gamepad1.right_stick_y,
                        gamepad1.left_bumper, gamepad1.right_bumper, 0.25);
            }else if(gamepad1.right_trigger > 0.2){
                //tankDrive(gamepad1.left_stick_y, gamepad1.right_stick_y);
                mecanumDrive(gamepad1.left_stick_y, gamepad1.right_stick_y,
                        gamepad1.left_bumper, gamepad1.right_bumper, 1);
            }else{
                //tankDrive(gamepad1.left_stick_y * 0.5, gamepad1.right_stick_y * 0.5);
                mecanumDrive(gamepad1.left_stick_y, gamepad1.right_stick_y,
                        gamepad1.left_bumper, gamepad1.right_bumper, 0.5);
            }

            //Motores
            //Servos
            //Mecanismos
        }
    }

    private void initializeMotors() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");

        setMotorDirections();
        setRunModeForMotors();
    }
    private void setMotorDirections() {
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftRear.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    private void setRunModeForMotors() {
        //leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    private void mecanumDrive(double tleftpower, double trightpower, boolean leftbumper, boolean rightbumper, double ajusteVelocidad){
        //Tank Variables
        double Left = 0;
        double Right = 0;

        //checar valores de errores
        if((tleftpower < 0.2) && (tleftpower > -0.2)){
            Left = 0;
        }else{
            Left = tleftpower;
        }
        if((trightpower < 0.2) && (trightpower > -0.2)){
            Right = 0;
        }else{
            Right = trightpower;
        }

        if(rightbumper){
            leftFront.setPower((Left +1)/2 * ajusteVelocidad);
            leftRear.setPower((Left -1)/2 * ajusteVelocidad);
            rightFront.setPower((Right +1)/2 * ajusteVelocidad);
            rightRear.setPower((Right -1)/2 * ajusteVelocidad);
        }else if(leftbumper){
            leftFront.setPower((Left -1)/2 * ajusteVelocidad);
            leftRear.setPower((Left +1)/2 * ajusteVelocidad);
            rightFront.setPower((Right -1)/2 * ajusteVelocidad);
            rightRear.setPower((Right +1)/2 * ajusteVelocidad);
        }else{
            leftFront.setPower(Left * ajusteVelocidad);
            leftRear.setPower(Left * ajusteVelocidad);
            rightFront.setPower(Right * ajusteVelocidad);
            rightRear.setPower(Right * ajusteVelocidad);
        }
    }
    private void tankDrive(double Lpower, double Rpower){
        double Left = 0;
        double Right = 0;

        //checar valores de errores
        if((Lpower < 0.2) && (Lpower > -0.2)){
            Left = 0;
        }else{
            Left = Lpower;
        }
        if((Rpower < 0.2) && (Rpower > -0.2)){
            Right = 0;
        }else{
            Right = Rpower;
        }

        leftFront.setPower(Left);
        leftRear.setPower(Left);
        rightFront.setPower(Right);
        rightRear.setPower(Right);
    }

}
