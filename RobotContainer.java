package frc.robot;

import frc.robot.subsystems.Motor;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
    private final Motor motorSubsystem = new Motor(Constants.KRACKEN_MOTOR_CAN_ID);
    private final XboxController xboxController = new XboxController(Constants.XBOX_CONTROLLER_PORT);

    public RobotContainer() {
        configureButtonBindings();
    }

    private void configureButtonBindings() {
        // Example binding: Press A button to stop the motor
        new JoystickButton(xboxController, XboxController.Button.kA.value)
            .onTrue(new InstantCommand(motorSubsystem::stopMotor, motorSubsystem));
    }

    public Command getTeleopCommand() {
        return new InstantCommand(() -> {
            double speed = -xboxController.getLeftY();
            if (Math.abs(speed) < Constants.DEADBAND_THRESHOLD) speed = 0;
            motorSubsystem.setMotorSpeed(speed * 0.1);
        }, motorSubsystem);
    }

    public Command getAutonomousCommand() {
        return new InstantCommand(() -> motorSubsystem.setMotorSpeed(Constants.AUTO_SPEED), motorSubsystem)
            .andThen(() -> motorSubsystem.stopMotor())
            .withTimeout(Constants.AUTO_DURATION);
    }
}
