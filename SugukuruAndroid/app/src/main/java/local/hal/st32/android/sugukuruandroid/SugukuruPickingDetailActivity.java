package local.hal.st32.android.sugukuruandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tester on 2016/11/14.
 */

public class SugukuruPickingDetailActivity extends Activity{

    private static final String _URL = net.ipAddress;
    private static final String method = "pickingDetail";
    private List<Map<String, String>> pickList;
    private String productId;
    private int unit;
    private String mode = "0";
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugukuru_detail);

        Intent intent = getIntent();
        productId = intent.getStringExtra("productId");

        TextView realTime = (TextView) findViewById(R.id.datetime);
        Calendar ca = new GregorianCalendar();
        String strRealTime = ca.get(Calendar.YEAR) + "/" + (ca.get(Calendar.MONTH)+1) + "/" + ca.get(Calendar.DAY_OF_MONTH) + "　" + ca.get(Calendar.HOUR_OF_DAY) + "時" +ca.get(Calendar.MINUTE)+"分現在";
        realTime.setText(strRealTime);
        setSpinner();
    }

    @Override
    protected void onResume(){
        super.onResume();
        RestAccess access = new RestAccess();
        access.execute(_URL);

    }

    private class RestAccess extends AsyncTask<String, Void, String> {
        private static final String DEBUG_TAG = "RestAccess";
        String result;

        public RestAccess() {}

        @Override
        public String doInBackground(String... params) {
            String urlStr = params[0];
            HttpURLConnection con = null;
            InputStream is = null;
            try {
                URL url = new URL(urlStr+"?method=" + method + "&productId=" + productId);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                is = con.getInputStream();
                result = is2String(is);
            } catch (MalformedURLException ex) {
                Log.e(DEBUG_TAG, "URL変換失敗", ex);
            } catch (IOException ex) {
                Log.e(DEBUG_TAG, "通信失敗", ex);
            } finally {
                if (con != null) {
                    con.disconnect();
                }
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException ex) {
                        Log.e(DEBUG_TAG, "InputStream解放失敗", ex);
                    }
                }
            }
            return result;

        }

        @Override
        public void onPostExecute(String result) {
            Replace re = new Replace();
            re.setRequestId("pickId");
            re.setRequestId("productId");
            re.setRequestId("productName");
            re.setRequestId("rackNumber");
            re.setRequestId("stock");
            re.setRequestId("needs");
            re.setRequestId("pickNum");
            re.setRequestId("inspectedAmount");
            re.setRequestId("pickState");
            re.setTableName("PickingList");
            re.setRequestId("unit");
            pickList = re.json(result);
            preview();
        }

    }

    private String is2String(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringBuffer sb = new StringBuffer();
        char[] b = new char[1024];
        int line;
        while (0 <= (line = reader.read(b))) {
            sb.append(b, 0, line);
        }
        return sb.toString();
    }

    public void preview(){
        TextView tvProductId = (TextView)findViewById(R.id.deProductNumber);
        TextView tvProductName = (TextView)findViewById(R.id.deProductName);
        TextView tvRackNumber = (TextView)findViewById(R.id.deRackNumbers);
        TextView tvStock = (TextView)findViewById(R.id.deStock);
        TextView tvNeeds = (TextView)findViewById(R.id.deNeeds);
        TextView tvPickNum = (TextView)findViewById(R.id.dePicking);
        TextView tvInspectedAmount = (TextView)findViewById(R.id.deInspected);
        TextView tvPickState = (TextView)findViewById(R.id.deState);

        Map<String, String> map = new HashMap<String, String>();
        map = pickList.get(0);
        tvProductId.setText(map.get("productId"));
        tvProductName.setText(map.get("productName"));
        tvRackNumber.setText(map.get("rackNumber"));
        tvStock.setText(map.get("stock"));
        tvNeeds.setText(map.get("needs"));
        tvPickNum.setText(map.get("pickNum"));
        tvInspectedAmount.setText(map.get("inspectedAmount"));
        tvPickState.setText(map.get("pickState"));
        unit = Integer.parseInt(map.get("unit"));
    }



    public void intentOrder(View view){
        Intent intent = new Intent(SugukuruPickingDetailActivity.this, SugukuruOrderActivity.class);
        //移動処理
        startActivity(intent);
        finish();
    }

    public void intentPicking(View view){
        Intent intent = new Intent(SugukuruPickingDetailActivity.this, SugukuruActivity.class);
        //移動処理
        startActivity(intent);
        finish();
    }

    public void backIntent(View view){
        finish();
    }

    /**
     * スピナーに値を入れるメソッド
     */
    private void setSpinner(){
        String[] labels = getResources().getStringArray(R.array.arr_mode);
        spinner = (Spinner)findViewById(R.id.spMode);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labels);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
        spinner.setFocusable(false);
    }

    public class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener{
        public void onItemSelected(AdapterView parent, View view, int position, long id) {
            if (spinner.isFocusable() == false) {
                spinner.setFocusable(true);
                return;
            }
            // Spinner を取得
            Spinner spinner1 = (Spinner) parent;
            // 選択されたアイテムのテキストを取得
            mode = spinner1.getSelectedItem().toString();
            switch (mode){
                case "ピッキング":
                    mode = "0";
                    break;
                case "検品":
                    mode = "1";
                    break;
            }
        }

        // 何も選択されなかった時の動作
        public void onNothingSelected(AdapterView parent) {}
    }


    public void pickingUp(View view){
        updatePreview(0);
    }

    public void pickingDown(View  view){
        updatePreview(1);
    }

    public void updatePreview(int num){
//        Map<String, String> map = new HashMap<String, String>();
//        map = pickList.get(0);
//        int stock = Integer.parseInt(map.get("stock"));
//        int needs = Integer.parseInt(map.get("needs"));
//        int pickNum = Integer.parseInt(map.get("pickNum"));
//        int inspectedAmount = Integer.parseInt(map.get("inspectedAmount"));
//        switch (mode){
//            case "0":
//                if(num == 0){
//                    stock -= unit;
//                    needs -= unit;
//                    pickNum += unit;
//                }else{
//                    stock += unit;
//                    needs += unit;
//                    pickNum -= unit;
//                }
//                break;
//            case "1":
//                if(num == 0){
//                    inspectedAmount += unit;
//                }else{
//                    inspectedAmount -= unit;
//                }
//        }
//        map.put("stock", String.valueOf(stock));
//        map.put("needs", String.valueOf(needs));
//        map.put("pickNum", String.valueOf(pickNum));
//        map.put("inspectedAmount", String.valueOf(inspectedAmount));
//        pickList.set(0, map);
        update up = new update();
        up.execute(_URL, String.valueOf(num));
//        preview();
    }

    private class update extends AsyncTask<String, Void, String> {
        private static final String DEBUG_TAG = "update";
        String result;
        @Override
        public String doInBackground(String... params) {
            String urlStr = params[0];
            String num = params[1];
            HttpURLConnection con = null;
            InputStream is = null;
            try {
                URL url = new URL(urlStr+"?method=" + "pickUpdate" + "&productId=" + productId + "&unit="+unit +"&value=" + num +"&mode="+ mode);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                is = con.getInputStream();
                result = is2String(is);
            } catch (MalformedURLException ex) {
                Log.e(DEBUG_TAG, "URL変換失敗", ex);
            } catch (IOException ex) {
                Log.e(DEBUG_TAG, "通信失敗", ex);
            } finally {
                if (con != null) {
                    con.disconnect();
                }
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException ex) {
                        Log.e(DEBUG_TAG, "InputStream解放失敗", ex);
                    }
                }
            }
            return result;

        }

        @Override
        public void onPostExecute(String result) {
            onResume();
        }
    }
}
