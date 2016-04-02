package addicted.edu.preferedconcert.entity;

/**
 * Created by addicted on 29.03.16.
 */
public class Song {
    private String title;
    private int votes;

    public Song(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int addVote() {
        votes++;
        return votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        return title.equals(song.title);

    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
