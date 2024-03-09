package emergencyservice;

public interface EmergServiceInterface {
	public void SendNotification(String emrgType);
	public void ActivateEmergencyProtocol(String location);
	public void SwitchOffFireEmergencySystem();
	
}
