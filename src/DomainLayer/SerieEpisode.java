package DomainLayer;

public class SerieEpisode {
    private int episodeId;
    private int itemId;
    private String title;
    private String duration;

    public SerieEpisode(int episodeId, int itemId, String title, String duration) {
        setItemId(itemId);
        setEpisodeId(episodeId);
        setTitle(title);
        setDuration(duration);
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "SerieEpisode{" +
                "title='" + title + '\'' +
                '}';
    }
}
