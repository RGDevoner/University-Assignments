import java.util.*;
public class Admin {
    private String username;
    private String password;
    private String AccountType;
    
    private Set<Song> songs = new HashSet<Song>();

    Admin(String username, String password, String AccountType) {
        this.username = username;
        this.password = password;
        this.AccountType = AccountType;
    }
    //Getters
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getAccountType() {return AccountType;}
    public Set<Song> getSongs() {return new HashSet<Song>(songs);}
    
    //Setters
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    
    public void addSong() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Title: ");
        String title = scanner.nextLine();
        System.out.println("Artist: ");
        String artist = scanner.nextLine();
        System.out.println("Description: ");
        String description = scanner.nextLine();
        System.out.println("ProductionYear: ");
        String productionYear = scanner.nextLine();
        System.out.println("Music Genre: ");
        String musicGenre = scanner.nextLine();

        songs.add(new Song(title,artist,description,productionYear,musicGenre));
    }
    public void removeSong(Song song) {

      
    }
    
    
}
    
    
