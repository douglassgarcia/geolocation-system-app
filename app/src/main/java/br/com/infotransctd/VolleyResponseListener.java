package br.com.infotransctd;

public interface VolleyResponseListener {

    public void success(int status, String message);

    public void error(String message);

}
