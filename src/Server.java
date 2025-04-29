import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Server {

    public static void main(String... args) {

        ServerSocket serverSocket = null;
        Socket socket = null;

        String serverName = "Server of Rahul Krishna";

        try {
            // open a server on port 5001 and listen for client connections
            serverSocket = new ServerSocket(5001);

            System.out.println("Server started on port 5001");

            boolean flag = true;

            // continuously accept clients
            while(flag) {
                System.out.println("---\nListening for new client...");

                // blocking; listens for a client connection
                socket = serverSocket.accept();

                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                // receive clientName from client
                String clientName = dis.readUTF();
                // receive clientNumber from client
                int clientNumber = Integer.parseInt(dis.readUTF());

                // display client and server name
                System.out.println(clientName + " connected to " + serverName);
                // display clients number
                System.out.println("Client's selected integer: " + clientNumber);

                int serverNumber;

                // terminate after receiving an integer value that is out of range
                if(clientNumber < 1 || clientNumber > 100) {
                    // sets sentinel value to prompt socket closure
                    serverNumber = -1;
                    flag = false;
                } else {
                    // generate random integer between 1-100
                    serverNumber = (int) ((Math.random() * 99) + 1);
                    // display random integer
                    System.out.println("Server's random integer: " + serverNumber);

                    // compute and display the sum of server + client integer
                    System.out.println("The sum of the server and client integers is: " + (clientNumber + serverNumber));
                }

                // send serverName to client
                dos.writeUTF(serverName);
                // send serverNumber to client
                dos.writeUTF("" + serverNumber);

                System.out.println("Closing connection with " + clientName);
            }

            // close server and socket
            serverSocket.close();
            socket.close();
            System.out.println("Closing " + serverName);
            // fall off stack
        } catch(Exception e) {
            //e.printStackTrace();
        }

    }

}
