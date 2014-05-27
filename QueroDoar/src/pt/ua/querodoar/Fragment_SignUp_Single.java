package pt.ua.querodoar;

import java.util.List;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Fragment_SignUp_Single extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";

	private EditText txtFirstName;
	private EditText txtLastName;
	private EditText txtEmail;
	private EditText txtPassword;
	private EditText txtPasswordConfirm;
	private Spinner spCity;
	private ToggleButton tbAnon;
	private Button btnCreateAccount;

	private String strFirstName;
	private String strLastName;
	private String strEmail;
	private String strPassword;
	private String strConfirmPassword;
	private String strCity;
	private boolean boolAnon;

	// flag for Internet connection status
	Boolean isInternetPresent = false;
	// Connection detector class
	ConnectionDetector cd;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static Fragment_SignUp_Single newInstance(int sectionNumber) {
		Fragment_SignUp_Single fragment = new Fragment_SignUp_Single();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public Fragment_SignUp_Single() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_signup_single,
				container, false);
		// TextView textView = (TextView) view.findViewById(R.id.section_label);
		// textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));

		cd = new ConnectionDetector(getActivity());

		txtFirstName = (EditText) view
				.findViewById(R.id.txtSignUpSingleFirstName);
		txtLastName = (EditText) view
				.findViewById(R.id.txtSignUpSingleLastName);
		txtEmail = (EditText) view.findViewById(R.id.txtSignUpSingleEmail);
		txtPassword = (EditText) view
				.findViewById(R.id.txtSignUpSinglePassword);
		txtPasswordConfirm = (EditText) view
				.findViewById(R.id.txtSignUpSinglePasswordConfirm);
		tbAnon = (ToggleButton) view.findViewById(R.id.tbSignUpSingleAnon);
		spCity = (Spinner) view.findViewById(R.id.spSignUpSingleCity);

		btnCreateAccount = (Button) view.findViewById(R.id.btnCreateAccount);
		btnCreateAccount.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.btnCreateAccount:
					// get Internet status
					isInternetPresent = cd.isConnectingToInternet();
					// check for Internet status
					if (isInternetPresent) {
						// Internet Connection is Present
						// make HTTP requests
						createAccount();
					} else {
						// Internet connection is not present
						// Ask user to connect to Internet
						showAlertDialog(getActivity(),
								"No Internet Connection",
								"You don't have internet connection.", false);
					}

					break;

				default:
					break;
				}

			}
		});

		onCreateParse();
		
		return view;

	}

	private void createAccount() {
		clearErrors();

		boolean cancel = false;
		View focusView = null;

		// Store values at the time of the login attempt.
		strFirstName = txtFirstName.getText().toString();
		strLastName = txtLastName.getText().toString();
		strEmail = txtEmail.getText().toString();
		strPassword = txtPassword.getText().toString();
		strConfirmPassword = txtPasswordConfirm.getText().toString();
		strCity = spCity.getSelectedItem().toString();
		boolAnon = tbAnon.isChecked();

		// Check for a valid confirm password.
		if (TextUtils.isEmpty(strConfirmPassword)) {
			txtPasswordConfirm
					.setError(getString(R.string.error_field_required));
			focusView = txtPasswordConfirm;
			cancel = true;
		} else if (strPassword != null
				&& !strConfirmPassword.equals(strPassword)) {
			txtPassword
					.setError(getString(R.string.error_invalid_confirm_password));
			focusView = txtPassword;
			cancel = true;
		}
		// Check for a valid password.
		if (TextUtils.isEmpty(strPassword)) {
			txtPassword.setError(getString(R.string.error_field_required));
			focusView = txtPassword;
			cancel = true;
		} else if (strPassword.length() < 4) {
			txtPassword.setError(getString(R.string.error_invalid_password));
			focusView = txtPassword;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(strEmail)) {
			txtEmail.setError(getString(R.string.error_field_required));
			focusView = txtEmail;
			cancel = true;
		} else if (!strEmail.contains("@")) {
			txtEmail.setError(getString(R.string.error_invalid_email));
			focusView = txtEmail;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.

			Toast.makeText(getActivity(), "signUp", Toast.LENGTH_SHORT).show();

			signUp(strFirstName, strLastName,
					strEmail.toLowerCase(Locale.getDefault()), strEmail,
					strPassword, strCity, boolAnon);

		}

	}

	private void signUp(String firstName, String lastName, String mUsername,
			String mEmail, String mPassword, String city, boolean anon) {
		// TODO Auto-generated method stub

		

		Toast.makeText(getActivity(), mUsername + " - " + mEmail,
				Toast.LENGTH_SHORT).show();
		ParseUser user = new ParseUser();
		user.setUsername(mUsername);
		user.setPassword(mPassword);
		user.setEmail(mEmail);
		user.put("firstname", firstName);
		user.put("lastname", lastName);
		user.put("anonymous", anon);

//		try {
//			ParseObject poCity = new ParseObject("City");
//			ParseQuery<ParseObject> query = ParseQuery.getQuery("City");
//			query.whereEqualTo("name", city);
//			List<ParseObject> result;
//			result = query.find();
//			String cityObjectID = result.get(0).getObjectId().toString();
//			user.put("city", city);
//		} catch (ParseException e1) {
//			signUpMsg("Error in City Query");
//		}

		user.signUpInBackground(new SignUpCallback() {
			public void done(ParseException e) {
				if (e == null) {
					signUpMsg("Account Created Successfully");
					Intent in = new Intent(getActivity(), MainActivity.class);
					startActivity(in);
				} else {
					// Sign up didn't succeed. Look at the ParseException
					// to figure out what went wrong
					signUpMsg("Account already taken.");
				}
			}
		});
	}

	protected void signUpMsg(String msg) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
	}

	private void clearErrors() {
		txtFirstName.setError(null);
		txtLastName.setError(null);
		txtEmail.setError(null);
		txtPassword.setError(null);
		txtPasswordConfirm.setError(null);
		// spCity.setError(null);
		// tbAnon.setError(null);
		// btnCreateAccount.setError(null);
	}

	@SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String title, String message,
			Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		// Setting alert dialog icon
		alertDialog.setIcon(R.drawable.fail);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}

	public void onCreateParse() {

		try {
			// TODO
		} catch (Exception e) {
			// TODO: handle exception
		}
		Parse.initialize(getActivity(),
				"wecAmPMM0H03a3HPTcpoY7AW2nKfFGtxgCOidzUo",
				"iquq2rrkjV0XxfZbyyVXVahaQfeR0RzSRTRpkTWz");
	}

}
