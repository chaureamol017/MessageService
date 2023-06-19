package com.mycomp.message.hub.gateway.fast2sms.v1;

import com.mycomp.message.hub.api.RestApiClient;
import com.mycomp.message.hub.core.restclient.RestMethod;
import com.mycomp.message.hub.core.restclient.RestMethodFactory;
import com.mycomp.message.hub.core.restclient.model.Credentials;
import com.mycomp.message.hub.gateway.fast2sms.v1.restmodel.Fast2smsOtpRequest;
import com.mycomp.message.hub.gateway.fast2sms.v1.restmodel.Fast2smsOtpResponse;
import javafx.util.Pair;

import java.util.List;

public class Fast2smsRestApiClient extends RestApiClient {
    private static final String BASE_URL = "https://www.fast2sms.com/dev/bulk";
    private static final String API_VERSION = null;
    private RestMethodFactory restMethodFactory;
    private Credentials credentials;

    public Fast2smsRestApiClient(final RestMethodFactory restMethodFactory, final Credentials credentials) {
        super(BASE_URL, API_VERSION);
        this.restMethodFactory = restMethodFactory;
        this.credentials = credentials;
    }

    @Override
    public String getGatewayName() {
        return "Fast2sms";
    }

    @Override
    public RestMethodFactory getRestMethodFactory() {
        return restMethodFactory;
    }

    @Override
    public Boolean isSeparateVersionInUrl() {
        return false;
    }

    public RestMethod<Fast2smsOtpResponse> createOtpMethod(final Fast2smsOtpRequest request, final List<Pair<String, String>> params) {
        final String endpoint = getServiceEndpoint("");

        final RestMethod<Fast2smsOtpResponse> methodForPost = createRestMethodForGet(endpoint, Fast2smsOtpResponse.class);
        methodForPost.addHeader("cache-control", "no-cache");
        final String route = "p";


        addParameters(params, methodForPost, route);

        if (request != null) {
            methodForPost.setBodyAsJson(request);
        }

        return methodForPost;
    }

    private void addParameters(List<Pair<String, String>> params, RestMethod<Fast2smsOtpResponse> methodForPost, String route) {
        String language = "english";
        methodForPost.addParam("authorization", credentials.getApiKey());
        methodForPost.addParam("language" , language);
        methodForPost.addParam("route", route);
        params.forEach(pair -> {
            methodForPost.addParam(pair.getKey(), pair.getValue());
        });
    }
}
