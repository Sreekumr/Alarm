package com.example.event;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    String[] mobileArray ={"Android","Iphoe","Windows","Blackberry","WebOs","Ubuntu","Windows7"};

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Implement events
        Button b = (Button) findViewById(R.id.btn1);
        TextView tv = (TextView) findViewById(R.id.txt1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText("Button Clicked");
            }
        });

        // Implement long click event on ListView
        ListView lv = findViewById(R.id.LV);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mobileArray);
        lv.setAdapter(adapter);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                Toast.makeText(getApplicationContext(), "Long Clicked", Toast.LENGTH_LONG).show();
                return true;
            }
        });

        // Implement multi-touch event
        ConstraintLayout myView = (ConstraintLayout) findViewById(R.id.main);
        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int pointerCount = event.getPointerCount();

                StringBuilder touchStatus = new StringBuilder();
                touchStatus.append("Touch Event\n");
                touchStatus.append("Number of touch points: ").append(pointerCount).append("\n");

                for (int i = 0; i < pointerCount; i++) {
                    int pointerId = event.getPointerId(i);
                    float x = event.getX(i);
                    float y = event.getY(i);
                    //touchStatus.append("Pointer ").append(pointerId).append(": (").append(x).append(", ").append(y).append(")\n");
                }

                Toast.makeText(MainActivity.this, touchStatus.toString(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
