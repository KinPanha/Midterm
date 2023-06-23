package kh.edu.rupp.ite.onlineshop.ui.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import kh.edu.rupp.ite.onlineshop.R;
import kh.edu.rupp.ite.onlineshop.databinding.FragmentDescriptionBinding;
import kh.edu.rupp.ite.onlineshop.ui.Fragment.DescriptionFragment;

public class Button extends Activity {
    private ImageButton imageButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showDescription(){
        imageButton = (ImageButton) findViewById(R.id.but);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(), DescriptionFragment.class);
                startActivity(intent);
            }
        });
    }
}
