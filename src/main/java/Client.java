import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
    public static void main(String args[]) throws IOException {
        Socket socket;
        PrintStream printStream = null;
        BufferedReader bufferedReader = null;
        try {
            socket = new Socket("localhost", 1500);
            printStream = new PrintStream(socket.getOutputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e ) {
            System.err.println("Unidentified hostname ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Could not get I/O ");
            System.exit(1);
        }

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        String fromServer = bufferedReader.readLine();
        System.out.println(fromServer);

        String message;
        while ((message = stdin.readLine()) != null) {
            printStream.println(message);
            System.out.println(bufferedReader.readLine());
            System.out.println(bufferedReader.readLine());
            if (message.equals("end")) {
                break;
            }
        }

        printStream.close();
        bufferedReader.close();
        stdin.close();
    }
}