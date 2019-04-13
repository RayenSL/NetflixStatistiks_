package DomainLayer;

public class Serie extends Footage {

    private String Similar;

    public Serie(int itemId, String title, int ageChecker, Genre genre, Language language) {
        super(itemId, title, ageChecker, genre, language);
    }

    public String getSimilar() {
        return Similar;
    }

    public void setSimilar(String similar) {
        Similar = similar;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
