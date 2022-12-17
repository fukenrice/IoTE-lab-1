package com.example.iotebook6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.GpioCallback;
import com.google.android.things.pio.PeripheralManager;

import java.io.IOException;

public class MainActivity extends Activity {

    // general purpose i/o pin
    private Gpio mREDLedGpio, mBLUELedGpio, mGREENLedGpio, mREDButtonGpio,
            mBLUEButtonGpio, mGREENButtonGpio;;
    boolean redPressed = false;
    boolean greenPressed = false;
    boolean bluePressed = false;
    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 = findViewById(R.id.text1);

        PeripheralManager manager = PeripheralManager.getInstance();
        try {
            mREDLedGpio = manager.openGpio("BCM19"); //red color
// Step 2. Configure as an output.
            mREDLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW); //is lit
// Step 1. Create GPIO connection.
            mREDButtonGpio = manager.openGpio("BCM4"); //red color button
// Step 2. Configure as an input.
            mREDButtonGpio.setDirection(Gpio.DIRECTION_IN);
// Step 3. Enable edge trigger events.
            mREDButtonGpio.setEdgeTriggerType(Gpio.EDGE_FALLING);
// Step 4. Register an event callback.
            mREDButtonGpio.registerGpioCallback(mREDCallback);


            mGREENLedGpio = manager.openGpio("BCM16"); //red color
// Step 2. Configure as an output.
            mGREENLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW); //is lit
// Step 1. Create GPIO connection.
            mGREENButtonGpio = manager.openGpio("BCM17"); //red color button
// Step 2. Configure as an input.
            mGREENButtonGpio.setDirection(Gpio.DIRECTION_IN);
// Step 3. Enable edge trigger events.
            mGREENButtonGpio.setEdgeTriggerType(Gpio.EDGE_FALLING);
// Step 4. Register an event callback.
            mGREENButtonGpio.registerGpioCallback(mGreenCallback);

            mBLUELedGpio = manager.openGpio("BCM26"); //red color
// Step 2. Configure as an output.
            mBLUELedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW); //is lit
// Step 1. Create GPIO connection.
            mBLUEButtonGpio = manager.openGpio("BCM27"); //red color button
// Step 2. Configure as an input.
            mBLUEButtonGpio.setDirection(Gpio.DIRECTION_IN);
// Step 3. Enable edge trigger events.
            mBLUEButtonGpio.setEdgeTriggerType(Gpio.EDGE_FALLING);
// Step 4. Register an event callback.
            mBLUEButtonGpio.registerGpioCallback(mBlueCallback);

        } catch (IOException e) {
            Log.e("onCreate", "Error on PeripheralIO API", e);
        }
    }

    // Step 4. Register an event callback.
    private GpioCallback mBlueCallback = new GpioCallback() {
        @Override
        public boolean onGpioEdge(Gpio gpio) {
            try {
                bluePressed = !bluePressed;
                mBLUELedGpio.setValue(bluePressed);
                text1.setTextColor(Color.BLUE);
                text1.setText("BLue");
                Log.i("GpioCallback", "blue LED button was pressed");
            } catch (IOException e) {
                Log.e("onKeyDown", "Error on PeripheralIO API", e);
            }
// Step 5. Return true to keep callback active.
            return true;
        }
    };

    // Step 4. Register an event callback.
    private GpioCallback mGreenCallback = new GpioCallback() {
        @Override
        public boolean onGpioEdge(Gpio gpio) {
            try {
                greenPressed = !greenPressed;
                mGREENLedGpio.setValue(greenPressed);
                text1.setTextColor(Color.GREEN);
                text1.setText("Green");
                Log.i("GpioCallback", "green LED button was pressed");
            } catch (IOException e) {
                Log.e("onKeyDown", "Error on PeripheralIO API", e);
            }
// Step 5. Return true to keep callback active.
            return true;
        }
    };


    // Step 4. Register an event callback.
    private GpioCallback mREDCallback = new GpioCallback() {
        @Override
        public boolean onGpioEdge(Gpio gpio) {
            try {
                redPressed = !redPressed;
                mREDLedGpio.setValue(redPressed);
                text1.setTextColor(Color.RED);
                text1.setText("RED");
                Log.i("GpioCallback", "Red LED button was pressed");
            } catch (IOException e) {
                Log.e("onKeyDown", "Error on PeripheralIO API", e);
            }
// Step 5. Return true to keep callback active.
            return true;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
// Step 6. Close the resource
        if (mREDButtonGpio != null) {
            mREDButtonGpio.unregisterGpioCallback(mREDCallback);
            try {
                mREDButtonGpio.close();
            } catch (IOException e) {
                Log.e("onDestroy", "Error on PeripheralIO API", e);
            }
        }
        if (mREDLedGpio != null) {
            try {
                mREDLedGpio.close();
            } catch (IOException e) {
                Log.e("onDestroy", "Error on PeripheralIO API", e);
            }
        }
        if (mGREENButtonGpio != null) {
            mGREENButtonGpio.unregisterGpioCallback(mGreenCallback);
            try {
                mGREENButtonGpio.close();
            } catch (IOException e) {
                Log.e("onDestroy", "Error on PeripheralIO API", e);
            }
        }
        if (mGREENLedGpio != null) {
            try {
                mGREENLedGpio.close();
            } catch (IOException e) {
                Log.e("onDestroy", "Error on PeripheralIO API", e);
            }
        }
        if (mBLUEButtonGpio != null) {
            mBLUEButtonGpio.unregisterGpioCallback(mBlueCallback);
            try {
                mBLUEButtonGpio.close();
            } catch (IOException e) {
                Log.e("onDestroy", "Error on PeripheralIO API", e);
            }
        }
        if (mBLUELedGpio != null) {
            try {
                mBLUELedGpio.close();
            } catch (IOException e) {
                Log.e("onDestroy", "Error on PeripheralIO API", e);
            }
        }
    }

}