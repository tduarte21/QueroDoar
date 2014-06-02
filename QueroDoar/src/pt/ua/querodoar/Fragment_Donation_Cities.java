package pt.ua.querodoar;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Fragment_Donation_Cities extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";

	List<ClassCity> cities;
	List<ClassInstitution> inst;
	Map<ClassCity, List<ClassInstitution>> collectionMapCityInst;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Parse.initialize(getActivity(),
				"wecAmPMM0H03a3HPTcpoY7AW2nKfFGtxgCOidzUo",
				"iquq2rrkjV0XxfZbyyVXVahaQfeR0RzSRTRpkTWz");

		loadCityList();

		// while(cities.isEmpty())
		// {
		// try {
		// Thread.sleep(2000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

		loadInstList();

		// while(inst.isEmpty())
		// {
		// try {
		// Thread.sleep(2000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

		//createCityInstCollection();

		// createInstGroupList();

	}

	private void loadCityList() {
		cities = new ArrayList<ClassCity>();

		inst = new ArrayList<ClassInstitution>();
		final String CITY_LABEL = "City";

		ParseQuery<ParseObject> query = ParseQuery.getQuery(CITY_LABEL);

		query.orderByDescending("name");

		// cities.add(new ClassCity("Aveiro", 1000));
		// cities.add(new ClassCity("Porto", 2000));
		// cities.add(new ClassCity("Coimbra", 1000));
		// cities.add(new ClassCity("Braga", 1100));

		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> cityList, ParseException e) {
				// newsList now contains the lasty ten news, and the "post"
				// field has been populated. For example:

				if (cityList != null) {
					for (ParseObject row : cityList) {
						// This does not require a network access.

						// Debug.waitForDebugger();
						// ParseObject title = row.getParseObject("title");
						// ParseObject image = row.getParseObject("image");
						// ParseObject description =
						// row.getParseObject("description");

						cities.add(new ClassCity(row.getString("objectId"), row
								.getString("name"), 1));

						

						// title.delete();
						// getInstitutions(row.getString("objectId"));
						// Log.d("post", "retrieved a related post");
					}
				}

			}
		});

	}

	private void loadInstList() {
		// TODO Auto-generated method stub
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Institution");
		// query.whereEqualTo("city", objectId);

		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> instList, ParseException e) {

				if (instList != null) {
					for (ParseObject row : instList) {
						inst.add(new ClassInstitution(row.getString("name"),
								row.getInt("image"), row.getString("city"),
								1000, row.getString("description")));
						showToast(row.getString("name"));
					}
				}
				createCityInstCollection();
			}

		});
	}

	private void createInstGroupList() {
		inst = new ArrayList<ClassInstitution>();
		inst.add(new ClassInstitution("Instituição 1", 0, "Aveiro", 1000,
				"Descrição 1"));
		inst.add(new ClassInstitution("Instituição 2", 0, "Porto", 1000,
				"Descrição 2"));
		inst.add(new ClassInstitution("Instituição 3", 0, "Coimbra", 1000,
				"Descrição 3"));
		inst.add(new ClassInstitution("Instituição 4", 0, "Braga", 1000,
				"Descrição 4"));

	}

	private void createCityInstCollection() {

		collectionMapCityInst = new LinkedHashMap<ClassCity, List<ClassInstitution>>();

		// android.os.Debug.waitForDebugger();
		for (ClassCity city : cities) {
			List<ClassInstitution> childList = new ArrayList<ClassInstitution>();

			for (ClassInstitution in : inst) {

				if (city.getObjectID().equals(in.getLocation())) {

					childList.add(in);
				}
				showToast(city.getObjectID() + " " + in.getLocation());

			}
			collectionMapCityInst.put(city, childList);
		}
		
		
		showToast("Cities: " + cities.size());
		showToast("Institutions: " + inst.size());
		showToast("Collection: " + collectionMapCityInst.size());
		
		
		new UpdateUI("result");

	}

	public class UpdateUI implements Runnable {

		private String result;

		public UpdateUI(String result) {
			this.result = result;
		}

		@Override
		public void run() {

			try {

				while (getView() == null) {
					Thread.sleep(500);
				}
				updateList(getView());

			} catch (Exception e) {
				showToast("JSON Error: " + e);
			}

		}
	}

	public static Fragment_Donation_Cities newInstance(int sectionNumber) {
		Fragment_Donation_Cities fragment = new Fragment_Donation_Cities();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_donation_cities, container,
				false);

		// while (collectionMapCityInst.isEmpty())
		//
		// {
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

		return v;
	}

	private void updateList(View v) {
		ExpandableListView expListView = (ExpandableListView) v
				.findViewById(R.id.elvInstitutionList);
		ExpandableCitiesListAdapter expListAdapter = new ExpandableCitiesListAdapter(
				getActivity(), cities, collectionMapCityInst);
		expListView.setAdapter(expListAdapter);
	}

	public class ExpandableCitiesListAdapter extends BaseExpandableListAdapter {

		private Activity context;
		private Map<ClassCity, List<ClassInstitution>> miscCollections;
		private List<ClassCity> topiclist;

		public ExpandableCitiesListAdapter(Activity context,
				List<ClassCity> topiclist,
				Map<ClassCity, List<ClassInstitution>> miscCollections) {
			this.context = context;
			this.miscCollections = miscCollections;
			this.topiclist = topiclist;
		}

		public Object getChild(int groupPosition, int childPosition) {
			return miscCollections.get(topiclist.get(groupPosition)).get(
					childPosition);
		}

		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		public View getChildView(final int groupPosition,
				final int childPosition, boolean isLastChild, View convertView,
				ViewGroup parent) {
			final ClassInstitution topic = (ClassInstitution) getChild(
					groupPosition, childPosition);
			LayoutInflater inflater = context.getLayoutInflater();

			if (convertView == null) {
				convertView = inflater.inflate(
						R.layout.expandable_cities_view_child, null);
			}

			TextView viewExpCityInstName = (TextView) convertView
					.findViewById(R.id.viewExpCityInstName);
			TextView viewExpCityInstDescr = (TextView) convertView
					.findViewById(R.id.viewExpCityInstDescr);
			ImageView viewExpCityInstImage = (ImageView) convertView
					.findViewById(R.id.viewExpCityInstImage);

			viewExpCityInstName.setText(topic.getName());
			viewExpCityInstDescr.setText(topic.getDescription());
			viewExpCityInstImage.setImageResource(topic.getImage());

			// delete.setOnClickListener(new OnClickListener() {
			//
			// public void onClick(View v) {
			// AlertDialog.Builder builder = new AlertDialog.Builder(
			// context);
			// builder.setMessage("Do you want to remove?");
			// builder.setCancelable(false);
			// builder.setPositiveButton("Yes",
			// new DialogInterface.OnClickListener() {
			// public void onClick(DialogInterface dialog,
			// int id) {
			// List<String> child = miscCollections
			// .get(topiclist.get(groupPosition));
			// child.remove(childPosition);
			// notifyDataSetChanged();
			// }
			// });
			// builder.setNegativeButton("No",
			// new DialogInterface.OnClickListener() {
			// public void onClick(DialogInterface dialog,
			// int id) {
			// dialog.cancel();
			// }
			// });
			// AlertDialog alertDialog = builder.create();
			// alertDialog.show();
			// }
			// });

			return convertView;
		}

		public int getChildrenCount(int groupPosition) {
			return miscCollections.get(topiclist.get(groupPosition)).size();
		}

		public Object getGroup(int groupPosition) {
			return topiclist.get(groupPosition);
		}

		public int getGroupCount() {
			return topiclist.size();
		}

		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			ClassCity city = (ClassCity) getGroup(groupPosition);
			if (convertView == null) {
				LayoutInflater infalInflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(
						R.layout.expandable_cities_view_parent, null);
			}

			TextView viewExpCityName = (TextView) convertView
					.findViewById(R.id.viewExpCityName);
			TextView viewExpCityDescr = (TextView) convertView
					.findViewById(R.id.viewExpCityDescr);
			ImageView viewExpCityImage = (ImageView) convertView
					.findViewById(R.id.viewExpCityImage);

			viewExpCityName.setText(city.getName());
			viewExpCityName.setTypeface(null, Typeface.BOLD);
			// viewExpCityDescr.setText(city.getPoints());
			// viewExpCityImage.setImageResource(city.getImage());

			return convertView;
		}

		public boolean hasStableIds() {
			return true;
		}

		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}

	}

	public void showToast(final String toast) {
		getActivity().runOnUiThread(new Runnable() {
			public void run() {
				Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();
			}
		});
	}

}
