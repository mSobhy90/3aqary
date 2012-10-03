package aaqary.MobileApp.ParserPackage;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

public class XMLParser {
	public static ArrayList<String> data = new ArrayList<String>();
	public static String reqType;
	public static String res;
	public static Boolean loginRes;

	public static void parse(String xmlReq) {
		XMLParser p = new XMLParser();
		reqType = xmlReq.substring(1, xmlReq.indexOf(">"));
		if (reqType.equals("login")) {
			p.parseLoginResponce(xmlReq);
		}
	}

	private void parseLoginResponce(String xml) {
		try {
			// data.clear();
			/* Get a SAXParser from the SAXPArserFactory. */
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			/* Get the XMLReader of the SAXParser we created. */
			XMLReader xr = sp.getXMLReader();
			/* Create a new ContentHandler and apply it to the XML-Reader */
			LoginHandler loginHandler = new LoginHandler();
			xr.setContentHandler(loginHandler);
			/* Parse the xml-data from our req. */
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			xr.parse(is);
			/* Parsing has finished. */
			/* Our ExampleHandler now provides the parsed data to us. */
			LoginParsedDataSet parsedExampleDataSet = loginHandler
					.getParsedData();
			loginRes = parsedExampleDataSet.getRes();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String createLoginRequest(String userName, String password) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter s = new StringWriter();
		try {
			serializer.setOutput(s);
			//serializer.startDocument("",false);
			serializer.startTag("", "login");
			serializer.startTag("", "username");
			serializer.text(userName);
			serializer.endTag("", "username");
			serializer.startTag("", "password");
			serializer.text(password);
			serializer.endTag("", "password");
			serializer.endTag("", "login");
			serializer.endDocument();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		res = s.toString();
		return res;
	}
}