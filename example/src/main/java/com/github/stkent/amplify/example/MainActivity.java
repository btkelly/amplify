package com.github.stkent.amplify.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.stkent.amplify.AmplifyStateTracker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button triggerButton = (Button) findViewById(R.id.trigger_button);
        triggerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        AmplifyStateTracker.getInstance()
                .notify(AmplifyStateTracker.ActionType.APP_CRASHED);
    }

}
