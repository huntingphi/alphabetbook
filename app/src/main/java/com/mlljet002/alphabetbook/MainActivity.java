package com.mlljet002.alphabetbook;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    static int lastImage;
    int[] imgVals = new int[26];
    Bitmap[] images=new Bitmap[26];
    static Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE,0x4);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
        getImagesFromFilesystem();Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();}
        controller= new Controller(images);
        if (savedInstanceState != null) {
            // if there is a bundle, use the saved image resource (if one is there)
            Intent viewImage = new Intent(MainActivity.this,ViewActivity.class);
            startActivity(viewImage);
        }else {


            setContentView(R.layout.activity_main);

        }
    }
    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        ImageView iv = (ImageView)this.findViewById(R.id.imageView);
        savedInstanceState.putInt("last image", lastImage);
        // Call the superclass to save the state of all the other controls in the view hierarchy
        super.onSaveInstanceState(savedInstanceState);
    }



    public void button_click(View view) {
        int id = view.getId();
        Intent viewImage = new Intent(MainActivity.this,ViewActivity.class);

        viewImage.putExtra("buttonId",id);
        startActivity(viewImage);




    }


    private void getImagesFromFilesystem() {

        //Make new thread
        AsyncTask fileTask = new AsyncTask() {
//            Bitmap img;

            @Override
            protected Object doInBackground(Object[] objects) {
                String imageName = "slide";
                for (int i=1; i<27; i++){
                    imageName = "slide";
                    if(i<10) {
                        imageName+=0+""+i;
                    }else{
                        imageName+=i;
                    }
                    imgVals[i-1] = getResources().getIdentifier(imageName,"drawable",getPackageName());
//                    images[i-1] =BitmapFactory.decodeResource(Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES).toString()+File.separator+imageName);
                    File directory = new File(Environment.getExternalStorageDirectory() + File.separator + "DCIM");
                        File file = new File(directory, imageName+".gif"); //or any other format supported
                    System.out.println(directory.getAbsolutePath());
                    FileInputStream streamIn = null;
                    try {
                        streamIn = new FileInputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    Bitmap bitmap = BitmapFactory.decodeStream(streamIn); //This gets the image


                    try {
                        streamIn.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    images[i-1] = bitmap;
                }
                return null;
            }
        };
        fileTask.execute();

    }

    }

