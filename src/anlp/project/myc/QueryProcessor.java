package anlp.project.myc;

/**
 * @author  Mert Yilmaz Cakir
 * @since   2017-06-10
 */

import com.hrzafer.reshaturkishstemmer.Resha;
import com.hrzafer.reshaturkishstemmer.Stemmer;

public class QueryProcessor {

	public QueryProcessor() {
	}

	public String[] getWordRoot(String searchText){

		Stemmer stemmer = Resha.Instance;

		String[] tokens = searchText.split(" ");
		for (int i=0; i<tokens.length; i++){
			System.out.println(tokens[i]); 
			tokens[i] = stemmer.stem(tokens[i]);
		}
		return tokens;

	}
}
