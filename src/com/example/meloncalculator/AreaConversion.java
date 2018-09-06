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

		// ������ֵ
		inputNum = (EditText) findViewById(R.id.input_edittext);
		// ����� ��λ
		inputUnit = (TextView) findViewById(R.id.input_unit);
		// ���ֵ
		result = (TextView) findViewById(R.id.result_text);
		// ����� ��λ
		resultUnit = (TextView) findViewById(R.id.result_unit);
		// ��λ����
		unitSpinner = (Spinner) findViewById(R.id.unit);
		// ѡ������
		measureSpinner = (Spinner) findViewById(R.id.measure);

		// ѡ����������ȵȵȵ�������
		ArrayAdapter<CharSequence> measureAdapter = ArrayAdapter
				.createFromResource(this, R.array.measure_array,
						android.R.layout.simple_spinner_item);
		measureAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		measureSpinner.setAdapter(measureAdapter);

		measureSpinner.setOnItemSelectedListener(this);
		// ѡ��λ���͵�ʱ���onItemSelected ���������ĵ�λ������ĵ�λ��ʾ����������һ������
		unitSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				// TODO Auto-generated method stub
				CharSequence unit_covert = unitAdapter.getItem(pos);
				if (unit_covert.equals("ǧ��-��")) {
					inputUnit.setText("ǧ��");
					resultUnit.setText("��");
					rate = 1000;	
				}else if (unit_covert.equals("km-m")) {
					inputUnit.setText("km");
					resultUnit.setText("m");
					rate = 1000;	
				}else if (unit_covert.equals("��-��")) {
					inputUnit.setText("��");
					resultUnit.setText("��");
					rate = 1.094;
				}else if (unit_covert.equals("m-yard")) {
					inputUnit.setText("m");
					resultUnit.setText("yard");
					rate = 1.094;
				} else if (unit_covert.equals("��-Ӣ��")) {
					inputUnit.setText("��");
					resultUnit.setText("Ӣ��");
					rate = 3.281;
				} else if (unit_covert.equals("m-foot")) {
					inputUnit.setText("m");
					resultUnit.setText("foot");
					rate = 3.281;
				} else if (unit_covert.equals("����-Ӣ��")) {
					inputUnit.setText("����");
					resultUnit.setText("Ӣ��");
					rate = 0.394;
				} else if (unit_covert.equals("cm-foot")) {
					inputUnit.setText("cm");
					resultUnit.setText("foot");
					rate = 0.394;
				}  else if (unit_covert.equals("ǧ��-Ӣ��")) {
					inputUnit.setText("ǧ��");
					resultUnit.setText("Ӣ��");
					rate = 2.205;
				} else if (unit_covert.equals("kg-pound")) {
					inputUnit.setText("kg");
					resultUnit.setText("pound");
					rate = 2.205;
				}  else if (unit_covert.equals("ǧ��-��˾")) {
					inputUnit.setText("ǧ��");
					resultUnit.setText("��˾");
					rate = 35.274;
				} else if (unit_covert.equals("kg-ounce")) {
					inputUnit.setText("kg");
					resultUnit.setText("ounce");
					rate = 35.274;
				} else if (unit_covert.equals("Ӣʯ-ǧ��")) {
					inputUnit.setText("Ӣʯ");
					resultUnit.setText("ǧ��");
					rate = 6.350;
				} else if (unit_covert.equals("stone-kg")) {
					inputUnit.setText("stone");
					resultUnit.setText("kg");
					rate = 6.350;
				}  else if (unit_covert.equals("����-����")) {
					inputUnit.setText("����");
					resultUnit.setText("����");
					rate = 1.852;
				} else if (unit_covert.equals("sea mile-kilometre")) {
					inputUnit.setText("sea mile");
					resultUnit.setText("kilometre");
					rate = 1.852;
				} else if (unit_covert.equals("ƽ����-ƽ��Ӣ��")) {
					inputUnit.setText("ƽ����");
					resultUnit.setText("ƽ��Ӣ��");
					rate = 10.764;
				}else if (unit_covert.equals("square meter-square foot")) {
					inputUnit.setText("square meter");
					resultUnit.setText("square foot");
					rate = 10.764;
				} else if (unit_covert.equals("ƽ����-ƽ����")) {
					inputUnit.setText("ƽ����");
					resultUnit.setText("ƽ����");
					rate = 1.196;
				}else if (unit_covert.equals("square meter-square yard")) {
					inputUnit.setText("square meter");
					resultUnit.setText("square yard");
					rate = 1.196;
				}else if (unit_covert.equals("ӢĶ-ƽ����")) {
					inputUnit.setText("ӢĶ");
					resultUnit.setText("ƽ����");
					rate = 4046.856;
				}else if (unit_covert.equals("acre-square meter")) {
					inputUnit.setText("acre");
					resultUnit.setText("square meter");
					rate = 4046.856;
				} else if (unit_covert.equals("���϶�-���϶�")) {
					inputUnit.setText("���϶�");
					resultUnit.setText("���϶�");
				} else if (unit_covert.equals("centigrade-fahrenheit")) {
					inputUnit.setText("centigrade");
					resultUnit.setText("fahrenheit");
				} else if (unit_covert.equals("���϶�-������")) {
					inputUnit.setText("���϶�");
					resultUnit.setText("������");
				} else if (unit_covert.equals("centigrade-kelvin")) {
					inputUnit.setText("centigrade");
					resultUnit.setText("kelvin");
				} else if (unit_covert.equals("���϶�-������")) {
					inputUnit.setText("���϶�");
					resultUnit.setText("������");
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
		// ����͸ı�ļ�����
		inputNum.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				String input_str = inputNum.getText().toString();
				// ����ĵ�λ
				inputUnit = (TextView) findViewById(R.id.input_unit);
				String inputUnitValue = (String) inputUnit.getText();
				// ����ĵ�λ
				resultUnit = (TextView) findViewById(R.id.result_unit);
				String resultUnitValue = (String) resultUnit.getText();
				String nameString[]={"���϶�","���϶�","������","centigrade","fahrenheit","kelvin"};
				if (input_str != null && !input_str.equals("")) {
					Log.i("SKL", ""+inputUnit);
					// �ж��ǲ����¶�
					if(Arrays.asList(nameString).contains(inputUnitValue)){
						// ���¶�
						// ���϶�-���϶�
						Log.i("SKL",""+inputUnitValue.equals("���϶�"));
						Log.i("SKL",""+resultUnitValue.equals("���϶�"));
						if(inputUnitValue.equals("���϶�")&&resultUnitValue.equals("���϶�")){
							Double a = Double.parseDouble(input_str) * 1.8 +32;
							result.setText("" + a );
						}else if(inputUnitValue.equals("centigrade")&&resultUnitValue.equals("fahrenheit")){
							Double a = Double.parseDouble(input_str) * 1.8 +32;
							result.setText("" + a );
						}else if(inputUnitValue.equals("���϶�")&&resultUnitValue.equals("������")){
							Double a = Double.parseDouble(input_str) + 273.15;
							result.setText("" + a );
						}else if(inputUnitValue.equals("centigrade")&&resultUnitValue.equals("kelvin")){
							Double a = Double.parseDouble(input_str) + 273.15;
							result.setText("" + a );
						}else if(inputUnitValue.equals("���϶�")&&resultUnitValue.equals("������")){
							Double a = (Double.parseDouble(input_str) -306.6)/1.8;
							result.setText("" + a );
						}else if(inputUnitValue.equals("fahrenheit")&&resultUnitValue.equals("kelvin")){
							Double a = (Double.parseDouble(input_str) -306.6)/1.8;
							result.setText("" + a );
						}
						
					}else{
						// �����¶�
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
		// ����ѡ��Ĳ�ͬ���ͣ���Ⱦ��util �Ĳ�ͬ��λ
		
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
  			case R.id.action_calculator:       //�˵����λת����
  				Intent myIntent = new Intent();
				myIntent.setClass(AreaConversion.this, Calculator.class);
				startActivity(myIntent);
				break;
  			 case R.id.exit:       //�˵���˳���
 	    	    
 				finish();
 				break;
		  }

		return super.onOptionsItemSelected(item);
	}

//	@Override
//	public boolean dispatchKeyEvent(KeyEvent event) {
//		Log.d("areaActivity", "dispatchKeyEvent->" + event.getKeyCode());
//		// �˵���
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
