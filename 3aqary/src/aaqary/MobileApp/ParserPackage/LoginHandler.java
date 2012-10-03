package aaqary.MobileApp.ParserPackage;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LoginHandler extends DefaultHandler {

	// ===========================================================
	// Fields
	// ===========================================================
	Boolean inRes = false;
	private LoginParsedDataSet myParsedDataSet = new LoginParsedDataSet();

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public LoginParsedDataSet getParsedData() {
		return this.myParsedDataSet;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	@Override
	public void startDocument() throws SAXException {
		this.myParsedDataSet = new LoginParsedDataSet();
	}

	@Override
	public void endDocument() throws SAXException {
		// Nothing to do
	}

	/**
	 * Gets be called on opening tags like: <tag> Can provide attribute(s), when
	 * xml was like: <tag attribute="attributeValue">
	 */
	@Override
	public void startElement(String namespaceURI, String tagName, String qName,
			Attributes atts) throws SAXException {
		if (qName.equals("result"))
			inRes = true;
	}

	/**
	 * Gets be called on closing tags like: </tag>
	 */
	@Override
	public void endElement(String namespaceURI, String tagName, String qName)
			throws SAXException {
		if (qName.equals("result"))
			inRes = false;
	}

	/**
	 * Gets be called on the following structure: <tag>characters</tag>
	 */
	@Override
	public void characters(char ch[], int start, int length) {
		//System.out.println(new String(ch, start, length));
		if(inRes)
			myParsedDataSet.setRes(Boolean.valueOf(new String(ch, start, length)));
	}
}