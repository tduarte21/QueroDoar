package pt.ua.querodoar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.os.NetworkOnMainThreadException;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class RequestListActivity extends ActionBarActivity {

	public static ArrayList<ClassProduct> requests = new ArrayList<ClassProduct>();

	String objectID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_request_list);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

		Bundle extras = getIntent().getExtras();
		objectID = extras.getString("inst");

		loadProductsList(objectID);
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
			Intent in = new Intent(RequestListActivity.this, Account.class);
			startActivity(in);

//		} else if (title.equals(getString(R.string.action_history))) {
//			
//			Intent in = new Intent(FeedActivity.this, DonationList.class);
//			startActivity(in);

		} else if (title.equals(getString(R.string.action_about))) {
			
			Intent in = new Intent(RequestListActivity.this, About.class);
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

	private void loadProductsList(String objectID) {

		try {
			Parse.initialize(this, "wecAmPMM0H03a3HPTcpoY7AW2nKfFGtxgCOidzUo",
					"iquq2rrkjV0XxfZbyyVXVahaQfeR0RzSRTRpkTWz");
		} catch (NetworkOnMainThreadException e) {
			// upon resume or recent Parse.initialize exceptions this
			// exception is thrown
			// Temporarily supressing it right now
			// e.printStackTrace();
		}

		requests.clear();

		final String IDinstFilter = objectID;

		final String LABEL = "Product";

		ParseQuery<ParseObject> query = ParseQuery.getQuery(LABEL);

		// query.whereEqualTo("IDinst", objectID);
		// Retrieve the most recent ones
		query.orderByDescending("createdAt");

		int size;
		try {
			size = query.count();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Only retrieve the last ones
		// query.setLimit(20);

		// Include the post data with each comment
		// query.include("title");
		// query.include("image");
		// query.include("description");

		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> prodList, ParseException e) {
				// newsList now contains the last ten news, and the "post"
				// field has been populated. For example:

				if (prodList != null) {
					for (ParseObject row : prodList) {
						// This does not require a network access.

						// ParseObject title = row.getParseObject("title");
						// ParseObject image = row.getParseObject("image");
						// ParseObject description =
						// row.getParseObject("description");

						//Debug.waitForDebugger();
						ParseUser inst = row.getParseUser("IDinst");
						String IDinst = inst.getObjectId();

						if (IDinstFilter.equals(IDinst)) {

							requests.add(new ClassProduct(row.getObjectId(),
									row.getString("name"), row
											.getParseFile("image"), IDinst, row
											.getNumber("price").floatValue(),
									row.getString("description")));
						}

						// title.delete();

						// Log.d("post", "retrieved a related post");
					}

					UpdateUI update = new UpdateUI("result");
					update.run();

				}
			}
		});

	}

	public class UpdateUI implements Runnable {

		private String result;

		public UpdateUI(String result) {
			this.result = result;
		}

		@Override
		public void run() {

			try {
				
				ProgressBar progress = (ProgressBar) findViewById(R.id.progressBarReqList);
				progress.setVisibility(progress.INVISIBLE);

				ArrayAdapter<ClassProduct> adapter = new MyListAdapter();
				ListView listView = (ListView) findViewById(R.id.lstViewProduct);

				listView.setClickable(true);

				final ArrayAdapter<ClassProduct> adapterFinal = adapter;

				OnItemClickListener listener = new OnItemClickListener() {

					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub

						ClassProduct product = adapterFinal.getItem(position);

//						showToast("Clicou em " + product.getName() + " "
//								+ product.getInstID());

						Intent intent = new Intent(RequestListActivity.this, RequestActivity.class);

						intent.putExtra("product", product.getObjectID());

						startActivityForResult(intent,1);

					}

				};

				listView.setOnItemClickListener(listener);

				listView.setAdapter(adapter);

			} catch (Exception e) {
				showToast("Error: " + e);
			}

		}
	}

	private class MyListAdapter extends ArrayAdapter<ClassProduct> implements
			OnItemClickListener {

		public MyListAdapter() {
			super(RequestListActivity.this, R.layout.fragment_request, requests);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View itemView = convertView;

			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			if (itemView == null) {
				itemView = inflater.inflate(R.layout.item_view, parent, false);
			}

			// Find the product to work with
			ClassProduct currentProduct = requests.get(position);

			TextView txtTitle = (TextView) itemView
					.findViewById(R.id.viewProduct);
			txtTitle.setText(currentProduct.getName());

			ImageView imageView = (ImageView) itemView
					.findViewById(R.id.imgProduct);

			try {

				ParseFile imageFile = (ParseFile) currentProduct.getImage();
				if (imageFile != null) {

					BitmapScaler scaler = new BitmapScaler(imageFile, 150);
					imageView.setImageBitmap(scaler.getScaled());

				}
			} catch (ParseException e) {
				showToast("Error loading image.");
			} catch (IOException e) {
				showToast("Error loading image.");
			}

			TextView txtDescription = (TextView) itemView
					.findViewById(R.id.viewProdDescr);
			txtDescription.setText(currentProduct.getDescription());

			TextView txPrice = (TextView) itemView.findViewById(R.id.viewPrice);
			txPrice.setText(currentProduct.getPrice() + "0€");

			return itemView;
			// return super.getView(position, convertView, parent);
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub

			// showToast("Clicou em " + position + " " + id);

		}

	}

	public void showToast(final String toast) {
		runOnUiThread(new Runnable() {
			public void run() {
				Toast.makeText(RequestListActivity.this, toast,
						Toast.LENGTH_SHORT).show();
			}
		});
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
			View rootView = inflater.inflate(R.layout.fragment_request_list,
					container, false);
			
			ProgressBar progress = (ProgressBar) rootView.findViewById(R.id.progressBarReqList);
			progress.setVisibility(progress.VISIBLE);
			
			return rootView;
		}
	}

	

}