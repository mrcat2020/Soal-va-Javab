package com.saeidpsl.soalvajavab;
 


import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import android.widget.TextView;


public class DataActivity extends Activity {
	
	    DBHelper dbhelper;  
	    int count =30;
	    ListView list=null; 
	    ArrayAdapter<String> adapter ;	  
	    ArrayList<String> listarry =null;  	    
	    TextView question =null;  
	    String myPath =null;   
	    Timer T=new Timer();
	    String Answer;
	    Timer Tlist=new Timer(); 
       MediaPlayer p = null;
       int next_question=0;
       private DBHelper db;
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		
		
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data);
		
		db = new DBHelper(this);
	  
	     question=(TextView)findViewById(R.id.question);

	      list = (ListView) findViewById(R.id.listView_answer); 
	     
	     final TextView counter=(TextView)findViewById(R.id.counter);
	    
	    
	    

	      
	    
	    
	    Cursor cs =  db.db_object.rawQuery("SELECT * FROM data WHERE type='question' AND stage='"+MainActivity.stage+"'  AND tag  =  '"+MainActivity.selected.trim() +"'  " , null);
		  
	      
		   
	      if (cs .moveToFirst()) { 
	    	  question.setText(cs.getString(cs.getColumnIndex("title")));
	           
	      };
	  
	      
	      
	      cs =  db.db_object.rawQuery("SELECT * FROM data WHERE type='answer' AND stage='"+MainActivity.stage+"'  AND tag like  '%"+MainActivity.selected.trim() +"%'  " , null);
	     listarry = new ArrayList<String>();
	      
	   
	     while (cs.moveToNext()) {
	              listarry.add(cs.getString(cs.getColumnIndex("title")));
	             
	      }
	    
	      
	       
	     adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, listarry);
		  
	      list.setAdapter(adapter);  
	   
	   
	      
	      
	      
	      list.setOnItemClickListener(new OnItemClickListener() {
	  		public void onItemClick(AdapterView<?> parent, View view,
	  				int position, long id) {
	  		  



		    
		    Cursor cs =  db.db_object.rawQuery("SELECT * FROM data WHERE   type='answer' AND stage='"+MainActivity.stage+"' "+
		     "AND tag like  '%"+MainActivity.selected.trim() +"%'   AND correct='true'", null);
		    String answer_correct = null;
		      if (cs .moveToFirst()) {
		    	    answer_correct =cs.getString(cs.getColumnIndex("title"));
		           } 
	  		    
		      
		      
		      
		      
		 T.cancel();
		 
			Tlist.scheduleAtFixedRate(new TimerTask() {         
	  	        @Override
	  	        public void run() {
	  	            runOnUiThread(new Runnable()
	  	            {
	  	                @Override
	  	                public void run()
	  	                {
	  	                	 
	  	                	next_question(); 
						   	  
	  	                }

	  	            });
	  	        }
	  	    }, 8000, 8000);
		    
		      
		      View newView = null ;
	    	 
             TextView tv = null;
             list.setEnabled(false); 
	  		    
			   int j = 0;
		   		while (listarry.size() > j) {
		   			 
		   		    
					if (!answer_correct.equals(listarry.get(j))){
		   			
						
		   			     newView = list.getChildAt(listarry.indexOf(listarry.get(j)));
			    	   newView.setBackgroundColor(Color.RED);
	                     tv = (TextView) newView;
			    	   tv.setTextColor(Color.WHITE);
			    	   
			    	   
			    	   
		   			}else{
		   			 
		   			 newView = list.getChildAt(listarry.indexOf(listarry.get(j)));
		   			  newView.setBackgroundColor(Color.GREEN);
		   			   tv = (TextView) newView;
		   			  tv.setTextColor(Color.parseColor("#0d0001"));
		   			  
		   				
		   			}
		   			 
					
					if (!answer_correct.equals(((TextView) view).getText())){
				 
				     newView = list.getChildAt(listarry.indexOf(((TextView) view).getText()));
		    	    newView.setBackgroundColor(Color.BLUE);
                   tv = (TextView) newView;
		    	    tv.setTextColor(Color.WHITE);
						
		    		playsound("false");
		    	    
		    		Answer="false";
		    		
					}else{
						
						playsound("Clap");
						
						Answer="true";
					  
						
					}
					
					
					
		   			j++;
		   		}
	  		    
	  		    
	  		}
	  	}); 
	       

	  	  
	  	  

	  	T.scheduleAtFixedRate(new TimerTask() {         
	  	        @Override
	  	        public void run() {
	  	            runOnUiThread(new Runnable()
	  	            {
	  	                @Override
	  	                public void run()
	  	                {
	  	                	count--;
						if(count!=-1){
							
							
							if(count>-1){

                              counter.setText(""+count);
							
							}
	  	                    
	  	                    
						}else{
							
			 


						    Cursor cs =  db.db_object.rawQuery("SELECT * FROM data WHERE   type='answer' AND stage='"+MainActivity.stage+"' "+
						     "AND tag like  '%"+MainActivity.selected.trim() +"%'   AND correct='true'", null);
						    String answer_correct = null;
						      if (cs .moveToFirst()) {
						    	    answer_correct =cs.getString(cs.getColumnIndex("title"));
						           } 
					  		    
						         
						      
						   View newView = null ;
					      TextView tv = null;
					      Answer="false";
					  		    
							   int j = 0;
						   		while (listarry.size() > j) {
						   			 
						   			
									if (!answer_correct.equals(listarry.get(j))){
						   			
										
						   			   newView = list.getChildAt(listarry.indexOf(listarry.get(j)));
							    	   newView.setBackgroundColor(Color.RED);
					                   tv = (TextView) newView;
							    	   tv.setTextColor(Color.WHITE);
							    	   
							    	   
							    	   
						   			}else{
						   			 
						   			 newView = list.getChildAt(listarry.indexOf(listarry.get(j)));
						   			  newView.setBackgroundColor(Color.GREEN);
						   			   tv = (TextView) newView;
						   			  tv.setTextColor(Color.parseColor("#0d0001"));
						   			   
						   			}
									
                                 j++;
                             }
			 	
			        	 playsound("false");
							
							
						}
	  	                	
						if(count==6){
							
						  playsound("timer");
						}
						
						if(count==-8){
							list.setEnabled(false); 
							next_question();
							 T.cancel();
							}
	  	                    
	  	                }

					 
	  	            });
	  	        }
	  	    }, 1000, 1000);
	  	
	  	 
	  	
	  	if (question.getText().toString().trim()==""){
	  		

	  		
 		    Toast.makeText(getApplicationContext(),
 			"خالی هست", Toast.LENGTH_SHORT).show();
	  		
	  		
	  		
	            Intent intent = new Intent(DataActivity.this, MainActivity.class);
	           intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	           startActivity(intent);
		 
	        Tlist.cancel();
	       T.cancel();
		  finish();
		  
		  
		  
	  	}
	  	
		
	}


	
	
	
	  private void next_question() {
		  next_question=1;

		    int lastId = 0 ;
		    String query = "SELECT stage  from data WHERE type='question' AND tag like  '%"+MainActivity.selected.trim() +"%'     order by stage DESC limit 1";
		    Cursor c =  db.db_object.rawQuery(query,null);
		    if (c != null && c.moveToFirst()) {
		    lastId = (int) c.getLong(0); 
		    }
		
		    
		    Tlist.cancel();
		    
 		  
		    MainActivity.answered.put(question.getText().toString().trim(), Answer);
		    
		    
		    if(lastId!=MainActivity.stage){
			  
			 MainActivity.stage++;
	  	  
	 		    Intent i = new Intent(DataActivity.this, DataActivity.class);  
   		    startActivity(i);
   		    finish();
   		     
   		    
		  }else{
			  
			  
			     Intent intent=new Intent(DataActivity.this,Result.class);
		         startActivity(intent);
			  
		  }
		    
		  
   		    
   		    
	 }
	
	


	
	  private void playsound(String sound) {
	  
	  p = new MediaPlayer();
	  try {
	  AssetFileDescriptor afd = getAssets().openFd("sounds/"+sound+".mp3");
	  p.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
	  afd.close();
	  p.prepare();
	  } catch (Exception e) {
	  e.printStackTrace();
	  }
	  p.start();
	 }
	
	
	 
	  
	  @Override
	  public void onBackPressed() {
		   Tlist.cancel();
	       T.cancel();
		  finish();
	      return;
	  }  
	  
	  
 
	  
 public void onUserLeaveHint() {  
		  
 if (next_question!=1){
	     Intent intent = new Intent(DataActivity.this, MainActivity.class);
	           intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	           startActivity(intent);
	 
	        Tlist.cancel();
	       T.cancel();
		  finish();
	  
           }
		 
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
	       
	      		
	      		 
	   		            Intent intent = new Intent(DataActivity.this, MainActivity.class);
	   		           intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	   		           startActivity(intent);
	      		 
	   		        Tlist.cancel();
	   		       T.cancel();
	   			  finish();
	   		     
	   		      
	      	break;
	      	
	      	}
	      	return super.onOptionsItemSelected(item);
	      }

	 
	  
	  

}
