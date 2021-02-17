import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server extends Thread {

    private ServerSocket serverSocket;

    public Server() {
        try {
            serverSocket = new ServerSocket(1500);
        } catch (IOException e) {
            System.out.println("Start server error: " + e);
        }
        System.out.println("Server starts...");
        this.start();
    }

    public void run() {
        try {
            while (true) {
                Socket client = this.serverSocket.accept();
                System.out.println("Connection accepted from " + client.getInetAddress().getHostAddress());
                Connection connection = new Connection(client);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new Server();
    }
}