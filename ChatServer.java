package Chat;
import java.rmi.*;
public interface ChatServer extends Remote {
public void registerClient(String host, int port, String registryName) 
throws RemoteException;
public void chat(String message) throws RemoteException;
}
