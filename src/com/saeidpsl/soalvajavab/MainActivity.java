package com.saeidpsl.soalvajavab;
 
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.app.Activity;  
import android.content.Intent;
import android.database.Cursor;  
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.ListView; 
import android.view.View; 
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {  
 
 DBHelper dbhelper;  
 int Process_id;
 public static int stage; 
 public static String selected; 
 public static Map<String, String> answered = new HashMap<String, String>(); 
 private DBHelper db;
 
 @Override  
 public void onCreate(Bundle savedInstanceState) {  
 
	 
	 super.onCreate(savedInstanceState);  
     setContentView(R.layout.activity_main);  

   
	db = new DBHelper(this);
  
    Cursor cs =  db.db_object.rawQuery("SELECT * FROM list" , null);
    ArrayList<String> listarry = new ArrayList<String>();
    
 
    if (cs .moveToFirst()) {

        while (cs.isAfterLast() == false) { 
            listarry.add(cs.getString(cs.getColumnIndex("group")));
            cs.moveToNext();
        }
    }
  
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, listarry);
    
    ListView list = (ListView) findViewById(R.id.listView1);  
    
    list.setAdapter(adapter);  
 
 
    
    cs.close();
      

    list.setOnItemClickListener(new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
		  
		    
		    selected=""+((TextView) view).getText();
		  
		     Intent intent=new Intent(MainActivity.this,DataActivity.class);
	         startActivity(intent);
   		 
   		 
   		stage=1;
		    
		}
	});
    
 
	 
   
 }  
 
 
 
 
 
 
 
 
 

}
