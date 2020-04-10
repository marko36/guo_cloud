package com.cloud.id.client;

public interface RandomCodeStrategy {


    int prefix();

    int next();

}
