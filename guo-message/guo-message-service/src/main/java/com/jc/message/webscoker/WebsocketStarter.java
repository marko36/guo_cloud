package com.jc.message.webscoker;

import com.alibaba.fastjson.JSONObject;
import org.tio.core.Tio;
import org.tio.server.ServerGroupContext;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.server.WsServerStarter;

public class WebsocketStarter {
    private static WebsocketStarter websocketStarter;
    private static WsServerStarter wsServerStarter;
    private ServerGroupContext serverGroupContext;

    private WebsocketStarter(){}

    private WebsocketStarter(int port,WebSockerMsgHandler wsMsgHandler)throws Exception{
        wsServerStarter = new WsServerStarter(port,wsMsgHandler);
        serverGroupContext = wsServerStarter.getServerGroupContext();
        serverGroupContext.setName(WebSockerServerConfig.PROTOCOL_NAME);
        serverGroupContext.setServerAioListener(WebSockerServerAioListener.me);
        //设置ip监控
        serverGroupContext.setIpStatListener(WebSockerIpStatListener.me);
        //设置ip统计时间段
        serverGroupContext.ipStats.addDurations(WebSockerServerConfig.IpStatDuration.IPSTAT_DURATIONS);
        //设置心跳超时时间
        serverGroupContext.setHeartbeatTimeout(WebSockerServerConfig.HEARTBEAT_TIMEOUT);
    }

    public static WebsocketStarter getInstance(){
        synchronized (WebsocketStarter.class){
            if (websocketStarter == null){
                try {
                    websocketStarter = new WebsocketStarter(WebSockerServerConfig.SERVER_PORT,WebSockerMsgHandler.me);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            return websocketStarter;
        }
    }

    public void start() throws Exception{
        wsServerStarter.start();
    }

    public void sendGroupMsg(String group,Object msg){
        WsResponse wsResponse = WsResponse.fromText(JSONObject.toJSONString(msg),WebSockerServerConfig.CHARSET);
        Tio.sendToGroup(serverGroupContext,group,wsResponse);
    }
}
