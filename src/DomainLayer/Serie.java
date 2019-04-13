package DomainLayer;

public class Serie extends Footage {

    private String Similar;

    public Serie(int itemId, String title, Genre genre, Language language, int ageChecker) {
        super(itemId, title, genre, language, ageChecker);
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
