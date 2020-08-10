package hotramen.apps.projectcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.realm.Realm;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById
    EditText etUsername;

    @ViewById
    EditText etPassword;

    @ViewById
    CheckBox cbRemember;


    Realm realm = Realm.getDefaultInstance();



    @AfterViews
    public void init() {
        SharedPreferences sharedPreferences = getSharedPreferences("login_info", MODE_PRIVATE);
        boolean isRemembered = sharedPreferences.getBoolean("isRemembered", false);
        String userID = sharedPreferences.getString("uuid", null);

        User userRemembered = realm.where(User.class).equalTo("uuid", userID).findFirst();

        if (isRemembered){
            assert userRemembered != null;
            etUsername.setText(userRemembered.getName());
            etPassword.setText(userRemembered.getPassword());
            cbRemember.setChecked(true);
        }



    }

    @Click(R.id.btnRegister)
    public void register(){
        Register_.intent(this)
                .start();
    }


    @Click(R.id.btnSignIn)
    public void login(){
        SharedPreferences sharedPreferences = getSharedPreferences("login_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

//        String u = sharedPreferences.getString("username", null);
//        String p = sharedPreferences.getString("password", null);

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        long result = realm.where(User.class)
                .equalTo("name", username)
                .count();
//        if(u == null && TextUtils.isEmpty(user) && TextUtils.isEmpty(pass)){

        User userSignIn = realm.where(User.class)
                .equalTo("name", username)
                .findFirst();


        if(username.equals("admin") && password.equals("123")){
            AdminHome_.intent(this)
                    .start();
        }
        else if(result == 0){
            Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_LONG).show() ;
        }
        else {
            assert userSignIn != null;

            if(!userSignIn.getPassword().equals(password)){
                Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_LONG).show() ;
            }

            else{
                Home_.intent(this)
                        .start();
                editor.putBoolean("isRemembered", cbRemember.isChecked());
                editor.putString("uuid", userSignIn.getUuid());
                editor.apply();

            }
        }
    }


//    @Click(R.id.btnClear)
//    public void clear(){
//        SharedPreferences sharedPreferences = getSharedPreferences("login_info", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
//        editor.apply();
//
//        etUsername.setText("");
//        etPassword.setText("");
//        cbRemember.setChecked(false);
//
//        Toast.makeText(getApplicationContext(), "Preferences Cleared", Toast.LENGTH_LONG).show();
//    }

}