package hotramen.apps.projectcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.realm.Realm;

@EActivity(R.layout.bio)
public class AddBio extends AppCompatActivity {

    @ViewById
    EditText etBio;

    Realm realm;

    @AfterViews
    public void init(){
        SharedPreferences sharedPreferences = getSharedPreferences("login_info", MODE_PRIVATE);
        String userID = sharedPreferences.getString("uuid", null);

        realm = Realm.getDefaultInstance();

        User userLoggedIn = realm.where(User.class).equalTo("uuid", userID).findFirst();

        assert userLoggedIn != null;
        String existingBio = userLoggedIn.getBio();

        if(!existingBio.equals(" ")){
            etBio.setText(existingBio);
        }

    }

    @Click(R.id.btnSetBio)
    public void setbio(){
        SharedPreferences sharedPreferences = getSharedPreferences("login_info", MODE_PRIVATE);
        String userID = sharedPreferences.getString("uuid", null);

        realm = Realm.getDefaultInstance();

        User userLoggedIn = realm.where(User.class).equalTo("uuid", userID).findFirst();
        assert userLoggedIn != null;

        String bio = etBio.getText().toString();

        if(TextUtils.isEmpty(bio)){
            try {
                realm.beginTransaction();
                userLoggedIn.setBio(" ");
                realm.copyToRealmOrUpdate(userLoggedIn);
                realm.commitTransaction();

                Toast t = Toast.makeText(this, "Updated!", Toast.LENGTH_LONG);
                t.show();
                finish();
                Home_.intent(this).start();
            }

            catch(Exception e)
            {
                Toast t = Toast.makeText(this, "Error registering", Toast.LENGTH_LONG);
                t.show();
            }
        }
        else {
            try {
                realm.beginTransaction();
                userLoggedIn.setBio(bio);
                realm.copyToRealmOrUpdate(userLoggedIn);
                realm.commitTransaction();

                Toast t = Toast.makeText(this, "Updated!", Toast.LENGTH_LONG);
                t.show();
                finish();
                Home_.intent(this).start();
            }

            catch(Exception e)
            {
                Toast t = Toast.makeText(this, "Error registering", Toast.LENGTH_LONG);
                t.show();
            }
        }

    }

}