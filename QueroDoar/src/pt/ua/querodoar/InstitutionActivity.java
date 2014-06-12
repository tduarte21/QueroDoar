package pt.ua.querodoar;

import java.io.IOException;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class InstitutionActivity extends ActionBarActivity {

	private String objectID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_institution);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

		Bundle extras = getIntent().getExtras();
		objectID = extras.getString("inst");

		// showToast(objectID);

	}

	public String getObjectID() {
		return objectID;
	}

	public class UpdateUI implements Runnable {

		private String objectID;
		private View view;

		public UpdateUI(String objectID, View view) {
			this.objectID = objectID;
			this.view = view;
		}

		@Override
		public void run() {

			try {

				ParseQuery<ParseUser> queryUser = ParseQuery
						.getQuery(ParseUser.class);

				//Debug.waitForDebugger();

				ParseUser user = queryUser.get(objectID);
				// showToast(user.getUsername());

				// updateList();

				TextView txtInstitutionName = (TextView) view
						.findViewById(R.id.txtInstitutionName);
				ImageView imgInstImage = (ImageView) view
						.findViewById(R.id.imgInstImage);
				TextView txtInstInfo = (TextView) view
						.findViewById(R.id.txtInstInfo);
				

				txtInstitutionName.setText(user.getString("name"));
				txtInstInfo.setText(user.getString("description"));
				
				
				try {

					ParseFile imageFile = (ParseFile) user.getParseFile("image");
					
					if (imageFile != null) {

						BitmapScaler scaler = new BitmapScaler(imageFile, 150);
						imgInstImage.setImageBitmap(scaler.getScaled());

					}
				} catch (ParseException e) {
					showToast("Error loading image.");
				} catch (IOException e) {
					showToast("Error loading image.");
				}

				

				

			} catch (Exception e) {
				// showToast("Error: " + e);
				e.printStackTrace();
			}

		}

	}

	private void updateList() {
		// TODO Auto-generated method stub

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
			Intent in = new Intent(InstitutionActivity.this, Account.class);
			startActivity(in);

//		} else if (title.equals(getString(R.string.action_history))) {
//			
//			Intent in = new Intent(FeedActivity.this, DonationList.class);
//			startActivity(in);

		} else if (title.equals(getString(R.string.action_about))) {
			
			Intent in = new Intent(InstitutionActivity.this, About.class);
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
			View rootView = inflater.inflate(R.layout.fragment_institution,
					container, false);

			InstitutionActivity instAct = (InstitutionActivity) getActivity();

			String obj = instAct.getObjectID();
			
			final String objFinal = obj;
			
			Button btnRequests = (Button) rootView.findViewById(R.id.btnRequests);
			
			btnRequests.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					Intent intent = new Intent(getActivity(), RequestListActivity.class);

					intent.putExtra("inst", objFinal);

					startActivity(intent);

				}
			});

			UpdateUI update = instAct.new UpdateUI(obj, rootView);
			update.run();

			return rootView;
		}
	}

	public void showToast(final String toast) {
		this.runOnUiThread(new Runnable() {
			public void run() {
				Toast.makeText(InstitutionActivity.this, toast,
						Toast.LENGTH_SHORT).show();
			}
		});
	}

}
