package com.example.meloncalculator;

import java.util.Arrays;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
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

public class AreaConversion extends Activity implements OnItemSelectedListener {
	
	public static String TAG = "skl";
	private EditText inputNum = null;
	private TextView inputUnit = null;
	private TextView result = null;
	private TextView resultUnit = null;
	private Spinner measureSpinner = null;
	private Spinner unitSpinner = null;
	private double rate = 1.0;
	ArrayAdapter<CharSequence> unitAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_area_conversion);

		// 输入数值
		inputNum = (EditText) findViewById(R.id.input_edittext);
		// 输入的 单位
		inputUnit = (TextView) findViewById(R.id.input_unit);
		// 结果值
		result = (TextView) findViewById(R.id.result_text);
		// 结果的 单位
		resultUnit = (TextView) findViewById(R.id.result_unit);
		// 单位下拉
		unitSpinner = (Spinner) findViewById(R.id.unit);
		// 选择类型
		measureSpinner = (Spinner) findViewById(R.id.measure);

		// 选择面积，长度等等的适配器
		ArrayAdapter<CharSequence> measureAdapter = ArrayAdapter
				.createFromResource(this, R.array.measure_array,
						android.R.layout.simple_spinner_item);
		measureAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		measureSpinner.setAdapter(measureAdapter);

		measureSpinner.setOnItemSelectedListener(this);
		// 选择单位类型的时候的onItemSelected ，会把输入的单位和输出的单位显示出来，加上一个比例
		unitSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				// TODO Auto-generated method stub
				CharSequence unit_covert = unitAdapter.getItem(pos);
				if (unit_covert.equals("千米-米")) {
					inputUnit.setText("千米");
					resultUnit.setText("米");
					rate = 1000;	
				}else if (unit_covert.equals("km-m")) {
					inputUnit.setText("km");
					resultUnit.setText("m");
					rate = 1000;	
				}else if (unit_covert.equals("米-码")) {
					inputUnit.setText("米");
					resultUnit.setText("码");
					rate = 1.094;
				}else if (unit_covert.equals("m-yard")) {
					inputUnit.setText("m");
					resultUnit.setText("yard");
					rate = 1.094;
				} else if (unit_covert.equals("米-英尺")) {
					inputUnit.setText("米");
					resultUnit.setText("英尺");
					rate = 3.281;
				} else if (unit_covert.equals("m-foot")) {
					inputUnit.setText("m");
					resultUnit.setText("foot");
					rate = 3.281;
				} else if (unit_covert.equals("厘米-英尺")) {
					inputUnit.setText("厘米");
					resultUnit.setText("英尺");
					rate = 0.394;
				} else if (unit_covert.equals("cm-foot")) {
					inputUnit.setText("cm");
					resultUnit.setText("foot");
					rate = 0.394;
				}  else if (unit_covert.equals("千克-英镑")) {
					inputUnit.setText("千克");
					resultUnit.setText("英镑");
					rate = 2.205;
				} else if (unit_covert.equals("kg-pound")) {
					inputUnit.setText("kg");
					resultUnit.setText("pound");
					rate = 2.205;
				}  else if (unit_covert.equals("千克-盎司")) {
					inputUnit.setText("千克");
					resultUnit.setText("盎司");
					rate = 35.274;
				} else if (unit_covert.equals("kg-ounce")) {
					inputUnit.setText("kg");
					resultUnit.setText("ounce");
					rate = 35.274;
				} else if (unit_covert.equals("英石-千克")) {
					inputUnit.setText("英石");
					resultUnit.setText("千克");
					rate = 6.350;
				} else if (unit_covert.equals("stone-kg")) {
					inputUnit.setText("stone");
					resultUnit.setText("kg");
					rate = 6.350;
				}  else if (unit_covert.equals("海里-公里")) {
					inputUnit.setText("海里");
					resultUnit.setText("公里");
					rate = 1.852;
				} else if (unit_covert.equals("sea mile-kilometre")) {
					inputUnit.setText("sea mile");
					resultUnit.setText("kilometre");
					rate = 1.852;
				} else if (unit_covert.equals("平方米-平方英尺")) {
					inputUnit.setText("平方米");
					resultUnit.setText("平方英尺");
					rate = 10.764;
				}else if (unit_covert.equals("square meter-square foot")) {
					inputUnit.setText("square meter");
					resultUnit.setText("square foot");
					rate = 10.764;
				} else if (unit_covert.equals("平方米-平方码")) {
					inputUnit.setText("平方米");
					resultUnit.setText("平方码");
					rate = 1.196;
				}else if (unit_covert.equals("square meter-square yard")) {
					inputUnit.setText("square meter");
					resultUnit.setText("square yard");
					rate = 1.196;
				}else if (unit_covert.equals("英亩-平方米")) {
					inputUnit.setText("英亩");
					resultUnit.setText("平方米");
					rate = 4046.856;
				}else if (unit_covert.equals("acre-square meter")) {
					inputUnit.setText("acre");
					resultUnit.setText("square meter");
					rate = 4046.856;
				} else if (unit_covert.equals("摄氏度-华氏度")) {
					inputUnit.setText("摄氏度");
					resultUnit.setText("华氏度");
				} else if (unit_covert.equals("centigrade-fahrenheit")) {
					inputUnit.setText("centigrade");
					resultUnit.setText("fahrenheit");
				} else if (unit_covert.equals("摄氏度-开尔文")) {
					inputUnit.setText("摄氏度");
					resultUnit.setText("开尔文");
				} else if (unit_covert.equals("centigrade-kelvin")) {
					inputUnit.setText("centigrade");
					resultUnit.setText("kelvin");
				} else if (unit_covert.equals("华氏度-开尔文")) {
					inputUnit.setText("华氏度");
					resultUnit.setText("开尔文");
				}else if (unit_covert.equals("fahrenheit-kelvin")) {
					inputUnit.setText("fahrenheit");
					resultUnit.setText("kelvin");
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		// 输入就改变的监听器
		inputNum.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				String input_str = inputNum.getText().toString();
				// 输入的单位
				inputUnit = (TextView) findViewById(R.id.input_unit);
				String inputUnitValue = (String) inputUnit.getText();
				// 结果的单位
				resultUnit = (TextView) findViewById(R.id.result_unit);
				String resultUnitValue = (String) resultUnit.getText();
				String nameString[]={"华氏度","摄氏度","开尔文","centigrade","fahrenheit","kelvin"};
				if (input_str != null && !input_str.equals("")) {
					Log.i("SKL", ""+inputUnit);
					// 判断是不是温度
					if(Arrays.asList(nameString).contains(inputUnitValue)){
						// 是温度
						// 摄氏度-华氏度
						Log.i("SKL",""+inputUnitValue.equals("摄氏度"));
						Log.i("SKL",""+resultUnitValue.equals("华氏度"));
						if(inputUnitValue.equals("摄氏度")&&resultUnitValue.equals("华氏度")){
							Double a = Double.parseDouble(input_str) * 1.8 +32;
							result.setText("" + a );
						}else if(inputUnitValue.equals("centigrade")&&resultUnitValue.equals("fahrenheit")){
							Double a = Double.parseDouble(input_str) * 1.8 +32;
							result.setText("" + a );
						}else if(inputUnitValue.equals("摄氏度")&&resultUnitValue.equals("开尔文")){
							Double a = Double.parseDouble(input_str) + 273.15;
							result.setText("" + a );
						}else if(inputUnitValue.equals("centigrade")&&resultUnitValue.equals("kelvin")){
							Double a = Double.parseDouble(input_str) + 273.15;
							result.setText("" + a );
						}else if(inputUnitValue.equals("华氏度")&&resultUnitValue.equals("开尔文")){
							Double a = (Double.parseDouble(input_str) -306.6)/1.8;
							result.setText("" + a );
						}else if(inputUnitValue.equals("fahrenheit")&&resultUnitValue.equals("kelvin")){
							Double a = (Double.parseDouble(input_str) -306.6)/1.8;
							result.setText("" + a );
						}
						
					}else{
						// 不是温度
						result.setText("" + Double.parseDouble(input_str) * rate);
					}
				} else {
					result.setText("0.0");
					
				}

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		// TODO Auto-generated method stub
		Log.i(TAG, ""+pos);
		switch (pos) {
		// 根据选择的不同类型，渲染出util 的不同单位
		
		case 0: {
			unitAdapter = ArrayAdapter.createFromResource(this,
					R.array.length_unit_array,
					android.R.layout.simple_spinner_item);
			unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			unitSpinner.setAdapter(unitAdapter);
		}

			break;
		case 1: {
			unitAdapter = ArrayAdapter.createFromResource(this,
					R.array.weight_unit_array,
					android.R.layout.simple_spinner_item);
			unitAdapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			unitSpinner.setAdapter(unitAdapter);
		}

			break;
		case 2: {
			unitAdapter = ArrayAdapter.createFromResource(this,
					R.array.area_unit_array,
					android.R.layout.simple_spinner_item);
			unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			unitSpinner.setAdapter(unitAdapter);
		}
		break;
		case 3: {
			unitAdapter = ArrayAdapter.createFromResource(this,
					R.array.temperature_unit_array,
					android.R.layout.simple_spinner_item);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.area_conversion, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		  switch(id){
  			case R.id.action_calculator:       //菜单项“单位转换”
  				Intent myIntent = new Intent();
				myIntent.setClass(AreaConversion.this, Calculator.class);
				startActivity(myIntent);
				break;
  			 case R.id.exit:       //菜单项“退出”
 	    	    
 				finish();
 				break;
		  }

		return super.onOptionsItemSelected(item);
	}

//	@Override
//	public boolean dispatchKeyEvent(KeyEvent event) {
//		Log.d("areaActivity", "dispatchKeyEvent->" + event.getKeyCode());
//		// 菜单键
//		if (event.getKeyCode() == KeyEvent.KEYCODE_MENU
//				&& !inputNum.getText().toString().equals("")) {
//			long now = SystemClock.uptimeMillis();
//			KeyEvent newKey = new KeyEvent(now, now, event.getAction(),
//					KeyEvent.KEYCODE_NUMPAD_DOT, 0);// .
//			return super.dispatchKeyEvent(newKey);
//		}
//		return super.dispatchKeyEvent(event);
//	}
//
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		Log.d("areaActivity", "onKeyDown->" + event.getKeyCode());
//		// TODO Auto-generated method stub
//		return super.onKeyDown(keyCode, event);
//	}
}
