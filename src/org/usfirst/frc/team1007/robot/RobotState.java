package org.usfirst.frc.team1007.robot;

//import org.usfirst.frc.team503.robot.subsystems.TurretSubsystem;
//import org.usfirst.frc.team503.turret.TurretThread;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Name:		RobotState 
 * Purpose:		
 * Author: 		Jyotsna Joshi
 * Date:		February 2017 
 * Comments:
 */
public class RobotState extends Subsystem {

    // Put methods for controlling this subsystem
	private boolean intakeIsRunning;
	private boolean currentDriveGear;
	private boolean indexerIsRunning;
	private boolean climberIsRunning;
	private boolean climberIsAccelerated;
	private boolean driveTrainIsReversed;
	private boolean gearPlacerBack;
	private boolean gearPlacerFront;
	private boolean climberIsRunningSlow;
	private State robotState;
	private TurretState turretState;
	private AllianceColor allianceColor;
	private Peg peg;
	private DoNothingAuton doNothingAuton;
	private DumpBin dumpBin;
	private boolean intakeGearRunning;
    private double gyroAngle;	
	
	
	public RobotState() {
		climberIsRunningSlow = false; 
		intakeIsRunning = false;
		currentDriveGear = false;    //low gear
		indexerIsRunning = false;
		climberIsRunning = false;
		climberIsAccelerated = false;
		driveTrainIsReversed = false;
		gearPlacerBack = false; //closed
		gearPlacerFront = false; //closed
		intakeGearRunning = false;
		robotState = State.DISABLED;
		allianceColor = AllianceColor.BLUE;
		peg = Peg.NONE;
		doNothingAuton = DoNothingAuton.DO_NOTHING;
		dumpBin = DumpBin.DONT_DUMP_BIN;
		gyroAngle = 0.0;		
	}	
	
	private static RobotState instance = new RobotState();
	
	public static RobotState getInstance(){
		return instance;
	}
	
	public enum State{
		DISABLED, AUTON, TELEOP, TEST, ENABLED;
	}
	
	public enum AllianceColor{
		RED, BLUE;
	}
	
	public AllianceColor getAllianceColorOption(){
		return allianceColor;
	}
	
	public void setAllianceColorOption(AllianceColor allianceColor){
		this.allianceColor = allianceColor;
	}
	
	public enum Peg{
		LEFT, RIGHT, CENTER, NONE;
	}
	
	public Peg getPegOption(){
		return peg;
	}
	
	public void setPegOption(Peg val){
		this.peg = val;
	}
	
	public enum DoNothingAuton{
		DO_NOTHING, DO_SOMETHING;
	}
	
	public DoNothingAuton getDoNothingOption(){
		return doNothingAuton;
	}
	
	public void setDoNothingAuton(DoNothingAuton val){
		this.doNothingAuton = val;
	}
	
	public enum DumpBin{
		DUMP_BIN, DONT_DUMP_BIN;
	}
	
	public DumpBin getDumpBinOption(){
		return dumpBin;
	}
	
	public void setDumpBinOption(DumpBin val){
		this.dumpBin = val;
	}
	
	public enum Shoot{
		SHOOT, DONT_SHOOT;
	}
	
	public enum TurretState{
		DISABLED, RESET_TURRET, SEEKING_TARGET, TARGET_FOUND, RUNNING_PID, ON_TARGET, TAKING_HINT;
	}
	
	public enum ShootingPresets{
		NoTracking(30.0, 4500, 503), Batter(13.0,3650,285.0), HopperRed(25.0, 4325, 3.0), CenterPegBlue(30.0,4600, 7.7), CenterPegRed(29.0, 4900, 224.0), PegNearHopperBlue(28, 4400, 275.0), PegNearHopperRed(26.0, 4500,299.0), FarPegBlue(34.0,5450,12.0), HopperBlue(23.0,3960,203.0), FarHopperBlue(28.0, 4500, 199.0), FarPegRed(34.0, 5450, 207.1);
		//pegnearhopperblue 272, 34, 4950
		//hopperred 34, 4400
		
		public double deflectorAngle;
		public int rpm;
		public double turretAngle; 
		private ShootingPresets(double deflectorAngle, int rpm, double turretAngle){
			this.deflectorAngle = deflectorAngle;
			this.rpm = rpm;
			this.turretAngle = turretAngle;
		}
		
	}
	
	public void setGyroAngle(double angle){
		gyroAngle = angle;
	}
	
	public double getGyroAngle(){
		return gyroAngle;
	}
	
	public void setGearIntakeRunning(boolean run){
		intakeGearRunning = run;
	}
	
	public boolean getGearIntakeRunning(){
		return intakeGearRunning;
	}
	
	public synchronized TurretState getTurretState(){
		return turretState;
	}
	
	public synchronized void setTurretState(TurretState state){
		System.out.println("Previous state: " + turretState.toString() + " Next state: " + state.toString());
		turretState = state;
	}
	
	public boolean getGearPlacerBack(){
		return gearPlacerBack;
	}
	
	public void setGearPlacerBack(boolean gearPlacer){
		this.gearPlacerBack = gearPlacer;
	}
	
	public boolean getGearPlacerFront(){
		return gearPlacerFront;
	}
	
	public boolean getClimberRunningSlow(){
		return climberIsRunningSlow;
	}
	
	public void setClimberRunningSlow(boolean climber){
		climberIsRunningSlow = climber;
	}
	
	public void setGearPlacerFront(boolean gearPlacer){
		this.gearPlacerFront = gearPlacer;
	}
	
	public State getState(){
		return robotState;
	}
	
	public void setState(State state){
		robotState = state;
	}
	
	public void setDriveTrainReversed(boolean reverse){
		driveTrainIsReversed = reverse;
	}
	
	public boolean getDriveTrainReversed(){
		return driveTrainIsReversed;
	}
	
	public void setClimberStatus(boolean status){
		climberIsRunning = status;
	}
	
	public void setClimberAccelerationStatus(boolean status){
		climberIsAccelerated = status;
	}
	
	public boolean getClimberStatus(){
		return climberIsRunning;
	}
	
	public boolean getClimberCanAccelerate(){
		return (!climberIsAccelerated);
	}
	
	public void setcurrentGear(boolean status) {
		currentDriveGear = status; 
	}
	
	public boolean getCurrentGear() {
		return currentDriveGear; 
	}
	
	public void setIntakeStatus(boolean status) {
		intakeIsRunning = status; 
	}
	
	public boolean getIntakeStatus() {
		return intakeIsRunning; 
	}
	
	public void setIndexerStatus(boolean status) {
		indexerIsRunning = status; 
	}
	
	public boolean getIndexerStatus() {
		return indexerIsRunning; 
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

