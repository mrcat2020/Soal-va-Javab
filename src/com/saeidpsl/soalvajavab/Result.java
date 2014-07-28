package com.saeidpsl.soalvajavab;
 



import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class Result extends Activity {
	DBHelper dbhelper;  
    ArrayList<String> listarry =null;  
    String myPath =null;  
	ArrayAdapter<String> adapter ;	
   ListView list_Result=null;   
   Timer T=new Timer();
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		 
		
		TextView allquestion=(TextView)findViewById(R.id.all_question);
		TextView correctanswer=(TextView)findViewById(R.id.correct_answer);
		TextView falseanswer=(TextView)findViewById(R.id.false_answer);	 
		 
		int all_question=0;   
		int correct_answer=0;
		int false_answer=0;

		   for (Map.Entry<String,String> entry : MainActivity.answered.entrySet()) {

			   all_question++;
				  
				  if (entry.getValue()=="true"){
					  
					  correct_answer++;
			   			    
		   			}else{
		   				
		   				false_answer++;
		   			  
		   			}
  			
  		}
 	  	  
 	  	  
		   allquestion.setText(Integer.toString(all_question));
		   correctanswer.setText(Integer.toString(correct_answer));
		   falseanswer.setText(Integer.toString(false_answer));
 	  	
  		 
	}


	
	  
	  @Override
	  public void onBackPressed() {
		 
		    Intent intent = new Intent(Result.this, MainActivity.class);
	           intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	           startActivity(intent);
		  
		  finish();
	      return;
	  }  
	  
	  
	    
	  @Override
	      public boolean onCreateOptionsMenu(Menu menu){
	      	MenuInflater inflater=getMenuInflater();
	      	inflater.inflate(R.menu.main, menu);
	      	return true;
	      }

	  @Override
	     
	  
	  public boolean onOptionsItemSelected(MenuItem item){
	    
	      	switch(item.getItemId())
	      	{
	      
	      	case R.id.menu_home:
	       
	      		
	      		 
	   		            Intent intent = new Intent(Result.this, MainActivity.class);
	   		           intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	   		           startActivity(intent);
	      		  
	   			  finish();
	   		     
	   		      
	      	break;
	      	
	      	}
	      	return super.onOptionsItemSelected(item);
	      }

	  
	  
	  


}
