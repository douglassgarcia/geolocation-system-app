package br.com.infotransctd;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class VolleyController {

    protected Context context;

    private static final String TAG_IDENTIFIER = VolleyController.class.getName();

    protected String baseUrl = "http://localhost:9000/";

    private VolleyResponseListener listener;


    protected VolleyController(Context context){
        this.context = context;
    }

    public void setVolleyResponseListener(VolleyResponseListener listener){
        this.listener = listener;
    }

    protected void fireResponseSuccess(int status, String message){
        if (listener != null){
            listener.success(status, message);
        }
    }

    protected void fireResponseError(String message){
        if (listener != null){
            listener.error( message);
        }
    }

    public void post(String url, JSONObject parametros){

        url = baseUrl + url;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, parametros,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        int status      = 0;
                        String mensagem = "";

                        try {

                            status = response.getInt("status");
                            mensagem = response.getString("mensagem");

                            fireResponseSuccess(status, mensagem);

                        }catch(JSONException e){
                            Log.e(TAG_IDENTIFIER, e.getMessage());
                            fireResponseError(e.getMessage());
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG_IDENTIFIER, error.getMessage());
                        fireResponseError(error.getMessage());
                    }
                }

        );

        VolleyRequestQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);

    }
}
