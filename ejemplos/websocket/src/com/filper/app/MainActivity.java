package com.filper.app;




import com.filper.notifications.ManageService;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;




public class MainActivity extends Activity {

	private static final String TAG = "com.example.zykor";    

	private EditText txt_userName;
	private Button btn_open;
	private Button btn_close;
	private ManageService manage; 

	
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);     
        txt_userName = (EditText) findViewById(R.id.txt_userName);
        btn_open = (Button) findViewById(R.id.btn_open);
        btn_close = (Button) findViewById(R.id.btn_close);
        manage = new ManageService();
        btn_open.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				manage.startService(txt_userName.getText().toString(), "ws://10.156.125.130:9000", MainActivity.this);
			}
		});

        btn_close.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				manage.stopService(MainActivity.this);
			}
		});
        
    }

   

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
