package test;

import proxy.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;


public class ProxyPatternTest {
    
    private RealSongService realService;
    private SongServiceProxy proxyService;
    
    @BeforeEach
    public void setUp() {
        realService = new RealSongService();
        proxyService = new SongServiceProxy(realService);
    }
    
    @Test
    public void testSong_Creation() {
        Song song = new Song(1, "Test Song", "Test Artist", "Test Album", 180);
        
        assertEquals(1, song.getSongID());
        assertEquals("Test Song", song.getTitle());
        assertEquals("Test Artist", song.getArtist());
        assertEquals("Test Album", song.getAlbum());
        assertEquals(180, song.getDuration());
    }
    
    @Test
    public void testSong_Equality() {
        Song song1 = new Song(1, "Test Song", "Artist", "Album", 180);
        Song song2 = new Song(1, "Different Title", "Different Artist", "Different Album", 200);
        Song song3 = new Song(2, "Test Song", "Artist", "Album", 180);
        

        assertEquals(song1, song2);

        assertNotEquals(song1, song3);
    }
    
    @Test
    public void testRealService_SearchById() {
        Song song = realService.searchById(9);
        
        assertNotNull(song);
        assertEquals("WHO ELSE?", song.getTitle());
        assertEquals("FRVRFRIDAY", song.getArtist());
    }
    
    @Test
    public void testRealService_SearchByIdNotFound() {
        Song song = realService.searchById(999);
        assertNull(song);
    }
    
    @Test
    public void testRealService_SearchByTitle() {
        List<Song> results = realService.searchByTitle("Roses");
        
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("Roses", results.get(0).getTitle());
    }
    
    @Test
    public void testRealService_SearchByAlbum() {
        List<Song> results = realService.searchByAlbum("Love Notes");
        
        assertNotNull(results);
        assertEquals(1, results.size());
    }
    
    @Test
    public void testProxy_SearchById() {
        Song song = proxyService.searchById(1);
        
        assertNotNull(song);
        assertEquals("Love Notes", song.getTitle());
    }
    
    @Test
    public void testProxy_CacheHit() {
        // First call - should fetch from real service
        long startTime1 = System.currentTimeMillis();
        Song song1 = proxyService.searchById(1);
        long endTime1 = System.currentTimeMillis();
        long duration1 = endTime1 - startTime1;
        

        long startTime2 = System.currentTimeMillis();
        Song song2 = proxyService.searchById(1);
        long endTime2 = System.currentTimeMillis();
        long duration2 = endTime2 - startTime2;
        

        assertEquals(song1.getTitle(), song2.getTitle());
        

        assertTrue(duration2 < duration1, "Cached call should be faster");
    }
    
    @Test
    public void testProxy_SearchByTitle() {
        List<Song> results = proxyService.searchByTitle("Take Me Home");
        
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("Take Me Home", results.get(0).getTitle());
    }
    
    @Test
    public void testProxy_SearchByTitleCaseInsensitive() {
        List<Song> results1 = proxyService.searchByTitle("take me home");
        List<Song> results2 = proxyService.searchByTitle("TAKE ME HOME");
        
        assertEquals(results1.size(), results2.size());
    }
    
    @Test
    public void testProxy_SearchByAlbum() {
        List<Song> results = proxyService.searchByAlbum("Love Notes");
        
        assertNotNull(results);
        assertEquals(1, results.size());
    }
    
    @Test
    public void testProxy_MultipleCacheHits() {

        Song song1 = proxyService.searchById(3);
        Song song2 = proxyService.searchById(3);
        Song song3 = proxyService.searchById(3);
        

        assertEquals(song1.getTitle(), song2.getTitle());
        assertEquals(song2.getTitle(), song3.getTitle());
    }
    
    @Test
    public void testProxy_DifferentSearchTypes() {
        Song byId = proxyService.searchById(1);
        List<Song> byTitle = proxyService.searchByTitle("Hard To Forget");
        List<Song> byAlbum = proxyService.searchByAlbum("SOUTHSIDE");
        
        assertNotNull(byId);
        assertNotNull(byTitle);
        assertNotNull(byAlbum);
        assertTrue(byTitle.size() > 0);
        assertTrue(byAlbum.size() > 0);
    }
    
    @Test
    public void testProxy_PartialTitleMatch() {
        List<Song> results = proxyService.searchByTitle("Bed");

        assertNotNull(results);
        assertTrue(results.size() > 0);
        assertTrue(results.get(0).getTitle().contains("Bed"));
    }
    
    @Test
    public void testRealService_HasDelay() {
        // Measure time for real service call
        long startTime = System.currentTimeMillis();
        realService.searchById(1);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        assertTrue(duration >= 900, "Server delay should be approximately 1 second");
    }
}
