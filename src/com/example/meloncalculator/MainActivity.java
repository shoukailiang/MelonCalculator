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
	 * 绑定元素
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
	        case R.id.about_us:       //菜单项“关于我们”
	        	// 弹框
	        	 AlertDialog.Builder builder  = new Builder(MainActivity.this);
	        	 builder.setTitle("关于我们") ;
	        	 builder.setMessage("寿恺梁(单位转换，弹框),袁其凯(计算器)") ;
	        	 builder.setPositiveButton("是" ,  null );
	        	 builder.show(); 
				break;
	        case R.id.back_blue:       //菜单项“关于我们”

				mainLayout.setBackgroundColor(Color.BLUE);
				break;
	        case R.id.back_white:       //菜单项“关于我们”
	    
				mainLayout.setBackgroundColor(Color.WHITE);
				break;
	        case R.id.exit:       //菜单项“退出”
	    	    
				finish();
				break;
		  }

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		System.out.println(v.getId());
		switch (v.getId()) {
			// 跳转
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
