package com.share.image.tollsmart.autherized.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.share.image.tollsmart.R;

import net.authorize.acceptsdk.AcceptSDKApiClient;
import net.authorize.acceptsdk.datamodel.merchant.ClientKeyBasedMerchantAuthentication;
import net.authorize.acceptsdk.datamodel.transaction.CardData;
import net.authorize.acceptsdk.datamodel.transaction.EncryptTransactionObject;
import net.authorize.acceptsdk.datamodel.transaction.TransactionObject;
import net.authorize.acceptsdk.datamodel.transaction.TransactionType;
import net.authorize.acceptsdk.datamodel.transaction.callbacks.EncryptTransactionCallback;
import net.authorize.acceptsdk.datamodel.transaction.response.EncryptTransactionResponse;
import net.authorize.acceptsdk.datamodel.transaction.response.ErrorTransactionResponse;

public class AutherizePayment extends AppCompatActivity implements View.OnClickListener, EncryptTransactionCallback {
    public static final String TAG = "WebCheckoutFragment";
    private final String CARD_NUMBER = "4111111111111111";
    private final String EXPIRATION_MONTH = "11";
    private final String EXPIRATION_YEAR = "2017";
    private final String CVV = "256";
    private final String POSTAL_CODE = "98001";
    private final String CLIENT_KEY = "3Zh4vbzK9VJ576Aap9pT64Ly55UMAtVEysx25PBrfZHvW7vr7Y3FP44ReMc3zEAw";
    // replace with your CLIENT KEY
    private final String API_LOGIN_ID = "4W29Wjy8"; // replace with your API LOGIN_ID

    private final int MIN_CARD_NUMBER_LENGTH = 13;
    private final int MIN_YEAR_LENGTH = 2;
    private final int MIN_CVV_LENGTH = 3;
    private final String YEAR_PREFIX = "20";

    private Button checkoutButton;
    private EditText cardNumberView;
    private EditText monthView;
    private EditText yearView;
    private EditText cvvView;

    private ProgressDialog progressDialog;


    private String cardNumber;
    private String month;
    private String year;
    private String cvv;

    private AcceptSDKApiClient apiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autherize_payment);

        try {
            apiClient = new AcceptSDKApiClient.Builder(this,
                    AcceptSDKApiClient.Environment.SANDBOX).connectionTimeout(
                    4000) // optional connection time out in milliseconds
                    .build();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        initialize();
    }
    private void initialize() {
        cardNumberView = (EditText) findViewById(R.id.card_number_view);
        setUpCreditCardEditText();
        monthView = (EditText) findViewById(R.id.date_month_view);
        yearView = (EditText) findViewById(R.id.date_year_view);
        cvvView = (EditText) findViewById(R.id.security_code_view);

        checkoutButton = (Button) findViewById(R.id.button_checkout_order);
        checkoutButton.setOnClickListener(this);

    }
    private void setUpCreditCardEditText() {
        cardNumberView.addTextChangedListener(new TextWatcher() {
            private boolean spaceDeleted;

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // check if a space was deleted
                CharSequence charDeleted = s.subSequence(start, start + count);
                spaceDeleted = " ".equals(charDeleted.toString());
            }

            public void afterTextChanged(Editable editable) {
                // disable text watcher
                cardNumberView.removeTextChangedListener(this);

                // record cursor position as setting the text in the textview
                // places the cursor at the end
                int cursorPosition = cardNumberView.getSelectionStart();
                String withSpaces = formatText(editable);
                cardNumberView.setText(withSpaces);
                // set the cursor at the last position + the spaces added since the
                // space are always added before the cursor
                cardNumberView.setSelection(cursorPosition + (withSpaces.length() - editable.length()));

                // if a space was deleted also deleted just move the cursor
                // before the space
                if (spaceDeleted) {
                    cardNumberView.setSelection(cardNumberView.getSelectionStart() - 1);
                    spaceDeleted = false;
                }

                // enable text watcher
                cardNumberView.addTextChangedListener(this);
            }

            private String formatText(CharSequence text) {
                StringBuilder formatted = new StringBuilder();
                int count = 0;
                for (int i = 0; i < text.length(); ++i) {
                    if (Character.isDigit(text.charAt(i))) {
                        if (count % 4 == 0 && count > 0) formatted.append(" ");
                        formatted.append(text.charAt(i));
                        ++count;
                    }
                }
                return formatted.toString();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!areFormDetailsValid()) return;

        progressDialog = ProgressDialog.show(AutherizePayment.this, this.getString(R.string.progress_title),
                this.getString(R.string.progress_message), true);

        try {
            EncryptTransactionObject transactionObject = prepareTransactionObject();

      /*
        Make a call to get Token API
        parameters:
          1) EncryptTransactionObject - The transactionObject for the current transaction
          2) callback - callback of transaction
       */
            apiClient.getTokenWithRequest(transactionObject, this);
        } catch (NullPointerException e) {
            // Handle exception transactionObject or callback is null.
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            if (progressDialog.isShowing()) progressDialog.dismiss();
            e.printStackTrace();
        }
    }
    private boolean areFormDetailsValid() {
        cardNumber = cardNumberView.getText().toString().replace(" ", "");
        month = monthView.getText().toString();
        cvv = cvvView.getText().toString();
        year = yearView.getText().toString();

        if (isEmptyField()) {
            checkoutButton.startAnimation(
                    AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_error));
            Toast.makeText(getApplicationContext(), "Empty fields", Toast.LENGTH_LONG).show();
            return false;
        }

        year = YEAR_PREFIX + yearView.getText().toString();

        return validateFields();
    }
    private boolean isEmptyField() {
        return (cardNumber != null && cardNumber.isEmpty()) || (month != null && month.isEmpty()) || (
                year != null
                        && year.isEmpty()) || (cvv != null && cvv.isEmpty());
    }
    private EncryptTransactionObject prepareTransactionObject() {
        ClientKeyBasedMerchantAuthentication merchantAuthentication =
                ClientKeyBasedMerchantAuthentication.
                        createMerchantAuthentication(API_LOGIN_ID, CLIENT_KEY);

        // create a transaction object by calling the predefined api for creation
        return TransactionObject.
                createTransactionObject(
                        TransactionType.SDK_TRANSACTION_ENCRYPTION) // type of transaction object
                .cardData(prepareCardDataFromFields()) // card data to get Token
                .merchantAuthentication(merchantAuthentication).build();
    }
    private CardData prepareCardDataFromFields() {
        return new CardData.Builder(cardNumber, month, year).cvvCode(cvv) //CVV Code is optional
                .build();
    }

    private boolean validateFields() {
        if (cardNumber.length() < MIN_CARD_NUMBER_LENGTH) {
            cardNumberView.requestFocus();
            cardNumberView.setError(getString(R.string.invalid_card_number));
            checkoutButton.startAnimation(
                    AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_error));
            return false;
        }
        int monthNum = Integer.parseInt(month);
        if (monthNum < 1 || monthNum > 12) {
            monthView.requestFocus();
            monthView.setError(getString(R.string.invalid_month));
            checkoutButton.startAnimation(
                    AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_error));
            return false;
        }
        if (month.length() < MIN_YEAR_LENGTH) {
            monthView.requestFocus();
            monthView.setError(getString(R.string.two_digit_month));
            checkoutButton.startAnimation(
                    AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_error));
            return false;
        }
        if (year.length() < MIN_YEAR_LENGTH) {
            yearView.requestFocus();
            yearView.setError(getString(R.string.invalid_year));
            checkoutButton.startAnimation(
                    AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_error));
            return false;
        }
        if (cvv.length() < MIN_CVV_LENGTH) {
            cvvView.requestFocus();
            cvvView.setError(getString(R.string.invalid_cvv));
            checkoutButton.startAnimation(
                    AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_error));
            return false;
        }
        return true;
    }
    @Override
    public void onErrorReceived(ErrorTransactionResponse error) {
        hideSoftKeyboard();
        if (progressDialog.isShowing()) progressDialog.dismiss();
        String errorString = getString(R.string.code) + error.getResponseMessages() + "\n" +
                getString(R.string.message) + error.getResponseMessages();
        System.out.println("enter the error reponse"+errorString);
    }

    @Override
    public void onEncryptionFinished(EncryptTransactionResponse response) {
        hideSoftKeyboard();
        if (progressDialog.isShowing()) progressDialog.dismiss();
        System.out.println("enter the Token"+response.getDataValue());
        Toast.makeText(getApplicationContext(),"Token:::"+response.getDataValue(),Toast.LENGTH_LONG).show();
    }
    public void hideSoftKeyboard() {
        InputMethodManager keyboard =
                (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getApplicationContext() != null && this.getCurrentFocus() != null) {
            keyboard.hideSoftInputFromInputMethod(this.getCurrentFocus().getWindowToken(), 0);
        }
    }
}
