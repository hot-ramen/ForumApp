package hotramen.apps.projectcs;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Thread extends RealmObject {

    @PrimaryKey
    private String userID;

    private String title;
    private String content;

    public String getUserID() {
        return userID;
    }

    public void getUserID(String uuid) {
        this.userID = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid='" + userID + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}


