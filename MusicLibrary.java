//see README for information about project to lazy to type it up again
//code is simple if you need notes for it in the future stop being dumb

import java.io.*;
import java.util.*;
public class MusicLibrary {
    private static List<Playlist> playlists = new ArrayList<>();
    public static void main(String[] args) throws IOException {
		Menu();
	}

	public static void Menu() throws IOException {
		System.out.println("Please enter below number to execute function");
		System.out.println("1. Create playlist, 2. Add song, 3. Remove song, 4. Search song, 5. Play song, 6. Exit");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Song song = null;
        Playlist play = null;

        switch(reader.readLine()) {
			case "1":
                play = createPlaylist(promptString("Enter playlist name:", reader));
                if(play == null) System.out.println("Playlist already exists");
                else System.out.println("Playlist " + play.getName() + " created");
			    break;
			case "2":
                while(play == null) {
                    play = getPlaylist(promptString("Enter playlist you want to add song to:", reader));
                }

                while(song == null) {
                    String name = promptString("Song name:", reader);
                    String album = promptString("Album name:", reader);
                    String artist = promptString("Artist name:", reader);
                    Double duration = promptDouble("Duration of song:", reader);
                    if(name != null && album != null && artist != null && duration != null) {
                        song = new Song(name, album, artist, duration);
                    } else {
                        System.out.println("Error entering information please retry");
                    }
                }
                addSong(play, song);
			    break;
			case "3":
                while(play == null) {
                    play = getPlaylist(promptString("Enter playlist you want to remove song from:", reader));
                }

                while(song == null) {
                    song = getSong(promptString("Enter song you want to remove:", reader));
                }

                removeSong(play, song);
			    break;
			case "4":
                List<Song> foundSongs = searchSong(promptString("Enter song keyword name:", reader));
                if(foundSongs.isEmpty()) System.out.println("no songs found with keyword");
                else {
                    System.out.println("Songs found with keyword:");
                    for(Song s : foundSongs) {
                        System.out.println("   " + s.getTitle() + " by " + s.getArtist());
                    }
                }
			    break;
            case "5":
                while(song == null) {
                    song = getSong(promptString("Enter song you want to play:", reader));
                }
                playSong(song);
			    break;
			case "6":
                System.exit(0);
			    break;
			default:
				Menu();
		}
        //for testing
        //printLibrary();
        Menu();
	}

    public static Playlist createPlaylist(String name) {
        for(Playlist play : playlists) {
            if(play.getName().equals(name)) return null;
        }
        Playlist play = new Playlist(name, new ArrayList<Song>());
        playlists.add(new Playlist(name, new ArrayList<Song>()));
        return play;
    }
    public static void addSong(Playlist playlist, Song song) {
        Boolean added = playlists.get(playlists.indexOf(playlist)).addSong(song);
        if(!added) System.out.println(song.getTitle() + " already in " + playlist.getName());
    }
    public static void removeSong(Playlist playlist, Song song) {
        Boolean added = playlists.get(playlists.indexOf(playlist)).removeSong(song);
        if(!added) System.out.println(song.getTitle() + " is not in the " + playlist.getName());
    }
    public static List<Song> searchSong(String keyword) {
        List<Song> matches = new ArrayList<>();
        for(Playlist play : playlists) {
            for(Song song : play.getSongs()) {
                if(song.getTitle().contains(keyword) || song.getArtist().contains(keyword) || song.getAlbum().contains(keyword)) {
                    matches.add(song);
                }
            }
        }
        return matches;
    }
    public static void playSong(Song song) {
        System.out.println(song.toString());
    }
    public static Song getSong(String name) {
        for(Playlist play : playlists) {
            for(Song song : play.getSongs()) {
                if(song.getTitle().equals(name)) return song;
            }
        }
        System.out.println("Song with given name not found");
        return null;
    }
    public static Playlist getPlaylist(String name) {
        for(Playlist play : playlists) {
            if(play.getName().equals(name)) return play;
        }
        System.out.println("Playlist with given name not found");
        return null;
    }
    public static void printLibrary() {
        System.out.println("=====Song library=====");
        for(Playlist play : playlists) {
            System.out.println(play.getName());
            for(Song song : play.getSongs()) {
                System.out.println("   " + song.toString());
            }
        }
        System.out.println("======================");
    }
    public static String promptString(Object prompt, BufferedReader reader) throws IOException {
        String s = "";
        while(s == null || s.equals("")) {
            if(s == null) System.out.println("Error please re-enter");
            else System.out.println(prompt);
            s = parseString(reader.readLine());
        }
        return s;
    }
    public static String parseString(String s) {
        if(s == null || s.length() == 0) return null;
        return s;
    }
    public static Double promptDouble(Object prompt, BufferedReader reader) throws IOException {
        Double d = -1.0;
        while(d == null || d < 0.0) {
            if(d == null) System.out.println("Error please re-enter");
            else System.out.println(prompt);
            d = parseDouble(reader.readLine());
        }
        return d;
    }
    public static Double parseDouble(String s) {
        Double d = 0.0;
        s = s.replace(",","");
        if(s == null || s.length() == 0) return null;
        try {
            d = Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return null;
        }
        if(d < 0.0) return null;
        return d;
    }
}