/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.networkqos;

import com.commondb.Common_DB;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.net.URI;
import java.net.URL;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


/**
 *
 * @author r.mohamed riyazath
 */
@WebService(serviceName = "NewWebService")
public class NewWebService {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "signin")
    public String signin(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
         try {
           Common_DB cd=new Common_DB();
            ResultSet rs=Common_DB.LoginCheck("psjav05", "login","username","password", username, password);
            if(rs.next()) {
            return "success";
            }
            else {
                return "username or password is invalid";
            }
        } catch (Exception ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            return "server temporarily not available";
        }
       
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "signup")
    public String signup(@WebParam(name = "username") String username, @WebParam(name = "password") String password, @WebParam(name = "email") String email) {
       try {
           Common_DB cd=new Common_DB();
            int rs=Common_DB.InsertTable("psjav05", "INSERT INTO login(username,password,email) VALUES('"+username+"','"+password+"','"+email+"')");
            if(rs>0) {
            return "success";
            }
            else {
                return "username is already available";
            }
        } catch (Exception ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            return "server temporarily not available";
        }
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "mobileinfo")
    public String mobileinfo(@WebParam(name = "bandwidth") String bandwidth, @WebParam(name = "networkname") String networkname, @WebParam(name = "phonetype") String phonetype, @WebParam(name = "simstate") String simstate, @WebParam(name = "networktype") String networktype,@WebParam(name = "osversion") String osversion, @WebParam(name="username") String username) {
       try {
            Common_DB cd=new Common_DB();
            int rs=Common_DB.UpdateTable("psjav05", "UPDATE login SET bandwidth='"+bandwidth+"',networkname='"+networkname+"',phonetype='"+phonetype+"',simstate='"+simstate+"',networktype='"+networktype+"',osversion='"+osversion+"' WHERE username='"+username+"'");
           
            if(rs>0) {
          
          File file=new File("E:\\NetworkQoSService\\build\\web\\videos/");
//          File[] files=file.listFiles(new FilenameFilter() {
//
//                    @Override
//                    public boolean accept(File dir, String name) {
//                    return name.toLowerCase().endsWith("mp4");
//                    }
//                });
          File[] files=file.listFiles();
          String filenames="";
          for(File filename : files) {
              filenames+=filename.getName()+"@";
          }
            return filenames;
            }
            else {
                return "Sorry unable to get mobile info";
            }
        } catch (Exception ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            return "server temporarily not available";
        }        
    
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Transcode")
    public String Transcode(@WebParam(name = "filename") String filename,@WebParam(name = "username") String username) {
        try {
             String filename1=null;
             String formatname=null;
            Common_DB cd=new Common_DB();
            ResultSet rs=Common_DB.ViewParticularData("psjav05", "login", "username", username);
            if(rs.next()) {
                int bandwidth=Integer.parseInt(rs.getString("bandwidth"));
                String networktype=rs.getString("networktype");
                String networkname=rs.getString("networkname");
                String osversion=rs.getString("osversion");
                String phonetype=rs.getString("phonetype");
               
                if(osversion.startsWith("2.2")) {
                    filename1=filename.substring(0,filename.lastIndexOf("."));
                    formatname="3gp";
                    TransCoder.TransCode(filename, filename1,formatname , "libfaac", new Integer(128000), 44100, 1600000, 620, 480);  
                }
                else if(filename.endsWith("3gp")){
                filename1=filename;    
                }
                else {
                if(bandwidth<70) {
                    
                    filename1=filename.substring(0,filename.lastIndexOf("."));
                      formatname="3gp";
                    TransCoder.TransCode(filename, filename1,formatname , "libfaac", new Integer(128000), 44100, 1600000, 620, 480);
                }
                else if(bandwidth>70 && 90>bandwidth) {
                    filename1=filename.substring(0,filename.lastIndexOf("."));
                    formatname="mp4";
                    TransCoder.TransCode(filename, filename1,formatname , "libfaac", new Integer(200000), 65100, 3200000, 620, 480);
                }
            }            
            return filename1+"."+formatname;
        }
            else {
                return "no file found";
            }
        }catch (Exception ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }
}
