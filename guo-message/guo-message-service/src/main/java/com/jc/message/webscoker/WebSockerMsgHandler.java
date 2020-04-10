package com.jc.message.webscoker;

import com.alibaba.fastjson.JSONObject;
import com.jc.cloud.auth.client.jwt.UserAuthUtil;
import com.jc.cloud.common.constant.RequestHeaderConstants;
import com.jc.cloud.common.util.SpringBeanUtil;
import com.jc.cloud.common.util.jwt.IJWTInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.common.WsSessionContext;
import org.tio.websocket.server.handler.IWsMsgHandler;

import java.util.Objects;

public class WebSockerMsgHandler implements IWsMsgHandler {
    private static Logger log = LoggerFactory.getLogger(WebSockerMsgHandler.class);

    public static final WebSockerMsgHandler me = new WebSockerMsgHandler();

    public WebSockerMsgHandler(){}

    /**
     * 握手时走这个方法，业务可以在这里获取cookie,request参数等
    * */
    @Override
    public HttpResponse handshake(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
        String clientIp = httpRequest.getClientIp();
        log.info("收到来自{}的ws握手包\r\n{}",clientIp,httpRequest.toString());
        return httpResponse;
    }
    /**
     * @param httpRequest
     * @param httpResponse
     * @param channelContext
     * @throws Exception
    * */
    @Override
    public void onAfterHandshaked(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
        try {
            WebScoketUser user = getWebUser(httpRequest);
            //绑定到群组，后面会有群发
            Tio.bindUser(channelContext,user.getUserId());
            Tio.bindGroup(channelContext,user.getSysModel());
            int count = Tio.getAllChannelContexts(channelContext.groupContext).getObj().size();
            String msg = "{name:'"+user.getUserName()+"'message:'"+channelContext.userid+" 进来了，共【" + count + "】人在线"+"'}";
            //用tio-webscoket，服务器发送到客户端的Packet都是WsResponse
            //WsResponse wsResponse = WsResponse.fromText(msg, WebSockerServerConfig.CHARSET);
            //			群发
            //Tio.sendToGroup(channelContext.groupContext, user.getSysModel(), wsResponse);
        }catch (Exception e){
            JSONObject ob = new JSONObject();
            ob.put("error","请登陆");
            WsResponse wsResponse = WsResponse.fromText(ob.toString(),WebSockerServerConfig.CHARSET);
            Tio.send(channelContext,wsResponse);
        }
    }
    /**
     * 字节消息（binaryType = arraybuffer）过来后会走这个方法
    * */
    @Override
    public Object onBytes(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
        return null;
    }
    /**
     * 当客户端发close flag时，会走这个方法
    * */
    @Override
    public Object onClose(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
        Tio.remove(channelContext,"receive close flag");
        return null;
    }

    /**
     * 字符消息（binaryType = blob）过来后会走这个方法
    * */
    @Override
    public Object onText(WsRequest wsRequest, String s, ChannelContext channelContext) throws Exception {
        WsSessionContext wsSessionContext = (WsSessionContext) channelContext.getAttribute();
        HttpRequest httpRequest = wsSessionContext.getHandshakeRequestPacket();//获取websocket握手包
        if (log.isDebugEnabled()){
            log.debug("握手包:{}",httpRequest);
        }
        log.info("收到ws消息:{}",s);
        if (Objects.equals("ping",s)){
            return null;
        }
//		String msg = "{name:'" + channelContext.userid + "',message:'" + text + "'}";
//		用tio-websocket，服务器发送到客户端的Packet都是WsResponse
//		WsResponse wsResponse = WsResponse.fromText(msg, WebSockerServerConfig.CHARSET);
//		群发
//		Tio.sendToGroup(channelContext.groupContext, Const.GROUP_ID, wsResponse);

        //返回值是要发送给客户端的内容，一般都是返回null
        return null;
    }

    public static class WebScoketUser {
        private String userId;
        private String userName;
        private String sysModel;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getSysModel() {
            return sysModel;
        }

        public void setSysModel(String sysModel) {
            this.sysModel = sysModel;
        }
    }
        private WebScoketUser getWebUser(HttpRequest request)throws Exception{
            String token = request.getParam("token");
            if (StringUtils.isEmpty(token)){
                throw new Exception("token is null");
            }
            IJWTInfo info = getUserAuthUtil().getInfoFromToken(token.replaceAll(RequestHeaderConstants.JWT_TOKEN_TYPE,""));
            WebScoketUser user = new WebScoketUser();
            user.setUserId(info.getUserId());
            user.setUserName(info.getUserName());
            user.setSysModel(info.getOtherInfo().get(RequestHeaderConstants.JWT_KEY_SYS_MODEL));
            return user;
        }
        private UserAuthUtil getUserAuthUtil(){
            return SpringBeanUtil.getBean(UserAuthUtil.class);
        }

}
