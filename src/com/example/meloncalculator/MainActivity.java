package com.example.meloncalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener {
	public static String TAG = "skl";
	private Button calcButton, covertButton;
	private RelativeLayout mainLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		bindWidgets();
	}
	/**
	 * ��Ԫ��
	 */
	private void bindWidgets() {
		calcButton = (Button) findViewById(R.id.button1);
		covertButton = (Button) findViewById(R.id.button2);
		mainLayout = (RelativeLayout)findViewById(R.id.relative_layout1);
		calcButton.setOnClickListener(this);
		covertButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		  switch(id){
	        case R.id.about_us:       //�˵���������ǡ�
	        	// ����
	        	 AlertDialog.Builder builder  = new Builder(MainActivity.this);
	        	 builder.setTitle("��������") ;
	        	 builder.setMessage("������(��λת��������),Ԭ�俭(������)") ;
	        	 builder.setPositiveButton("��" ,  null );
	        	 builder.show(); 
				break;
	        case R.id.back_blue:       //�˵���������ǡ�

				mainLayout.setBackgroundColor(Color.BLUE);
				break;
	        case R.id.back_white:       //�˵���������ǡ�
	    
				mainLayout.setBackgroundColor(Color.WHITE);
				break;
	        case R.id.exit:       //�˵���˳���
	    	    
				finish();
				break;
		  }

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		System.out.println(v.getId());
		switch (v.getId()) {
			// ��ת
			case R.id.button1:
				Intent myIntent0 = new Intent();
				myIntent0.setClass(MainActivity.this, Calculator.class);
				startActivity(myIntent0);
				break;
				
			case R.id.button2:
				Log.i(TAG, "121");
				Intent myIntent = new Intent();
				myIntent.setClass(MainActivity.this, AreaConversion.class);
				startActivity(myIntent);
				break;
			default:
				break;
		}
		
	}
}
