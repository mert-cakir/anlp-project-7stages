package anlp.project.myc;

/**
 * @author ${Mert Yilmaz CAKIR}
 *
 * ${Ranking}
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class WebRanker {

	int i,count = 0;

	public WebRanker() {
	}

	public int getFindString(File fileEntry, String str){

		try (BufferedReader br = new BufferedReader(new FileReader(fileEntry))) {

			Boolean found;
			String line;
			while ((line = br.readLine()) != null) {
				found = line.contains(str);
				if (found == true) {
					String subStr = line.substring(line.indexOf(" ") + 1);
					i = Integer.parseInt(subStr);
					count += i;
				}
			}

		} catch (Exception e) {
		}
		return count;
	}
}
