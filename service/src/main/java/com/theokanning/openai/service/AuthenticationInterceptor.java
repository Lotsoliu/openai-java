package com.theokanning.openai.service;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

/**
 * OkHttp Interceptor that adds an authorization token header.
 */
public class AuthenticationInterceptor implements Interceptor {

    private final String token;

    /**
     * Constructs an AuthenticationInterceptor with the given token.
     *
     * @param token the OpenAI API token, must not be null or blank
     */
    public AuthenticationInterceptor(String token) {
        Objects.requireNonNull(token, "OpenAI token required");
        if (token.trim().isEmpty()) {
            throw new IllegalArgumentException("OpenAI token must not be blank");
        }
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .header("Authorization", "Bearer " + token)
                .build();
        return chain.proceed(request);
    }
}
