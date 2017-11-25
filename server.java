/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatorconsole ;

/**
 *
 * @author Mayank Gupta
 */


import java.io.IOException;
 import java.net.* ;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class server2  {
    
   String HEADER ;
   int CSeq = 0 ;
   final String status = "Registerd" ; 
   Reginfo info ;
   //ArrayList list ;
   DatagramSocket sock1,sock2 ;
   DatagramPacket p,q ;
   String message ;
   ArrayList<Reginfo> regis ;
   InetAddress clientAddress , serverAddress;
   String client1Port, client2Port, client1Number, client2Number , client1Address, client2Address;
   String IP1, IP2 ;
    byte [] hostIp ;
     int a , b ;
 
 final int port = 5679 ;
 
  server2() throws SocketException 
  {
     sock1 = new DatagramSocket(port) ;
      regis = new ArrayList<>() ;
    
//     sock2 = new DatagramSocket();
  }
    
    
   
   public void go() 
   {
       byte[] receive = new byte[600];
      while(true) 
      {
         
          
          p = new DatagramPacket(receive, receive.length) ;
           try {
               sock1.receive(p);
           } catch (IOException ex) {
               Logger.getLogger(server2.class.getName()).log(Level.SEVERE, null, ex);
           }
          String msg = new String(p.getData()) ;
         System.out.println(msg) ;
          
          
           try {
               Parsemess(msg) ;
           } catch (IOException ex) {
               Logger.getLogger(server2.class.getName()).log(Level.SEVERE, null, ex);
           }
          
      }
   }
    public void Parsemess(String mes) throws IOException 
    {  
        info = new Reginfo() ; 
       
        String s[] = mes.split("\r\n");
        
        String s2  = s[0].substring(0,8).trim();
        int len  = s.length-2;
        int i=0;
        HEADER = "SIP\\2.0\\ 200 OK\r\n";
        if(s2.equals("REGISTER")){
            while(i<=len){
                if(s[i].contains("Contact"))
                {
                    String s4 = s[i].substring(14, 39).trim() ;
                    String [] s5 = s4.split("@") ;
                    String [] s6 = s5[1].split(":") ;
                    
                   info.regNumber = s5[0] ; 
                   info.regIp = s6[0] ;
                   info.regPort = s6[1] ;
                   
                   client1Address = info.regIp ;
                    regis.add(info) ;
             
               
                     for(Reginfo info1 : regis) {
                        System.out.println(info1.getRegPort()+" "+info1.getRegIp()+ " "+info1.getRegNumber());
                    }
                       
                    
                         }
                     i++ ;     
                    }
           
                        String s6 = HEADER ;
                       for(i = 1 ; i <= len ; i++)
                           s6 += s[i] + "\r\n" ;
           
                        byte[] data = s6.getBytes();
                        clientAddress = InetAddress.getLoopbackAddress() ;
                      //  clientAddress = InetAddress.getByName(client1Address) ;
                         q = new DatagramPacket(data,data.length, clientAddress, Integer.parseInt(info.regPort)) ; //client port
                          sock1.send(q);
                }
        else if(s2.equals("INVITE"))
              {
                   a = 0 ;
                   b  = 0 ;
                   byte[] data ;
                   while(i<=len){
                    
                   if(s[i].contains("From"))
                   {
                       String s4 = s[i].substring(11, 15).trim() ;
                       client1Number = s4 ;
                   
                      for(Reginfo info4 : regis)
                      {
                          if(info4.getRegNumber().equals(client1Number))
                              client1Port = info4.getRegPort() ;
                          b = 1 ;
                      }
                      if(b == 0)
                      {
                        System.out.println("Client is not Registerd") ;
                          break ;
                      }
                   
                   }
                       
               if(s[i].contains("To"))
                {
                    String s4 = s[i].substring(9, 13).trim() ;
                    
                    client2Number = s4 ;
                 //   System.out.println("   CP:   "+client1Port);
                    int a = 0 ;
                    
                    
                     String s11 = "Status-Line: SIP/2.0 100 TRYING\r\n" ;
                    //Trying 100 Packet
                     for(i = 1 ; i < len ; i++)
                    {
                        s11 += s[i] + "\r\n" ;
                    }
                       data = s11.getBytes();
                      q = new DatagramPacket(data,data.length, InetAddress.getLoopbackAddress(), Integer.parseInt(client1Port)) ;
                     sock1.send(q);
                      
                    
                    
                   for(Reginfo info1 : regis)
                   {  String s10 = s[0]+"\r\n" ;
                     //  System.out.println("Client2Number"+info1.getRegNumber()) ;
                       if(info1.getRegNumber().equals(client2Number))
                       {
                           client2Port = info1.getRegPort() ;
                           client2Address = info1.getRegIp() ;
                            for(i = 1 ; i < len ; i++)
                             {
                              s10 += s[i] + "\r\n" ;
                               }
                      data = s10.getBytes();
                      q = new DatagramPacket(data,data.length, InetAddress.getLoopbackAddress(), Integer.parseInt(client2Port)) ;
                     sock1.send(q);
                  
                          // System.out.println("Client2Port "+client2Port+"Client2Address "+client2Address) ;
                          a = 1 ;
                       }
                   }
                   if(a == 0)
                   {
                       System.out.println("404 NOT FOUND");
                      }
                    
                   
                   
                    
                
                             
           }
                 i++ ;    }
              }
        
        
                    else if(s[0].contains("180 RINGING"))
              {
                 // System.out.println("TRYING PACKET TO SEND") ;               
                  byte[] data = mes.getBytes();
            //    q = new DatagramPacket(data,data.length, InetAddress.getByName("loopback"), 5678) ;
                    //    System.out.println("Client1Port "+client1Port);
                        q = new DatagramPacket(data,data.length, InetAddress.getLoopbackAddress(), Integer.parseInt(client1Port)) ;
                   
                    sock1.send(q);
                     
                   } 
         else if(mes.contains("200 OK"))
              {
                 // System.out.println("TRYING PACKET TO SEND") ;               
                  byte[] data = mes.getBytes();
            //    q = new DatagramPacket(data,data.length, InetAddress.getByName("loopback"), 5678) ;
                     q = new DatagramPacket(data,data.length, InetAddress.getLoopbackAddress(), Integer.parseInt(client1Port)) ;
                      
                     sock1.send(q);
                     
                   } 
          
              else if(mes.contains("ACK"))
              {
                 byte[] data = mes.getBytes();
            //    q = new DatagramPacket(data,data.length, InetAddress.getByName("loopback"), 5678) ;
                      q = new DatagramPacket(data,data.length, InetAddress.getLoopbackAddress(), Integer.parseInt(client2Port)) ;
                     sock1.send(q);
                    
              return ;
              }
               
    }  
           
    
        
        
        
        
        
        
    

    public static void main(String[] args) throws SocketException, IOException {
       server2  sudp = new server2() ;
       sudp.go() ;
      

    
    }
    
    
    
}