import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.net.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dao.ClientImpl;
import dao.ClientmailImpl;
import dao.DossierImpl;
import dao.IClient;
import dao.IClientmail;
import dao.IDossier;
import dao.IMessage;
import dao.IResponsable;
import dao.ITache;
import dao.MessageImpl;
import dao.RespoImpl;
import dao.TacheImpl;
import entities.Dossier;
import entities.Messenger;
import entities.Tache;

import java.io.*;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.servlet.http.*;
import javax.servlet.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class test {
	public static String getIdRandom(int ch,int nbr){
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		String output="";
		for(int i=0;i<ch;i++){
			int n = (int)(Math.random()*10);
			output+=n+"";
		}
		for(int i=0;i<nbr;i++){
			int n = (int)(Math.random()*alphabet.length);
			output+=alphabet[n]+"";
		}
		return output;
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException, ParseException {
		IDossier dossierDAO;
		ITache tacheDAO;
		IResponsable respoDAO;
		dossierDAO = new DossierImpl();
		IMessage messageDAO;
		tacheDAO = new TacheImpl();
		respoDAO = new RespoImpl();
		messageDAO = new MessageImpl();
		IClientmail clientmailDAO;
		clientmailDAO = new ClientmailImpl();
		IClient clientDAO;
		clientDAO = new ClientImpl();
		 for(int i=0;i<900;i++){
			 String id =getIdRandom(2,4);
			 System.out.println(id);
			
		 }
		
//		
//		List<Tache> tach = tacheDAO.ListTachesRollback(14,35, "2020-01-25");
//		for(int i=0;i<tach.size();i++)
//		{
//			System.out.println(tach.get(i));
//		}
		
	// --------------------
		
//		 String USER_NAME = "Tracking.GmbH";  // GMail user name (just the part before "@gmail.com")
//		 String PASSWORD = "Bachelor."; // GMail password
//		 String RECIPIENT = "aymenbjuni@gmail.com";
//
//		        String from = USER_NAME;
//		        String pass = PASSWORD;
//		        String[] to = { RECIPIENT }; // list of recipient email addresses
//		        String subject = "Server Started!";
//		        String body = "Welcome back, Aymen";
//
//		        sendFromGMail(from, pass, to, subject, body);
//		    }
//
//		    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
//		        Properties props = System.getProperties();
//		        String host = "smtp.gmail.com";
//		        props.put("mail.smtp.starttls.enable", "true");
//		        props.put("mail.smtp.host", host);
//		        props.put("mail.smtp.user", from);
//		        props.put("mail.smtp.password", pass);
//		        props.put("mail.smtp.port", "587");
//		        props.put("mail.smtp.auth", "true");
//
//		        Session session = Session.getDefaultInstance(props);
//		        MimeMessage message = new MimeMessage(session);
//
//		        try {
//		            message.setFrom(new InternetAddress(from));
//		            InternetAddress[] toAddress = new InternetAddress[to.length];
//
//		            // To get the array of addresses
//		            for( int i = 0; i < to.length; i++ ) {
//		                toAddress[i] = new InternetAddress(to[i]);
//		            }
//
//		            for( int i = 0; i < toAddress.length; i++) {
//		                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
//		            }
//
//		            message.setSubject(subject);
//		            message.setText(body);
//		            Transport transport = session.getTransport("smtp");
//		            transport.connect(host, from, pass);
//		            transport.sendMessage(message, message.getAllRecipients());
//		            transport.close();
//		        }
//		        catch (AddressException ae) {
//		            ae.printStackTrace();
//		        }
//		        catch (MessagingException me) {
//		            me.printStackTrace();
//		        }
		
		//------------------------
		    
		
		
//		STRING TO = "KARGO.GMBH@GMAIL.COM";
//		String from = "aymenbjuni@gmail.com";
		
								
//		String email = "aymenbjuni@gmail.com";
//		String subject = "minka wa ilayaka";
//		String message = "teesttt";
//		
//		if(!message.equals(null)){
//			String host = "smtp.gmail.com";
//	    	int port = 587;
//	    	String username = "Kargo.GmbH";
//	    	String password = "Bachelor.";
//	    	
//			Properties props = new Properties();
//			props.put("mail.smtp.auth", "false");
//	    	props.put("mail.smtp.starttls.enable", "true");
//	        Session session = Session.getDefaultInstance(props, null);
//			
//	        String body = message;
//	        
//		try {
//	        	Message m = new MimeMessage(session);
//	        	m.setFrom(new InternetAddress(email, null));
//	        	m.addRecipient(Message.RecipientType.TO, new InternetAddress("Kargo.GmbH@gmail.com", "me"));
//	        	m.setSubject(subject);
//	        	m.setText(body);
//	            
//	        	Transport transport = session.getTransport("smtp");
//	    		transport.connect(host, port, username, password);
//	     
//	    		Transport.send(m);
//	        } catch (AddressException e) {
//	        	e.printStackTrace();
//	        } catch (javax.mail.MessagingException e) {
//				e.printStackTrace();
//			}
//    }
		
//		String dt = "2008-01-01";  // Start date
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar c = Calendar.getInstance();
//		c.setTime(sdf.parse(dt));
//		c.add(Calendar.DATE, 14);  // number of days to add
//		dt = sdf.format(c.getTime());  // dt is now the new date
//		System.out.println(dt);
		
//		int id =clientmailDAO.getIdCM("oussamadridi3290@gmail.com");
//		System.out.println(id);
		
			String msg= "test";
			String number= "0623198124";
			clientDAO.sms(number, msg);

//		try {
//            String recipient = "+212623198124";
//            String message = " tisalat!";
//            String username = "admin";
//            String password = "abc123";
//            String originator = "+440987654321";
//
//            String requestUrl  = "http://127.0.0.1:9501/api?action=sendmessage&" +
//            					 "username=" + URLEncoder.encode(username, "UTF-8") +
//            					 "&password=" + URLEncoder.encode(password, "UTF-8") +
//            					 "&recipient=" + URLEncoder.encode(recipient, "UTF-8") +
//            					 "&messagetype=SMS:TEXT" +
//            					 "&messagedata=" + URLEncoder.encode(message, "UTF-8") +
//            					 "&originator=" + URLEncoder.encode(originator, "UTF-8") +
//            					 "&serviceprovider=GSMModem1" +
//            					 "&responseformat=html";
//
//
//
//            URL url = new URL(requestUrl);
//            HttpURLConnection uc = (HttpURLConnection)url.openConnection();
//
//            System.out.println(uc.getResponseMessage());
//
//            uc.disconnect();
//
//    } catch(Exception ex) {
//            System.out.println(ex.getMessage());
//
//    }
		
		
}
}

