package com.jc.message.core.entity.vedio;

import com.jc.message.core.constant.VedioMessageType;
import lombok.Data;

@Data
public class DispatchVedioMsg extends BaseVedioMsg{
    public String userId;

    public DispatchVedioMsg(){}

    public DispatchVedioMsg(String vedioId,String vedioPath,String userId){
        super(vedioId,vedioPath, VedioMessageType.DISPATCH);
        this.userId = userId;
    }

}
