package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
 
public class sms {
	public String sendSms() {
		try {
			// Construct data
			String apiKey = "apikey=" + "i1GUqz1e2kE-JBN7T2upCytor1d6QoCSajW63LSE4g";
			String message = "&message=" + "salam ";
			String sender = "&sender=" + "twam";
			String numbers = "&numbers=" + "212619860033";
			//System.out.println("sms sent! ! !");
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
						System.out.println("sms sent! ! !");

			return stringBuffer.toString();

		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}
}