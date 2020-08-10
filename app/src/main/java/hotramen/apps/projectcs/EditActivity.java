package hotramen.apps.projectcs;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import io.realm.Realm;

@EActivity(R.layout.edit)
public class EditActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.edit);
//    }

    @Extra("data")
    String id;

    @ViewById
    EditText etUserEdit;

    @ViewById
    EditText etPassEdit;

    @ViewById
    EditText etConfirmPassEdit;

    Realm realm;

    @AfterViews
    public void init() {
        realm = Realm.getDefaultInstance();

        User userToEdit = realm.where(User.class).equalTo("uuid", id).findFirst();
        assert userToEdit != null;
        String currentName = userToEdit.getName();

        etUserEdit.setText(currentName);
    }

    @Click(R.id.btnEdit)
    public void edit() {
        User nameExists = realm.where(User.class)
                .equalTo("name", etUserEdit.getText().toString())
                .findFirst();

        String userField = etUserEdit.getText().toString();
        String passField = etPassEdit.getText().toString();
        String passConfirmField = etConfirmPassEdit.getText().toString();

        User userToEdit = realm.where(User.class).equalTo("uuid", id).findFirst();
        assert userToEdit != null;

        if (nameExists != null && !userField.equals(userToEdit.getName())) {
            Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(userField) || TextUtils.isEmpty(passField) || TextUtils.isEmpty(passConfirmField)) {
            Toast.makeText(getApplicationContext(), "Do not leave any fields empty", Toast.LENGTH_LONG).show();
        }
        else if (!passField.equals(passConfirmField)) {
            Toast.makeText(getApplicationContext(), "Password not matched", Toast.LENGTH_LONG).show();
        }
        else {

            try {
                realm.beginTransaction();
                userToEdit.setName(userField);
                userToEdit.setPassword(passField);
                realm.copyToRealmOrUpdate(userToEdit);
                realm.commitTransaction();

                Toast t = Toast.makeText(this, "Updated!", Toast.LENGTH_LONG);
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
}