package com.fast.develop.fastdevelopdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.fast.develop.fastcore.app.ConfigType;
import com.fast.develop.fastcore.app.Configurator;
import com.fast.develop.fastcore.app.Fast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fast.init(this)
                .withApiHost("http://127.0.0.1")
                .configure();



        String apihost = Configurator.getInstace().getConfiguation(ConfigType.API_HOST);
        Toast.makeText(this, apihost + "", Toast.LENGTH_SHORT).show();

    }
}
