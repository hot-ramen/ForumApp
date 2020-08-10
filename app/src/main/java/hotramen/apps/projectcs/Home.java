package hotramen.apps.projectcs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.ImageView;
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

    @ViewById
    ImageView ivDP;


    Realm realm;

    FeedAdapter adapter;



    @AfterViews
    public void init() {

        SharedPreferences sharedPreferences = getSharedPreferences("login_info", MODE_PRIVATE);
        String userID = sharedPreferences.getString("uuid", null);

        realm = Realm.getDefaultInstance();

        User userLoggedIn = realm.where(User.class).equalTo("uuid", userID).findFirst();

        assert userLoggedIn != null;
        String username = userLoggedIn.getName();
        String bio = userLoggedIn.getBio();

        tvUsername.setText(username);

        if(bio.equals(" ")){
            tvBio.setText("Add a bio now!");
        }
        else{
            tvBio.setText(bio);
        }



        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvFeed.setLayoutManager(mLayoutManager);

        realm = Realm.getDefaultInstance();

        RealmResults<Thread> list = realm.where(Thread.class)
                .findAll()
                .sort("title", Sort.ASCENDING);

        adapter = new FeedAdapter(this, list, true);
        rvFeed.setAdapter(adapter);
    }

    @Click(R.id.btnLogOut)
    public void close() {
        finish();
        MainActivity_.intent(this).start();
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

    @Click(R.id.btnBio)
    public void addbio(){
        AddBio_.intent(this)
                .start();

    }


}