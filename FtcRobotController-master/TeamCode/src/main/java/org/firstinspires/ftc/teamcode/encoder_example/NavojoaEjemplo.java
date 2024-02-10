package org.firstinspires.ftc.teamcode.encoder_example;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "geimpad")
public class NavojoaEjemplo extends LinearOpMode {

    // Motores y servos
    private DcMotor leftFront, leftRear, rightFront, rightRear, slide, picker, succion, garra;
    private Servo servo_1, servo_2, avion;

    private int ticksPerRotation;
    //slide auto
    private int abierto, cerrado;
    private int posicion_original, nueva_posicion, picker_throw, picker_default;
    private double potencia;

    @Override
    public void runOpMode() {
        initializeMotors();
        abierto = 0;  // FALTA AJUSTAR ESTOS VALORES PAPUS
        cerrado = 0;

        potencia = 1;
        posicion_original = 0;
        picker_default = 0;
        nueva_posicion = 50;
        picker_throw = 50;


        telemetry.addData("Status", "Running");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            double power = getPower();

            // Control de motores con gamepad1
            tankDrive(gamepad1.left_stick_y * power, gamepad1.left_stick_y * power,
                    gamepad1.right_stick_y * power, gamepad1.right_stick_y * power);

            // Control del slide automático
            controlSlideAuto();

            if (gamepad2.y) {
                slide.setPower(1);
            } else if (gamepad2.a) {
                slide.setPower(-1);
            }

            //no funcion
            succion.setPower(gamepad2.x ? -1.0 : (gamepad2.b ? 1.0 : 0.0));

            picker.setPower(gamepad2.left_stick_y);

            //picker.setPower(gamepad2.x ? -power : (gamepad2.b ? 1.0 : 0.0));



            //garra
            if(gamepad2.dpad_up){
                garra.setPower(0.5);
            }
            else if(gamepad2.dpad_down) {
                garra.setPower(-0.5);
            }else{
                garra.setPower(0);
            }

            if(gamepad2.dpad_right){
                //servo_1.setPosition(abierto);
                //servo_2.setPosition(-abierto);
            } else if(gamepad2.dpad_left){
                //servo_2.setPosition(cerrado);
                //servo_1.setPosition(cerrado);
            }

            //avion
            if(gamepad2.left_bumper){
                avion.setPosition(90);
            }

            // Tanque movimiento
            if (gamepad1.right_trigger >= 0.9) {
                tankDrive(-1 * power, 1 * power, 1 * power, -1 * power);
            } else if (gamepad1.left_trigger >= 0.9) {
                tankDrive(1 * power, -1 * power, -1 * power, 1 * power);
            }

            // TANK DRIVE
            if (gamepad1.dpad_left) {
                tankDrive(1 * power, 0, 0, 1 * power);
            } else if (gamepad1.dpad_right) {
                tankDrive(0, 1 * power, 1 * power, 0);
            }
        }
    }

    private double getPower() {
        return gamepad1.left_bumper ? 0.25 : (gamepad1.right_bumper ? 1.0 : 0.5);
    }

    private void initializeMotors() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        slide = hardwareMap.get(DcMotor.class, "slide");
        picker = hardwareMap.get(DcMotor.class, "picker");
        succion = hardwareMap.get(DcMotor.class, "succion");
        garra = hardwareMap.get(DcMotor.class, "garra");

        setMotorDirections();
        setRunModeForMotors();

        //ticksPerRotation = leftFront.getMotorType().getTicksPerRev();
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

    private void tankDrive(double lfPower, double lrPower, double rfPower, double rrPower) {
        // Control del tanque de movimiento
        leftFront.setPower(lfPower);
        leftRear.setPower(lrPower);
        rightFront.setPower(rfPower);
        rightRear.setPower(rrPower);
    }

    private void controlSlideAuto() {
        // Control del slide automático
        if (gamepad2.y) {
            slideAuto();
        } else if (gamepad2.a) {
            slideReset();
        } else{
            slide.setPower(0);
        }
    }

    private void slideAuto() {

        slide.setPower(1);

        //slide.setTargetPosition(nueva_posicion);
        //slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //slide.setPower(potencia);

        //picker.setTargetPosition(picker_throw);
        //picker.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //picker.setPower(potencia);
    }

    private void slideReset() {

        slide.setPower(-1);

        //slide.setTargetPosition(posicion_original);
        //slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //slide.setPower(potencia);
    }

}
