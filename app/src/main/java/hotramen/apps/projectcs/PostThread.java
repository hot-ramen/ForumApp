package hotramen.apps.projectcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.UUID;

import io.realm.Realm;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

@EActivity(R.layout.post_thread)
public class PostThread extends AppCompatActivity {


    @Extra("author")
    String author;

    @ViewById
    EditText etTitle;

    @ViewById
    EditText etContent;




    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_thread);


        realm = Realm.getDefaultInstance();

    }


    @Click(R.id.btnCancel)
    public void cancel(View view){
        finish();
    }

    @Click(R.id.btnPost)
    public void post(){
        User userToPost = realm.where(User.class).equalTo("uuid", author).findFirst();
        assert userToPost != null;

        long count = 0;
        if(etContent.length() == 0){

            realm.beginTransaction();
            Thread newThread =  new Thread();
            newThread.setUuid(UUID.randomUUID().toString());
            newThread.setTitle(etTitle.getText().toString());
            newThread.setContent(" ");
            newThread.setAuthor(userToPost);
            userToPost.getThreads().add(newThread);
            realm.commitTransaction();



            try {
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(newThread);

                count = realm.where(Thread.class).count();

                realm.commitTransaction();

                Toast t = Toast.makeText(this, "Posted: " + count, Toast.LENGTH_LONG);
                t.show();

               finish();

            } catch (RealmPrimaryKeyConstraintException e) {
                e.printStackTrace();
                Toast t = Toast.makeText(this, "Error Saving", Toast.LENGTH_LONG);
                t.show();
            }
        }

        else if(etTitle.length() != 0 && etContent.length() != 0){
            realm.beginTransaction();
            Thread newThread =  new Thread();
            newThread.setUuid(UUID.randomUUID().toString());
            newThread.setTitle(etTitle.getText().toString());
            newThread.setContent(etContent.getText().toString());
            newThread.setAuthor(userToPost);
            userToPost.getThreads().add(newThread);
            realm.commitTransaction();

            try {
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(newThread);

                count = realm.where(Thread.class).count();

                realm.commitTransaction();

                Toast t = Toast.makeText(this, "Posted: " + count, Toast.LENGTH_LONG);
                t.show();

               finish();

            } catch (RealmPrimaryKeyConstraintException e) {
                e.printStackTrace();
                Toast t = Toast.makeText(this, "Error Saving", Toast.LENGTH_LONG);
                t.show();
            }
        }
    }
}