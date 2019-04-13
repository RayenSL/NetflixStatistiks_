package DomainLayer;

public class Profile {
    private int profileId;
    private String profileName;
    private int itemId;
    private String dOB;

    public Profile(int profileId, String profileName, int itemId, String dOB) {
        setProfileId(profileId);
        setProfileName(profileName);
        setItemId(itemId);
        setdOB(dOB);
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getdOB() {
        return dOB;
    }

    public void setdOB(String dOB) {
        this.dOB = dOB;
    }
}
