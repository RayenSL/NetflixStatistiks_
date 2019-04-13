package DomainLayer;

public class Movie extends Footage {

    private String duration;

    public Movie(int itemId, String title, String duration, Genre genre, Language language, int ageChecker) {
        super(itemId, title, genre, language, ageChecker);
        setDuration(duration);
    }


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
