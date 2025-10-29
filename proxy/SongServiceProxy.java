package proxy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SongServiceProxy implements SongService {
    private RealSongService realSongService;
    private Map<Integer, Song> idCache;
    private Map<String, List<Song>> titleCache;
    private Map<String, List<Song>> albumCache;
    
    public SongServiceProxy(RealSongService realSongService) {
        this.realSongService = realSongService;
        this.idCache = new HashMap<>();
        this.titleCache = new HashMap<>();
        this.albumCache = new HashMap<>();
    }
    
    @Override
    public Song searchById(Integer songID) {
        if (idCache.containsKey(songID)) {
            return idCache.get(songID);
        }
        
        Song song = realSongService.searchById(songID);
        
        if (song != null) {
            idCache.put(songID, song);
        }
        
        return song;
    }
    
    @Override
    public List<Song> searchByTitle(String title) {
        String cacheKey = title.toLowerCase();
        
        if (titleCache.containsKey(cacheKey)) {
            return titleCache.get(cacheKey);
        }
        
        List<Song> songs = realSongService.searchByTitle(title);
        titleCache.put(cacheKey, songs);
        
        return songs;
    }
    
    @Override
    public List<Song> searchByAlbum(String album) {
        String cacheKey = album.toLowerCase();
        
        if (albumCache.containsKey(cacheKey)) {
            return albumCache.get(cacheKey);
        }
        
        List<Song> songs = realSongService.searchByAlbum(album);
        albumCache.put(cacheKey, songs);
        
        return songs;
    }
}
