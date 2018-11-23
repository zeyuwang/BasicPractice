package main.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class PalindromeClient {
    private static final String ENDPOINT = "palindromer-bd7e0fc867d57915.elb.us-east-1.amazonaws.com";
    private static final int PORT = 7777;
    private static final String SERVICE_RESPOND_PREFIX = "!!!";
    private static final String SUCCESS_CODE = "!!! flag";
    private static final String NEW_LINE_STRING = "\n";
    private static final String BLANK_STRING = " ";

    public static void main(String[] args) {
        runPalindromeClient();
    }

    public static void runPalindromeClient() {
        String lineFromServer;
        // Use try-with-resources statement to automatically close the socket and input/output stream.
        try (
            Socket clientSocket = new Socket(ENDPOINT, PORT);
            BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter backToServer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
        ) {
            lineFromServer = inputFromServer.readLine();
            while (lineFromServer != null) {
                if (lineFromServer.startsWith(SERVICE_RESPOND_PREFIX)) {
                    if (lineFromServer.startsWith(SUCCESS_CODE)) {
                        System.out.println("!!!Successfully find all palindromes from server.");
                    } else {
                        //We print out the error message from server and let the server to decide how to deal with the error case.
                        System.out.println("!!!Received error from server: " + lineFromServer);
                    }
                } else {
                    String[] words = lineFromServer.split(BLANK_STRING);
                    for (String word : words) {
                        if (isPalindrome(word)) {
                            backToServer.write(word);
                            backToServer.write(BLANK_STRING);
                        }
                    }

                    backToServer.write(NEW_LINE_STRING);
                    backToServer.flush();
                }
                lineFromServer = inputFromServer.readLine();
            }
        } catch (IOException e) {
            System.out.println("IOException happened when calling Palindrome Server");
        }
    }

    public static boolean isPalindrome(String word) {
        if (word == null) throw new IllegalArgumentException("Palindrome string can't be null.");
        int wordLength = word.length();
        if (wordLength == 0) throw new IllegalArgumentException("Palindrome string can't be empty.");
        int lastIndex = wordLength - 1;
        for (int i = 0; i < wordLength / 2; i++) {
            if (word.charAt(i) != word.charAt(lastIndex - i)) {
                return false;
            }
        }
        return true;
    }
}