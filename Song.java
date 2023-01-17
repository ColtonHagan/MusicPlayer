public class Song {
    private String title = "";
    private String artist = "";
    private String album = "";
    private Double duration = 0.0;
    public Song(String title, String artist, String album, Double duration) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
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
    public Double getDuration() {
        return duration;
    }
    @Override
    public String toString() {
        return title + " " + artist + " " + album + " " + duration;
    }
}
