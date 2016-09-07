package com.kokabi.p.navigationsample.Services;

import android.util.Log;

import com.kokabi.p.navigationsample.Help.AppController;
import com.kokabi.p.navigationsample.Help.ErrorHandling;
import com.kokabi.p.navigationsample.Objects.ServerObj;
import com.kokabi.p.programmingmarathon1.R;

import retrofit2.Call;
import retrofit2.Callback;


public class ServerResponse {

    public void setCall(final String methodName, Call<ServerObj> call, final ServerListener serverListener) {

        Log.i("==UrlServer", call.request().url().toString());

        call.enqueue(new Callback<ServerObj>() {

            @Override
            public void onResponse(Call<ServerObj> call, retrofit2.Response<ServerObj> response) {

                if (response.code() == 200) {

                    Log.i("==GetServer", response.body().getData().toString());

                    String stateResponse = new ErrorHandling().ErrorCode(response.body());

                    if (stateResponse.equals("OK")) {
                        serverListener.onSuccess(methodName, response.body().getData());
                    } else {
                        serverListener.onFailure(methodName, response.body().getMessage());
                    }

                } else {
                    Log.i("==CodeServer", String.valueOf(response.code()));
                    Log.i("==MessageServer", String.valueOf(response.message()));
                    serverListener.onFailure(methodName, response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<ServerObj> call, Throwable t) {
                serverListener.onFailure(methodName, AppController.getCurrentContext().getString(R.string.serverError));
            }

        });
    }

}
