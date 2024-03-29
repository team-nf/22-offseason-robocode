package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
// import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.ShooterHorizontal;

public class HorizontalAutoSteer extends CommandBase {
    private final ShooterHorizontal m_subsystem;

    public HorizontalAutoSteer(ShooterHorizontal subsystem) {
        m_subsystem = subsystem;

        addRequirements(m_subsystem);
    }

    @Override
    public void execute() {
        m_subsystem.autoSteer();
    }

    @Override 
    public boolean isFinished() {
        return true;
    }

    public void test() {
        m_subsystem.testSteer();
    }
}