package Chat;
import java.rmi.*;
public interface ChatClient extends Remote {
public void printMessage(String message) throws RemoteException;
}