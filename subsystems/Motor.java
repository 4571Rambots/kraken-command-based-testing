package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Motor extends SubsystemBase {
    private final TalonFX krakenMotor;

    public Motor(int motorCANID) {
        krakenMotor = new TalonFX(motorCANID);
    }

    public void setMotorSpeed(double speed) {
        krakenMotor.setControl(new DutyCycleOut(speed));
    }

    public void stopMotor() {
        setMotorSpeed(0);
    }
}
