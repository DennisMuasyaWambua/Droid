package com.example.droid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.droid.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String mOrderMessage;
    public static final String EXTRA_ORDER_KEY = "MY KEY FOR ORDER MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.droid.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //explicit intent for opening the order activity
                Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
                intent.putExtra(EXTRA_ORDER_KEY,mOrderMessage);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @SuppressLint({"NonConstantResourceId", "QueryPermissionsNeeded"})
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.action_order:
                Intent intent = new Intent(getApplicationContext(),OrderActivity.class);
                intent.putExtra(EXTRA_ORDER_KEY,mOrderMessage);
                if(intent.resolveActivity(getPackageManager())!=null){
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Cannot handle intent", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.action_call:
                Uri phone = Uri.parse("tel:0715533144");
                Intent call = new Intent(Intent.ACTION_DIAL,phone);
                if(call.resolveActivity(getPackageManager())!=null){
                    startActivity(call);
                }else{
                    Toast.makeText(getApplicationContext(), "Cannot handle intent", Toast.LENGTH_SHORT).show();
                }

                return true;
            case R.id.action_locate_us:
                String location = "Argwings Kodhek Road, Kilimani, Nairob";
                Uri loc = Uri.parse("geo:0,0?q="+location);
                Intent geo = new Intent(Intent.ACTION_VIEW,loc);
                if(geo.resolveActivity(getPackageManager())!= null){
                    startActivity(geo);
                }else{
                    Toast.makeText(getApplicationContext(), "Cannot handle intent", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.action_about:
                String cake = "https://www.cakes.co.ke/";
                Uri cakeSite = Uri.parse(cake);
                Intent about_us = new Intent(Intent.ACTION_VIEW,cakeSite);
                if(about_us.resolveActivity(getPackageManager())!= null){
                    startActivity(about_us);
                }else{
                    Toast.makeText(getApplicationContext(),"Cannot process intent",Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.action_status:
                return true;
            default:
                break;

        }

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    public void displayToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }


    public void showIceCreamMessage(View view) {
        mOrderMessage = getString(R.string.ice_cream_order);
        displayToast(mOrderMessage);
    }

    public void showDonutOrderMessage(View view) {
        mOrderMessage = getString(R.string.donut_order);
        displayToast(mOrderMessage);
    }

    public void showMuffinOrder(View view) {
        mOrderMessage = getString(R.string.muffin_order);
        displayToast(mOrderMessage);
    }
}