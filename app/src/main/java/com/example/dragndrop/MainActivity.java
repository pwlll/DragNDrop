package com.example.dragndrop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    String msg;
    private android.widget.RelativeLayout.LayoutParams layoutParams;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=(ImageView)findViewById(R.id.imageView);

        img.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        layoutParams=(RelativeLayout.LayoutParams)v.getLayoutParams();
                        break;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        int xCord=(int)event.getX();
                        int yCord=(int)event.getY();
                        break;

                    case DragEvent.ACTION_DRAG_LOCATION:
                        xCord = (int) event.getX();
                        yCord = (int) event.getY();
                        break;

                    case DragEvent.ACTION_DRAG_ENDED:
                        xCord = (int) event.getX();
                        yCord = (int) event.getY();
                        layoutParams.leftMargin = xCord;
                        layoutParams.topMargin = yCord;
                        v.setLayoutParams(layoutParams);
                        v.setVisibility(View.VISIBLE);
                        break;

                    default: break;
                }
                return true;
            }
        });

        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData clipData=ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(img);

                    img.startDragAndDrop(clipData, shadowBuilder, img, 0);
                    //img.setVisibility(View.INVISIBLE);
                    return true;
                } else {
                    return false;
                }
            }
        });

    }
}