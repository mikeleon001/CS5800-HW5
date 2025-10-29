package proxy;

public class Song {
    private Integer songID;
    private String title;
    private String artist;
    private String album;
    private int duration;
    
    public Song(Integer songID, String title, String artist, String album, int duration) {
        this.songID = songID;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }
    
    public Integer getSongID() {
        return songID;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getArtist() {
        return artist;
    }
    
    public String getAlbum() {
        return album;
    }
    
    public int getDuration() {
        return duration;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return songID.equals(song.songID);
    }
    
    @Override
    public int hashCode() {
        return songID.hashCode();
    }
}
