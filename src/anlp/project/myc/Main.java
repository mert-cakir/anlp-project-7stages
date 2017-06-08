package anlp.project.myc;

public class Main {
	
	private static final String url = "http://www.izu.edu.tr/";

	public static void main(String[] args) throws Exception{
		
		WebCrawler crawler = new WebCrawler();
		crawler.Crawling(url);
		
		TextProcessing tProcessing = new TextProcessing();
		tProcessing.textProcessing(url);
		
		Indexing indexing = new Indexing();
		indexing.textIndexing(url);

	}

}
