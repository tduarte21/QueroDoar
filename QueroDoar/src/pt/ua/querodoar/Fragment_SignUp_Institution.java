package pt.ua.querodoar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import pt.ua.querodoar.R;

public class Fragment_SignUp_Institution extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static Fragment_SignUp_Institution newInstance(int sectionNumber) {
		Fragment_SignUp_Institution fragment = new Fragment_SignUp_Institution();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public Fragment_SignUp_Institution() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_signup_institution,
				container, false);
		//TextView textView = (TextView) rootView
		//		.findViewById(R.id.section_label);
		//textView.setText(Integer.toString(getArguments().getInt(
		//		ARG_SECTION_NUMBER)));
		return rootView;

	}

}