package com.example.gihan.mashawyery.ui;

import android.content.Intent;
import android.os.Bundle;
;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gihan.mashawyery.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    //firebase
    private DatabaseReference mUserDataBase;
    private FirebaseUser currentUser;
    FirebaseAuth mAuth;
    private DatabaseReference mRootRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("مشاويري ");
        setSupportActionBar(toolbar);

        Button addTrip = (Button) findViewById(R.id.btn_add_trip);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        //----------head of navigation drawer --------------------------------------------------------------------

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View v = navigationView.getHeaderView(0);
        final TextView email = (TextView) v.findViewById(R.id.main_tv_email);
        final TextView name = (TextView) v.findViewById(R.id.main_tv_name);
        final CircleImageView image = (CircleImageView) v.findViewById(R.id.main_image_view);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = currentUser.getUid();
        mUserDataBase = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
        mUserDataBase.keepSynced(true);
        mUserDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String namee = dataSnapshot.child("name").getValue().toString();
                String emaile = dataSnapshot.child("email").getValue().toString();
                String imagee = dataSnapshot.child("image").getValue().toString();
                email.setText(emaile);
                name.setText(namee);

                Picasso.with(getApplicationContext()).load(imagee).placeholder(R.drawable.man).into(image);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent personalIntent = new Intent(getApplicationContext(), PersonalActivity.class);
                startActivity(personalIntent);
            }
        });

        navigationView.setNavigationItemSelectedListener(this);
        //-------------------------------------------------------------------

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent firstIntent=new Intent(getApplicationContext(),MainMap.class);
            startActivity(firstIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        // Handle the action of item

        if (id == R.id.nav_payment) {
            Intent paymentIntent = new Intent(getApplicationContext(), PaymentActivity.class);
            startActivity(paymentIntent);

        } else if (id == R.id.nav_trips) {
            Intent tripActivity = new Intent(getApplicationContext(), TripsActivity.class);
            startActivity(tripActivity);

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.help) {

        } else if (id == R.id.phone) {

        } else if (id == R.id.nav_logout) {
            mAuth.signOut();
            Intent loginIntent = new Intent(getApplicationContext(), Login.class);
            startActivity(loginIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();


        FirebaseUser User=FirebaseAuth.getInstance().getCurrentUser();

        if(User==null) {
            sendToStart();
        }
        }

    private void sendToStart() {
        Intent firstIntent=new Intent(getApplicationContext(),First.class);
        startActivity(firstIntent);
        finish();
    }

    void addTrip() {
        mAuth = FirebaseAuth.getInstance();
        String mCurrentUserId = mAuth.getCurrentUser().getUid();

        String mDriver = "4MuHB3Tim5deVi29ahEceMUXgOw1";


        String currentUserRef = "trips/" + mCurrentUserId ;
        String driverRef = "trips/" + mDriver ;
        mRootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userTripPush = mRootRef.child("trips").child(mCurrentUserId).child(mDriver).push();
        String push_id = userTripPush.getKey();

        Map tripsMap = new HashMap();
        tripsMap.put("from", "الشهداء");
        tripsMap.put("to", "مطار اسكندريه ");
        tripsMap.put("driver", mDriver);
        tripsMap.put("user",mCurrentUserId);
        tripsMap.put("day","الخميس");
        tripsMap.put("date", "s5/9/2018");
        tripsMap.put("time"," 5 :00 صباحا ");
        tripsMap.put("distance", "250");
        tripsMap.put("price", "3");
        tripsMap.put("waitTime", "2.5");
        tripsMap.put("cost", "750");
        tripsMap.put("kindOFTrip", "ملاكي ");
        tripsMap.put("kindCar"," لانسر  ");



        Map tripUserMap = new HashMap();

        tripUserMap.put(currentUserRef + "/" + push_id, tripsMap);
        tripUserMap.put(driverRef + "/" + push_id, tripsMap);

        mRootRef.updateChildren(tripUserMap, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                if (databaseError != null) {
                    Toast.makeText(getApplicationContext(), "Error when senfd message", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "correct", Toast.LENGTH_LONG).show();

                }

            }
        });

    }
}
