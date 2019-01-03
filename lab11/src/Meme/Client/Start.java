package Meme.Client;


import Meme.Serialize.Data;

import java.io.*;
import java.net.*;



public class Start {
    public static void run(Data dt) throws IOException {
        Socket socket = null;
        String host = "localhost";

        socket = new Socket(host, 6666);

        File file = new File(dt.getPath());
        // Get the size of the file
        long length = file.length();
        byte[] bytes = new byte[16 * 1024];
        InputStream in = new FileInputStream(file);
        OutputStream out = socket.getOutputStream();

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }



        out.close();
        in.close();
        socket.close();



        Socket echoSocket = null;
        PrintWriter pw = null;
        BufferedReader br = null;

        try {
            echoSocket = new Socket("localhost", 6666);
            pw = new PrintWriter(echoSocket.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(
                    echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: localhost.");
            System.exit(1);
        }

        pw.println(dt.getName());
        pw.println(dt.getUpString());
        pw.println(dt.getDownString());

        br.close();
        pw.close();
        echoSocket.close();

    }
}


//public class Start {
//    public static void run() throws IOException {
//
//        Socket echoSocket = null;
//        PrintWriter out = null;
//        BufferedReader in = null;
//
//        try {
//            echoSocket = new Socket("localhost", 6666);
//            out = new PrintWriter(echoSocket.getOutputStream(), true);
//            in = new BufferedReader(new InputStreamReader(
//                    echoSocket.getInputStream()));
//        } catch (UnknownHostException e) {
//            System.err.println("Don't know about host: localhost.");
//            System.exit(1);
//        } catch (IOException e) {
//            System.err.println("Couldn't get I/O for "
//                    + "the connection to: localhost.");
//            System.exit(1);
//        }
//
//        BufferedReader stdIn = new BufferedReader(
//                new FileReader("src/Meme/Serialize/tmp.txt"));
//
//        out.println(stdIn.toString());
//        out.println("cos jest");
////        System.out.println(stdIn.toString());
////        System.out.println("cos moze byc");
//
//
//
//        //  zrobić out.println(
//        // albo client  --plik-->  server
//
////        String userInput;
//
////        System.out.println("Type a message: ");
////        while ((userInput = stdIn.readLine()) != null) {
////            out.println(userInput);
////            System.out.println("echo: " + in.readLine());
////        }
//
//        out.close();
//        in.close();
//        stdIn.close();
//        echoSocket.close();
//    }
//}
