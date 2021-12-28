
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataLoader data = new DataLoader();
		List<ArtistNode> li = new ArrayList<ArtistNode>();
		try {
			li = data.loadAllFilesInDirectory("C:\\Users\\emily\\eclipse-workspace\\SpotifyWrapped\\src\\songlib");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Iterator<ArtistNode> it = li.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}

}