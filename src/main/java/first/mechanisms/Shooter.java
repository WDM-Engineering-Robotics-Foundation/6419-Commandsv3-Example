package first.mechanisms;

import org.wpilib.command3.Command;
import org.wpilib.command3.Mechanism;

import com.ctre.phoenix6.controls.CoastOut;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

public class Shooter implements Mechanism {
    
    private TalonFX leftMotor, rightMotor;

    public Shooter() {
        // init motors
    }

    public Command spinSetVelo(double velocity) {
        return runRepeatedly(() -> {
            leftMotor.setControl(new VelocityVoltage(velocity));
            rightMotor.setControl(new VelocityVoltage(velocity));
        }).named("ShooterSpinSetVelo(" + velocity + " rps)");
    }

    public Command stop() {
        return runRepeatedly(() -> {
            leftMotor.setControl(new CoastOut());
            rightMotor.setControl(new CoastOut());
        }).named("ShooterIdle");
    }

    public Command fireSetVelo(double velocity) {
        return run((coroutine) -> {
            // await() - runs a command and waits for it to finish
            // fork() - runs a command and does not wait for it to finish
            // awaitAll() = parallel group
            // awaitAny() = race group
        
            coroutine.fork(spinSetVelo(velocity));
            coroutine.waitUntil(() -> atSpeed(velocity));
            coroutine.awaitAll(
                Mechanisms.spindexer().spin(),
                Mechanisms.kicker().feed()
            );

        }).named("ShooterFireSetVelo(" + velocity + " rps)");
    }

    public boolean atSpeed(double target) {
        return true;
    }
    
}
