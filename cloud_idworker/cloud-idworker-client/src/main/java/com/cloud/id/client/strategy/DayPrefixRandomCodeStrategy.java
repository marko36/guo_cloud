package com.cloud.id.client.strategy;

import com.cloud.id.client.IdUtils;
import com.cloud.id.client.IdWorker;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DayPrefixRandomCodeStrategy extends DefaultRandomCodeStrategy {
    private final String dayFormat;
    private String lastDay;
    private IdUtils idUtils;

    public DayPrefixRandomCodeStrategy(String dayFormat ,IdUtils idUtils)  {
        super(idUtils);
        this.dayFormat = dayFormat;
        String day = createDate();
        if (day.equals(lastDay))
            throw new RuntimeException("init failed for day unrolled");

        lastDay = day;

        availableCodes.clear();

        prefixIndex = Integer.parseInt(lastDay);
        if (tryUsePrefix()) return;

        throw new RuntimeException("prefix is not available " + prefixIndex);
    }


    private String createDate() {
        return new SimpleDateFormat(dayFormat).format(new Date());
    }

    @Override
    public int next() {
        return super.next();
    }
}
