package anlp.project.myc;

/**
 * @author ${Mert Yilmaz CAKIR}
 *
 * ${Web Crawler}
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;

public class WebCrawler {
	
	private static final String FILENAME = "/home/myilmaz/Desktop/ANLP/Project/";
	private static final int MAX_DEPTH = 2;
	private HashSet<String> links;

	public WebCrawler(){
		links = new HashSet<>();
	}
	
	public void Crawling(String url) throws IOException, URISyntaxException{
		WebCrawler crawler = new WebCrawler();
		String path = getDomainName(url);
		new File(path).mkdir();
		crawler.getPageLinks(path, url, 0);
	}
	
	public String getDomainName(String url) throws URISyntaxException {
	    URI uri = new URI(url);
	    String domain = uri.getHost();
	    return domain;
	}

	public void getPageLinks(String path, String url, int depth) throws IOException, URISyntaxException {
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME + path + "-crawler", true));

		if ((!links.contains(url) && (depth < MAX_DEPTH))) {
			
			System.out.println(">> Depth: " + depth + " [" + url + "]");
			bw.write(url+"\n");
			
			try {
				
				links.add(url);

				Document document = Jsoup.connect(url).get();
				Elements linksOnPage = document.select("a[href]");

				depth++;
				for (Element page : linksOnPage) {
					
					getPageLinks(path, page.attr("abs:href"), depth);
				}
				
			} catch (IOException e) {
				
				System.err.println("For '" + url + "': " + e.getMessage());
				
			}
		}
		bw.close();
	}
	
	public String getCrawlerTxtName(String url) throws URISyntaxException{
		String domain = getDomainName(url);
		return FILENAME + domain + "-crawler.txt";
	}
	
}