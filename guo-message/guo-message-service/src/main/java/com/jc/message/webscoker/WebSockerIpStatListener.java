package com.jc.message.webscoker;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.intf.Packet;
import org.tio.core.stat.IpStat;
import org.tio.core.stat.IpStatListener;

public class WebSockerIpStatListener implements IpStatListener {
    private static Logger log = LoggerFactory.getLogger(WebSockerIpStatListener.class);

    public static final WebSockerIpStatListener me = new WebSockerIpStatListener();

    public WebSockerIpStatListener(){}

    @Override
    public void onExpired(GroupContext groupContext, IpStat ipStat) {
        if (log.isInfoEnabled()){
            log.info("可以把统计数据入库\r\n{}", JSONObject.toJSONString(ipStat));
        }
    }

    @Override
    public void onAfterConnected(ChannelContext channelContext, boolean b, boolean b1, IpStat ipStat) throws Exception {
        if (log.isInfoEnabled()){
            log.info("onAfterConnected\t\n{}",JSONObject.toJSONString(ipStat));
        }
    }

    @Override
    public void onDecodeError(ChannelContext channelContext, IpStat ipStat) {
        if (log.isInfoEnabled()){
            log.info("onDecodeError\r\n{}",JSONObject.toJSONString(ipStat));
        }
    }

    @Override
    public void onAfterSent(ChannelContext channelContext, Packet packet, boolean b, IpStat ipStat) throws Exception {
        if (log.isInfoEnabled()){
            log.info("onAfterSent\t\n{}\r\n{}",packet.logstr(), JSONObject.toJSON(ipStat));
        }
    }

    @Override
    public void onAfterDecoded(ChannelContext channelContext, Packet packet, int i, IpStat ipStat) throws Exception {
        if (log.isInfoEnabled()){
            log.info("onAfterDecode\r\n{}\r\n{}",packet.logstr(),JSONObject.toJSONString(ipStat));
        }
    }

    @Override
    public void onAfterReceivedBytes(ChannelContext channelContext, int i, IpStat ipStat) throws Exception {
        if (log.isInfoEnabled()){
            log.info("onAfterReceivedBytes\r\n{}",JSONObject.toJSONString(ipStat));
        }
    }

    @Override
    public void onAfterHandled(ChannelContext channelContext, Packet packet, IpStat ipStat, long l) throws Exception {
        if (log.isInfoEnabled()){
            log.info("onAfterHandled\r\n{}\r\n{}",packet.logstr(),JSONObject.toJSONString(ipStat));
        }
    }
}
