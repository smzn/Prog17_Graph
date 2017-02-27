package com.example.mizuno.prog17_05;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by mizuno on 2017/02/26.
 */

public class AsyncHttpGetJson extends AsyncTask<Void, Void, String> {

    private String result = new String();
    private BarActivity barActivity;
    private LineActivity lineActivity;
    private PieActivity pieActivity;
    private int flg;

    public AsyncHttpGetJson(BarActivity activity, int flg) {
        this.barActivity = activity;
        this.flg = flg;
    }
    public AsyncHttpGetJson(LineActivity activity, int flg) {
        this.lineActivity = activity;
        this.flg = flg;
    }
    public AsyncHttpGetJson(PieActivity activity, int flg) {
        this.pieActivity = activity;
        this.flg = flg;
    }
    //flg Bar -> 0, Line -> 1

    @Override
    protected String doInBackground(Void... params) {
        HttpURLConnection con = null;
        URL url = null;
        String urlSt = "http://ms000.sist.ac.jp/oc/weathers/json/";

        try {
            // URLの作成
            url = new URL(urlSt);
            // 接続用HttpURLConnectionオブジェクト作成
            con = (HttpURLConnection)url.openConnection();
            // リクエストメソッドの設定
            con.setRequestMethod("POST");
            // リダイレクトを自動で許可しない設定
            con.setInstanceFollowRedirects(false);
            // URL接続からデータを読み取る場合はtrue
            con.setDoInput(true);
            // URL接続にデータを書き込む場合はtrue
            con.setDoOutput(true);

            // 接続
            con.connect(); // ①
            InputStream in = con.getInputStream();
            String readSt = readInputStream(in);

            // 配列を取得する場合
            JSONArray jsonArray = new JSONObject(readSt).getJSONArray("Weather");
            for (int n = 0; n < jsonArray.length(); n++) {
                // User data
                JSONObject userObject = jsonArray.getJSONObject(n);
                Log.d("Object", String.valueOf(userObject));
                String name = userObject.getString("name");
                double temperature = userObject.getDouble("temperature");
                double max = userObject.getDouble("max");
                double min = userObject.getDouble("min");
                result += name + "," + temperature+","+ max+","+ min+",";
            }
            Log.d("result", result);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    public String readInputStream(InputStream in) throws IOException, UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        String st = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        while((st = br.readLine()) != null)
        {
            sb.append(st);
        }
        try
        {
            in.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return sb.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        String str[] = s.split(",", 0);
        if(this.flg == 0){
            barActivity.setBarChart(str);
        }else if(this.flg == 1){
            lineActivity.setLineChart(str);
        }else if(this.flg == 2){
            pieActivity.setPieChart(str);
        }
    }
}
