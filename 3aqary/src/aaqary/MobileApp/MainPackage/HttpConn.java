package aaqary.MobileApp.MainPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Properties;

import android.util.Log;

public class HttpConn {

	private String urlStr;

	public HttpConn(String url) {
		this.urlStr = url;
	}

	public String executePostRequest(String request) {

		HttpURLConnection connection = null;
		OutputStreamWriter wr = null;
		BufferedReader rd = null;
		StringBuilder sb = null;
		URL serverAddress = null;
		String line = null;
		String result = null;

		try {
			@SuppressWarnings("unused")
			Properties systemProperties = System.getProperties();
//			systemProperties.setProperty("http.proxyHost", "10.193.118.30");
//			systemProperties.setProperty("http.proxyPort", "3128");
			serverAddress = new URL(urlStr);
			// set up out communications stuff
			connection = null;

			// Set up the initial connection
			connection = (HttpURLConnection) serverAddress.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			// connection.setConnectTimeout(120000); /// set connection timeOut
			// to 2 minutes
			connection.setConnectTimeout(0); // / set connection timeOut to
												// infinite
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setRequestProperty("Content-Type", "text/xml");
			connection.setReadTimeout(10000);
			Log.v( "1" , "Content-Type" + connection.getRequestProperty("Content-Type"));

			connection.connect();

			// get the output stream writer and write the output to the server

			wr = new OutputStreamWriter(connection.getOutputStream());
			wr.write(request);
			wr.flush();
			wr.close();
			Log.v( "1" ," REQUEST : " + request);

			// read the result from the server
			rd = new BufferedReader(new InputStreamReader(connection
					.getInputStream()));
			sb = new StringBuilder();

			while ((line = rd.readLine()) != null) {
				sb.append(line + '\n');
			}

			Log.v( "1" ,"REPLY : " + sb.toString());
			result = sb.toString();
			rd.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// close the connection, set all objects to null
			connection.disconnect();
			rd = null;
			sb = null;
			wr = null;
			connection = null;
		}

		return result;
	}
}
