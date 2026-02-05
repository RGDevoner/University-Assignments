import java.util.Scanner;

public class Song {
    private String title;
    private String artist;
    private String description;
    private String productionYear;
    private String lastStreamed;
    private String musicGenre;

//  private Set<Song,ReviewArray> Song;
    private double averagePhysiologicalMoodRating;
    private double averageActivityRating;
    private double averageHardOrSoftRating;

    private int[] moodRatingCount = new int[3];
    private int[] activityRatingCount = new int[3];
    private int[] hardOrSoftRatingCount = new int[3];

    // Constructor
    public Song(String title, String artist, String description, String productionYear, String musicGenre) {
        this.title = title;
        this.artist = artist;
        this.description = description;
        this.productionYear = productionYear;
        this.musicGenre = musicGenre;

        this.averagePhysiologicalMoodRating=0;
        this.averageActivityRating=0;
        this.averageHardOrSoftRating=0;

        this.moodRatingCount=0;
        this.activityRatingCount=0;
        this.hardOrSoftRatingCount=0;
    }


    // Getters and Setters για Title
    public void setTitle(String title) {
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    //Getters and Setters για Artist
    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    //Getters and Setters για Description
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    //Getters and Setters για ProductionYear
    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public String getProductionYear() {
        return productionYear;
    }
    
    // Setter and Setters για το lastStreamed
    public void setLastStreamed(String lastStreamed) {
        this.lastStreamed = lastStreamed;
    }

    public String getLastStreamed() {
        return lastStreamed;
    }

    //Getters and Setters για MusicGenre
    public String getMusicGenre() {
        return musicGenre;
    }

    public void setMusicGenre(String MusicGenre) {
        this.MusicGenre = MusicGenre;
    }

    //AveragePhycologicalmoodrating 
    public getaveragePhysiologicalMoodRating(){
        return averagePhysiologicalMoodRating;
    }
    public void RatingReviews(){
         
        Scanner scanner = new Scanner(System.in);
        String 

        if rating.equals(happy){
            
        }


    }

}

    