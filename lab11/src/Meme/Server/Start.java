package Meme.Server;

import Meme.DataBase.DB;
import Meme.Serialize.Data;

import javax.imageio.ImageIO;
import java.awt.Image;

//import java.awt.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;



public class Start {

    public static DB db = new DB();


    public static void main(String[] args) throws IOException {

        while(true) {

            ServerSocket serverSocket = null;

            try {
                serverSocket = new ServerSocket(6666);
            } catch (IOException ex) {
                System.out.println("Can't setup server on this port number. ");
            }

            Socket socket = null;
            InputStream in = null;
            OutputStream out = null;

            try {
                socket = serverSocket.accept();
            } catch (IOException ex) {
                System.out.println("Can't accept client connection. ");
            }

            try {
                in = socket.getInputStream();
            } catch (IOException ex) {
                System.out.println("Can't get socket input stream. ");
            }

            try {
                out = new FileOutputStream("src/Meme/Serialize/tmp.jpg");
            } catch (FileNotFoundException ex) {
                System.out.println("File not found. ");
            }

            byte[] bytes = new byte[16 * 1024];

            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);                                           // wysylamy zdjecie
            }

            in.close();
            out.close();

            socket.close();


            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("Accept failed: 6666");
                System.exit(-1);
            }
            PrintWriter pr = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));


            String nazwa = br.readLine();                                          //  nazwa i napisy
            //System.out.println(br.readLine());                                 // zrobić jeszcze zadanie z testami
            //System.out.println(br.readLine());
            String up = br.readLine();
            String down = br.readLine();


            pr.close();
            in.close();
            clientSocket.close();
            serverSocket.close();

            BufferedImage img = ImageIO.read(new File("src/Meme/Serialize/tmp.jpg"));

           // BufferedImage buffimg = new BufferedImage((int)img.getWidth(), (int)img.getHeight(), BufferedImage.TYPE_INT_RGB);


            Graphics g = img.getGraphics();


            g.setFont(new Font("Arial", Font.BOLD,40));
            g.setColor(Color.red);

            g.drawString(up, 110, 80);
            g.drawString(down, 110, ( img.getHeight()-50));


            RenderedImage ri = img;

            File file = new File("src/Meme/Serialize/tmp.jpg");
            ImageIO.write(ri, "jpg", file);


            db.run(nazwa);


            // zadanie z testami


            // server powinien stworzyc mema i wyslac go do bazy danych - zdjęcia do bazy danych

            // klient może przeglądać memy z bazy danych


        }

    }
}

//public class Start {
//
//    public static void main(String[] args) throws IOException {
//        ServerSocket serverSocket = null;
//        try {
//            serverSocket = new ServerSocket(6666);
//        } catch (IOException e) {
//            System.out.println("Could not listen on port: 6666");
//            System.exit(-1);
//        }
//
//        Socket clientSocket = null;
//        try {
//            clientSocket = serverSocket.accept();
//        } catch (IOException e) {
//            System.out.println("Accept failed: 6666");
//            System.exit(-1);
//        }
//        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(
//                        clientSocket.getInputStream()));
//        String inputLine = "";
//
//        while (inputLine != null) {
//            inputLine = in.readLine();
//            //out.println(inputLine);
//            System.out.println(inputLine);
//        }
//
//        out.close();
//        in.close();
//        clientSocket.close();
//        serverSocket.close();
//
//    }
//}
