package ie.gmit;

import java.rmi.*;

public interface ServiceServer extends Remote {
	
	public Object[] getServiceList() throws RemoteException;
	public Service getService(Object serviceKey) throws RemoteException;

}
