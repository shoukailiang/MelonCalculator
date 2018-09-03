package com.example.meloncalculator;


import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;

public class AreaConversion extends Activity implements OnItemSelectedListener{
	
	private EditText inputNum = null;
	private TextView inputUnit = null;
	private TextView result = null;
	private TextView resultUnit =null;
	private Spinner measureSpinner = null;
	private Spinner unitSpinner = null;	
	private double rate = 1.0;
	ArrayAdapter<CharSequence> unitAdapter =null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_area_conversion);
		
		// 输入数值
		inputNum = (EditText)findViewById(R.id.input_edittext);
		// 输入的    单位
		inputUnit = (TextView)findViewById(R.id.input_unit);
		 // 结果值
		result = (TextView)findViewById(R.id.result_text);
		// 结果的    单位
		resultUnit = (TextView)findViewById(R.id.result_unit);
		// 单位下拉
		unitSpinner = (Spinner)findViewById(R.id.unit);
		// 选择类型
		measureSpinner = (Spinner)findViewById(R.id.measure);
		
		ArrayAdapter<CharSequence> measureAdapter = ArrayAdapter.createFromResource(this,
		        R.array.measure_array, android.R.layout.simple_spinner_item);
		measureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		measureSpinner.setAdapter(measureAdapter);	
			
		measureSpinner.setOnItemSelectedListener(this);
		unitSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, 
		            int pos, long id) {
				// TODO Auto-generated method stub
				CharSequence unit_covert = unitAdapter.getItem(pos);
				if (unit_covert.equals("千米-米")) {
//					Toast.makeText(MainActivity.this, "km-mile", Toast.LENGTH_SHORT).show();
					inputUnit.setText("千米");
					resultUnit.setText("米");
					rate = 1000;
				} else if (unit_covert.equals("米-码")) {
					inputUnit.setText("米");
					resultUnit.setText("码");
					rate = 1.094;
				} else if (unit_covert.equals("米-英尺")) {
					inputUnit.setText("米");
					resultUnit.setText("英尺");
					rate = 3.281;
				} else if (unit_covert.equals("厘米-英尺")) {
					inputUnit.setText("厘米");
					resultUnit.setText("英尺");
					rate = 0.394;
				} else if (unit_covert.equals("千克-英镑")) {
					inputUnit.setText("千克");
					resultUnit.setText("英镑");				
					rate = 2.205;
				} else if (unit_covert.equals("千克-盎司")) {
					inputUnit.setText("千克");
					resultUnit.setText("盎司");
					rate = 35.274;
				} else if (unit_covert.equals("海里-公里")) {
					inputUnit.setText("海里");
					resultUnit.setText("公里");
					rate = 1.852;
				} 
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		inputNum.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before,     
	                int count) {
				// TODO Auto-generated method stub
				String input_str = inputNum.getText().toString();
				if (input_str !=null && !input_str.equals("")) {
					result.setText(""+Double.parseDouble(input_str)*rate);
				}else {
					result.setText("0.0");
				}
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
		// TODO Auto-generated method stub
		switch (pos) {
		case 0:{
				unitAdapter = ArrayAdapter.createFromResource(this,
		        R.array.length_unit_array, android.R.layout.simple_spinner_item);
				unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				unitSpinner.setAdapter(unitAdapter);		
			}
			
			break;
		case 1:{
				unitAdapter = ArrayAdapter.createFromResource(this,
						R.array.weight_unit_array, android.R.layout.simple_spinner_item);
				unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				unitSpinner.setAdapter(unitAdapter);
			}
			
			break;
		default:
			break;
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
	}


	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		Log.d("areaActivity", "dispatchKeyEvent->"+event.getKeyCode());
		// TODO Auto-generated method stub
		if (event.getKeyCode()==KeyEvent.KEYCODE_MENU&&!inputNum.getText().toString().equals("")) {
			long now = SystemClock.uptimeMillis(); 
			KeyEvent newKey = new KeyEvent(now, now, event.getAction(), KeyEvent.KEYCODE_NUMPAD_DOT, 0);
			return super.dispatchKeyEvent(newKey);
		}
		return super.dispatchKeyEvent(event);
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d("areaActivity", "onKeyDown->"+event.getKeyCode());
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}
}
