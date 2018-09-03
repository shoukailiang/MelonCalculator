package com.example.meloncalculator;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	public static String TAG = "skl";
	private Button calcButton, covertButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bindWidgets();
	}

	/**
	 * °ó¶¨ÔªËØ
	 */
	private void bindWidgets() {
		calcButton = (Button) findViewById(R.id.button1);
		covertButton = (Button) findViewById(R.id.button2);
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

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		System.out.println(v.getId());
		switch (v.getId()) {
		// Ìø×ª
			case R.id.button1:
				Log.i("YQK", "calcu");
				Intent calcuIntent = new Intent();
				calcuIntent.setClass(MainActivity.this, Calculator.class);
				startActivity(calcuIntent);
				break;
			case R.id.button2:
				Log.i("SKL", "121");
				//Intent myIntent = new Intent();
				//myIntent.setClass(MainActivity.this, AreaConversion.class);
				//startActivity(myIntent);
				break;
			default:
				break;
		}
		
	}
}
