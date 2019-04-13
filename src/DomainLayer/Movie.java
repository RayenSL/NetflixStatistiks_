package DomainLayer;

public class Movie extends Footage {

    private String duration;


    public Movie(int itemId, String title, int ageChecker, Genre genre, Language language) {
        super(itemId, title, ageChecker, genre, language);

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
