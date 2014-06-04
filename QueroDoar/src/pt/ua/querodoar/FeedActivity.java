package pt.ua.querodoar;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AnalogClock;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class FeedActivity extends ActionBarActivity {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	// private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	public static ArrayList<ClassNews> news = new ArrayList<ClassNews>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed);

		// mNavigationDrawerFragment = (NavigationDrawerFragment)
		// getSupportFragmentManager()
		// .findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();
		mTitle = getString(R.string.news_feed);

		// Set up the drawer.
		// mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
		// (DrawerLayout) findViewById(R.id.drawer_layout));

		/*
		 * View v = inflater.inflate(R.layout.fragment_productlist, container,
		 * false); ArrayAdapter<Product> adapter = new MyListAdapter(); ListView
		 * listView = (ListView) v.findViewById(R.id.lstViewProducts);
		 * listView.setAdapter(adapter);
		 * 
		 * return v;
		 */

		try {
			Parse.initialize(this, "wecAmPMM0H03a3HPTcpoY7AW2nKfFGtxgCOidzUo",
					"iquq2rrkjV0XxfZbyyVXVahaQfeR0RzSRTRpkTWz");
		} catch (NetworkOnMainThreadException e) {
			// upon resume or recent Parse.initialize exceptions this
			// exception is thrown
			// Temporarily supressing it right now
			// e.printStackTrace();
		}

		// By specifying no write privileges for the ACL, we can ensure the role
		// cannot be altered.
		// ParseACL adminRoleACL = new ParseACL();
		// adminRoleACL.setPublicReadAccess(true);
		// ParseRole role = new ParseRole("Admin", adminRoleACL);

		// ParseACL userRoleACL = new ParseACL();
		// userRoleACL.setPublicReadAccess(true);
		// ParseRole userRole = new ParseRole("User", userRoleACL);

		// ParseACL instRoleACL = new ParseACL();
		// instRoleACL.setPublicReadAccess(true);
		// ParseRole role = new ParseRole("Institution", instRoleACL);

		// userRole.saveInBackground();

		news.clear();

		// LoadNewsTask loadNews = new LoadNewsTask();
		// loadNews.execute();

		showToast(getString(R.string.loading_news));
		loadNewsList();

		// Font path
		String fontPath = "fonts/RobotoCondensed-Regular.ttf";

		// Loading Font Face
		Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

		Button btnDonate = (Button) findViewById(R.id.btnDonate);

		btnDonate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(FeedActivity.this, DonationListByCities.class);
				startActivity(in);
			}
		});

		// Applying font
		btnDonate.setTypeface(tf);

		// ParseObject testObject = new ParseObject("TestObject");
		// testObject.put("foo", "bar");
		// testObject.saveInBackground();

		// PushService.setDefaultPushCallback(this, FeedActivity.class);
		// ParseInstallation.getCurrentInstallation().saveInBackground();

	}

	// @Override
	// public void onNavigationDrawerItemSelected(int position) {
	// // update the main content by replacing fragments
	// FragmentManager fragmentManager = getSupportFragmentManager();
	// fragmentManager
	// .beginTransaction()
	// .replace(R.id.container,
	// PlaceholderFragment.newInstance(position + 1)).commit();
	// }

	public void onSectionAttached(int number) {
		/*
		 * switch (number) { case 1: mTitle =
		 * getString(R.string.title_section1); break; case 2: mTitle =
		 * getString(R.string.title_section2); break; case 3: mTitle =
		 * getString(R.string.title_section3); break; }
		 */
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// if (!mNavigationDrawerFragment.isDrawerOpen()) {
		// // Only show items in the action bar relevant to this screen
		// // if the drawer is not showing. Otherwise, let the drawer
		// // decide what to show in the action bar.
		getMenuInflater().inflate(R.menu.menu, menu);
		restoreActionBar();
		// return true;
		// }
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		String title = (String) item.getTitle();

		if (title.equals(getString(R.string.action_account))) {
			Intent in = new Intent(FeedActivity.this, DonationList.class);
			startActivity(in);

		} else if (title.equals(getString(R.string.action_history))) {
			
			Intent in = new Intent(FeedActivity.this, DonationList.class);
			startActivity(in);

		} else if (title.equals(getString(R.string.action_about))) {
			
			Intent in = new Intent(FeedActivity.this, DonationList.class);
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
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_feed, container,
					false);
			// TextView textView = (TextView)
			// rootView.findViewById(R.id.section_label);
			// textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));

			return rootView;

		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((FeedActivity) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
	}

	// private class LoadNewsTask extends AsyncTask<String, Void, String> {
	//
	// ArrayAdapter<ClassNews> adapter;
	//
	// @Override
	// protected String doInBackground(String... params) {
	// // this is where you implement your long-running task
	// //return doSomeLongOperation();
	//
	// final String NEWS_LABEL = "News";
	//
	// ParseQuery<ParseObject> query = ParseQuery.getQuery(NEWS_LABEL);
	//
	// // Retrieve the most recent ones
	// query.orderByDescending("createdAt");
	//
	// // Only retrieve the last ten
	// query.setLimit(10);
	//
	// // Include the post data with each comment
	// // query.include("title");
	// // query.include("image");
	// // query.include("description");
	//
	// query.findInBackground(new FindCallback<ParseObject>() {
	// public void done(List<ParseObject> newsList, ParseException e) {
	// // newsList now contains the last ten news, and the "post"
	// // field has been populated. For example:
	//
	// if (newsList != null) {
	// for (ParseObject row : newsList) {
	// // This does not require a network access.
	//
	// // ParseObject title = row.getParseObject("title");
	// // ParseObject image = row.getParseObject("image");
	// // ParseObject description =
	// // row.getParseObject("description");
	//
	// news.add(new ClassNews(row.getString("title"), row
	// .getInt("image"), row.getString("description")));
	//
	// // title.delete();
	//
	// // Log.d("post", "retrieved a related post");
	// }
	// }
	// }
	// });
	//
	//
	// adapter = new MyListAdapter();
	//
	// // // Query for the latest objects from Parse.
	// // query.findInBackground(new FindCallback<ParseObject>() {
	// // public void done(final List<ParseObject> newsList, ParseException e)
	// // {
	// // if (e != null) {
	// // // There was an error or the network wasn't available.
	// // return;
	// // }
	// //
	// // // Release any objects previously pinned for this query.
	// // ParseObject.unpinAllInBackground(NEWS_LABEL, newsList, new
	// // DeleteCallback() {
	// // public void done(ParseException e) {
	// // if (e != null) {
	// // // There was some error.
	// // return;
	// // }
	// //
	// // // Add the latest results for this query to the cache.
	// // ParseObject.pinAllInBackground(NEWS_LABEL, newsList);
	// // }
	// // });
	// // }
	// // });
	//
	// // ParseQuery<ParseObject> query = ParseQuery.getQuery("News");
	// // query.include("title");w(, new GetCallback<ParseObject>() {
	// // public void done(ParseObject object, ParseException e) {
	// // if (e == null) {
	// // // object will be your game score
	// //
	// //
	// // String title = object.getString("title");
	// // int image = object.getInt("image");
	// // String description = object.getString("description");
	// //
	// // news.add(new ClassNews(title,image,description));
	// //
	// // } else {
	// // // something went wrong
	// // }
	// // }
	// // });
	//
	//
	//
	// return "";
	//
	//
	//
	// }
	//
	// @Override
	// public void onPostExecute(String result) {
	// // here, you can update, and manipulate, your views
	// //updateUiWithResult(result);
	//
	// //ArrayAdapter<ClassNews> adapter = new MyListAdapter();
	// ListView listView = (ListView) findViewById(R.id.lstViewNews);
	// listView.setAdapter(adapter);
	// showToast(adapter.getCount()+"");
	//
	// }
	// }
	//
	// private void populateNews() {
	// saveNews("Notícia 1", R.drawable.hd, "Descrição - Máquina Café");
	// SystemClock.sleep(350);
	// saveNews("Notícia 2", R.drawable.hd, "Descrição - Lata de Atum");
	// SystemClock.sleep(350);
	// saveNews("Notícia 3", R.drawable.hd, "Descrição - Água 0.5L");
	// SystemClock.sleep(350);
	// saveNews("Notícia 4", R.drawable.hd, "Descrição - Água 5L");
	// SystemClock.sleep(350);
	// saveNews("Notícia 5", R.drawable.hd, "Descrição - Hamburguer");
	// SystemClock.sleep(350);
	// saveNews("Notícia 6", R.drawable.hd, "Descrição - Chá");
	// SystemClock.sleep(350);
	// saveNews("Notícia 7", R.drawable.hd, "Descrição - Pizza");
	// SystemClock.sleep(350);
	// saveNews("Notícia 8", R.drawable.hd, "Descrição - Cenoura");
	// }
	//
	// private void saveNews(String title, int image, String description) {
	//
	// // Parse.initialize(this, "wecAmPMM0H03a3HPTcpoY7AW2nKfFGtxgCOidzUo",
	// // "iquq2rrkjV0XxfZbyyVXVahaQfeR0RzSRTRpkTWz");
	//
	// ParseObject parseObject = new ParseObject("News");
	//
	// parseObject.put("title", title);
	// parseObject.put("image", image);
	// parseObject.put("description", description);
	//
	// parseObject.saveInBackground();
	// }

	public class UpdateUI implements Runnable {

		private String result;

		public UpdateUI(String result) {
			this.result = result;
		}

		@Override
		public void run() {

			try {

				ArrayAdapter<ClassNews> adapter = new MyListAdapter();
				ListView listView = (ListView) findViewById(R.id.lstViewNews);
				listView.setAdapter(adapter);

			} catch (Exception e) {
				showToast("Error: " + e);
			}

		}
	}

	private void loadNewsList() {

		// Parse.initialize(this,
		// "wecAmPMM0H03a3HPTcpoY7AW2nKfFGtxgCOidzUo","iquq2rrkjV0XxfZbyyVXVahaQfeR0RzSRTRpkTWz");

		final String NEWS_LABEL = "News";

		ParseQuery<ParseObject> query = ParseQuery.getQuery(NEWS_LABEL);

		// Retrieve the most recent ones
		query.orderByDescending("createdAt");

		// Only retrieve the last ones
		query.setLimit(20);

		// Include the post data with each comment
		// query.include("title");
		// query.include("image");
		// query.include("description");

		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> newsList, ParseException e) {
				// newsList now contains the last ten news, and the "post"
				// field has been populated. For example:

				if (newsList != null) {
					for (ParseObject row : newsList) {
						// This does not require a network access.

						// ParseObject title = row.getParseObject("title");
						// ParseObject image = row.getParseObject("image");
						// ParseObject description =
						// row.getParseObject("description");

						news.add(new ClassNews(row.getString("title"), row
								.getParseFile("image"), row
								.getString("description")));

						// title.delete();

						// Log.d("post", "retrieved a related post");
					}

					UpdateUI update = new UpdateUI("result");
					update.run();

				}
			}
		});

		// // Query for the latest objects from Parse.
		// query.findInBackground(new FindCallback<ParseObject>() {
		// public void done(final List<ParseObject> newsList, ParseException e)
		// {
		// if (e != null) {
		// // There was an error or the network wasn't available.
		// return;
		// }
		//
		// // Release any objects previously pinned for this query.
		// ParseObject.unpinAllInBackground(NEWS_LABEL, newsList, new
		// DeleteCallback() {
		// public void done(ParseException e) {
		// if (e != null) {
		// // There was some error.
		// return;
		// }
		//
		// // Add the latest results for this query to the cache.
		// ParseObject.pinAllInBackground(NEWS_LABEL, newsList);
		// }
		// });
		// }
		// });

		// ParseQuery<ParseObject> query = ParseQuery.getQuery("News");
		// query.include("title");w(, new GetCallback<ParseObject>() {
		// public void done(ParseObject object, ParseException e) {
		// if (e == null) {
		// // object will be your game score
		//
		//
		// String title = object.getString("title");
		// int image = object.getInt("image");
		// String description = object.getString("description");
		//
		// news.add(new ClassNews(title,image,description));
		//
		// } else {
		// // something went wrong
		// }
		// }
		// });

	}

	private class MyListAdapter extends ArrayAdapter<ClassNews> {

		public MyListAdapter() {
			super(FeedActivity.this, R.layout.activity_feed, news);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View itemView = convertView;

			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			if (itemView == null) {
				itemView = inflater.inflate(R.layout.news_view, parent, false);
			}

			// Find the product to work with
			ClassNews currentNews = news.get(position);

			TextView txtTitle = (TextView) itemView
					.findViewById(R.id.viewNewsTitle);
			txtTitle.setText(currentNews.getTitle());

			ImageView imageView = (ImageView) itemView
					.findViewById(R.id.imgNews);

			try {
				ParseFile imageFile = (ParseFile) currentNews.getImage();
				if (imageFile != null) {
					byte[] file = imageFile.getData();
					Bitmap imageFileBitmap = BitmapFactory.decodeByteArray(
							file, 0, file.length);
					imageView.setImageBitmap(imageFileBitmap);
				}
			} catch (ParseException e) {
				showToast("Error loading image.");
			}

			TextView txtDescription = (TextView) itemView
					.findViewById(R.id.viewNewsDescr);
			txtDescription.setText(currentNews.getDescription());

			return itemView;
			// return super.getView(position, convertView, parent);
		}

	}

	public void showToast(final String toast) {
		runOnUiThread(new Runnable() {
			public void run() {
				Toast.makeText(FeedActivity.this, toast, Toast.LENGTH_SHORT)
						.show();
			}
		});
	}

}
