package pt.ua.querodoar;

import com.parse.ParseUser;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class DonationListByCities extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_donation_list_by_cities);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new Fragment_Donation_Cities()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		String title = (String) item.getTitle();

		if (title.equals(getString(R.string.action_account))) {
			Intent in = new Intent(DonationListByCities.this, Account.class);
			startActivity(in);

//		} else if (title.equals(getString(R.string.action_history))) {
//			
//			Intent in = new Intent(FeedActivity.this, DonationList.class);
//			startActivity(in);

		} else if (title.equals(getString(R.string.action_about))) {
			
			Intent in = new Intent(DonationListByCities.this, About.class);
			startActivity(in);

		} else if (title.equals(getString(R.string.action_logout))) {
			ParseUser.logOut();
			System.exit(0);
		}
		else if (title.equals(getString(R.string.action_exit))) {
			
			System.exit(0);
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
			View rootView = inflater
					.inflate(R.layout.fragment_donation_list_by_cities,
							container, false);
			return rootView;
		}
	}

}
