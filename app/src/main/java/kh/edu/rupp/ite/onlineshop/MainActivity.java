package kh.edu.rupp.ite.onlineshop;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import kh.edu.rupp.ite.onlineshop.R;
import kh.edu.rupp.ite.onlineshop.ui.Fragment.HomeFragment;
import kh.edu.rupp.ite.onlineshop.ui.Fragment.MoreFragment;
import kh.edu.rupp.ite.onlineshop.ui.Fragment.ProductFragment;
import kh.edu.rupp.ite.onlineshop.ui.Fragment.ProfileFragment;
import kh.edu.rupp.ite.onlineshop.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = ActivityMainBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());
       binding.bottomNavigation.setOnItemSelectedListener(item -> {
           if (item.getItemId() == R.id.mhome){
             showFragment(new HomeFragment());
           }
           else if (item.getItemId() == R.id.mproduct){
               showFragment(new ProductFragment());
           }
           else if (item.getItemId() == R.id.mprofile){
               showFragment(new ProfileFragment());
           }
           else if (item.getItemId() == R.id.mmore){
               showFragment(new MoreFragment());
           }
           return true;
       });

    }
    private void showFragment(Fragment fragment){
        // FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // FragmentTransition
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        // Replace HomeFragment in lytFragment
        fragmentTransaction.replace(binding.lytFragment.getId(), fragment);

        // Commit transaction
        fragmentTransaction.commit();
    }
}