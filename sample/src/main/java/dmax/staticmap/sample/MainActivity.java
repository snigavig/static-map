package dmax.staticmap.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * @user: Maxim Dybarsky | maxim.dybarskyy@gmail.com
 * @date: 7/29/14
 * @time: 4:22 PM
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(new MapsAdapter(this));
    }
}
