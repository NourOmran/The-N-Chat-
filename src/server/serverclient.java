
package server;
import java.net.InetAddress;
public class serverclient {
    public String name ; 
    public InetAddress address ;
    public int port; 

    public serverclient(String name, InetAddress address, int port ) {
        this.name = name;
        this.address = address;
        this.port = port;
        
    }
    
    
}
