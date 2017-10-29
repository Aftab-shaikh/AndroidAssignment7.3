package com.example.user1.androidassignment73;
/*
This project is just to give simple explanation how to get image from gallery and add to Image view.
 */

// Imported required clasess

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



// class started here
public class MainActivity extends AppCompatActivity {

    // some private refrence variable for image view and button
    private ImageView image;
     private Button button;
      private final int Imagepick=1;


    // oncreat method work as main method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // id's assinged to buttons and imageview
        image= (ImageView) findViewById(R.id.imageView);
        button= (Button) findViewById(R.id.buttonn);

        //set click listner to button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // set intent to preform action for picking up the image from gallery
                Intent i= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,Imagepick);


            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    //onActivityResult method to setup logic for pickup the image from gallry

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      // below is the logic return to get image from gallry
        switch (requestCode){
            case Imagepick:
                if(requestCode==RESULT_OK){
                    Uri uri=data.getData();
                    String [] projection= {MediaStore.Images.Media.DATA};
                    Cursor cur= getContentResolver().query(uri,projection,null,null,null);
                    cur.moveToFirst();

                    int columsindex= cur.getColumnIndex(projection[0]);
                    String filepath= cur.getString(columsindex);
                    cur.close();

                    Bitmap bitmap= BitmapFactory.decodeFile(filepath);
                    Drawable drawable= new BitmapDrawable(bitmap);
                    image.setBackground(drawable);


                }
                  // break
            break;
            default:
                break;

        }



    }


} // class closed
