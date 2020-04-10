package com.jc.cloud.home;

import lombok.Data;

import java.io.Serializable;

@Data
public class MonitorFrom implements Serializable {
    private String addrs;
    private Integer port;
    private String userName;
    private String password;
    private String monitorName;
}
