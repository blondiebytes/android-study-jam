package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

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
        int price = calculatePrice();
      //  String priceMessage = "Total: $" + price + "\n" + "Thank you!";
        boolean isWhippedCreamChecked = ((CheckBox) findViewById(R.id.whipped_cream_check_box)).isChecked();
        String priceMessage = createOrderSummary(price, isWhippedCreamChecked);
        displayMessage(priceMessage);
    }

    /**
     *
     * @param price of the order
     * @return text summary
     */
    public String createOrderSummary(int price, boolean isWhippedCreamChecked) {
        return "Name: Kathryn Hodge\n" +
                "Add Whipped Cream? " + isWhippedCreamChecked + "\n" +
                "Quantity: " + quantity + "\n"+
                "Total: $"+price+"\n" +
                "Thank you!";
    }

    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
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


    public int calculatePrice() {
        return quantity * 5;
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}