package proxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RealSongService implements SongService {
    private Map<Integer, Song> songDatabase;
    
    public RealSongService() {
        this.songDatabase = new HashMap<>();
        initializeSongDatabase();
    }
    
    private void initializeSongDatabase() {
        songDatabase.put(1, new Song(1, "Love Notes", "Olivia B Moore", "Love Notes", 198));
        songDatabase.put(2, new Song(2, "Saviour", "Anfa Rose", "Mermaids", 192));
        songDatabase.put(3, new Song(3, "Take Me Home", "Makar", "Take Me Home", 196));
        songDatabase.put(4, new Song(4, "Blue Rain", "REGI FLIH, Emerson", "Blue Rain", 204));
        songDatabase.put(5, new Song(5, "Roses", "J'calm", "NICHE", 184));
        songDatabase.put(6, new Song(6, "Hard To Forget", "Sam Hunt", "SOUTHSIDE", 205));
        songDatabase.put(7, new Song(7, "In My Bed", "Rotimi Wale", "The Beauty of Becoming", 185));
        songDatabase.put(8, new Song(8, "You Proof", "Morgan Wallen", "One Thing At A Time", 157));
        songDatabase.put(9, new Song(9, "WHO ELSE?", "FRVRFRIDAY", "CRUISE CONTROL", 179));
        songDatabase.put(10, new Song(10, "Satellite", "Teflon Sega", "Velvet Pill", 159));
    }
    
    private void simulateServerDelay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    @Override
    public Song searchById(Integer songID) {
        simulateServerDelay();
        return songDatabase.get(songID);
    }
    
    @Override
    public List<Song> searchByTitle(String title) {
        simulateServerDelay();
        return songDatabase.values().stream()
                .filter(song -> song.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Song> searchByAlbum(String album) {
        simulateServerDelay();
        return songDatabase.values().stream()
                .filter(song -> song.getAlbum().toLowerCase().contains(album.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    public void addSong(Song song) {
        songDatabase.put(song.getSongID(), song);
    }
}
