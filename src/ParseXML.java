import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public final class ParseXML extends JFrame{ 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyTableModel tm;
	private JTable table;
	private DocumentBuilderFactory f;
	private DocumentBuilder db;
	private Document doc;
	private NodeList nl;
	private ArrayList<String> myData = new ArrayList<>();
	private StringTokenizer st;
	
	ParseXML (String winTitle){
		  super(winTitle);
		  		  
		  tm = new MyTableModel();
		  table = new JTable(tm );

		  add(new JScrollPane(table));
		try {
			parseXML();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		    
		   
	}
	public ArrayList<String> getList(){
		
		ArrayList<String> list = myData;
		return list;
	}
	public static void main(String args[]){    
		  
		ParseXML myFrame = new ParseXML( "Parse Document" );
		
		
		myFrame.pack();   
		myFrame.setVisible(true);

		} 
	
	public void parseXML() throws ParserConfigurationException, SAXException, IOException{
		f = DocumentBuilderFactory.newInstance();
		db = f.newDocumentBuilder();
		doc = db.parse(new File("src/file.xml"));
		
		nl = doc.getElementsByTagName("product");
		
		String title = "";
		int price = 0, amount = 0;
		for (int i = 0; i < nl.getLength(); i++){
			Element element = (Element) nl.item(i);
			title = element.getElementsByTagName("title").item(0).getChildNodes().item(0).getNodeValue();
			price = Integer.parseInt(element.getElementsByTagName("price").item(0).getChildNodes().item(0).getNodeValue());
			amount = Integer.parseInt(element.getElementsByTagName("amount").item(0).getChildNodes().item(0).getNodeValue());
			
			myData.add(title + "," + price + "," + amount);
		}
	}
	
	
	 final class MyTableModel extends AbstractTableModel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String[] colNames = {"Product name", "Price", "Quantity"};
		ArrayList<String> list = getList();
			
		public int getColumnCount() {
		      return 3;
		    }
		 
		 public int getRowCount() {
		      return list.size();
		    	
		    }
		 
		 public String getColumnName(int col){
		    	return colNames[col];
		    }
		 
		 public void setValueAt(Object value, int row, int col){
			    
		     
		 }
		 public Object getValueAt(int row, int col) {
		     
			 String p;
			 
			 switch (col) {
	    	  case 0:   
	    		  p = myData.get(row);
	    		  st = new StringTokenizer(p, ",");
	    		  return st.nextToken();
	    	  case 1:   
	    		  return st.nextToken();
	    	  case 2:   
	    		  return st.nextToken();
	    	  default:
	    	    return "";
	    	}
		 }
		   
	 }
}
