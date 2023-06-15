package AliAmrol.discord;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Locale;


/**
 * The type User handler.
 */
public class UserHandler implements Runnable {
    private Socket client;

    private ObjectOutputStream outputStream;

    private ObjectInputStream inputStream;

    private static ArrayList<UserHandler> clients = new ArrayList<>();

    private static DataBase dataBase;

    /**
     * Instantiates a new User handler.
     *
     * @param client the client
     * @throws IOException the io exception
     */
    public UserHandler(Socket client) throws IOException {
        this.client = client;
        outputStream = new ObjectOutputStream(client.getOutputStream());
        inputStream = new ObjectInputStream(client.getInputStream());
    }

    @Override
    public void run() {
        try {
            DataBase newDataBase;
            newDataBase = (DataBase) inputStream.readObject();
            updateList(newDataBase);
            sendDataBaseToClients();
            while (!client.isClosed()) {
                Object o = null;
                if(inputStream != null) {
                    o = inputStream.readObject();
                }
                if(o instanceof String){
                    String order = (String)o;
                    if (order.toLowerCase(Locale.ROOT).equals("#exit")) {
                        clients.remove(this);
                        client.close();
                    }
                    else {
                        if(order.toLowerCase(Locale.ROOT).equals("#delete account")){
                            clients.remove(this);
                            client.close();
                        }
                        else {
                            Object object = inputStream.readObject();
                            if(object instanceof DataBase){
                                newDataBase = (DataBase)object ;
                                updateList(newDataBase);
                                sendDataBaseToClients();
                            }
                        }
                    }
                }
            }
            client.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update list.
     *
     * @param updatedDataBase the updated data base
     */
    public synchronized void updateList(DataBase updatedDataBase){
        dataBase = updatedDataBase;
    }

    /**
     * Send data base to clients.
     *
     * @throws IOException the io exception
     */
    public synchronized void sendDataBaseToClients() throws IOException {
        for (UserHandler other : clients) {
            try {
                other.outputStream.writeUnshared(dataBase);
            } catch (IOException e) {
                //closeEverything(other.client, other.outputStream, other.inputStream);
            }
        }
    }

    /**
     * Remove user handler.
     */
    public void removeUserHandler() {
        clients.remove(this);
    }

    /**
     * Gets clients.
     *
     * @return the clients
     */
    public ArrayList<UserHandler> getClients() {
        return clients;
    }
}
