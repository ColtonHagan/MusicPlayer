import java.util.List;

public class Playlist {
    private String name = "";
    private List<Song> songs = null;
    public Playlist(String name, List<Song> songs) {
        this.name = name;
        this.songs = songs;
    }

    public String getName() { return name; }
    public List<Song> getSongs() { return songs; }
    public Boolean addSong(Song song) {
        if(!matches(song)) {
            songs.add(song);
            return true;
        }
        return false;
    }
    public Boolean removeSong(Song song) {
        if(matches(song)) {
            songs.remove(song);
            return true;
        }
        return false;
    }
    public Boolean matches(Song song) {
        for(Song s : songs) {
            if(s.getTitle().equals(song.getTitle()) && s.getAlbum().equals(song.getAlbum())) return true;
        }
        return false;
    }
}
