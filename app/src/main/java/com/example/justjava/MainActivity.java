package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int numberOfCoffees = 1;
    int price;
    boolean hasWhippedCream, hasChoco;
    String name, priceMessage;


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox cb = findViewById(R.id.cb_whipped_cream);
        hasWhippedCream = cb.isChecked();
        cb = findViewById(R.id.cb_choco);
        hasChoco = cb.isChecked();

        EditText et = findViewById(R.id.et_name);
        name = et.getText().toString();
        if(numberOfCoffees == 0){
            displayMessage("Your order is free!");
        } else {
            price = calculatePrice();
            priceMessage = createOrderSummary();
            displayMessage(priceMessage);
        }

//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:"));
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Just java order for " + name);
//        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
//        if (intent.resolveActivity(getPackageManager()) != null){
//            startActivity(intent);
//        }
    }

    /**
     * This method adds the quantity by 1
     */
    public void increment(View view){
        if(numberOfCoffees == 30){
            display(numberOfCoffees);
        } else {
            display(++numberOfCoffees);
        }
    }

    /**
     * This method reduces the quantity by 1
     */
    public void decrement(View view){
        if(numberOfCoffees > 1) {
            display(--numberOfCoffees);
        } else {
            display(numberOfCoffees);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.tv_qty_number);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen
     */
    private void displayMessage(String text){
        TextView orderSummary = findViewById(R.id.tv_order_summary);
        orderSummary.setText(text);
    }

    /**
     * This method displays order summary
     */
    private String createOrderSummary(){
        String summaryMessage = "Name : " + name;
        summaryMessage = summaryMessage + "\nAdd whipped cream ? " + hasWhippedCream;
        summaryMessage = summaryMessage + "\nAdd chocolate ? " + hasChoco;
        summaryMessage = summaryMessage + "\nQuantity : " + numberOfCoffees;
        summaryMessage = summaryMessage + "\nTotal : $ " + price;
        summaryMessage = summaryMessage + "\nThank You!";
        return summaryMessage;
    }

    /**
     * Calculates the price of the order.
     * quantity is the number of cups of coffee ordered
     */
    private int calculatePrice() {
        price = numberOfCoffees * 5;
        if (hasWhippedCream && hasChoco){
            price = price + 3;
        } else if (hasWhippedCream){
            price++;
        } else if (hasChoco){
            price = price + 2;
        }
        return price;
    }

}
