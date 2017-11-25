/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatorconsole;




import java.net.* ;
import java.awt.* ;
import java.awt.event.* ;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class clientUdp4 {
   
    
    JFrame frame = new JFrame() ;
	private final String WINDOW_TITLE = "CellPhone Simulation";
	private final int WINDOW_WIDTH = 200; 
	private final int WINDOW_HEIGHT = 300; 
        String input;
        String phoneNumber = new String();
	String client2Number = "5000" ;

	// The following named controls will appear in our GUI

    private JPanel textPanel = new JPanel();
    private JPanel digitPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();

    
    private JLabel displayLabel = new JLabel ("           OperatorConsole ");

    private JTextField displayTextField = new JTextField(20);
    
    private JButton send_RegisterButton = new JButton("Register");
    private JButton clear_InviteButton = new JButton("Invite");
    private JButton end_ReceivedButton = new JButton(" Recieved ");
    private JButton redial_EndButton = new JButton("End");
    private JButton clearButton = new JButton("Clear") ;
    private JButton Button1 = new JButton("1");
    private JButton Button2 = new JButton("2");
    private JButton Button3 = new JButton("3");
    private JButton Button4 = new JButton("4");
    private JButton Button5 = new JButton("5");
    private JButton Button6 = new JButton("6");
    private JButton Button7 = new JButton("7");
    private JButton Button8 = new JButton("8");
    private JButton Button9 = new JButton("9");
    private JButton Button0 = new JButton("0");
    private JButton astrButton = new JButton("*");
    private JButton boundButton = new JButton("#");
    
   
    
    Thread t1,t2 , t ;
     DatagramSocket sock1, sock2 ;
        int  clientPort = 5680 ;
   int client2Port = 5678 ;
   int serverPort = 5679 ;
   String clientAddress = "172.210.120.122" , client2Address = "13";

        
      
public static void main(String[]args)
{
    clientUdp4 client = new clientUdp4() ;
    client.doStuff(); 
   
    
}
    
    void doStuff()
    {
         Clientudp1 client1  = new Clientudp1() ;
     
         t = new Thread(client1); 
         t.start();    
         client1.GUI() ;     
    }     
       

   
            
             
       
        
      
    public class Clientudp1 implements Runnable{
  
    
   
    DatagramPacket inPacket , outPacket ;
    InetAddress serverAddress  ;
//   int  clientPort = 5680 ;
  // int client2Port = 5678 ;
   //int serverPort = 5679 ;
   //String clientAddress = "172.210.120.122" , client2Address = "13";
   int client1Number = 5001 ;
      String mess1 ;      
      
    int i = 1;
    
  
    public Clientudp1() {
    try {
               sock2 = new DatagramSocket(clientPort); //client port
           } catch (SocketException ex) {
               Logger.getLogger(Clientudp1.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    
    
    public void GUI() {
        
        frame.setTitle(WINDOW_TITLE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildTextPanel();
        buildButtonPanel();
        buildDigitPanel();
        frame.setLayout(new BorderLayout());
        frame.add(textPanel,BorderLayout.NORTH);
        frame.add(buttonPanel,BorderLayout.CENTER);
        frame.add(digitPanel,BorderLayout.SOUTH);
        clear_InviteDisplay();
         frame.setSize(200, 400);
        frame.setVisible(true);
        
        
        
        
        
        
        
        
        
        
       
        
       
    }
   
	public void buildTextPanel () {
		textPanel.setLayout(new BorderLayout());
		textPanel.add(displayLabel,BorderLayout.NORTH);
		textPanel.add(displayTextField,BorderLayout.CENTER);
                textPanel.setBackground(Color.RED);
	}


	public void buildButtonPanel () {
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(send_RegisterButton);
		buttonPanel.add(clear_InviteButton);
		buttonPanel.add(end_ReceivedButton);
                buttonPanel.add(redial_EndButton);
                buttonPanel.add(clearButton) ;
		send_RegisterButton.addActionListener(new Send_RegisterButtonListener());
		clear_InviteButton.addActionListener(new Clear_InviteButtonListener());
		end_ReceivedButton.addActionListener(new End_ReceivedButtonListener());
                redial_EndButton.addActionListener(new Redial_EndButtonListener());
                clearButton.addActionListener(new clearButtonListener()) ;
                buttonPanel.setBackground(Color.BLACK);
	
                end_ReceivedButton.setVisible(false) ;
        
        
        }

	public void buildDigitPanel () {
		digitPanel.setLayout(new GridLayout(4,3));
		digitPanel.add(Button1);
		digitPanel.add(Button2);
                digitPanel.add(Button3);
                digitPanel.add(Button4);
                digitPanel.add(Button5);
                digitPanel.add(Button6);
                digitPanel.add(Button7);
                digitPanel.add(Button8);
                digitPanel.add(Button9);
                digitPanel.add(astrButton);
                digitPanel.add(Button0);
                digitPanel.add(boundButton);

        
           InnerListener listener= new InnerListener();
                Button1.addActionListener(listener);
                Button2.addActionListener(listener);
                Button3.addActionListener(listener);
                Button4.addActionListener(listener);
                Button5.addActionListener(listener);
                Button6.addActionListener(listener);
                Button7.addActionListener(listener);
                Button8.addActionListener(listener);
                Button9.addActionListener(listener);
                Button0.addActionListener(listener);
                astrButton.addActionListener(listener);
                boundButton.addActionListener(listener);
                
        
        }

    
    
    
    
    
    
    
    
    
  
    public String Call_ID(){
        double id = Math.random();
      String str = ""+id;
      
      return str;
        
    }
    
    
    public String Gen_Tag()
    {
      double id = Math.random();
      String str = ""+id;
      
      return str;
        
        
    }
    
    public String RegisterToServer(){
        
        String str = "REGISTER sip:"+serverAddress+"SIP/2.0\r\n" ;
        str = str + "Call-ID: "+clientAddress+"-"+Call_ID()+"\r\n";
        str = str + "CSeq:"+ i++ +"REGISTER\r\n" ;
        str = str + "From: <sip:"+client1Number+"@1"+serverAddress+">;tag ="+ Gen_Tag() +"\r\n" ;
        str = str + "To: <sip:"+client1Number+"@"+serverAddress+">\r\n" ;
        str = str + "Via: SIP/2.0/UDP"+clientAddress+":"+clientPort+";branch=z9hG4f564515646\r\n" ;
        str = str + "Max-Forwards: 1\r\n" ;
        str = str+  "Contact: <sip:"+client1Number+"@"+clientAddress+":"+clientPort+">\r\n" ;
        
        return str;
        
    }
    
    public String InviteToClient()
    {
        client2Number = phoneNumber;
        String str =  "INVITE   sip:"+client2Number+"@"+serverAddress+":5678 SIP/2.0\r\n" ;
         str += "Call-ID:" +clientAddress+"-"+Call_ID()+"\r\n";
        str += "CSeq: 8 INVITE\r\n" ;
        str += "From: <sip:"+client1Number+"@"+serverAddress+">;tag="+Gen_Tag()+"\r\n" ;
        str += "To: <sip:"+client2Number+"@"+serverAddress+">\r\n" ;
        str += "Via: SIP/2.0/UDP "+clientAddress+":"+clientPort+";branch=z9hG4bK75964856286179\r\n" ;
        str += "Max-Forwards: 1\r\n" ;
        str += "Contact: <sip:"+client1Number+"@"+clientAddress+":"+clientPort+">\r\n" ;
     return str ;
        
    }
   
    public void Register_Server()
    {
        
       
        String str = RegisterToServer();
        
       
         byte [] data = str.getBytes() ;
         try {
             sock1 = new DatagramSocket() ;         
             serverAddress = InetAddress.getLoopbackAddress();          
             outPacket = new DatagramPacket(data, 0, data.length, serverAddress, serverPort) ;              
            sock1.send(outPacket);
              
             
         }catch(Exception ex) 
         {
         }
    }
     public void Invite_Client()
    {
        
       
        String str = InviteToClient();
        
       client2Number = phoneNumber;
         byte [] data = str.getBytes() ;
         try {
             sock1 = new DatagramSocket() ;         
             serverAddress = InetAddress.getLoopbackAddress();          
             outPacket = new DatagramPacket(data, 0, data.length, serverAddress, serverPort) ;              
            sock1.send(outPacket);
              
             
         }catch(Exception ex) 
         {
         }
    }
     public void Trying_Packet() 
     {
         String s1 = "Status-Line: SIP/2.0 100 TRYING\r\n"  ;
         
         String [] s = mess1.split("\r\n") ;
         int len = s.length - 1 ;
         int i ;
         for( i = 1 ; i < len ; i++)
         {
            s1 += s[i] +"\r\n";
         }
         
         byte [] data = s1.getBytes() ;
         try {
             sock1 = new DatagramSocket() ;         
             serverAddress = InetAddress.getLoopbackAddress();          
             outPacket = new DatagramPacket(data, 0, data.length, serverAddress, serverPort) ;              
            sock1.send(outPacket);
              
             
         }catch(Exception ex) 
         {
         }
         
     }
     public void Ringing_Packet()
     {
         
        String s1 = "Status-Line: SIP/2.0 180 RINGING\r\n"  ;
       
        String s2 = InviteToClient() ;
        
        String s[] = s2.split("\r\n") ;
        int len = s.length - 1 ,  i  ;
        for( i = 1 ; i < len ; i++)
        {
            s1 += s[i]+"\r\n" ;
        }
         byte [] data = s1.getBytes() ;
         try {
             sock1 = new DatagramSocket() ;         
             serverAddress = InetAddress.getLoopbackAddress();          
             outPacket = new DatagramPacket(data, 0, data.length, serverAddress, serverPort) ;              
            sock1.send(outPacket);
              
             
         }catch(Exception ex) 
         {
         } 
       
     
         
         
         
     }
     public void Received_Packet()
     {
         String s1 = "SIP\\2.0\\ 200 OK\r\n" ;
        
           String s2 = InviteToClient() ;
        
        String s[] = s2.split("\r\n") ;
        int len = s.length - 1 ,  i  ;
        for( i = 1 ; i < len ; i++)
        {
            s1 += s[i]+"\r\n" ;
        }
         byte [] data = s1.getBytes() ;
         try {
             sock1 = new DatagramSocket() ;         
             serverAddress = InetAddress.getLoopbackAddress();          
             outPacket = new DatagramPacket(data, 0, data.length, serverAddress, serverPort) ;              
            sock1.send(outPacket);
              
             
         }catch(Exception ex) 
         {
         } 
       
         
         
       
     }

    @Override
    public void run() {
        mess1 = "\0";
       i = 0 ;
       while(true)
       {
         //  System.out.println("Recieve") ;
           
           try {   
               Thread.sleep(1000);
           } catch (InterruptedException ex) {
               Logger.getLogger(Clientudp1.class.getName()).log(Level.SEVERE, null, ex);
           }
           byte[] receive = new byte[1000];
           try {
                //System.out.println("clientPort" +clientPort) ;
               inPacket = new DatagramPacket(receive,receive.length) ;
               sock2.receive(inPacket);
               // System.out.println("Received") ;
               
              
               mess1 = new String(inPacket.getData()) ;
              
              
              System.out.println(mess1) ;
           } catch (IOException ex) {
               Logger.getLogger(Clientudp1.class.getName()).log(Level.SEVERE, null, ex);
           }
                     String s[] = mess1.split("\r\n") ;
      
           if(s[0].contains("200 OK"))
           {int N1  = 0;
            String s2 = "" ;
               String str = "ACK sip:5007@172.210.140.51 SIP/2.0\r\n" ;
                 int len = s.length - 2 ;
               i = 1 ;
               while(i <= len)
               {
                
                   if(s[i].contains("From"))
                   {
                       s2 = s[i].substring(11,15) ;
                       
                       
                       
                   }
                   i++ ;
               }
               
             
               if(s2.equals(client2Number))
               
               
               {
               
               
               for(i = 1 ; i <  (s.length -1) ; i++)
               {
                   str += s[i]+"\r\n" ;
               }
   
           
          
          
              
          byte[] data = str.getBytes();
              serverAddress = InetAddress.getLoopbackAddress(); 
              outPacket = new DatagramPacket(data, 0, data.length, serverAddress, serverPort) ; 
               try {      
                   sock1.send(outPacket);
               } catch (IOException ex) {
                   Logger.getLogger(Clientudp1.class.getName()).log(Level.SEVERE, null, ex);
               }
             
                displayTextField.setText("Call Received...") ;
               openRTPLine();
          
               t.stop();
               
               }       

           }
           if(s[0].contains("RINGING"))
           {
                displayTextField.setText("180 RINGING...") ;
                 end_ReceivedButton.setVisible(true) ;
                               
           }
           if(s[0].contains("INVITE"))
           {
               Ringing_Packet() ;
               displayTextField.setText("180 Ringing...");
               
               
                end_ReceivedButton.setVisible(true) ;
               
           }
           
       }
    
    
    }
     public void openRTPLine()
    {
         Sound son = new Sound();
    
        son.openSrcDataLine();
         t1 = new Thread(son);
        t1.start();
         t2 = new Thread(son);
        t2.start();
      
        

        
    }
   
    
	private class Send_RegisterButtonListener implements ActionListener {

            
      	public void actionPerformed(ActionEvent e) {

                Register_Server() ;
                displayTextField.setText("Registering..") ;  
		}
	}


	private class Clear_InviteButtonListener implements ActionListener {


      	public void actionPerformed(ActionEvent e) {
  
            
	       	Invite_Client() ;
                System.out.println("Client2Number"+client2Number);
                displayTextField.setText("Trying 100...") ;
		}
	}
        private class clearButtonListener implements ActionListener {


      	public void actionPerformed(ActionEvent e) {
  
            
	   phoneNumber = "" ;
                displayTextField.setText("") ;
		}
	}


	private class End_ReceivedButtonListener implements ActionListener {

      	public void actionPerformed(ActionEvent e) {

               
                Received_Packet() ;
               displayTextField.setText("Call Received");
        }
	}

        private class Redial_EndButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
                  
                  t1.stop();
                  t2.stop();
                  
                  
                  displayTextField.setText("Call Ended") ;         
        }
        }

   	

   	public void clear_InviteDisplay() {
		displayTextField.setText(" ");
		
	}
   	
 
        
    
      
        
        private class InnerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
                  if(e.getSource()==Button1) {
                      phoneNumber += "1" ;                 
                      displayTextField.setText(phoneNumber); 
                    
                  }
                   else if (e.getSource()==Button2) {
                     phoneNumber += "2" ;                 
                      displayTextField.setText(phoneNumber); 
                    
                     
                   }
                   else if (e.getSource()==Button3) {
                   phoneNumber += "3" ;                 
                      displayTextField.setText(phoneNumber); 
                    
                   }
                   else if (e.getSource()==Button4) {
                   phoneNumber += "4" ;                 
                      displayTextField.setText(phoneNumber); 
                    
                   
                   
                   }
                   else if (e.getSource()==Button5) {
                   phoneNumber += "5" ;                 
                      displayTextField.setText(phoneNumber); 
                    
                   
                   }
                   else if (e.getSource()==Button6) {
                   phoneNumber += "6" ;                 
                      displayTextField.setText(phoneNumber); 
                    
                   
                   }
                   else if (e.getSource()==Button7) {
                   phoneNumber += "7" ;                 
                      displayTextField.setText(phoneNumber); 
                    
                   
                   }
                   else if (e.getSource()==Button8) {
                   phoneNumber += "8" ;                 
                      displayTextField.setText(phoneNumber); 
                    
                   
                   }
                   else if (e.getSource()==Button9) {
                   phoneNumber += "9" ;                 
                      displayTextField.setText(phoneNumber); 
                    
                   }
                   else if (e.getSource()==Button0) {
                        phoneNumber += "0" ;                 
                      displayTextField.setText(phoneNumber); 
                    
                   }
                   else if (e.getSource()==astrButton) {
                    // displayTextField.setText("*"); 
                   }
                   else if (e.getSource()==boundButton) {
                    // displayTextField.setText("#"); 
                   }
                   
         }       
    
    
    
    
    
        }
   
    
    }
   
    
    public class Sound implements Runnable {

    float sampleRate = 8000;
    int sampleSizeInBits = 8;
    int channels = 1;
    boolean signed = true;
    boolean bigEndian = false;
    File file;
    AudioInputStream ais;
    TargetDataLine line;
    SourceDataLine srcLine;
    DatagramSocket sock;
    DatagramPacket outPacket, inPacket;
   
    InetAddress clientSocket;
    AudioFormat format;
  
   

    public void openSrcDataLine() {
      // clientPort = 5060;
     //   System.out.println("CLient1Port"+client1Port) ;
/*
     try {
            sock = new DatagramSocket(clientPort);

        } catch (SocketException ex) {
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
        }
  */      
    
    }

    @Override
    public void run() {
        if (Thread.currentThread() == t1) {
           
 
                //     file = new File("C:\\Users\\vccs\\Desktop\\Audio.wav");
             format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
                DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
                try {
                    line = (TargetDataLine) AudioSystem.getLine(info);

                    line.open(format);
                    line.start();

                    int numBytesRead;

                    byte[] buff1 = new byte[12];
                    byte[] buff2 = new byte[172];
                    byte[] buff = new byte[172];
                    while (true) {
                     
                        numBytesRead = line.read(buff, 0, buff.length);
                        System.out.println("Bytes Read:" + numBytesRead);

                      System.out.println("Buffer: " + buff);
                        try {

                            outPacket = new DatagramPacket(buff, 0, buff.length, InetAddress.getLoopbackAddress(), clientPort);
                            sock2.send(outPacket);

                        } catch (Exception ex) {
                        }

                    }

                } catch (LineUnavailableException ex) {
                    Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        } else {
             try {
            format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            srcLine = (SourceDataLine) AudioSystem.getLine(info);
            srcLine.open(format);
            srcLine.start();

        } catch (LineUnavailableException ex) {
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            while (true) {
                byte[] receive = new byte[172];

                inPacket = new DatagramPacket(receive, receive.length);
                try {
                    sock2.receive(inPacket);
                } catch (IOException ex) {
                    Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Hello");
                for (int i = 0; i < receive.length; i++) {
                    System.out.print(receive[i] + " ");

                }
                ByteArrayInputStream bals = new ByteArrayInputStream(receive);
                ais = new AudioInputStream(bals, format, receive.length);

                System.out.println(" ");
                srcLine.write(receive, 0, receive.length);
            }
        }
    }
}

    
    
    
}
