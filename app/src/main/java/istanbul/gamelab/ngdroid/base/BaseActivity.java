package istanbul.gamelab.ngdroid.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gamelab.karagozhacivat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ngdroidapp.GameStatistic;
import com.ngdroidapp.PlayerData;

import istanbul.gamelab.ngdroid.core.AppManager;
import istanbul.gamelab.ngdroid.util.Log;

public class BaseActivity extends Activity {
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private PlayerData otherPlayerData;
    private boolean isRoom;
    private static final String TAG = BaseActivity.class.getSimpleName();
    private GameStatistic gameStatistic;
    private RelativeLayout gamesurface;
    protected AppManager appmanager;
    private boolean isdevelopmentmode, isfreeversion, isgprelease;
    private String roomId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        makeFullScreen();
        appmanager = new AppManager(this);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        setContentView(R.layout.activity_game);
        gamesurface = (RelativeLayout)findViewById(R.id.gameSurface);
        gamesurface.addView(appmanager);
        mAuth =FirebaseAuth.getInstance();
        isdevelopmentmode = false;
        isfreeversion = true;
        isgprelease = true;
        isRoom = false;
        roomId = null;
        otherPlayerData = null;
        if(getUser() == null){
            mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        FirebaseUser user = mAuth.getCurrentUser();
                        Log.i("User",user.getUid());
                        updateUI(user);
                    }else {
                        Log.i("User","Failed Connects");
                        Toast.makeText(getApplicationContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                     updateUI(null);
                    }
                }
            });
        }
    }
    public void setIsRoomId(String roomId1){
        roomId = roomId1;
    }
    public void setIsRoom(boolean isRoom1){
        isRoom = isRoom1;
    }

    public PlayerData getOtherPlayerData() {
        return otherPlayerData;
    }
    public void setOtherPlayerData (PlayerData pdata){
        otherPlayerData = pdata;
    }
    public void firebaseSetRoom(){
        try {
            if(isRoom && roomId != null){
            if(otherPlayerData != null){
                databaseReference.child("rooms").child(roomId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(otherPlayerData != null && dataSnapshot.hasChild(otherPlayerData.getUserid())){
                            otherPlayerData = dataSnapshot.child(otherPlayerData.getUserid()).getValue(PlayerData.class);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
            else {
                databaseReference.child("rooms").child(roomId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getChildrenCount() > 1){
                            for (DataSnapshot ds: dataSnapshot.getChildren()){
                                Log.i("ChildrenCountJs",""+ds.getChildrenCount());
                                if(!ds.getValue(PlayerData.class).getUserid().equalsIgnoreCase(getUser().getUid())){
                                    otherPlayerData = ds.getValue(PlayerData.class);
                                    Log.i("fbreal",otherPlayerData.getUserid());
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            }
        }catch (Exception e){

        }
    }
    public void setFirebaseUserStatistic(GameStatistic gameStatistic){
        if(getUser()!=null) {
            databaseReference.child("Users").child(getUser().getUid()).child("statistic").setValue(gameStatistic);
        } }
    public GameStatistic getFirebaseUserStatistic(){
        try {
            databaseReference.child("Users").child(getUser().getUid()).child("statistic").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    gameStatistic = dataSnapshot.getValue(GameStatistic.class);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }catch (Exception e){

        }
        return gameStatistic;
    }
    public void updateUI(FirebaseUser currentUser) {
        user = currentUser;
    }
    public FirebaseUser getUser(){
        return user;
    }
    public void makeFullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(Build.VERSION.SDK_INT < 19) { //19 or above api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else {
            //for lower api versions
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        appmanager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        makeFullScreen();
        appmanager.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        roomId = null;
        otherPlayerData = null;
        isRoom = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        makeFullScreen();
        /*if(mAuth.getCurrentUser() != null){
        //FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
        }*/
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        makeFullScreen();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onWindowFocusChanged(boolean changed) {
        super.onWindowFocusChanged(changed);
        makeFullScreen();
    }

    protected void setDevelopmentMode(boolean isTestMode) {
        isdevelopmentmode = isTestMode;
        if (isdevelopmentmode) Log.setLogLevel(Log.LOGLEVEL_VERBOSE);
        else Log.setLogLevel(Log.LOGLEVEL_SILENT);
    }

    public boolean isDevelopmentMode() {
        return isdevelopmentmode;
    }

    protected void setFreeVersion(boolean isFullVersion) {
        isfreeversion = isFullVersion;
    }

    public boolean isFreeVersion() {
        return isfreeversion;
    }

    protected void setGPRelease(boolean isGPRelease) {
        isgprelease = isGPRelease;
    }

    public boolean isGPRelease() {
        return isgprelease;
    }

    public void addView(View view) {
        gamesurface.addView(view);
    }

}
