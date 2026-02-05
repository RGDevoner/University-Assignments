public class Review {
    private int Phycologicalmoodrating;
    private String ActivityRating;
    private String HardOrSoftRating;
    private static int totalReviews;

    // Constructor
    public Review(String Phycologicalmoodrating, String activityRating, String HardOrSoftRating) {
        this.Phycologicalmoodrating = Phycologicalmoodrating;
        this.ActivityRating = activityRating;
        this.HardOrSoftRating = HardOrSoftRating;
        totalReviews++;
    }

    // Getters
    public String getPhycologicalmoodrating() {
        return Phycologicalmoodrating;
    }

    public String getActivityRating() {
        return ActivityRating;
    }

    public String getHardOrSoftRating() {
        return HardOrSoftRating;
    }

    // Setters
    public void setPhycologicalmoodrating(String Phycologicalmoodrating) {
        this.Phycologicalmoodrating = Phycologicalmoodrating;
    }

    public void setActivityRating(String activityRating) {
        this.ActivityRating = activityRating;
    }

    public void setHardOrSoftRating(String HardOrSoftRating) {
        this.HardOrSoftRating = HardOrSoftRating;
    }
    public static int getTotalReviews() {
        return totalReviews;
    }
}