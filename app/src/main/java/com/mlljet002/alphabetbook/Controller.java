package com.mlljet002.alphabetbook;
import android.content.Intent;
import android.graphics.Bitmap;

import java.io.Serializable;
/**
 * Created by minad on 10/4/17.
 */

public class Controller{
    int currentIndex;
    private Bitmap[] images;
    private static final int[] BUTTON_IDS = {
            R.id.a,
            R.id.b,
            R.id.c,
            R.id.d,
            R.id.e,
            R.id.f,
            R.id.g,
            R.id.h,
            R.id.i,
            R.id.j,
            R.id.k,
            R.id.l,
            R.id.m,
            R.id.n,
            R.id.o,
            R.id.p,
            R.id.q,
            R.id.r,
            R.id.s,
            R.id.t,
            R.id.u,
            R.id.v,
            R.id.w,
            R.id.x,
            R.id.y,
            R.id.z,
    };




    public Controller(Bitmap[] images) {
        this.images = images;

    }

    public Bitmap[] getImages() {
        return images;
    }

    public void setImages(Bitmap[] images) {
        this.images = images;

    }
    Bitmap getImage(){
        return images[currentIndex];

    }


    Bitmap getNextImage(){
        if(currentIndex+1<26)currentIndex++;
        return images[currentIndex];

    }

    Bitmap getPrevImage(){
        if(currentIndex-1>=0)currentIndex--;
        return images[currentIndex];

    }


    Bitmap getLastImage(){
        currentIndex=images.length-1;
        return images[currentIndex];

    }
    Bitmap getFirstImage(){
        currentIndex=0;
        return images[currentIndex];

    }



    Bitmap getImage(int id){
//        int ImageIndex;
//        System.out.println(BUTTON_IDS[0]);
//        System.out.println(id);
        for (int i = 0; i <BUTTON_IDS.length ; i++) {
            if(id == BUTTON_IDS[i]){
                currentIndex=i;
                return images[i];

            }
        }
        System.out.println("NotFound");
        return images[0];

    }



}
