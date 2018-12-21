package server2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Server extends Thread{

    private Map<String, String> pass = new HashMap<>();
    private int port;
    private ArrayList IDList= new ArrayList<>();
    private Random rand = new Random();

    private String getNextID(){
        String newID = null;
        while(IDList.contains(newID) || newID == null){
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<10; i++){
                sb.append(rand.nextInt(126));
            }
            newID = sb.toString();
        }
        IDList.add(newID);
        return newID;
    }

    public boolean removeID(String id){
        return IDList.remove(id);
    }

    public boolean checkID(String id){
        return IDList.contains(id);
    }

    private Server(int p){
        port = p;
        pass.put("admin", Integer.toString("admin".concat("nimda").hashCode()));
        pass.put("hubert", Integer.toString("hubert".concat("hk").hashCode()));
        pass.put("gosc", Integer.toString("gosc".concat("gg").hashCode()));
    }

    @Override
    public void run(){
        try{
            ServerSocket SS = new ServerSocket(port);
            System.out.println("Port: " + port);
            while(true){
                try{
                    Socket sock = SS.accept();
                    try{
                        PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                        String text = in.readLine();
                        out.println(command(text));
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                } catch (IOException e){
                    System.out.println("Not Accepted: " + port);
                }
            }
        } catch (IOException e) {
            System.out.println("Port error: " + port);
        }
    }

    private String command(String str){
        String[] div = str.split(" ");
        if(div.length<=1)
            return "false";
        if(div[0].equals("LOGIN")){
            String[] lp = div[1].split(";");
            if(lp.length <= 1)
                return "false";
            String psw = pass.get((lp[0]));
            if(psw != null && psw.equals(lp[1])){
                return getNextID();
            }
        } else if(div[0].equals("LOGOUT")){
            return Boolean.toString(removeID(div[1]));
        } else if (div[0].equals("LS") && checkID(div[1])){
            return "\"LS result\"";
        } else if (div[0].equals("GET") && checkID(div[1])){
            return "\"GET result\"";
        }
        return "false";
    }


        public static void main(String[] args) throws InterruptedException {
        Server server = new Server(1234);
        server.start();
        server.join();
    }
}
