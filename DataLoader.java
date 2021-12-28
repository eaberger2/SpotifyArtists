import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.io.*;



// public class (implemented primarily in final app week)

public class DataLoader {

    public List<ArtistNode> loadFile(String csvFilePath) throws FileNotFoundException {
        // TODO Auto-generated method stub
    	List<ArtistNode> list = new ArrayList<ArtistNode>();
    	
    	try {
    	BufferedReader file = new BufferedReader(new FileReader(new File(csvFilePath)));
    	//Scanner file = new Scanner(new File(csvFilePath));
    	String firstLine = file.readLine();
    	String[] categories = firstLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
    	int titleCol = 0;
    	int artistCol = 0;
    	
    	if(!firstLine.contains("Track Name") || !firstLine.contains("Artist Name(s)")) {	
    			throw new FileNotFoundException();
    	}
    	
    	for(int i = 0; i < categories.length; i++) {
    		if(categories[i].contains("Track Name")) {
    			titleCol = i;
    		}
    		if(categories[i].contains("Artist Name(s)")) {
    			artistCol = i;
    		}
    		
    	}
    	
    	String title = "";
    	String artist = "";
        ArrayList<String> addedArtists = new ArrayList<String>();
    	while(file.ready()) {
    		String line = file.readLine();
    		String[] lineSplit = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
    			lineSplit[titleCol] = lineSplit[titleCol].replaceAll("\"","");
    			title = lineSplit[titleCol];
    			title.trim();
    			lineSplit[artistCol] = lineSplit[artistCol].replaceAll("\"","");
    			artist = lineSplit[artistCol];
    			String[] artists = artist.split(",");
    			for(int i=0; i<artists.length; i++) {
    				artists[i].trim();
                    if(addedArtists.contains(artists[i])){
                        int index = addedArtists.indexOf(artists[i]);
                        list.get(index).incCount();
                        list.get(index).addSong(title);
                    }
                    else{
                    ArtistNode newNode = new ArtistNode(artists[i],title);
                    newNode.incCount();
    				list.add(newNode);
                    addedArtists.add(artists[i]);
                    }
    			}
    			
    	}
    	file.close();
    	}
    	catch(FileNotFoundException e) {
    		e.printStackTrace();
    	}
    	catch(IOException e2) {
    		e2.printStackTrace();
    	}
    	
        return list;
    }


    public List<ArtistNode> loadAllFilesInDirectory(String directoryPath) throws FileNotFoundException {
        // TODO Auto-generated method stub
    	List<ArtistNode> dirList = new ArrayList<ArtistNode>();
    	List<ArtistNode> tempList = new ArrayList<ArtistNode>();
    	try {
        File directory = new File(directoryPath);
    	String[] contents = directory.list();
    	for(int i = 0; i < contents.length; i++) {
    		if(contents[i].contains(".csv")) {
    			String path = directoryPath + "\\" + contents[i];
    			tempList = this.loadFile(path);
    			for(int j = 0; j < tempList.size(); j++) {
    				dirList.add(tempList.get(j));
    			}
    		}
    	}
    	}
    	catch(FileNotFoundException e) {
    		e.printStackTrace();	
    	}
    	
    	return dirList;
    }
}

