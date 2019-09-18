package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Universe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int universeId;

    private String universeName;
    private String universeDesc;
    private String universeGenre;
    private String universeHistory;
    private String universeNotes;
    private String universePicture;

    public int getUniverseId() {
        return universeId;
    }

    public void setUniverseId(int universeId) {
        this.universeId = universeId;
    }

    public String getUniverseName() {
        return universeName;
    }

    public void setUniverseName(String universeName) {
        this.universeName = universeName;
    }

    public String getUniverseDesc() {
        return universeDesc;
    }

    public void setUniverseDesc(String universeDesc) {
        this.universeDesc = universeDesc;
    }

    public String getUniverseGenre() {
        return universeGenre;
    }

    public void setUniverseGenre(String universeGenre) {
        this.universeGenre = universeGenre;
    }

    public String getUniverseHistory() {
        return universeHistory;
    }

    public void setUniverseHistory(String universeHistory) {
        this.universeHistory = universeHistory;
    }

    public String getUniverseNotes() {
        return universeNotes;
    }

    public void setUniverseNotes(String universeNotes) {
        this.universeNotes = universeNotes;
    }

    public String getUniversePicture() {
        return universePicture;
    }

    public void setUniversePicture(String universePicture) {
        this.universePicture = universePicture;
    }
}
