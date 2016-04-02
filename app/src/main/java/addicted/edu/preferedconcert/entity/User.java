package addicted.edu.preferedconcert.entity;

import java.util.ArrayList;

/**
 * Created by addicted on 02.04.16.
 */
public class User {
    private String email;
    private String password;
    private boolean isAdmin;
    private ArrayList<String> favorites = new ArrayList<String>();

    public ArrayList<String> getFavorites() {
        return favorites;
    }

    public boolean addFavorite(String favorite) {
        if (favorites.contains(favorite)) return false;
        favorites.add(favorite);
        return true;
    }

    public void removeFavorite(String favorite) {
        favorites.remove(favorite);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return email.equals(user.email);

    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
