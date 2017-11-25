/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatorconsole;



/**
 *
 * @author vccs
 */
public class Reginfo {
    String regIp, regPort , regNumber ;

    
  Reginfo()
  {
      regIp = "0.0.0.0" ;
      regPort = "0000" ;
      regNumber = "0000";
      
  }
  Reginfo(String IP, String Port, String Number)
  {
      regIp = IP ;
      regPort = Port ;
      regNumber = Number ;
      
  }
     
    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public String getRegPort() {
        return regPort;
    }

    public void setRegPort(String regPort) {
        this.regPort = regPort;
    }

    public String getRegNumber() {
        return regNumber;
    }

   
    
            
    
}
