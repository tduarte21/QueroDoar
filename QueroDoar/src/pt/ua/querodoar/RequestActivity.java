package pt.ua.querodoar;

import java.io.IOException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class RequestActivity extends ActionBarActivity {

	private String objectID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_request);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

		try {
			Parse.initialize(this, "wecAmPMM0H03a3HPTcpoY7AW2nKfFGtxgCOidzUo",
					"iquq2rrkjV0XxfZbyyVXVahaQfeR0RzSRTRpkTWz");
		} catch (NetworkOnMainThreadException e) {
			// upon resume or recent Parse.initialize exceptions this
			// exception is thrown
			// Temporarily supressing it right now
			// e.printStackTrace();
		}

		Bundle extras = getIntent().getExtras();
		objectID = extras.getString("product");

		// showToast(objectID);

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

				ParseQuery<ParseObject> query = ParseQuery.getQuery("Product");

				// Debug.waitForDebugger();

				ParseObject product = query.get(objectID);
				// showToast(user.getUsername());

				// updateList();

				TextView txtProductName = (TextView) view
						.findViewById(R.id.viewProductName);
				ImageView imgInstImage = (ImageView) view
						.findViewById(R.id.imgProductRequest);
				TextView txtProdReqPrice = (TextView) view
						.findViewById(R.id.viewProdReqPrice);
				TextView txtInstName = (TextView) view
						.findViewById(R.id.viewInstitutionName);
				TextView txtProdReqDescr = (TextView) view
						.findViewById(R.id.viewProdReqDescr);

				EditText txtPrice = (EditText) view.findViewById(R.id.etPrice);
				EditText txtQuantity = (EditText) view
						.findViewById(R.id.etQuantity);

				final EditText txtPriceFinal = txtPrice;
				final ParseObject productFinal = product;
				final EditText txtQuantityFinal = txtQuantity;

				txtQuantity.addTextChangedListener(new TextWatcher() {

					public void afterTextChanged(Editable s) {

					}

					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
					}

					public void onTextChanged(CharSequence s, int start,
							int before, int count) {

						double priceToPay = 0;

						if (txtQuantityFinal.getText() != null) {
							if (txtQuantityFinal.getText().toString() != ""
									&& txtQuantityFinal.getText().toString() != "Quantidade") {
								try {

									int quant = Integer
											.parseInt(txtQuantityFinal
													.getText().toString());
									priceToPay = quant
											* productFinal.getDouble("price");

								} catch (Exception e) {
									// TODO: handle exception
								}

							}

							txtPriceFinal.setText(priceToPay + "0€");
						}

					}
				});

				txtInstName.setText("");

				try {
					ParseUser inst = product.getParseUser("IDinst");
					txtInstName.setText(inst.getString("name"));
				} catch (Exception e) {
				}

				txtProductName.setText(product.getString("name"));
				txtProdReqDescr.setText(product.getString("description"));
				txtPrice.setText(product.getDouble("price") + "0€");
				txtProdReqPrice.setText(product.getDouble("price") + "0€");

				// showToast(product.getString("description"));

				// txtPrice.setText(product.getString("price"));
				txtPrice.setEnabled(false);

				try {

					ParseFile imageFile = (ParseFile) product
							.getParseFile("image");
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

	public String getObjectID() {
		return objectID;
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
			Intent in = new Intent(RequestActivity.this, Account.class);
			startActivity(in);

			// } else if (title.equals(getString(R.string.action_history))) {
			//
			// Intent in = new Intent(FeedActivity.this, DonationList.class);
			// startActivity(in);

		} else if (title.equals(getString(R.string.action_about))) {

			Intent in = new Intent(RequestActivity.this, About.class);
			startActivity(in);

		} else if (title.equals(getString(R.string.action_logout))) {
			ParseUser.logOut();
			System.exit(0);
		} else if (title.equals(getString(R.string.action_exit))) {

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
			View rootView = inflater.inflate(R.layout.fragment_request,
					container, false);

			RequestActivity reqAct = (RequestActivity) getActivity();

			String obj = reqAct.getObjectID();

			final String objFinal = obj;

			final View view = rootView;

			Button btnRequests = (Button) rootView
					.findViewById(R.id.btnDonateProduct);

			btnRequests.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					EditText txtQuantity = (EditText) view
							.findViewById(R.id.etQuantity);

					String quantity = "";
					if (!(txtQuantity.getText() == null)) {
						quantity = txtQuantity.getText().toString();
					}
					if (TextUtils.isEmpty(quantity)) {
						txtQuantity
								.setError(getString(R.string.error_field_required));
						// focusView = mPasswordEditText;
						// cancel = true;
					} else {

						AlertDialog dialog = new AlertDialog.Builder(
								getActivity()).create();
						dialog.setTitle("Doar");
						dialog.setMessage("Tem a certeza que pertende doar?");
						dialog.setCancelable(false);
						dialog.setButton(DialogInterface.BUTTON_POSITIVE,
								"Sim", new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int buttonId) {
										Intent intent = new Intent(
												getActivity(),
												ThanksActivity.class);

										intent.putExtra("inst", objFinal);

										startActivity(intent);

										// try {
										//
										// } catch (Throwable e) {
										// // TODO Auto-generated catch block
										// e.printStackTrace();
										// }
									}
								});
						dialog.setButton(DialogInterface.BUTTON_NEGATIVE,
								"Não", new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int buttonId) {
										// ans_false.run();
									}
								});
						dialog.setIcon(android.R.drawable.ic_dialog_alert);
						dialog.show();

					}

				}
			});

			UpdateUI update = reqAct.new UpdateUI(obj, rootView);
			update.run();

			return rootView;
		}
	}

	public void showToast(final String toast) {
		this.runOnUiThread(new Runnable() {
			public void run() {
				Toast.makeText(RequestActivity.this, toast, Toast.LENGTH_SHORT)
						.show();
			}
		});
	}

}
