
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
//import java.util.List;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataLoader data = new DataLoader();
		ArrayList<ArtistNode> li = new ArrayList<ArtistNode>();
		try {
			li = data.loadAllFilesInDirectory("C:\\Users\\emily\\OneDrive\\Documents\\GitHub\\SpotifyArtists\\SpotifyArtists\\songlib");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		Backend newLi = new Backend(li);
		ArrayList<ArtistNode> sorted = newLi.sort();
		Iterator<ArtistNode> it = sorted.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}

}