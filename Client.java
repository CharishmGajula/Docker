import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Server address
        int port = 8080; // Port the server is listening on
        
        try (Socket socket = new Socket(serverAddress, port)) {
            System.out.println("Connected to server at " + serverAddress + ":" + port);

            // Input and Output Streams for sending to and receiving from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Sending a message to the server
            out.println("Hello Server!");
            System.out.println("Server says: " + in.readLine());

            // Sending an exit message
            out.println("exit");
            System.out.println("Server says: " + in.readLine());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
