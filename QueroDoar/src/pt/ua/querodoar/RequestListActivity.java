package pt.ua.querodoar;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

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
		getMenuInflater().inflate(R.menu.request_list, menu);
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
	
	
	private void loadProductsList(String objectID) {

		// Parse.initialize(this,
		// "wecAmPMM0H03a3HPTcpoY7AW2nKfFGtxgCOidzUo","iquq2rrkjV0XxfZbyyVXVahaQfeR0RzSRTRpkTWz");

		final String LABEL = "Products";

		ParseQuery<ParseObject> query = ParseQuery.getQuery(LABEL);

		query.whereEqualTo("IDinst", objectID);
		// Retrieve the most recent ones
		query.orderByDescending("createdAt");

		// Only retrieve the last ones
		query.setLimit(20);

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

						requests.add(new ClassProduct(row.getString("name"), row.getParseFile("image"), row.getString("IDinst"), row.getNumber("price").floatValue(), row.getString("description")));

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

				ArrayAdapter<ClassProduct> adapter = new MyListAdapter();
				ListView listView = (ListView) findViewById(R.id.lstViewProduct);
				listView.setAdapter(adapter);

			} catch (Exception e) {
				showToast("Error: " + e);
			}

		}
	}
	
	private class MyListAdapter extends ArrayAdapter<ClassProduct> {

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

			TextView txtTitle = (TextView) itemView.findViewById(R.id.viewProduct);
			txtTitle.setText(currentProduct.getName());

			ImageView imageView = (ImageView) itemView.findViewById(R.id.imgProduct);

			try {
				ParseFile imageFile = (ParseFile) currentProduct.getImage();
				if (imageFile != null) {
					byte[] file = imageFile.getData();
					Bitmap imageFileBitmap = BitmapFactory.decodeByteArray(
							file, 0, file.length);
					imageView.setImageBitmap(imageFileBitmap);
				}
			} catch (ParseException e) {
				showToast("Error loading image.");
			}

			TextView txtDescription = (TextView) itemView.findViewById(R.id.viewProdDescr);
			txtDescription.setText(currentProduct.getDescription());
			
			TextView txPrice = (TextView) itemView.findViewById(R.id.viewPrice);
			txPrice.setText(currentProduct.getPrice()+"");

			return itemView;
			// return super.getView(position, convertView, parent);
		}

	}

	public void showToast(final String toast) {
		runOnUiThread(new Runnable() {
			public void run() {
				Toast.makeText(RequestListActivity.this, toast, Toast.LENGTH_SHORT)
						.show();
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
			return rootView;
		}
	}

}
