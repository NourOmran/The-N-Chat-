/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author nouro
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements  Runnable {
	private static final long serialVersionUID = 1L;
	private String name, address;
	private int port;
	//think socket as power switch in order to conncet you have to turn it on so in order to connect to network you need to connect to sockect 
	private DatagramSocket socket;
	private InetAddress ip;
        private Thread send,listen,run; 
        private boolean running=false;
	public Client(String name, String address, int port) {
		this.name = name;
		this.address = address;
		this.port = port;
		boolean connect = openConnection(address);
		if (!connect) {
			System.err.println("Connection failed!");
			System.out.println("Connection failed!");
		}
	
               System.out.println("Attempting a connection to " + address + ":" + port + ", user: " + name);       
               run= new Thread(this,"Client");
               running=true;
               run.start();
	}

  
           @Override
      public void run() {
        System.out.println("tread");
        listen();
    }
	 
	private boolean openConnection(String address) {
		try {
                        
			//socket = new DatagramSocket();//Constructs a datagram socket and binds it to any available port on the local host machine.
			ip = InetAddress.getByName(address);//to convert string to ip adress
                        System.out.println(address);
                        socket = new DatagramSocket();
                         
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (SocketException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
        
        private void listen (){ 
            
            listen = new Thread("listen"){
            public void run () {
                while (running ){
                try {
                    String s=receive();
                    System.out.println("server :"+ s);
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            }
            };
            listen.start();
        }
        
        
        private String receive () throws IOException {
            //to recevive the data we need to make obj from DatagramPacket
            byte []  data = new byte[1024];//when recceving packets of data we are receving array of byte  
            DatagramPacket packet = new DatagramPacket(data, data.length);
            //now DatagramPacket create packet but packet now is empty to fill it with data we will do this next line ya m3lm 
            socket.receive(packet);
            String message = new String (packet.getData());//return array of byte
           // System.out.println(message);
            message=message.trim() ;
            return message;
            
        }
        
        public  void send (final byte[]data ){ 
            send = new Thread("send"){
                public void run () {
                    try {
                        //now we want to convert data to datagram packet
                       
                        
                        DatagramPacket packet = new DatagramPacket(data, data.length,ip,port);//at sending we need to the ip and the port we are sending to
                       // System.out.println("the connection condition is : "+socket.isConnected());
                        try {
                          socket.getBroadcast();  
                                }catch(SocketException e){
                                    System.out.println(e);
                        }
                      
                        //System.out.println("The socket is bounded: "+socket.isBound());  
                        socket.send(packet);
                             System.out.println(socket.getRemoteSocketAddress());  
                    } catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        };
         send.start();
       }
	void send(String message) {
		//if (message.equals("")) return;
		//message = name + ": " + message;
		//System.out.println(message);
		send(message.getBytes());
	}
        
       
}