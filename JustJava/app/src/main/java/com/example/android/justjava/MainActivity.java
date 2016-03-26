package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
      //  String priceMessage = "Total: $" + price + "\n" + "Thank you!";
        boolean isWhippedCreamChecked = ((CheckBox) findViewById(R.id.whipped_cream_check_box)).isChecked();
        boolean isChocolateChecked = ((CheckBox) findViewById(R.id.chocolate_check_box)).isChecked();
        int price = calculatePrice(isWhippedCreamChecked, isChocolateChecked);
        String name = ((EditText) findViewById(R.id.name_field)).getText().toString();
        String priceMessage = createOrderSummary(price, isWhippedCreamChecked, isChocolateChecked, name);
       // displayMessage(priceMessage);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java for " + name);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /**
     *
     * @param price of the order
     * @param isWhippedCreamChecked is whether or not the user wants whipped cream topping
     * @param isChocolateChecked is whether or not the user wants chocolate
     * @param name is the name of the person
     * @return text summary
     */
    public String createOrderSummary(int price, boolean isWhippedCreamChecked, boolean isChocolateChecked, String name) {
        return "Name: "+ name+"\n" +
                "Add Whipped Cream? " + isWhippedCreamChecked + "\n" +
                "Add Chocolate? " + isChocolateChecked + "\n" +
                "Quantity: " + quantity + "\n"+
                "Total: $"+price+"\n" +
                "Thank you!";
    }

    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
           return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
           return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    public int calculatePrice(boolean isWhippedCreamChecked, boolean isChocolateChecked) {
        int pricePerCup = 5;
        if (isWhippedCreamChecked) {
            pricePerCup+=1;
        }
        if (isChocolateChecked) {
            pricePerCup+=2;
        }
        return quantity * pricePerCup;
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
      //  TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
      //  orderSummaryTextView.setText(message);
    }
}