package hotramen.apps.projectcs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    Button btnSignOut;


    Realm realm;


    @AfterViews
    public void init() {
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
//        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
//        rvFeed.setLayoutManager(mLayoutManager);
//
//        realm = Realm.getDefaultInstance();
//
//        RealmResults<User> list = realm.where(User.class)
//                .findAll()
//                .sort("name", Sort.ASCENDING);
//
//        adapter = new UserAdapter(this, list, true);
//        rvFeed.setAdapter(adapter);

        //spaghetti code pa to copy past lang for init
    }

    @Click(R.id.btnSignOut)
    public void close() {
        finish();
    }


    public void delete(View view) {
//        User user = (User) view.getTag();
//
//        // once an object is deleted you cant delete it again, check if isValid
//        // this is needed since you can double tap a button very easily
//        if (user.isValid())
//        {
//            realm.beginTransaction();
//            user.deleteFromRealm();
//            realm.commitTransaction();
//        }
    }

    public void edit(View view) {
//        User userToEdit = (User) view.getTag();
//        String id = userToEdit.getUuid();
//
//
//        EditActivity_.intent(this)
//                .id(id)
//                .start();
//    }
    }
}