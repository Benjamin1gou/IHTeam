package local.hal.st32.android.sugukuruandroid;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

/**
 * Created by Tester on 2016/11/14.
 */

public class SugukuruOrderDetailActivity extends ListActivity {

    private ListView _list;
    private List<Map<String, String>> pickList;
    private static final String _URL = net.ipAddress;
    private String orderId;
    private static final String method = "orderDetail";
    private Spinner spinner;
    private String sort = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugukuru_order_detail);

        Intent intent = getIntent();
        orderId = intent.getStringExtra("id");

        _list = getListView();

        TextView realTime = (TextView) findViewById(R.id.datetime);
        Calendar ca = new GregorianCalendar();
        String strRealTime = ca.get(Calendar.YEAR) + "/" + (ca.get(Calendar.MONTH)+1) + "/" + ca.get(Calendar.DAY_OF_MONTH) + "　" + ca.get(Calendar.HOUR_OF_DAY) + "時" +ca.get(Calendar.MINUTE)+"分現在";
        realTime.setText(strRealTime);
        setSpinner();
    }

    @Override
    protected void onResume(){
        super.onResume();
        RestAccess access = new RestAccess(_list);
        access.execute(_URL);
    }


    private class RestAccess extends AsyncTask<String, Void, String> {
        private static final String DEBUG_TAG = "RestAccess";
        String result;

        ListView _list;
        public RestAccess(ListView list) {
            _list = list;
        }

        @Override
        public String doInBackground(String... params) {
            String urlStr = params[0];
            HttpURLConnection con = null;
            InputStream is = null;
            try {
                Log.e("",orderId);
                URL url = new URL(urlStr+"?method=" + method + "&value=" + orderId +"&sort="+sort);
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

            re.setRequestId("num");
            re.setRequestId("productNumber");
            re.setRequestId("productName");
            re.setRequestId("amount");
            re.setRequestId("step");
            pickList = re.json(result);
            Log.e("", ""+pickList);
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
        String[] from = {"num", "productNumber", "productName", "amount", "step"};
        int[] to = {R.id.clNum, R.id.clProductNumber, R.id.clProductName, R.id.clAmount, R.id.clStep};
        SimpleAdapter adapter = new SimpleAdapter(SugukuruOrderDetailActivity.this, pickList, R.layout.order_detail_row, from, to);
        setListAdapter(adapter);
        numberOfCase();
    }

    public void numberOfCase(){
        TextView num = (TextView)findViewById(R.id.allNumber);
        num.setText("全"+(pickList.size())+"件");
    }

    public void intentOrder(View view){
        Intent intent = new Intent(SugukuruOrderDetailActivity.this, SugukuruOrderActivity.class);
        //移動処理
        startActivity(intent);
        finish();
    }

    public void intentPicking(View view){
        Intent intent = new Intent(SugukuruOrderDetailActivity.this, SugukuruActivity.class);
        //移動処理
        startActivity(intent);
        finish();
    }

    /**
     * スピナーに値を入れるメソッド
     */
    private void setSpinner(){
        String[] labels = getResources().getStringArray(R.array.arr_order_detail);
        spinner = (Spinner)findViewById(R.id.spRefinement);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labels);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
        spinner.setFocusable(false);
    }

    /**
     * スピナーを押された時のリスナーメソッド
     */
    public class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener{
        public void onItemSelected(AdapterView parent, View view, int position, long id) {
            if (spinner.isFocusable() == false) {
                spinner.setFocusable(true);
                return;
            }
            // Spinner を取得
            Spinner spinner1 = (Spinner) parent;
            // 選択されたアイテムのテキストを取得
            sort = spinner1.getSelectedItem().toString();
            switch (sort){
                case "未作業":
                    sort = "0";
                    break;
                case "ピッキング済":
                    sort = "1";
                    break;
                case "検品済":
                    sort = "2";
                    break;
                case "小分済":
                    sort = "3";
                    break;
                case "全件":
                    sort = "";
                    break;
            }
            RestAccess access = new RestAccess(_list);

            access.execute(_URL);
            Log.e("spinner", sort);
        }

        // 何も選択されなかった時の動作
        public void onNothingSelected(AdapterView parent) {}
    }

    public void onListItemClick(ListView listView, View view, int position, long id){
        super.onListItemClick(listView, view, position, id);
        Map<String, String> item = pickList.get(position);

        String num = item.get("num");
        String step = item.get("step");
        if("検品済".equals(step)) {
            stepUpdate update = new stepUpdate();
            update.execute(_URL, num);
            item.put("step", "小分済");
            pickList.set(position, item);
            preview();
        }
    }



    private class stepUpdate extends AsyncTask<String, Void, String>{
        private static final String DEBUG_TAG = "stepUpdate";
        String result;
        @Override
        public String doInBackground(String... params) {
            String num = params[1];
            String urlStr = params[0];
            HttpURLConnection con = null;
            InputStream is = null;
            try {
                Log.e("",orderId);
                URL url = new URL(urlStr+"?method=" + "stepUpdate" + "&value=" + orderId + "&num="+ num);
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

        }

    }

    public void backIntent(View view){
        finish();
    }






}
