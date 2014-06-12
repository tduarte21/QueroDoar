package pt.ua.querodoar;

import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.parse.ParseUser;

public class Account extends ActionBarActivity {

	ParseUser user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account, menu);
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

		ParseUser user = ParseUser.getCurrentUser();

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_account,
					container, false);

			final View view = rootView;

			Button btnUpdate = (Button) rootView
					.findViewById(R.id.btnEditAccount);
			btnUpdate.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					Debug.waitForDebugger();
					EditText txtEditSingleFirstName = (EditText) view
							.findViewById(R.id.txtEditSingleFirstName);
					EditText txtEditSingleLastName = (EditText) view
							.findViewById(R.id.txtEditSingleLastName);
					EditText txtEditSingleEmail = (EditText) view
							.findViewById(R.id.txtEditSingleEmail);
					EditText txtEditSinglePassword = (EditText) view
							.findViewById(R.id.txtEditSinglePassword);
					EditText txtEditSinglePasswordConfirm = (EditText) view
							.findViewById(R.id.txtEditSinglePasswordConfirm);
					Spinner spEditSingleCity = (Spinner) view
							.findViewById(R.id.spEditSingleCity);
					ToggleButton tbEditSingleAnon = (ToggleButton) view
							.findViewById(R.id.tbEditSingleAnon);

					ParseUser user = ParseUser.getCurrentUser();

					if (txtEditSingleFirstName.getText() != null) {
						if (!txtEditSingleFirstName.getText().toString()
								.equals(""))
							user.put("firstname", txtEditSingleFirstName
									.getText().toString());
					}

					if (txtEditSingleLastName.getText() != null) {
						if (!txtEditSingleLastName.getText().toString()
								.equals(""))
							user.put("lastname", txtEditSingleLastName
									.getText().toString());
					}

					if (txtEditSinglePassword.getText() != null
							&& txtEditSinglePasswordConfirm.getText() != null) {
						if (txtEditSinglePassword
								.getText()
								.toString()
								.equals(txtEditSinglePasswordConfirm.getText()
										.toString())) {

							if (!txtEditSinglePassword.getText().toString()
									.equals(""))
								user.put("password", txtEditSinglePassword
										.getText().toString());

						}

					}

					user.put("anonymous", tbEditSingleAnon.isChecked());

					Toast.makeText(getActivity(), "A atualizar os dados...",
							Toast.LENGTH_SHORT).show();

					user.saveInBackground();

					try {
						this.finalize();
					} catch (Throwable e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});

			EditText txtEditSingleFirstName = (EditText) rootView
					.findViewById(R.id.txtEditSingleFirstName);
			EditText txtEditSingleLastName = (EditText) rootView
					.findViewById(R.id.txtEditSingleLastName);
			EditText txtEditSingleEmail = (EditText) rootView
					.findViewById(R.id.txtEditSingleEmail);
			EditText txtEditSinglePassword = (EditText) rootView
					.findViewById(R.id.txtEditSinglePassword);
			EditText txtEditSinglePasswordConfirm = (EditText) rootView
					.findViewById(R.id.txtEditSinglePasswordConfirm);
			Spinner spEditSingleCity = (Spinner) rootView
					.findViewById(R.id.spEditSingleCity);
			ToggleButton tbEditSingleAnon = (ToggleButton) rootView
					.findViewById(R.id.tbEditSingleAnon);

			txtEditSingleFirstName.setText(user.getString("firstname"));
			txtEditSingleLastName.setText(user.getString("lastname"));
			txtEditSingleEmail.setText(user.getString("email"));
			txtEditSinglePassword.setText(user.getString("password"));
			txtEditSinglePasswordConfirm.setText(user.getString("password"));
			// spEditSingleCity.getText(user.getString("firstname"));
			tbEditSingleAnon.setChecked(user.getBoolean("anonymous"));

			return rootView;
		}

		// public class UpdateUI implements Runnable {
		//
		// private String result;
		//
		// public UpdateUI(String result) {
		// this.result = result;
		// }
		//
		// @Override
		// public void run() {
		//
		// try {
		//
		// } catch (Exception e) {
		// //showToast("Error: " + e);
		// }
		//
		// }
		// }

	}

	public void showToast(final String toast) {
		this.runOnUiThread(new Runnable() {
			public void run() {
				Toast.makeText(Account.this, toast, Toast.LENGTH_SHORT).show();
			}
		});
	}

}
