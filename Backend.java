import java.util.ArrayList;

public class Backend {
    ArrayList<ArtistNode> list;
    ArrayList<ArtistNode> sortedList;


    public Backend(ArrayList<ArtistNode> li){
        this.list = li;
    }

    public void sort(){
        int[] counts = listOfValues(list);
        
    }

    public int[] listOfValues(ArrayList<ArtistNode> li){
        //int min = findMin(li);
        int max = findMax(li);
        //int size = max-min;
        int[] counts = new int[max];
        for(int i = 0; i<max; i++){
            counts[i] = findCount(i,li);
        }
        return counts;
    }

    public int findCount(int num, ArrayList<ArtistNode> li){
        int count = 0;
        for(int i = 0; i<li.size(); i++){
            if(li.get(i).getCount() == num){
                count++;
            }
        }
        return count;
    }

    public int findMax(ArrayList<ArtistNode> li){
        int currMax = 0;
        for(int i=0; i<li.size(); i++){
            if(currMax<li.get(i).getCount()){
                currMax = li.get(i).getCount();
            }
        }
        return currMax;
    }

    public int findMin(ArrayList<ArtistNode> li){
        int currMin = 0;
        for(int i=0; i<li.size(); i++){
            if(currMin>li.get(i).getCount()){
                currMin = li.get(i).getCount();
            }
        }
        return currMin;
    }

}
