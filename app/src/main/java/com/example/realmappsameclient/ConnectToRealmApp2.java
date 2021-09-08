package com.example.realmappsameclient;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.realmappsameclient.model.PersonB;
import com.example.realmappsameclient.module.RealmApp2;

import org.bson.types.ObjectId;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.sync.SyncConfiguration;

public class ConnectToRealmApp2 {

    App app;
    String timeStamp;
    SimpleDateFormat simpleDateFormat;

    final String firstName = "Mike";
    final String lastName = "Smith";
    final String _partition = "two";
    final String RealmAppID02  = "<<Realm App ID 2>>";
    private static final String TAG = "MongoDB RealmApp02";

    public ConnectToRealmApp2() {}

    public void RealmApp2(Context context) {

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        timeStamp = simpleDateFormat.format(new Date());

        Credentials credentials = Credentials.anonymous();
        app = new App(new AppConfiguration.Builder(RealmAppID02).build());
        app.loginAsync(credentials, it -> {
            if (it.isSuccess()) {
                Log.w(TAG,"MongoDB Auth: Success");

                User user = app.currentUser();
                if (user!= null){
                    SyncConfiguration config = new SyncConfiguration.Builder(user, _partition)
                            .modules(new RealmApp2())
                            .allowQueriesOnUiThread(true)
                            .allowWritesOnUiThread(true)
                            .build();

                    Realm.getInstanceAsync(config, new Realm.Callback() {
                        @Override
                        public void onSuccess(Realm realm) {
                            Log.w(TAG,"PersonB MongoDB Realm: "+ "Successfully opened a realm");

                            // send data to MongoDB with DataAvailable flag false
                            realm.executeTransaction(r -> {
                                // Instantiate the class using the factory function.
                                PersonB person = r.createObject(PersonB.class, new ObjectId());
                                // Configure the instance.
                                person.setFirtsName(firstName);
                                person.setLastName(lastName);
                                person.set_partition(_partition);
                                person.setTimestamp(timeStamp);
                            });

                            Log.w(TAG, "PersonB document inserted in MongoDB successfully");

                            realm.close();
                            Log.w(TAG,"PersonB MongoDB Realm closed");

                        }
                    });
                }else {
                    Log.w(TAG,"PersonB MongoDBx Realm: Unsuccessful in opening the realm");
                }

            } else {
                Log.e("MongoDB Auth", it.getError().toString());
                Toast.makeText(context,"Error while connecting to MongoDB",Toast.LENGTH_LONG).show();
            }
        });



    }

}
