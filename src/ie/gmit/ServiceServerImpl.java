package ie.gmit;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;
import java.rmi.*;
import java.util.*;

public class ServiceServerImpl extends UnicastRemoteObject implements ServiceServer, Serializable {

	private static final long serialVersionUID = 1L;
	HashMap serviceList;
	
	public ServiceServerImpl() throws RemoteException{
		setUpServices();
	}
	
	private void setUpServices(){
		serviceList = new HashMap();
		serviceList.put("Dice rolling Service", new DiceService());
		serviceList.put("Encryption Service", new EncryptionService());
		serviceList.put("Day of the Week Service", new DayOfTheWeekService());	
		serviceList.put("Compression Service", new CompressionService());		
	}
	
	public Object[] getServiceList() {
		System.out.println("in remote");
		return serviceList.keySet().toArray();
	}
	
	
	public Service getService(Object serviceKey) throws RemoteException{
		Service theService = (Service)serviceList.get(serviceKey);
		return theService;
	}
	
	
	
	public static void main(String[] args) {
		try{
			LocateRegistry.createRegistry(1099);
			Naming.bind("ServiceServer", new ServiceServerImpl());
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		System.out.println("remote service is running...");

	}

}
