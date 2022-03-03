package br.com.infotransctd;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.DriverManager;

public class DriverController extends VolleyController{

    private static final String TAG_IDENTIFIER = DriverController.class.getName();

        public DriverController(Context context) {
        super(context);
    }

    public void insertDriver() {
          Driver driver = new Driver();
          driver.setBadge(1234);
          driver.setName("José Bruno Fernandes");

        JSONObject parametros = new JSONObject();

        try{

            parametros.put("badge", driver.getBadge());
            parametros.put("name", driver.getName());

        }catch(JSONException e){
            Log.e(TAG_IDENTIFIER, e.getMessage());
        }

        post("geolocation/driver", parametros);
    }

}
