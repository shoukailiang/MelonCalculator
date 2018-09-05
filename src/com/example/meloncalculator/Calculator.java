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
	/*计算器上数字按钮 */
	private Button num0,num1,num2,num3,num4,num5,num6,num7,num8,num9;
	
	/*运算符 加 减 乘 除 百分号*/
	private Button add,substract,divide,multiply,per;
	
	/*清除 退格 等于 点 */
	private Button ac,del,dot,equal;
	
	/*计算结果 */
	private EditText mResult;
	
	/*已输入字符*/
	private String InputText = "";
	
	/*判断是否被计算过*/
	private boolean isCounted = false;
	
	/*
	 * startWithSubtract bool
	 * 以负号开头减号运算 如 -1-1
	 */
	private boolean startWithSubtract = false;
	
	/*
	 * noStartWithOperator bool
	 * 不以负号开头 但有减号运算 如 2-1
	 */
	private boolean noStartWithOperator=false;
	
	/*
	 *  startWithOperator bool
	 *  以负号开头 但不用减号运算，用其他操作符运算 如 -5+2
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
	 * 初始化控件
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
	 * 初始化绑定事件
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
		 * 运算符
		 */
		case R.id.add:
			/*
             * 判断表达式是否可以进行计算
             * 是 先计算再添加字符
             * 否 添加字符
             *
             * 判断计算后的字符是否是 error
             * 是 置零
             * 否 添加运算符
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
                    } else if ((InputText.substring(InputText.length() - 1)).equals("×")) {
                    	InputText = InputText.replace("×", "+");
                    } else if ((InputText.substring(InputText.length() - 1)).equals("÷")) {
                    	InputText = InputText.replace("÷", "+");
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
					}else if((InputText.substring(InputText.length()-1)).equals("×")){
						InputText = InputText.replace("×", "-");
					}else if((InputText.substring(InputText.length()-1)).equals("÷")){
						InputText = InputText.replace("÷", "-");
					}else if(!(InputText.substring(InputText.length()-1)).equals("×")){
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
						InputText += "×";
					}
				}else{
					
					if(isCounted){
						isCounted = false;
					}
					
					if((InputText.substring(InputText.length()-1)).equals("+")){
						InputText = InputText.replace("+", "×");
					}else if((InputText.substring(InputText.length()-1)).equals("-")){
						InputText = InputText.replace("-", "×");
					}else if((InputText.substring(InputText.length()-1)).equals("÷")){
						InputText = InputText.replace("÷", "×");
					}else if(!(InputText.substring(InputText.length()-1)).equals("×")){
						InputText += "×";
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
						InputText += "÷";
					}
				}else{
					
					if(isCounted){
						isCounted = false;
					}
					
					if((InputText.substring(InputText.length()-1)).equals("+")){
						InputText = InputText.replace("+", "÷");
					}else if((InputText.substring(InputText.length()-1)).equals("×")){
						InputText = InputText.replace("×", "÷");
					}else if((InputText.substring(InputText.length()-1)).equals("-")){
						InputText = InputText.replace("-", "÷");
					}else if(!(InputText.substring(InputText.length()-1)).equals("÷")){
						InputText += "÷";
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
			 * 判断是否运算过
             * 否
             * 判断是否有运算符，有 判断运算符之后的数字，无 判断整个数字
             * 判断数字是否过长，是则不能添加小数点，否则可以添加
             * 判断已经存在的数字里是否有小数点
             * 是
             * 字符串置为 0.
             */
			if(!isCounted){
				
				if(InputText.contains("+")||InputText.contains("-")||
					InputText.contains("×")||InputText.contains("÷")){
					
					String param1 = null;
					String param2 = null;
					
					if(InputText.contains("+")){
						param1 = InputText.substring(0, InputText.indexOf("+"));
						param2 = InputText.substring(InputText.indexOf("+")+1);
					}else if(InputText.contains("-")){
						param1 = InputText.substring(0, InputText.indexOf("-"));
						param2 = InputText.substring(InputText.indexOf("-")+1);
					}else if(InputText.contains("×")){
						param1 = InputText.substring(0, InputText.indexOf("×"));
						param2 = InputText.substring(InputText.indexOf("×")+1);
					}else if(InputText.contains("÷")){
						param1 = InputText.substring(0, InputText.indexOf("÷"));
						param2 = InputText.substring(InputText.indexOf("÷")+1);
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
			 * 判断数字是否有运算符
			 * 是 不做任何操作
			 * 否 进行下一步
			 * 
			 * 判断数字是否是 0
             * 是 不做任何操作
             * 否 进行除百
             * 
             * 将字符串转换成Double类型，进行运算后，再转换成String型
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
			 * 字符串长度大于0时才能截取字符串
			 * 如果长度为1，则直接把字符串设置为0
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
		 * 设置显示
		 */
		mResult.setText(InputText);
	}
	
	/*
	 * 进行运算，得到结果
	 * @return 返回结果
	 */
	private String getResult(){
		/*
		 * 结果
		 */
		String tempResult = null;
		/*
		 * 两个String类型的参数
		 */
		String param1 = null;
		String param2 = null;
		/*
		 * 转换后的两个Double类型的参数
		 */
		double arg1 = 0;
		double arg2 = 0;
		double result = 0;
		
		getCondition();
		
		/*
		 * 如果有运算符，则进行运算
		 * 否则把已经存在的数据再传出去
		 */
		if(startWithOperator||noStartWithOperator||startWithSubtract){
			if(InputText.contains("+")){
				/*
				 * 先获取两个字符串参数(运算的数字)
				 */
				param1 = InputText.substring(0,InputText.indexOf("+"));
				param2 = InputText.substring(InputText.indexOf("+")+1);
				/*
				 * 如果第二个参数为空，则还是显示当前字符
				 */
				if(param2.equals("")){
					tempResult = InputText;
				}else{
					/*
					 * 转换String 为 Double型
					 * 计算后在转化成String型
					 * 进行正则表达式处理
					 */
					arg1 = Double.parseDouble(param1);
					arg2 = Double.parseDouble(param2);
					result = arg1 + arg2;
					tempResult = String.format("%f", result);
					tempResult = removeZeroAndDot(tempResult);
				}
				
			}else if(InputText.contains("×")){
				param1 = InputText.substring(0,InputText.indexOf("×"));
				param2 = InputText.substring(InputText.indexOf("×")+1);
				
				if(param2.equals("")){
					tempResult = InputText;
				}else{
					arg1 = Double.parseDouble(param1);
					arg2 = Double.parseDouble(param2);
					result = arg1 * arg2;
					tempResult = String.format("%f", result);
					tempResult = removeZeroAndDot(tempResult);
				}
				
			}else if(InputText.contains("÷")){
				param1 = InputText.substring(0,InputText.indexOf("÷"));
				param2 = InputText.substring(InputText.indexOf("÷")+1);
				
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
	 * 先判断是否按过等于号
     * 是 按数字则显示当前数字
     * 否 在已有的表达式后添加字符
     *
     * 判断数字是否就是一个 0
     * 是 把字符串设置为空字符串。
     *   1、打开界面没有运算过的时候，AC键归零或删除完归零的时候，会显示一个 0
     *   2、当数字是 0 的时候，设置成空字符串，再按 0 ，数字会还是 0，不然可以按出 000 这种数字
     * 否 添加按下的键的字符
     *
     * 判断数字是否包含小数点
     * 是 数字不能超过十位
     * 否 数字不能超过九位
     *
     * 进行上面的判断后，再判断数字是否超过长度限制
     * 超过不做任何操作
     * 没超过可以再添数字
	 */
	private String isOverRange(String InputText,String s){
		/*
		 * 判断是否进行过计算
		 */
		if(!isCounted){
			
			if(InputText.contains("e")){
				InputText = "0";
			}
			/*
			 * 判断文本是否只有一个0
			 * 如果只有一个0，则文本清空
			 */
			if(InputText.equals("0")){
				InputText = "";
			}
			/*
			 * 判断是否有运算符
			 * 如果有则判断第二个数字
			 * 否则判断整个字符串
			 */
			if(InputText.contains("+")||InputText.contains("-")||
				InputText.contains("÷")||InputText.contains("×")){
				
				/*
				 * 截取第二个数字
				 * substring 从+号第一次出现的地方到结尾为第二个数字
				 * param2 String 是运算符后的字符串
				 */
				String param2 = null;
				if(InputText.contains("+")){
					param2 = InputText.substring(InputText.indexOf("+")+1);
				}else if(InputText.contains("-")){
					param2 = InputText.substring(InputText.indexOf("-")+1);
				}else if(InputText.contains("÷")){
					param2 = InputText.substring(InputText.indexOf("÷")+1);
				}else if(InputText.contains("×")){
					param2 = InputText.substring(InputText.indexOf("×")+1);
				}
				
				if(InputText.substring(InputText.length()-1).equals("+")||
					InputText.substring(InputText.length()-1).equals("-")||
					InputText.substring(InputText.length()-1).equals("÷")||
					InputText.substring(InputText.length()-1).equals("×")){
					
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
				 * 不包括运算符时 一个数字
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
			s = s.replaceAll("0+?$", ""); //去掉多余的0
			s = s.replaceAll("[.]$", ""); //如果最后一位是.则去掉
		}
		return s;
	}
	
	/*
	 * 判断表达式
	 * 
	 */
	private boolean judgeExpression(){
		
		getCondition();
		
		String tempParam2 = null;
		
		if(startWithOperator||noStartWithOperator||startWithSubtract){
			if(InputText.contains("+")){
				/*
				 * 先获取第二个参数
				 */
				tempParam2 = InputText.substring(InputText.indexOf("+")+1);
				/*
				 * 如果第二个参数为空，则表达式不成立
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
			}else if(InputText.contains("÷")){
				tempParam2 = InputText.substring(InputText.indexOf("÷")+1);
				
				if(tempParam2.equals("")){
					return false;
				}else{
					return true;
				}
			}else if(InputText.contains("×")){
				tempParam2 = InputText.substring(InputText.indexOf("×")+1);
				
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
		 * 以负号开头，且运算符不是减号
		 */
		startWithOperator = InputText.startsWith("-")&&(InputText.contains("+")||
				InputText.contains("÷")||InputText.contains("×"));
		/*
		 * 以负号开头，且运算符是减号
		 */
		startWithSubtract = InputText.startsWith("-")&&(InputText.lastIndexOf("-")!= 0);
		/*
		 * 不以负号开头，且包含运算符
		 */
		noStartWithOperator = !InputText.startsWith("-")&&(InputText.contains("+")||
					InputText.contains("-")||InputText.contains("÷")||InputText.contains("×"));
	}
}
