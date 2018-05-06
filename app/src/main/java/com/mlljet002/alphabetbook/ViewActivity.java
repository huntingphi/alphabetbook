package com.mlljet002.alphabetbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ViewActivity extends AppCompatActivity {
    Controller controller;
    int buttonId;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            controller = MainActivity.controller;
            buttonId = extras.getInt("buttonId");
            //The key argument here must match that used in the other activity
            imageView = (ImageView)findViewById(R.id.imageView);
            imageView.setImageBitmap(controller.getImage(buttonId));
        }
        else{
            imageView.setImageBitmap(controller.getImage(MainActivity.lastImage));
        }

    }

    public void click(View view) {
        int id = view.getId();
        switch (id){
            case R.id.first:
                imageView.setImageBitmap(controller.getFirstImage());

                break;
            case R.id.last:
        imageView.setImageBitmap(controller.getLastImage());
                break;
            case R.id.next:
                imageView.setImageBitmap(controller.getNextImage());
                break;
            case R.id.previous:
                imageView.setImageBitmap(controller.getPrevImage());
                break;
            case R.id.overview:
                this.onBackPressed();
//                this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
                break;

        }
        MainActivity.lastImage=controller.currentIndex;


    }
}
