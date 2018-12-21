package server2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class Client {
    private static String ip = "localhost";
    private static int port = 1234;

    private static String run_command(String comm){
        try{
            Socket sock = new Socket(ip, port);
            try{
                PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                out.println(comm);
                return in.readLine();
            } catch (IOException e){
                e.printStackTrace();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return "false";
    }


    public static void main(String[] args) {
        System.out.println("type LOGIN \"user name\" \"password\"");
        System.out.println("type GET \"file name\"");
        System.out.println("type LS");

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String id = "";
        while(true){
            System.out.print(port + "@" + ip +" $ ");
            String in = null;
            try {
                in = input.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(Objects.equals(in, "exit"))
                break;
            else if(Objects.requireNonNull(in).startsWith("LOGIN")){
                String[] arg = in.split(" ");
                if (arg.length<3)
                    System.out.println("LOGIN ERROR");
                else{
                    String str = run_command(arg[0] + " " + arg[1] + ";" + arg[1].concat(arg[2]).hashCode());
                    if (str.equals("false")) {
                        System.out.println("LOGIN ERROR");
                    } else {
                        id = str;
                        System.out.println("LOGIN SUCCES");
                    }
                }

            }
            else if (in.startsWith("LOGOUT")){
                System.out.println(run_command("LOGOUT " + id));
            }
            else if (in.equals("LS")){
                System.out.println(run_command("LS "+id));
            }
            else if (in.startsWith("GET")){
                String[] arg = in.split(" ");
                if (arg.length<2) System.out.println("GET ERROR");
                else System.out.println(run_command("GET "+ id + " " + arg[1]));
            }
        }
    }


}
