package pt.ua.querodoar;

import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseRole;
import com.parse.ParseUser;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;

public class InstitutionActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_institution);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		
		Bundle extras = getIntent().getExtras();
		String objectID = extras.getString("inst");
		
		showToast(objectID);
		
		
		
		
		ParseQuery<ParseUser> queryUser = ParseQuery.getQuery(ParseUser.class);

		queryUser.whereMatches("objectID", objectID);

		queryUser.findInBackground(new FindCallback<ParseUser>() {
			public void done(List<ParseUser> userList, ParseException e) {
				if (userList != null) {
					
					for (ParseUser parseUser : userList) {
						showToast(parseUser.getUsername());
					}


				}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.institution, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_institution,
					container, false);
			return rootView;
		}
	}
	
	public void showToast(final String toast) {
		this.runOnUiThread(new Runnable() {
			public void run() {
				Toast.makeText(InstitutionActivity.this, toast, Toast.LENGTH_SHORT).show();
			}
		});
	}

}
