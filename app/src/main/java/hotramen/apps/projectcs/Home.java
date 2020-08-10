package hotramen.apps.projectcs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

@EActivity(R.layout.home)
public class Home extends AppCompatActivity {

    @ViewById
    RecyclerView rvFeed;

    @ViewById
    Button btnMakeTrd;

    @ViewById
    Button btnEditProf;


    @ViewById
    TextView tvUsername;

    @ViewById
    TextView tvBio;


    Realm realm;

    FeedAdapter adapter;


    @AfterViews
    public void init() {

        SharedPreferences sharedPreferences = getSharedPreferences("login_info", MODE_PRIVATE);
        String userID = sharedPreferences.getString("uuid", null);

        User userLoggedIn = realm.where(User.class).equalTo("uuid", userID).findFirst();

        String username = userLoggedIn.getName();
        String bio = userLoggedIn.getBio();

        tvUsername.setText(username);
        tvBio.setText(bio);


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvFeed.setLayoutManager(mLayoutManager);

        realm = Realm.getDefaultInstance();

        RealmResults<Thread> list = realm.where(Thread.class)
                .findAll()
                .sort("title", Sort.ASCENDING);

        adapter = new FeedAdapter(this, list, true);
        rvFeed.setAdapter(adapter);

        //spaghetti code pa to copy past lang for init
    }

    @Click(R.id.btnSignOut)
    public void close() {
        finish();
    }

    @Click(R.id.btnEditProf)
    public void editprofile(){
        SharedPreferences sharedPreferences = getSharedPreferences("login_info", MODE_PRIVATE);
        String userID = sharedPreferences.getString("uuid", null);

        EditActivity_.intent(this)
                .id(userID)
                .start();
    }

    @Click(R.id.btnMakeTrd)
    public void makethread(){
        SharedPreferences sharedPreferences = getSharedPreferences("login_info", MODE_PRIVATE);
        String userID = sharedPreferences.getString("uuid", null);

        PostThread_.intent(this)
                .author(userID)
                .start();
    }


}