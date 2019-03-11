package com.takip.takip.scenes;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.takip.takip.R;
import com.takip.takip.models.User;
import com.takip.takip.models.UserType;

public class MainActivity extends AppCompatActivity implements RecycleManagerFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (User.currentUser.type == UserType.MINISTRY) {
            MinistryFragment ministryFragment = new MinistryFragment();
            fragmentTransaction.replace(R.id.container, ministryFragment);
        } else if (User.currentUser.type == UserType.PRODUCER) {
            ProducerFragment producerFragment = new ProducerFragment();
            fragmentTransaction.replace(R.id.container, producerFragment);
        } else if (User.currentUser.type == UserType.SUPPLIER) {
            SupplierFragment supplierFragment = new SupplierFragment();
            fragmentTransaction.replace(R.id.container, supplierFragment);
        } else {
            RecycleManagerFragment recycleManagerFragment = new RecycleManagerFragment();
            fragmentTransaction.replace(R.id.container, recycleManagerFragment);
        }

        fragmentTransaction.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
