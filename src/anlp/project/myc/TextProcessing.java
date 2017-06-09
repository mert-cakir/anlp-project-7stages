package anlp.project.myc;

/**
 * @author ${Mert Yilmaz CAKIR}
 *
 * ${Text Processing}
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class TextProcessing {

	private static final String FILENAME = "/home/myilmaz/Desktop/ANLP/Project/";
	
	public TextProcessing(){
	}
	
	public void textProcessing(String path) throws Exception {
		
		TextProcessing tProcessing = new TextProcessing();
		tProcessing.getTextProcessing(path);
		System.out.println("Files were created.");
	}
	
	public String getFileName(String path) throws URISyntaxException {
	    return FILENAME + getDomainName(path) + "-crawler";
	}
	
	public String getDomainName(String url) throws URISyntaxException {
	    URI uri = new URI(url);
	    String domain = uri.getHost();
	    return domain;
	}
	
	private void getTextProcessing(String path) throws Exception{
		
		try (BufferedReader br = new BufferedReader(new FileReader(getFileName(path)))) {

			String line;
			while ((line = br.readLine()) != null) {

				BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME + getDomainName(path) + "-textProcessing", true));
				System.out.println(line+"\nCreating files...");
				URL url = new URL(line);
				InputStream in = url.openStream();

				Document doc = Jsoup.parse(in, "UTF-8", "");

				Elements divs = doc.select("div, p, b");
				for (Element div : divs) {
					if (div.ownText() != ""){

						writer.write(div.ownText() + "\n");

					}
				}

				writer.close();

			}
		} catch (Exception e) {
			System.err.println("For '" + path + "': " + e.getMessage());
		}
		
	}
}
