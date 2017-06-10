package anlp.project.myc;

/**
 * @author  Mert Yilmaz Cakir
 * @since   2017-06-10
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class Summarization {
	
	private static final String FILENAME = "/home/myilmaz/Desktop/ANLP/Project/";
	private String result = "";
	
	public Summarization() {
	}
	
	public String getFileName(String path) throws URISyntaxException {
	    return FILENAME + getDomainName(path) + "-textProcessing";
	}
	
	public String getDomainName(String url) throws URISyntaxException {
	    URI uri = new URI(url);
	    String domain = uri.getHost();
	    return domain;
	}
	
	
	public String getSummary(String path){
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		Map.Entry<String, Integer> maxEntry = null;
		
		try (BufferedReader br = new BufferedReader(new FileReader(getFileName(path)))) {

			String line;
			while ((line = br.readLine()) != null) {
				map.put(line, line.length());
			}
			for (Map.Entry<String, Integer> cmpMap : map.entrySet()){
				if (maxEntry == null || cmpMap.getValue().compareTo(maxEntry.getValue()) > 0)
					maxEntry = cmpMap;
			}
			
			result = maxEntry.getKey();
			System.out.println("\n" + path + "\n-------------------------\n" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
