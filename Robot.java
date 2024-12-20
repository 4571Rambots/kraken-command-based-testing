package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
    private RobotContainer robotContainer;
    private Command autonomousCommand;

    @Override
    public void robotInit() {
        robotContainer = new RobotContainer();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = robotContainer.getAutonomousCommand();
        if (autonomousCommand != null) autonomousCommand.schedule();
    }

    @Override
    public void autonomousPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    @Override
    public void teleopPeriodic() {
        CommandScheduler.getInstance().run();
        robotContainer.getTeleopCommand().schedule();
    }

    @Override
    public void disabledInit() {
        CommandScheduler.getInstance().cancelAll();
    }
}
