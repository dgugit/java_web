import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class Connection extends Thread {
    protected Socket socket;
    protected BufferedReader fromClient;
    protected PrintStream toClient;
    protected List<String> usedWords;
    protected Character appLastCharacter = '-';

    public Connection(Socket socket) {
        this.socket = socket;
        this.usedWords = new ArrayList<String>();
        try {
            fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            toClient = new PrintStream(socket.getOutputStream());

        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println("Unable to set up streams: " + ex);
                return;
            }
            e.printStackTrace();
        }
        this.start();
    }

    public void run() {
        String clientMessage, result;
        try {
            while (true) {
                toClient.println("Your word: ");
                this.socket.setSoTimeout(20000);
                clientMessage = fromClient.readLine();
                if (clientMessage == null || clientMessage.equals("end")) {
                    return;
                } else {

                    result = processMessage(clientMessage);
                    if (result == "key0") {
                        toClient.println("You LOSE (incorrect first letter)");
                        this.socket.close();
                    }
                    if (result == "key1") {
                        toClient.println("You LOSE (already used word)");
                        this.socket.close();
                    } else {
                        toClient.println(result);

                    }

                }
            }
        } catch (SocketTimeoutException soe) {
            toClient.println("You waste your time. YOU LOSE");
            try {
                socket.close();
            } catch (IOException e) {
            }
        } catch (IOException e) {
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }

    public String processMessage(String message) {
        String s = message.replaceAll("\\s", "");
        String result = "My word: ";
        Character last_character = s.charAt(s.length() - 1);
        String word = "";

        try {
            CSVReader csvReader = new CSVReader(new FileReader("src/main/java/words.csv"));
            String[] csvrow = null;
            if (usedWords.contains(s)) {
                return "key1";
            }
            usedWords.add(s);
            System.out.println("last app character - |" + appLastCharacter + "| user first letter |" + s.charAt(0));

            if (appLastCharacter == '-' || (s.charAt(0) == appLastCharacter && appLastCharacter != '-')) {
                while ((csvrow = csvReader.readNext()) != null) {
                    if (csvrow[0].charAt(0) == last_character) {
                        System.out.println("used :" + usedWords);
                        System.out.println("word --- " + csvrow[0] + " --- character --- " + last_character);
                        if (!usedWords.contains(csvrow[0])) {

                            word = csvrow[0];
                            appLastCharacter = word.charAt(word.length() - 1);
                            usedWords.add(csvrow[0]);
                            return word;
                        }
                    }

                }
            } else {
                return "key0";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return word;

    }
}