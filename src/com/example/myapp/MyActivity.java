package com.example.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MyActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    private Button scanBtn;
    private TextView formatText, contentText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        scanBtn =  (Button)findViewById(R.id.button);
        formatText = (TextView) findViewById(R.id.scan_format);
        contentText=(TextView)findViewById(R.id.scan_content);

        scanBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.button){
            IntentIntegrator scanInt = new IntentIntegrator(this);
            scanInt.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanRes = IntentIntegrator.parseActivityResult(requestCode,resultCode,intent);
        if(scanRes != null){
            String scanCon = scanRes.getContents();
            String scanFor = scanRes.getFormatName();

            formatText.setText("FORMAT: "+scanFor);
            contentText.setText("CONTENT:        "+scanCon);

        }else{
            Toast.makeText(getApplicationContext(),"Nothing has scanned yet",Toast.LENGTH_LONG).show();
        }
    }
}
