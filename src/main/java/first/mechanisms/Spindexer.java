package first.mechanisms;

import org.wpilib.command3.Command;
import org.wpilib.command3.Mechanism;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import first.Constants;

import static first.Constants.SpindexerConstants;

public class Spindexer implements Mechanism {
    private TalonFX spindexerMotor;

    Spindexer() {
        spindexerMotor = new TalonFX(SpindexerConstants.MOTOR_ID, new CANBus()); // TODO
        
        MotorOutputConfigs configs = new MotorOutputConfigs();
        // ternary
        // condition ? valueIfTrue : valueIfFalse;
        configs.Inverted = SpindexerConstants.REVERSED ? 
            InvertedValue.Clockwise_Positive : 
            InvertedValue.CounterClockwise_Positive;
        // runs when no other command is active
        setDefaultCommand(stop());
    }

    public Command spin() {
        return runRepeatedly(() -> {
            // duty cycle out = percent output
            spindexerMotor.setControl(new DutyCycleOut(SpindexerConstants.SPIN_SPEED)); // TODO
            
        }).named("SpindexerSpin");
    }

    public Command stop() {
        return runRepeatedly(() -> {
            // duty cycle out = percent output
            spindexerMotor.setControl(new DutyCycleOut(0.0));
            
        }).named("SpindexerIdle");
    }
}
