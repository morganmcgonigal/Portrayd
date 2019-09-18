package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Religion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int religionId;

    private String religionName;
    private String religionHistory;
    private String religionNotes;
    private String religionPicture;

    public int getReligionId() {
        return religionId;
    }

    public void setReligionId(int religionId) {
        this.religionId = religionId;
    }

    public String getReligionName() {
        return religionName;
    }

    public void setReligionName(String religionName) {
        this.religionName = religionName;
    }

    public String getReligionHistory() {
        return religionHistory;
    }

    public void setReligionHistory(String religionHistory) {
        this.religionHistory = religionHistory;
    }

    public String getReligionNotes() {
        return religionNotes;
    }

    public void setReligionNotes(String religionNotes) {
        this.religionNotes = religionNotes;
    }

    public String getReligionPicture() {
        return religionPicture;
    }

    public void setReligionPicture(String religionPicture) {
        this.religionPicture = religionPicture;
    }
}
