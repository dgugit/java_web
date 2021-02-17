import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class Connection extends Thread {
    protected Socket socket;
    protected BufferedReader fromClient;
    protected PrintStream toClient;
    protected List<String> usedWords;

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
        String clientMessage;
        try {
            while (true) {
                toClient.println("Your word: ");
                clientMessage = fromClient.readLine();
                if (clientMessage == null || clientMessage.equals("end")) {
                    return;
                } else {
                    toClient.println(processMessage(clientMessage));
                }
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
        String s = message.replaceAll("\\s","");
        String result = "My word: ";
        Character last_character = s.charAt(s.length() - 1);
        String word = "";

        try {
            CSVReader csvReader = new CSVReader(new FileReader("src/main/java/words.csv"));
            String[] csvrow = null;
            while ((csvrow = csvReader.readNext()) != null) {
                if(csvrow[0].charAt(0) == last_character)
                {
                    System.out.println("word --- " + csvrow[0]  + " --- character --- "+ last_character );
                    if(!usedWords.contains(csvrow[0])) {
                        word = csvrow[0];
                        usedWords.add(csvrow[0]);
                        break;
                    }
                }

            }
        }catch (Exception e)
        {
            System.out.println(e);
        }


        return word;
    }

    public boolean processWord(String word) {
        char[] arr = word.toCharArray();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                return true;
            }
        }
        return false;
    }
}