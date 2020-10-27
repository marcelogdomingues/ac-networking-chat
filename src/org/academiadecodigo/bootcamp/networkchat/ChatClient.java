package org.academiadecodigo.bootcamp.networkchat;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    static String host;
    static String port;
    static Socket socket;

    public static void startServer(){

        getClientData();
        setupClient();
        while(true) {
            readMessageFromServer();
        }

    }

    public static void getClientData() {

        Scanner receiveHost = new Scanner(System.in);
        System.out.println("Host : ");

        Scanner receivePort = new Scanner(System.in);
        System.out.println("Port : ");

        host = receiveHost.nextLine();
        port = receivePort.nextLine();


        if (host != null && port != null) {

            System.out.println();
            System.out.println("=======");
            System.out.println();
            System.out.println("Host inserted is: " + host + " and Port inserted is: " + port);

        }
    }

    public static void setupClient(){

        try {

            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, Integer.parseInt(port));

        }catch (Exception e){

            System.out.println(e.getMessage());

        }

    }

    public static void readMessageFromServer(){

        //Get the return message from the server
        try {

            InputStream inputMessage = socket.getInputStream();

            InputStreamReader ireadMessage = new InputStreamReader(inputMessage);

            BufferedReader br = new BufferedReader(ireadMessage);

            System.out.println(socket);

            String message = br.readLine();

            System.out.println("Received from server: " + message + "\n");

        }catch (Exception e){

            System.out.println(e.getMessage());

        }
    }

/*    public static void sendMessage(){

        try {

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        }catch (IOException e){

        }


    }*/

    public static void main(String[] args) {

        startServer();


    }

}
