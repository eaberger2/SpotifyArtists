import java.util.ArrayList;
import java.util.Iterator;
//import java.util.Scanner;

public class ArtistNode {
	ArrayList<Song> songs;
	String name;
	Song song;
	int count;
	
	public ArtistNode(String name, String song) {
		this.name = name;
		songs = new ArrayList<Song>();
        Song s = new Song(song);
        s.incCount();
	    songs.add(s);
        
	}
	
	public class Song {
		public String name;
		public int count;
		
		public Song(String n) {
			this.name = n;
			this.count = 0;
		}
		
		public String getName() {
			return this.name;
		}
		
		public void incCount() {
			this.count++;
		}
		public int getCount() {
			return count;
		}
	}
	
	public Song getSong() {
		return song;
	}
	
	public void addSong(String s) {
        boolean found = false;
        for(int i = 0; i<songs.size(); i++){
            if(songs.get(i).getName().equals(s)){
                songs.get(i).incCount();
                found = true;
            }
        }
        if(!found){
            Song newSong = new Song(s);
            newSong.incCount();
		    songs.add(newSong);
        }
	}
	
	public ArrayList<Song> getSongs(){
		return songs;
	}
	
	public int getCount() {
		return count;
	}
	
	public String getName() {
		return name;
	}
	
	public void incCount() {
		count++;
	}
	
	public String toString() {
		String str = "Artist: "+this.name+" Artist Count: "+getCount()+"\n";
		Iterator<Song> it = songs.iterator();
		while(it.hasNext()) {
			Song next = it.next();
		str += " Song: "+next.getName() + " Song Count: "+next.getCount()+"\n";
		}
		return str;
	}
	
}


