package first.mechanisms;

import org.wpilib.command3.Command;
import org.wpilib.command3.Mechanism;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;

import first.Constants.KickerConstants;

import static first.Constants.KickerConstants;

public class Kicker implements Mechanism {
    private TalonFX kickerMotor;
    
    Kicker() {
        kickerMotor = new TalonFX(KickerConstants.MOTOR_ID, new CANBus());
        setDefaultCommand(stop());
    }

    /**
     * runs the kicker so that it feeds fuel into the shooter
     */
    public Command feed() {
        return runRepeatedly(() -> {
            kickerMotor.setControl(new DutyCycleOut(KickerConstants.SPIN_SPEED));
        }).named("KickerFeed");
    }
    
    /**
     * sets the kicker to coast / 0 power
     */
    public Command stop() {
        return runRepeatedly(() -> {
            kickerMotor.setControl(new DutyCycleOut(0.0));
        }).named("KickerIdle");
    }
}
