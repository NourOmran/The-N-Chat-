
package server;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nouro
 */
public class Server implements  Runnable{
    private List<serverclient> clients = new ArrayList<serverclient>();
    private boolean running=false;
    private Thread run ,manage ,send ,recive ;
    private DatagramSocket socket;
    private int port ;
 
   
    public Server (int port ){
        this.port=port;
        try {
            socket=new DatagramSocket(port);
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        run=new Thread(this,"server");
        run.start();
    }
                       
    @Override
    public void run() {
       running = true ; 
       System.out.println("server started");
       recive();
    }
                    
    private void recive(){ 
            recive=new Thread("recive"){
             public void run(){
                   while(running){
                         byte []  data = new byte[1024];//when recceving packets of data we are receving array of byte  
                        DatagramPacket packet = new DatagramPacket(data, data.length);
                        try {
                           socket.receive(packet);
                       } catch (IOException ex) {
                           Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                       }
                        String message = new String (packet.getData());
                        clients.add(new serverclient("nour",packet.getAddress(),packet.getPort()));
                        System.out.println(clients.get(0).address.toString()+":" +clients.get(0).port);
                        if (message.startsWith("$#$")){
                          Loadingchat load = new Loadingchat();
                          load.CreateFile("admin");
                        }
                        else {
                          Loadingchat load = new Loadingchat();
                             try {
                                 load.write("admin.txt",message);
                             } catch (IOException ex) {
                                 Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                             }
                        }
                        
                                             }
                }
       };
       recive.start();

    }
    private void send (final byte[]data, final InetAddress clientaddrss, final int port  ){ 
            send = new Thread("send"){
                public void run () {
                        //now we want to convert data to datagram packet
                        DatagramPacket packet = new DatagramPacket(data, data.length,clientaddrss,port);//
                    try {
                        socket.send(packet);
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     
                }
        };
         send.start();
       }
}
