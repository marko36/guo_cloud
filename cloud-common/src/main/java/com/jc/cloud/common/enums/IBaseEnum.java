package com.jc.cloud.common.enums;

import java.io.Serializable;

public   interface  IBaseEnum<T extends Serializable> {
    T getType();

}
