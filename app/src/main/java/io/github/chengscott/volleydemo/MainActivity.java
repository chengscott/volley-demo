package io.github.chengscott.volleydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class MainActivity extends AppCompatActivity {
    public final String novelTag = "GreyTag";
    private TextView novel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        novel = (TextView) findViewById(R.id.novel);
        novel.setMovementMethod(new ScrollingMovementMethod());
        getGreyNovel();
    }

    private void getGreyNovel() {
        String url = "http://chengscott.tw/talk/page/Google_Volley_Intro/string/Fifty-Shades-Ch29.txt";
        StringRequest greyRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        novel.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        AppController.getInstance().addToRequestQueue(greyRequest, novelTag);
    }
}
