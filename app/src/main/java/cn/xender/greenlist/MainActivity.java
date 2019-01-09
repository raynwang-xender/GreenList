package cn.xender.greenlist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import cn.xender.StatisticsParams;
import cn.xender.XenderShare;

public class MainActivity extends Activity {

    XenderShare xenderShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.share_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**    Set key & secret     */
                StatisticsParams.initKeyAndSecret("test_key","test_secret");
                /**    Set Params    */
                StatisticsParams.addCustomParams("test_params1","test_params2");

                /**    Init   */
                xenderShare = new XenderShare(MainActivity.this);

                /**    Set file paths for transfer   */
                xenderShare.appendNewFile("/storage/emulated/0/aaa.png");
                xenderShare.appendNewFile("/storage/emulated/0/bbb.png");

                /**    Start Xender for transfer   */
                xenderShare.shareByXender();
            }
        });

        findViewById(R.id.query_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**    Get native json of Params   */
                JSONObject params = StatisticsParams.getParams(MainActivity.this);
                try {
                    String params1 = (String) params.get("params1");
                    String params2 = (String) params.get("params2");

                    Toast.makeText(MainActivity.this,"params1:"+params1+"  params2:"+params2,Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this,"No Params",Toast.LENGTH_LONG).show();
                }
            }
        });

        findViewById(R.id.delete_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**    Delete native Params   */
                int deleteRows = StatisticsParams.deleteParams(MainActivity.this);

                Toast.makeText(MainActivity.this,"deleteRows: "+deleteRows,Toast.LENGTH_LONG).show();
            }
        });
    }

}


