package com.example.meloncalculator;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Calculator extends Activity implements OnClickListener{
	/*�����������ְ�ť */
	private Button num0,num1,num2,num3,num4,num5,num6,num7,num8,num9;
	
	/*����� �� �� �� �� �ٷֺ�*/
	private Button add,substract,divide,multiply,per;
	
	/*��� �˸� ���� �� */
	private Button ac,del,dot,equal;
	
	/*������ */
	private EditText mResult;
	
	/*�������ַ�*/
	private String InputText = "";
	
	/*�ж��Ƿ񱻼����*/
	private boolean isCounted = false;
	
	/*
	 * startWithSubtract bool
	 * �Ը��ſ�ͷ�������� �� -1-1
	 */
	private boolean startWithSubtract = false;
	
	/*
	 * noStartWithOperator bool
	 * ���Ը��ſ�ͷ ���м������� �� 2-1
	 */
	private boolean noStartWithOperator=false;
	
	/*
	 *  startWithOperator bool
	 *  �Ը��ſ�ͷ �����ü������㣬���������������� �� -5+2
	 */
	private boolean startWithOperator = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculator);
		bindWidgets();
		bindEvent();
	}
	
	/*
	 * bindWidgets
	 * ��ʼ���ؼ�
	 */
	private void bindWidgets(){
		num0 = (Button)findViewById(R.id.zero);
		num1 = (Button)findViewById(R.id.one);
		num2 = (Button)findViewById(R.id.two);
		num3 = (Button)findViewById(R.id.three);
		num4 = (Button)findViewById(R.id.four);
		num5 = (Button)findViewById(R.id.five);
		num6 = (Button)findViewById(R.id.six);
		num7 = (Button)findViewById(R.id.seven);
		num8 = (Button)findViewById(R.id.eight);
		num9 = (Button)findViewById(R.id.nine);
		
		add = (Button)findViewById(R.id.add);
		substract = (Button)findViewById(R.id.substract);
		divide = (Button)findViewById(R.id.divide);
		multiply = (Button)findViewById(R.id.multiply);
		per = (Button)findViewById(R.id.per);
		
		ac = (Button)findViewById(R.id.AC);
		del = (Button)findViewById(R.id.DEL);
		dot = (Button)findViewById(R.id.dot);
		equal = (Button)findViewById(R.id.equal);
		
		mResult = (EditText)findViewById(R.id.Result);
		InputText = mResult.getText().toString();
	}
	
	/*
	 * bindEvent()
	 * ��ʼ�����¼�
	 */
	private void bindEvent(){
		num0.setOnClickListener(this);
		num1.setOnClickListener(this);
		num2.setOnClickListener(this);
		num3.setOnClickListener(this);
		num4.setOnClickListener(this);
		num5.setOnClickListener(this);
		num6.setOnClickListener(this);
		num7.setOnClickListener(this);
		num8.setOnClickListener(this);
		num9.setOnClickListener(this);
		
		add.setOnClickListener(this);
		substract.setOnClickListener(this);
		multiply.setOnClickListener(this);
		divide.setOnClickListener(this);
		per.setOnClickListener(this);
		
		ac.setOnClickListener(this);
		del.setOnClickListener(this);
		dot.setOnClickListener(this);
		equal.setOnClickListener(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calculator, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onClick(View v){
		switch (v.getId()) {
		case R.id.zero:
			InputText = isOverRange(InputText, "0");
			break;
		case R.id.one:
			InputText = isOverRange(InputText, "1");
			break;
		case R.id.two:
			InputText = isOverRange(InputText, "2");
			break;
		case R.id.three:
			InputText = isOverRange(InputText, "3");
			break;
		case R.id.four:
			InputText = isOverRange(InputText, "4");
			break;
		case R.id.five:
			InputText = isOverRange(InputText, "5");
			break;
		case R.id.six:
			InputText = isOverRange(InputText, "6");
			break;
		case R.id.seven:
			InputText = isOverRange(InputText, "7");
			break;
		case R.id.eight:
			InputText = isOverRange(InputText, "8");
			break;
		case R.id.nine:
			InputText = isOverRange(InputText, "9");
			break;
		/*
		 * �����
		 */
		case R.id.add:
			/*
             * �жϱ��ʽ�Ƿ���Խ��м���
             * �� �ȼ���������ַ�
             * �� ����ַ�
             *
             * �жϼ������ַ��Ƿ��� error
             * �� ����
             * �� ��������
			 */
			
			if(!InputText.contains("e")){

				if(judgeExpression()){
					InputText = getResult();
					if(InputText.equals("error")){
						
					}else{
						InputText += "+";
					}
	
				}else{
					if(isCounted){
						isCounted = false;
					}
					
					if ((InputText.substring(InputText.length() - 1)).equals("-")) {
						InputText = InputText.replace("-", "+");
                    } else if ((InputText.substring(InputText.length() - 1)).equals("��")) {
                    	InputText = InputText.replace("��", "+");
                    } else if ((InputText.substring(InputText.length() - 1)).equals("��")) {
                    	InputText = InputText.replace("��", "+");
                    } else if (!(InputText.substring(InputText.length() - 1)).equals("+")) {
                    	InputText += "+";
                    }

					
				}
			}else{
				InputText = "0";
			}
			break;
		case R.id.substract:
			
			if(!InputText.contains("e")){
				if(judgeExpression()){
					InputText = getResult();
					if(InputText.equals("error")){
						
					}else{
						InputText += "-";
					}
				}else{
					
					if(isCounted){
						isCounted = false;
					}
					
					if((InputText.substring(InputText.length()-1)).equals("+")){
						InputText = InputText.replace("+", "-");
					}else if((InputText.substring(InputText.length()-1)).equals("��")){
						InputText = InputText.replace("��", "-");
					}else if((InputText.substring(InputText.length()-1)).equals("��")){
						InputText = InputText.replace("��", "-");
					}else if(!(InputText.substring(InputText.length()-1)).equals("��")){
						InputText += "-";
					}
				}
			}else{
				InputText = "0";
			}
			break;
		case R.id.multiply:
			if(!InputText.contains("e")){
				if(judgeExpression()){
					InputText = getResult();
					if(InputText.equals("error")){
						
					}else{
						InputText += "��";
					}
				}else{
					
					if(isCounted){
						isCounted = false;
					}
					
					if((InputText.substring(InputText.length()-1)).equals("+")){
						InputText = InputText.replace("+", "��");
					}else if((InputText.substring(InputText.length()-1)).equals("-")){
						InputText = InputText.replace("-", "��");
					}else if((InputText.substring(InputText.length()-1)).equals("��")){
						InputText = InputText.replace("��", "��");
					}else if(!(InputText.substring(InputText.length()-1)).equals("��")){
						InputText += "��";
					}
				}
			}else{
				InputText = "0";
			}
			break;
		case R.id.divide:
			if(!InputText.contains("e")){
				if(judgeExpression()){
					InputText = getResult();
					if(InputText.equals("error")){
						
					}else{
						InputText += "��";
					}
				}else{
					
					if(isCounted){
						isCounted = false;
					}
					
					if((InputText.substring(InputText.length()-1)).equals("+")){
						InputText = InputText.replace("+", "��");
					}else if((InputText.substring(InputText.length()-1)).equals("��")){
						InputText = InputText.replace("��", "��");
					}else if((InputText.substring(InputText.length()-1)).equals("-")){
						InputText = InputText.replace("-", "��");
					}else if(!(InputText.substring(InputText.length()-1)).equals("��")){
						InputText += "��";
					}
				}
			}else{
				InputText = "0";
			}
			break;
		case R.id.equal:
			InputText = getResult();
			isCounted = true;
			break;
		case R.id.dot:
			/*
			 * �ж��Ƿ������
             * ��
             * �ж��Ƿ������������ �ж������֮������֣��� �ж���������
             * �ж������Ƿ���������������С���㣬����������
             * �ж��Ѿ����ڵ��������Ƿ���С����
             * ��
             * �ַ�����Ϊ 0.
             */
			if(!isCounted){
				
				if(InputText.contains("+")||InputText.contains("-")||
					InputText.contains("��")||InputText.contains("��")){
					
					String param1 = null;
					String param2 = null;
					
					if(InputText.contains("+")){
						param1 = InputText.substring(0, InputText.indexOf("+"));
						param2 = InputText.substring(InputText.indexOf("+")+1);
					}else if(InputText.contains("-")){
						param1 = InputText.substring(0, InputText.indexOf("-"));
						param2 = InputText.substring(InputText.indexOf("-")+1);
					}else if(InputText.contains("��")){
						param1 = InputText.substring(0, InputText.indexOf("��"));
						param2 = InputText.substring(InputText.indexOf("��")+1);
					}else if(InputText.contains("��")){
						param1 = InputText.substring(0, InputText.indexOf("��"));
						param2 = InputText.substring(InputText.indexOf("��")+1);
					}
					Log.d("Anonymous param1",param1);
					Log.d("Anonymous param2",param2);
					
					boolean isContainedDot = param2.contains(".");
					if(param2.length()>=9){
						
					}else if(!isContainedDot){
						if(param2.equals("")){
							InputText += "0.";
						}else{
							InputText += ".";
						}
					}else{
						return;
					}
				}else{
					boolean isContainedDot = InputText.contains(".");
					if(InputText.length()>=9){
						
					}else if(!isContainedDot){
						InputText += ".";
					}else{
						return;
					}
				}
				isCounted = false;
			}else{
				InputText = "0.";
				isCounted = false;
			}
			break;
		case R.id.per:
			/*
			 * �ж������Ƿ��������
			 * �� �����κβ���
			 * �� ������һ��
			 * 
			 * �ж������Ƿ��� 0
             * �� �����κβ���
             * �� ���г���
             * 
             * ���ַ���ת����Double���ͣ������������ת����String��
			 */
			if(InputText.equals("error")){
				
			}else{
				getCondition();
				
				if(startWithOperator||startWithSubtract||noStartWithOperator){
					
				}else{
					if(InputText.equals("0")){
						return;
					}else{
						Double temp = Double.parseDouble(InputText);
						temp = temp / 100;
						InputText = String.valueOf(temp);
					}
				}
			}
			break;
		case R.id.DEL:
			/*
			 * �ַ������ȴ���0ʱ���ܽ�ȡ�ַ���
			 * �������Ϊ1����ֱ�Ӱ��ַ�������Ϊ0
			 * 
			 */
			if(InputText.equals("error")){
				InputText = "0";
			}else if(InputText.length()>0){
				if(InputText.length()==1){
					InputText = "0";
				}else{
					InputText =InputText.substring(0,InputText.length()-1);
				}
			}
			break;
		case R.id.AC:
			InputText = "0";
			break;
		default:
			break;
		}
		/*
		 * ������ʾ
		 */
		mResult.setText(InputText);
	}
	
	/*
	 * �������㣬�õ����
	 * @return ���ؽ��
	 */
	private String getResult(){
		/*
		 * ���
		 */
		String tempResult = null;
		/*
		 * ����String���͵Ĳ���
		 */
		String param1 = null;
		String param2 = null;
		/*
		 * ת���������Double���͵Ĳ���
		 */
		double arg1 = 0;
		double arg2 = 0;
		double result = 0;
		
		getCondition();
		
		/*
		 * ���������������������
		 * ������Ѿ����ڵ������ٴ���ȥ
		 */
		if(startWithOperator||noStartWithOperator||startWithSubtract){
			if(InputText.contains("+")){
				/*
				 * �Ȼ�ȡ�����ַ�������(���������)
				 */
				param1 = InputText.substring(0,InputText.indexOf("+"));
				param2 = InputText.substring(InputText.indexOf("+")+1);
				/*
				 * ����ڶ�������Ϊ�գ�������ʾ��ǰ�ַ�
				 */
				if(param2.equals("")){
					tempResult = InputText;
				}else{
					/*
					 * ת��String Ϊ Double��
					 * �������ת����String��
					 * ����������ʽ����
					 */
					arg1 = Double.parseDouble(param1);
					arg2 = Double.parseDouble(param2);
					result = arg1 + arg2;
					tempResult = String.format("%f", result);
					tempResult = removeZeroAndDot(tempResult);
				}
				
			}else if(InputText.contains("��")){
				param1 = InputText.substring(0,InputText.indexOf("��"));
				param2 = InputText.substring(InputText.indexOf("��")+1);
				
				if(param2.equals("")){
					tempResult = InputText;
				}else{
					arg1 = Double.parseDouble(param1);
					arg2 = Double.parseDouble(param2);
					result = arg1 * arg2;
					tempResult = String.format("%f", result);
					tempResult = removeZeroAndDot(tempResult);
				}
				
			}else if(InputText.contains("��")){
				param1 = InputText.substring(0,InputText.indexOf("��"));
				param2 = InputText.substring(InputText.indexOf("��")+1);
				
				if(param2.equals("0")){
					tempResult = "error";
				}else if(param2.equals("")){
					tempResult = InputText;
				}else{
					arg1 = Double.parseDouble(param1);
					arg2 = Double.parseDouble(param2);
					result = arg1 / arg2;
					tempResult = String.format("%f", result);
					tempResult = removeZeroAndDot(tempResult);
				}
				
			}else if(InputText.contains("-")){
				param1 = InputText.substring(0,InputText.indexOf("-"));
				param2 = InputText.substring(InputText.indexOf("-")+1);
				
				if(param2.equals("")){
					tempResult = InputText;
				}else{
					arg1 = Double.parseDouble(param1);
					arg2 = Double.parseDouble(param2);
					result = arg1 - arg2;
					tempResult = String.format("%f", result);
					tempResult = removeZeroAndDot(tempResult);
				}
				
			}
			
			if(tempResult.length()>=10){
				tempResult = String.format("%e", Double.parseDouble(tempResult));
			}else if(tempResult.contains(".")){
				if(tempResult.substring(0,tempResult.indexOf(".")).length() >=10){
					tempResult = String.format("%e", Double.parseDouble(tempResult));
				}
			}
			
		}else{
			tempResult = InputText;
		}
		return tempResult;
	}
	/*
	 * ���ж��Ƿ񰴹����ں�
     * �� ����������ʾ��ǰ����
     * �� �����еı��ʽ������ַ�
     *
     * �ж������Ƿ����һ�� 0
     * �� ���ַ�������Ϊ���ַ�����
     *   1���򿪽���û���������ʱ��AC�������ɾ��������ʱ�򣬻���ʾһ�� 0
     *   2���������� 0 ��ʱ�����óɿ��ַ������ٰ� 0 �����ֻỹ�� 0����Ȼ���԰��� 000 ��������
     * �� ��Ӱ��µļ����ַ�
     *
     * �ж������Ƿ����С����
     * �� ���ֲ��ܳ���ʮλ
     * �� ���ֲ��ܳ�����λ
     *
     * ����������жϺ����ж������Ƿ񳬹���������
     * ���������κβ���
     * û����������������
	 */
	private String isOverRange(String InputText,String s){
		/*
		 * �ж��Ƿ���й�����
		 */
		if(!isCounted){
			
			if(InputText.contains("e")){
				InputText = "0";
			}
			/*
			 * �ж��ı��Ƿ�ֻ��һ��0
			 * ���ֻ��һ��0�����ı����
			 */
			if(InputText.equals("0")){
				InputText = "";
			}
			/*
			 * �ж��Ƿ��������
			 * ��������жϵڶ�������
			 * �����ж������ַ���
			 */
			if(InputText.contains("+")||InputText.contains("-")||
				InputText.contains("��")||InputText.contains("��")){
				
				/*
				 * ��ȡ�ڶ�������
				 * substring ��+�ŵ�һ�γ��ֵĵط�����βΪ�ڶ�������
				 * param2 String �����������ַ���
				 */
				String param2 = null;
				if(InputText.contains("+")){
					param2 = InputText.substring(InputText.indexOf("+")+1);
				}else if(InputText.contains("-")){
					param2 = InputText.substring(InputText.indexOf("-")+1);
				}else if(InputText.contains("��")){
					param2 = InputText.substring(InputText.indexOf("��")+1);
				}else if(InputText.contains("��")){
					param2 = InputText.substring(InputText.indexOf("��")+1);
				}
				
				if(InputText.substring(InputText.length()-1).equals("+")||
					InputText.substring(InputText.length()-1).equals("-")||
					InputText.substring(InputText.length()-1).equals("��")||
					InputText.substring(InputText.length()-1).equals("��")){
					
					InputText += s;
				}else{
					if(param2.contains(".")){
						if(param2.length()>=10){
							
						}else{
							InputText += s;
						}
					}else{
						if(param2.length()>=9){
							
						}else{
							InputText += s;
						}
					}
				}
			}else{
				/*
				 * �����������ʱ һ������
				 */
				if(InputText.contains(".")){
					if(InputText.length()>=10){
						
					}else{
						InputText += s;
					}
				}else{
					if(InputText.length()>=9){
						
					}else{
						InputText += s;
					}
				}
			}
			
			isCounted = false;
		
		}else{
			InputText = s;
			isCounted = false;
		}
		return InputText;	
	}
	
	public static String removeZeroAndDot(String s){
		if(s.indexOf(".") >0){
			s = s.replaceAll("0+?$", ""); //ȥ�������0
			s = s.replaceAll("[.]$", ""); //������һλ��.��ȥ��
		}
		return s;
	}
	
	/*
	 * �жϱ��ʽ
	 * 
	 */
	private boolean judgeExpression(){
		
		getCondition();
		
		String tempParam2 = null;
		
		if(startWithOperator||noStartWithOperator||startWithSubtract){
			if(InputText.contains("+")){
				/*
				 * �Ȼ�ȡ�ڶ�������
				 */
				tempParam2 = InputText.substring(InputText.indexOf("+")+1);
				/*
				 * ����ڶ�������Ϊ�գ�����ʽ������
				 */
				if(tempParam2.equals("")){
					return false;
				}else{
					return true;
				}
			}else if(InputText.contains("-")){
				tempParam2 = InputText.substring(InputText.indexOf("-")+1);
				
				if(tempParam2.equals("")){
					return false;
				}else{
					return true;
				}
			}else if(InputText.contains("��")){
				tempParam2 = InputText.substring(InputText.indexOf("��")+1);
				
				if(tempParam2.equals("")){
					return false;
				}else{
					return true;
				}
			}else if(InputText.contains("��")){
				tempParam2 = InputText.substring(InputText.indexOf("��")+1);
				
				if(tempParam2.equals("")){
					return false;
				}else{
					return true;
				}
			}
		}
		
		return false;
		
	}
	
	
	private void getCondition(){
		/*
		 * �Ը��ſ�ͷ������������Ǽ���
		 */
		startWithOperator = InputText.startsWith("-")&&(InputText.contains("+")||
				InputText.contains("��")||InputText.contains("��"));
		/*
		 * �Ը��ſ�ͷ����������Ǽ���
		 */
		startWithSubtract = InputText.startsWith("-")&&(InputText.lastIndexOf("-")!= 0);
		/*
		 * ���Ը��ſ�ͷ���Ұ��������
		 */
		noStartWithOperator = !InputText.startsWith("-")&&(InputText.contains("+")||
					InputText.contains("-")||InputText.contains("��")||InputText.contains("��"));
	}
}
