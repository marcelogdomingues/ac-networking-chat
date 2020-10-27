package org.academiadecodigo.bootcamp.networkchat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {

    static String port;
    private static Socket socket;

    public static void start() {

        getServerData();

        serverListener();

        serverSetup();

        while (socket.isConnected()) {
            readMessageFromClient();
            sendMessageToClient();
        }

        stop();
    }

    public static void stop() {

        try{

            socket.close();

        }catch (Exception e){

            System.out.println(e.getMessage());

        }finally {

            System.exit(0);
        }

    }

    public static void getServerData() {

        Scanner receiveData = new Scanner(System.in);
        System.out.println("Port: ");

        port = receiveData.nextLine();

        if (port != null) {

            System.out.println();
            System.out.println("=======");
            System.out.println();
            System.out.println("Port inserted is: " + port);

        }
    }

    public static void serverListener() {

        try {

            int port = Integer.parseInt(getPort());

            ServerSocket serverSocket = new ServerSocket(port);

            socket = serverSocket.accept();

            System.out.println("Server Started and listening to the port " + port);

        }catch (Exception e){

            System.out.println(e.getMessage());

        }
    }

    public static void readMessageFromClient(){

        //Reads the message from the client
        try {

            InputStream inputMessage = socket.getInputStream();

            InputStreamReader ireadMessage = new InputStreamReader(inputMessage);

            BufferedReader readMessage = new BufferedReader(ireadMessage);

            String message = readMessage.readLine();

            System.out.println("Received from client: " + message + "\n");


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void sendMessageToClient(){

        try {

            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String message = bufferRead.readLine();

            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter writeOutputStream = new OutputStreamWriter(outputStream);
            BufferedWriter writeMessage = new BufferedWriter(writeOutputStream);
            writeMessage.write(message);
            writeMessage.flush();

            System.out.println("Sent message to " + socket + "client: " + message + "\n");

        }catch (Exception e){

            System.err.println(e.getMessage());
            e.printStackTrace();

        }

    }

    public static void serverSetup(){

        try{

            //PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        }catch (Exception e ){

            System.out.println(e.getMessage());

        }


    }

    public static String getPort() {
        return port;
    }

    public static void main(String[] args) {

        start();


    }

}
