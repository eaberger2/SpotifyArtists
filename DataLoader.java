import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.io.*;



// public class (implemented primarily in final app week)

public class DataLoader {

    public ArrayList<ArtistNode> loadFile(String csvFilePath) throws FileNotFoundException {
        // TODO Auto-generated method stub
    	ArrayList<ArtistNode> list = new ArrayList<ArtistNode>();
    	
    	try {
		//reads file
    	BufferedReader file = new BufferedReader(new FileReader(new File(csvFilePath)));
    	String firstLine = file.readLine();
		//split first line by columns
    	String[] categories = firstLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
    	int titleCol = 0;
    	int artistCol = 0;
    	//throws exception if the file does not contain needed song info
    	if(!firstLine.contains("Track Name") || !firstLine.contains("Artist Name(s)")) {	
    			throw new FileNotFoundException();
    	}
    	//asigns song name and artist name column
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
		//arraylist to keep track of which artists are already in the list
        ArrayList<String> addedArtists = new ArrayList<String>();
		//loops while the file has a next line
    	while(file.ready()) {
			//reads next line of the file
    		String line = file.readLine();
			//splits the file by designated columns
    		String[] lineSplit = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				//removes quotations from the title column
    			lineSplit[titleCol] = lineSplit[titleCol].replaceAll("\"","");
    			title = lineSplit[titleCol];	
    			title.trim();
				//removes quotations from the artist column
    			lineSplit[artistCol] = lineSplit[artistCol].replaceAll("\"","");
    			artist = lineSplit[artistCol];
				//splits the artist column by commas
    			String[] artists = artist.split(",");
				//goes through each artist featured on the song
    			for(int i=0; i<artists.length; i++) {
					//trims artist strings
    				//String newArtistName = artists[i].trim();
					artists[i].trim();
                    if(addedArtists.contains(artists[i])){
                        int index = addedArtists.indexOf(artists[i]);
                        list.get(index).incCount();
                        list.get(index).addSong(title);
                    }
                    else{
					//creates a new artist node with new artist and song
                    ArtistNode newNode = new ArtistNode(artists[i],title);
					//increment the artist's count
                    newNode.incCount();
					//add the artist to the overall list
    				list.add(newNode);
					//add the name of the artist to the list that keeps track of them
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


    public ArrayList<ArtistNode> loadAllFilesInDirectory(String directoryPath) throws FileNotFoundException {
        // TODO Auto-generated method stub
    	ArrayList<ArtistNode> dirList = new ArrayList<ArtistNode>();
    	ArrayList<ArtistNode> tempList = new ArrayList<ArtistNode>();
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

