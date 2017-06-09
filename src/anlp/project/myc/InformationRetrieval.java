package anlp.project.myc;

/**
 * @author ${Mert Yilmaz CAKIR}
 *
 * ${Information Retrieval}
 */

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class InformationRetrieval {
	
	private static final String FILENAME = "/home/myilmaz/Desktop/ANLP/Project/";
	
	public InformationRetrieval() {
	}
	
	public String iRetrieval(String search){
		
		int rankCount = 0;
		Map<String, Integer> map = new HashMap<String, Integer>();
		Map.Entry<String, Integer> maxEntry = null;
		
		WebRanker ranker = new WebRanker();
		
		QueryProcessor processor = new QueryProcessor();
		String str[] = processor.getWordRoot(search);

		final File folder = new File(FILENAME);
		for (final File fileEntry : folder.listFiles()){
			if (fileEntry.getName().contains("-index")){
				for (String subStr : str){
					rankCount = ranker.getFindString(fileEntry, subStr);
				}
				System.out.println("Total " + fileEntry + ": " + rankCount);
				map.put(fileEntry.toString(), rankCount);
			}
		}

		for (Map.Entry<String, Integer> cmpMap : map.entrySet()){
			if (maxEntry == null || cmpMap.getValue().compareTo(maxEntry.getValue()) > 0)
				maxEntry = cmpMap;
		}
		String result = "http://" + maxEntry.getKey().substring(maxEntry.getKey().indexOf("Project/") + 8,
				maxEntry.getKey().indexOf("-index"));
		
		System.out.println("Searching result: " + result);
		
		return result;
		
	}
}