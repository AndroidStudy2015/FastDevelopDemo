package com.fast.develop.fastdevelopdemo;

import com.fast.develop.fastcore.activities.ProxyActivity;
import com.fast.develop.fastcore.delegates.FastDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public FastDelegate setRootDelegate() {
        return new ExampleDelegate();
    }


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Fast.init(this)
//                .withApiHost("http://127.0.0.1")
//                .withIcons(new FontAwesomeModule())
//                .withIcons(new FontDemoMoudle())
//                .configure();
//
//
//        String apihost = Configurator.getInstace().getConfiguation(ConfigType.API_HOST);
//        Toast.makeText(Fast.getApplicationContext(), apihost + "", Toast.LENGTH_SHORT).show();
//
//    }
}
