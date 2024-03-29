package frc.robot.subsystems;

import static frc.robot.Constants.HorizontalConstants.*;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team9029.wpilibj9029.MPU6050;

/**
 * Did this because i want to see if i can move the shooter horizontally and vertically at the same time.
 */
public class ShooterHorizontal extends SubsystemBase {
    public final Spark m_motorController = new Spark(MOTOR_PORT);
    private final DigitalInput m_switch = new DigitalInput(DIGITAL_PORT);
    private int m_pendingReturn = 0;

    private final MPU6050 m_mpu6050 = new MPU6050(I2C_ADDRESS);

    public ShooterHorizontal() {
        m_mpu6050.reset();
        
    }

    public void manualSteer(double speed) {
        // saçmalık
        // m_motorController.set(speed);
        setSpeed(speed);
    }

    // Default olarak 0 derece
    public void autoSteer() {
        autoSteer(0);
    }
    
    public void autoSteer(double targetAngle) {
        // Debug icin
        // m_mpu6050.printAngles();
        
        // gyro ile aramızda olan açıyla orantılı olarak motora güç ver
        double angle = m_mpu6050.getAngleX();
        setSpeed(
            m_mpu6050._map(angle-targetAngle, 0, 360, 0, MAX_SPEED),
            angle
        );
    }

    public void testSteer() {
        testSteer(TEST_DESTINATION);
    }

    public void testSteer(double targetAngle) {
        double angle = m_mpu6050.getAngleX();
        setSpeed(m_mpu6050._map(angle - targetAngle , 0, 360, 0, TEST_SPEED));
    }

    public void returnToStart() {
        boolean isReached = _setAngle(0, m_mpu6050.getAngleX(), RETURN_SPEED);
        if (isReached) {
            m_pendingReturn = 0;
        }
    }

    public void returnToEnd() {
        boolean isReached = _setAngle(MAX_ANGLE, m_mpu6050.getAngleX(), RETURN_SPEED);
        if (isReached) {
            m_pendingReturn = 0;
        }
    }

    private boolean _setAngle(double targetAngle, double currentAngle, double speed) {
        // hedefe ulaştıysak
        if (Math.abs(targetAngle - currentAngle) <= ANGLE_TOLERANCE) {
            System.out.println("[HORT] TARGET IS REACHED");
            return true;
        } else if (targetAngle < currentAngle) {
            m_motorController.set(-speed);
            return false;
        } else if (targetAngle > currentAngle) {
            m_motorController.set(speed);
            return false;
        } 

        System.out.println("UNKNOWN ERROR 1");
        return false;
    }

    public void setSpeed(double speed) {
        setSpeed(speed, m_mpu6050.getAngleX());
    }

    public void setSpeed(double speed, double currentAngle) {
        // eğer fonksiyon çağırılmadan önce yakın bir zamanda açı 
        // ölçülmüşse tekrar okumakla uğraşmamak için angle parametresi var

        // Başa dönme aşamasındaysak elleme
        if (m_pendingReturn == -1) {
            returnToStart(); 
            return;
        } else if (m_pendingReturn == 1) {
            returnToEnd();
            return;
        }

        // Açı çok geldiyse
        if (currentAngle >= MAX_ANGLE-MAX_ANGLE_TOLERANCE) {
            if (currentAngle >= MAX_ANGLE) {
                // shooterı başa döndür
                m_pendingReturn = -1;
                returnToStart();
            } else {
                m_motorController.set(speed/2);
            }
        }

        // Açı az geldiyse
        else if (MAX_ANGLE_TOLERANCE >= currentAngle) {
            m_motorController.set(speed/2);
            if (0 >= currentAngle) { 
                // shooterı sona döndür
                m_pendingReturn = 1;
                returnToEnd();
            } else {
                m_motorController.set(speed/2);
            }
        }
        
        // sorun yoksa 
        else {
            m_motorController.set(speed);
        }
    }

    public double getGyroAngle() {
        return m_mpu6050.getAngleX();
    }

    @Override
    public void periodic() {

    }

    @Override
    public void simulationPeriodic() {

    }

    public void configure() {

    }
}
