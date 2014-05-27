package pt.ua.querodoar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Fragment_Donation_Institutions extends Fragment {
	
	private static final String ARG_SECTION_NUMBER = "section_number";

	
	List<ClassInstitution> institutions;
	Map<ClassInstitution,List<ClassProduct>> collectionMapInstProd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Parse.initialize(getActivity(), "wecAmPMM0H03a3HPTcpoY7AW2nKfFGtxgCOidzUo",
				"iquq2rrkjV0XxfZbyyVXVahaQfeR0RzSRTRpkTWz");
		
		createInstitutionGroupList();

	}
	
	private void createInstitutionGroupList() {

		institutions = new ArrayList<ClassInstitution>();
		final String INSTITUTION_LABEL = "Institution";

		ParseQuery<ParseObject> query = ParseQuery.getQuery(INSTITUTION_LABEL);

		query.orderByDescending("name");

		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> institutionList, ParseException e) {
				
				if (institutionList != null) {
					for (ParseObject row : institutionList) {
						// This does not require a network access.

						// ParseObject title = row.getParseObject("title");
						// ParseObject image = row.getParseObject("image");
						// ParseObject description = row.getParseObject("description");

						institutions.add(new ClassInstitution(row.getString("name"), row
								.getInt("image"), row.getString("city"),row.getInt("year"),row.getString("description")));
					}
				}

			}
		});

	}

	public static Fragment_Donation_Institutions newInstance(int sectionNumber) {
		Fragment_Donation_Institutions fragment = new Fragment_Donation_Institutions();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_donation_institutions, container, false);

		return v;
	}

}

