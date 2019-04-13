package DomainLayer;

public class Footage {
    private int itemId;
    private String title;
    private int ageChecker;
    private Genre genre;
    private Language language;

    public Footage(int itemId, String title, Genre genre, Language language, int ageChecker) {
        setItemId(itemId);
        setTitle(title);
        setGenre(genre);
        setLanguage(language);
        setAgeChecker(ageChecker);
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAgeChecker() {
        return ageChecker;
    }

    public void setAgeChecker(int ageChecker) {
        this.ageChecker = ageChecker;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getGenreString(){
        return String.valueOf(getGenre());
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Language getLanguage() {
        return language;
    }

    public String getLanguageString(){
        return String.valueOf(getLanguage());
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
