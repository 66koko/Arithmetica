package ikolab.arithemtica;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity implements OnClickListener {
	
	
	@Override
	public void onBackPressed() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Exit Arithmetica ?")
		.setCancelable(false)
		.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// TODO Auto-generated method stub
				finish();
				System.exit(0);
			}
		})
		.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

	//ui items
	private Button playBtn, helpBtn, highBtn;
	//level names
	String[] levelNames = {"Easy", "Medium", "Hard"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//retrieve references
		playBtn = (Button)findViewById(R.id.play_btn);
		helpBtn = (Button)findViewById(R.id.help_btn);
		highBtn = (Button)findViewById(R.id.high_btn);

		//listen for clicks
		playBtn.setOnClickListener(this);
		helpBtn.setOnClickListener(this);
		highBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		if(view.getId()==R.id.play_btn){
			//play button
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Choose a level")
			.setSingleChoiceItems(levelNames, 0, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					//start gameplay
					startPlay(which);
				} 
			});
			AlertDialog ad = builder.create();
			ad.show();
		}
		else if(view.getId()==R.id.help_btn){
			//how to play button
			Intent i=new Intent(getApplicationContext(), HowToPlay.class);
			startActivity(i);
			
		}
		else if(view.getId()==R.id.high_btn){
			//high scores button
			Intent i=new Intent(getApplicationContext(), HighScores.class);
			startActivity(i);
		}
	}

	private void startPlay(int chosenLevel){
		//start gameplay
		Intent playIntent = new Intent(this, PlayGame.class);
		playIntent.putExtra("level", chosenLevel);
		this.startActivity(playIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	    * On selecting action bar icons
	    * */
	   @Override
	   public boolean onOptionsItemSelected(MenuItem item) {
	       // Take appropriate action for each action item click
	       switch (item.getItemId()) {
	    
	           
	       case R.id.close:
	           // website action
	    	   Close();
	           return true;
	          
	       case R.id.apropos:
	           // album open
	           Apropos();
	         return true;
	         
	       case R.id.share:
	           // album open
	           Share();
	         return true;
	         
	       	         
	       default:
	           return super.onOptionsItemSelected(item);
	       }
	   }

	   private void Close() {
		// TODO Auto-generated method stub
		  
		   finish();
	       System.exit(0);
		
	}

	

	

	private void Share() {
		

			AlertDialog.Builder adb=new AlertDialog.Builder(MainActivity.this);



			adb.setTitle("Arithemtica::");

			adb.setMessage("Do you want to share Arithmetica"+" ?");

			adb.setPositiveButton("No", null);
			
			adb.setNeutralButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int which) {
					
					final Intent MessIntent = new Intent(Intent.ACTION_SEND);
					MessIntent.setType("text/plain");
					MessIntent.putExtra(Intent.EXTRA_TEXT,"Hello, I have just downloaded Arithmetica, a simple Arithemtic Game. \n\nEasy way to do some simple math ! \n\n Interested? Download it from Samsung App Store.\n\nLets Play.");
					MainActivity.this.startActivity(Intent.createChooser(MessIntent, " Share this Game ..."));					}
			});



			adb. setIcon(R.drawable.ic_launcher);
			adb.show();
		
			}




	private void Apropos() {
		// TODO Auto-generated method stub
		   Intent i = new Intent(MainActivity.this, HowToPlay.class);
	       startActivity(i);
	}
}
