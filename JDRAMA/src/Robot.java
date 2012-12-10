import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.addon.tetrix.TetrixControllerFactory;
import lejos.nxt.addon.tetrix.TetrixMotor;
import lejos.nxt.addon.tetrix.TetrixMotorController;
import lejos.nxt.addon.tetrix.TetrixServo;
import lejos.nxt.addon.tetrix.TetrixServoController;
import lejos.util.Delay;

public class Robot 
{
	TetrixMotorController motorController;				//motor controllers
	TetrixServoController servoController; 
	
	UltrasonicSensor eyes1;								//sensor
	UltrasonicSensor eyes2;
	
	TetrixServo mount;									//motors and servo
	TetrixServo mount2;
	TetrixMotor leftMotor;
	TetrixMotor rightMotor;
	
	public Robot()
	{	
		eyes1 = new UltrasonicSensor(SensorPort.S3);
		eyes2 = new UltrasonicSensor(SensorPort.S2);
		motorController = new TetrixMotorController(SensorPort.S4, TetrixControllerFactory.DAISY_CHAIN_POSITION_1);
		servoController = new TetrixServoController(SensorPort.S4, TetrixControllerFactory.DAISY_CHAIN_POSITION_2);
		mount = servoController.getServo(TetrixServoController.SERVO_1);
		mount2 = servoController.getServo(TetrixServoController.SERVO_2);
		leftMotor = motorController.getBasicMotor(TetrixMotorController.MOTOR_2);
		rightMotor = motorController.getBasicMotor(TetrixMotorController.MOTOR_1);
		leftMotor.setReverse(true);
		rightMotor.setReverse(true);
	}
	
	public void forward(int power, int duration) 
	{
		leftMotor.setPower(power);
		rightMotor.setPower(power);
		
		leftMotor.forward();
		rightMotor.forward();
		
		Delay.msDelay(duration);															//duration is measured in milliseconds
		leftMotor.flt();
		rightMotor.flt();
		
	}
	public void backward(int duration, int power)
	{
		leftMotor.setPower(power);
		rightMotor.setPower(power);
		
		leftMotor.backward();
		rightMotor.backward();
		
		Delay.msDelay(duration);
		leftMotor.stop();
		rightMotor.stop();
	}
	public void turnHere(float heading)
	{
		leftMotor.setPower(100);
		rightMotor.setPower(100);
		
		if (heading < 0)
		{
			leftMotor.backward();
			rightMotor.forward();
			
			Delay.msDelay((long)(Math.abs(heading*SECONDS_PER_DEGREE)));
			leftMotor.stop();
			rightMotor.stop();
		}
		else if (heading > 0)
		{
			leftMotor.forward();
			rightMotor.backward();
			
			Delay.msDelay((long)(heading*SECONDS_PER_DEGREE));
			leftMotor.stop();
			rightMotor.stop();
		}
		
		
	}
	public void setSensorAngle(int angle) 														//moves sensor to the next angle within 200 degrees and gets distance
	{
		mount.setAngle(angle);
		mount2.setAngle(200-angle);
	}
	public int getDistance1()
	{										
		return eyes1.getDistance();
	}
	public int getDistance2()
	{
		return eyes2.getDistance();
	}
	public void waitForEnter()
	{
		Button.ENTER.waitForPress();
	}
	private static final double SECONDS_PER_DEGREE = 14.2-(14.2/3);
}
