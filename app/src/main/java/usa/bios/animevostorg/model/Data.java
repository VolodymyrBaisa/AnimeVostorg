package usa.bios.animevostorg.model;

import org.parceler.Parcel;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Bios on 8/16/2017.
 */

@Parcel(value = Parcel.Serialization.BEAN, analyze = { Data.class })
@RealmClass
public class Data extends RealmObject {
    @PrimaryKey
    private int id;
    private RealmList<RealmString> screenImage;
    private int rating;
    private int votes;
    private String description;
    private int isFavorite;
    private String title;
    private String series;
    private String count;
    private Long timer;
    private String director;
    private String urlImagePreview;
    private String year;
    private String genre;
    private String type;
    private int isLikes;

    public int getId() {
        return id;
    }

    public RealmList<RealmString> getScreenImage() {
        return screenImage;
    }

    public int getRating() {
        return rating;
    }

    public int getVotes() {
        return votes;
    }

    public String getDescription() {
        return description;
    }

    public int getIsFavorite() {
        return isFavorite;
    }

    public String getTitle() {
        return title;
    }

    public String getSeries() {
        return series;
    }

    public String getCount() {
        return count;
    }

    public Long getTimer() {
        return timer;
    }

    public String getDirector() {
        return director;
    }

    public String getUrlImagePreview() {
        return urlImagePreview;
    }

    public String getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getType() {
        return type;
    }

    public int getIsLikes() {
        return isLikes;
    }
}
