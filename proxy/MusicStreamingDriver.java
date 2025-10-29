package proxy;

import java.util.List;


public class MusicStreamingDriver {
    public static void main(String[] args) {

        RealSongService realService = new RealSongService();
        SongServiceProxy proxyService = new SongServiceProxy(realService);
        

        System.out.println("Searching for song ID 1...");
        long startTime = System.currentTimeMillis();
        Song song1 = proxyService.searchById(1);
        long endTime = System.currentTimeMillis();
        System.out.println("Found: " + song1.getTitle());
        System.out.println("Time Taken: " + (endTime -startTime)+ "ms");
        
        System.out.println("\nSearching for song ID 1 again...");
        startTime = System.currentTimeMillis();
        song1 = proxyService.searchById(1);
        endTime = System.currentTimeMillis();
        System.out.println("Found: " + song1.getTitle());
        System.out.println("Time taken: " + (endTime - startTime) + "ms [Cached!]");

        System.out.println("\nSearching for title 'Hard To Forget'...");
        startTime = System.currentTimeMillis();
        List<Song> titleResults = proxyService.searchByTitle("Hard To Forget");
        endTime = System.currentTimeMillis();
        System.out.println("Found " + titleResults.size() + " song(s)");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        
        System.out.println("\nSearching for title 'Hard To Forget' again...");
        startTime = System.currentTimeMillis();
        titleResults = proxyService.searchByTitle("Hard To Forget");
        endTime = System.currentTimeMillis();
        System.out.println("Found " + titleResults.size() + " song(s)");
        System.out.println("Time taken: " + (endTime - startTime) + "ms [Cached!]");
        

        System.out.println("\nSearching for album 'One Thing At A Time'...");
        startTime = System.currentTimeMillis();
        List<Song> albumResults = proxyService.searchByAlbum("One Thing At A Time");
        endTime = System.currentTimeMillis();
        System.out.println("Found " + albumResults.size() + " song(s)");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        
        System.out.println("\nSearching for album 'One Thing At A Time' again...");
        startTime = System.currentTimeMillis();
        albumResults = proxyService.searchByAlbum("One Thing At A Time");
        endTime = System.currentTimeMillis();
        System.out.println("Found " + albumResults.size() + " song(s)");
        System.out.println("Time taken: " + (endTime - startTime) + "ms [Cached!]");
    }
}
