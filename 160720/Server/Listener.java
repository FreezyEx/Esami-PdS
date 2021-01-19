import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.util.*;
import java.io.*;
import static java.lang.String.valueOf;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Listener implements ActionListener{

    private Gui mainFrame;
    private JTextField port;
    
    private Server server;
    private Scanner scan;
    private PrintWriter out;
    
    private Logger logger = Logger.getLogger("Listener");
    
    public Listener(Gui m,JTextField p){
        mainFrame = m;
        port = p;
    }
    
    public void actionPerformed(ActionEvent ae) {
        
        //apro 
        if(ae.getActionCommand().equals("START")){            
            try {
                System.out.println(Integer.parseInt(port.getText()));
                server = new Server(Integer.parseInt(port.getText()),mainFrame); 
                Thread serverThread = new Thread(server);
                serverThread.start();
                
                
            } catch (IOException ex) {
                logger.log(Level.WARNING,"server not open");
            }
            mainFrame.setChangePort(false);
            mainFrame.buttons(true);
            logger.log(Level.INFO,"server started");
        }
        else if(ae.getActionCommand().equals("CLOSE")){
            try {
                server.close();
            } catch (IOException ex) {
                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
            }
            mainFrame.setChangePort(true);
            mainFrame.buttons(false);
            logger.log(Level.INFO,"server is closed");
        }       
        
    }
    
}

