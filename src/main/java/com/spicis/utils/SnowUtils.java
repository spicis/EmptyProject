package com.spicis.utils;

import cn.hutool.core.lang.Snowflake;
import org.springframework.stereotype.Component;

@Component
public class SnowUtils {

    public static final int WORK_ID = 1;
    public static final int DATA_CENTER_ID = 1;

    private Snowflake snowflake;

    public Snowflake getSnowflake() {
        if (this.snowflake == null) {
            this.snowflake = new Snowflake(WORK_ID, DATA_CENTER_ID);
        }
        return this.snowflake;
    }
}
