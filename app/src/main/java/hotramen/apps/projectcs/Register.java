package hotramen.apps.projectcs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.UUID;

import io.realm.Realm;

@EActivity(R.layout.register)
public class Register extends AppCompatActivity {

    @ViewById
    EditText etUsernameReg;

    @ViewById
    EditText etPassReg;

    @ViewById
    EditText etPassConfirmReg;

    Realm realm;

    @AfterViews
    public void init(){
        realm = Realm.getDefaultInstance();
    }

    @Click(R.id.btnRegister)
    public void register() {

        User nameExists = realm.where(User.class)
                .equalTo("name", etUsernameReg.getText().toString())
                .findFirst();

        String userField = etUsernameReg.getText().toString();
        String passField = etPassReg.getText().toString();
        String passconfirmField = etPassConfirmReg.getText().toString();

        if (nameExists!=null){
            Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_LONG).show() ;
        }
        else if(TextUtils.isEmpty(userField) || TextUtils.isEmpty(passField) || TextUtils.isEmpty(passconfirmField) ){
            Toast.makeText(getApplicationContext(), "Do not leave any fields empty", Toast.LENGTH_LONG).show() ;
        }
        else if (!passField.equals(passconfirmField)){
            Toast.makeText(getApplicationContext(), "Password not matched", Toast.LENGTH_LONG).show() ;
        }
        else {

            User newUser = new User();
            newUser.setUuid(UUID.randomUUID().toString());
            newUser.setName(userField);
            newUser.setPassword(passField);
            newUser.setBio(" ");


            long count = 0;

            try {
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(newUser);

                count = realm.where(User.class).count();

                realm.commitTransaction();

                Toast t = Toast.makeText(this, "Registered: "+count, Toast.LENGTH_LONG);
                t.show();
                finish();
            }

            catch(Exception e)
            {
                Toast t = Toast.makeText(this, "Error registering", Toast.LENGTH_LONG);
                t.show();
            }



        }
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.register);
//    }
}