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
		
		// ������ֵ
		inputNum = (EditText)findViewById(R.id.input_edittext);
		// �����    ��λ
		inputUnit = (TextView)findViewById(R.id.input_unit);
		 // ���ֵ
		result = (TextView)findViewById(R.id.result_text);
		// �����    ��λ
		resultUnit = (TextView)findViewById(R.id.result_unit);
		// ��λ����
		unitSpinner = (Spinner)findViewById(R.id.unit);
		// ѡ������
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
				if (unit_covert.equals("ǧ��-��")) {
//					Toast.makeText(MainActivity.this, "km-mile", Toast.LENGTH_SHORT).show();
					inputUnit.setText("ǧ��");
					resultUnit.setText("��");
					rate = 1000;
				} else if (unit_covert.equals("��-��")) {
					inputUnit.setText("��");
					resultUnit.setText("��");
					rate = 1.094;
				} else if (unit_covert.equals("��-Ӣ��")) {
					inputUnit.setText("��");
					resultUnit.setText("Ӣ��");
					rate = 3.281;
				} else if (unit_covert.equals("����-Ӣ��")) {
					inputUnit.setText("����");
					resultUnit.setText("Ӣ��");
					rate = 0.394;
				} else if (unit_covert.equals("ǧ��-Ӣ��")) {
					inputUnit.setText("ǧ��");
					resultUnit.setText("Ӣ��");				
					rate = 2.205;
				} else if (unit_covert.equals("ǧ��-��˾")) {
					inputUnit.setText("ǧ��");
					resultUnit.setText("��˾");
					rate = 35.274;
				} else if (unit_covert.equals("����-����")) {
					inputUnit.setText("����");
					resultUnit.setText("����");
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
