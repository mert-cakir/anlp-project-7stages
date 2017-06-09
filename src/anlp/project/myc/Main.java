package anlp.project.myc;

public class Main {

	private static final String URL = "https://dahianlamindakide.ayriyazilir.com/"; /*http://www.izu.edu.tr/*/
	private static final String SEARCH = "ünlü Olan kelimeler";

	public static void main(String[] args) throws Exception{

		WebCrawler crawler = new WebCrawler();
		crawler.Crawling(URL);

		TextProcessing tProcessing = new TextProcessing();
		tProcessing.textProcessing(URL);

		Indexing indexing = new Indexing();
		indexing.textIndexing(URL);

		InformationRetrieval retrieval = new InformationRetrieval();

		Summarization summarization = new Summarization();
		summarization.getSummary(retrieval.iRetrieval(SEARCH));

	}

}
