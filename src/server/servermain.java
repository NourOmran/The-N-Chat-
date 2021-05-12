
package server;

/**
 *
 * @author nouro
 */
 
public class servermain {
    private int port=8090 ; 
    private Server server; 
    servermain(int port ){ 
        this.port=port; 
        server=new Server(port);
    }
    public static void main (String [] args){
        int port;
        port=8090;   
        new servermain(port);
}
}