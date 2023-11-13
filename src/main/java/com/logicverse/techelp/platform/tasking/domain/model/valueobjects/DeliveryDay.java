package com.logicverse.techelp.platform.tasking.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Embeddable
public record DeliveryDay(Date delivery_day) {
    public DeliveryDay(){this(null);}

    public DeliveryDay {
        if (delivery_day == null) {
            throw new IllegalArgumentException("Date cannot be null or blank");
        }
    }

    public Date getDeliveryDay() {
        String deliveryDayString = String.valueOf(delivery_day);
        Date deliveryDate = null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {

            deliveryDate = dateFormat.parse(deliveryDayString);
        } catch (ParseException e) {
            e.printStackTrace(); //
        }

        return deliveryDate;
    }
}
