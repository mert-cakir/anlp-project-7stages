package anlp.project.myc;

/**
 * @author ${Mert Yilmaz CAKIR}
 *
 * ${Indexing}
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Indexing {

	private static final String FILENAME = "/home/myilmaz/Desktop/ANLP/Project/";

	public Indexing() {
	}

	public String getDomainName(String url) throws URISyntaxException {
		URI uri = new URI(url);
		String domain = uri.getHost();
		return domain;
	}
	
	public String getQueryName(String url) throws URISyntaxException {
		URI uri = new URI(url);
		String domain = uri.getQuery();
		if (uri.getQuery() != null) {
			domain = uri.getQuery();
		} else {
			domain = "";
		}
		return domain;
	}

	public String getFileName(String path) throws URISyntaxException {
		return FILENAME + getDomainName(path) + "-crawler";
	}


	public void textIndexing(String path) throws IOException, URISyntaxException{

		try (BufferedReader br = new BufferedReader(new FileReader(getFileName(path)))) {

			String line;
			while ((line = br.readLine()) != null) {

				BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME  + 
						getDomainName(line) + getQueryName(line) + "-index", true));
				System.out.println(line+"\nCreating indexing files...");

				int sumChars = 0;
				Map<String, Integer> map = new HashMap<String, Integer>();

				URL url = new URL(line);
				InputStream stream = url.openStream();

				Document doc = Jsoup.parse(stream, "UTF-8", "");

				Elements divs = doc.select("div, p, b");
				for (Element div : divs) {
					if (div.ownText() != "") {
						sumChars += div.ownText().length();
						String[] word = div.ownText().split(" ");
						for (String t : word){

							if (map.containsKey(t)){
								map.put(t, map.get(t)+1);
							} else {
								map.put(t, 1);
							}
						}
					}
				}
				System.out.println("Sum of all words: " + sumChars);
				System.out.print("Number of singular words: ");

				List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
				Collections.sort(entries, new Comparator<Map.Entry<String,Integer>>() {
					@Override
					public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
						return o1.getValue().compareTo(o2.getValue());
					}
				});
				for(int i = 0; i < map.size(); i++){
					System.out.println(entries.get(entries.size() - i - 1).getKey()+" "+entries.get(entries.size() - i - 1).getValue());
					writer.write(entries.get(entries.size() - i - 1).getKey()+" "+entries.get(entries.size() - i - 1).getValue() + "\n");
				}
				writer.close();
			}
		} catch (Exception e) {
			System.err.println("For '" + path + "': " + e.getMessage());
		}

	}
}
