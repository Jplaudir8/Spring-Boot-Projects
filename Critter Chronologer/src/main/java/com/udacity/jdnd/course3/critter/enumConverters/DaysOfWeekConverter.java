package com.udacity.jdnd.course3.critter.enumConverters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.DayOfWeek;
import java.util.logging.Logger;

@Converter(autoApply = true) // autoApply = true: tells Hibernate to apply this converter to all entity attributes of type DaysOfWeek.
public class DaysOfWeekConverter implements AttributeConverter<DayOfWeek, String> {

    Logger log = Logger.getLogger(DayOfWeek.class.getSimpleName());

    @Override
    public String convertToDatabaseColumn(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY:
                logDBConversion(dayOfWeek, "Monday");
                return "Monday";
            case TUESDAY:
                logDBConversion(dayOfWeek, "Tuesday");
                return "Tuesday";
            case WEDNESDAY:
                logDBConversion(dayOfWeek, "Wednesday");
                return "Wednesday";
            case THURSDAY:
                logDBConversion(dayOfWeek, "Thursday");
                return "Thursday";
            case FRIDAY:
                logDBConversion(dayOfWeek, "Friday");
                return "Friday";
            case SATURDAY:
                logDBConversion(dayOfWeek, "Saturday");
                return "Saturday";
            case SUNDAY:
                logDBConversion(dayOfWeek, "Sunday");
                return "Sunday";
            default:
                throw new IllegalArgumentException("DayOfWeek ["+ dayOfWeek +"] not supported.");
        }
    }

    @Override
    public DayOfWeek convertToEntityAttribute(String dbData) {
        switch (dbData) {
            case "Monday":
                logEntityConversion("Monday", DayOfWeek.MONDAY);
                return DayOfWeek.MONDAY;
            case "Tuesday":
                logEntityConversion("Tuesday", DayOfWeek.TUESDAY);
                return DayOfWeek.TUESDAY;
            case "Wednesday":
                logEntityConversion("Wednesday", DayOfWeek.WEDNESDAY);
                return DayOfWeek.WEDNESDAY;
            case "Thursday":
                logEntityConversion("Thursday", DayOfWeek.THURSDAY);
                return DayOfWeek.THURSDAY;
            case "Friday":
                logEntityConversion("Friday", DayOfWeek.FRIDAY);
                return DayOfWeek.FRIDAY;
            case "Saturday":
                logEntityConversion("Saturday", DayOfWeek.SATURDAY);
                return DayOfWeek.SATURDAY;
            case "Sunday":
                logEntityConversion("Sunday", DayOfWeek.SUNDAY);
                return DayOfWeek.SUNDAY;
            default:
                throw new IllegalArgumentException("DayOfWeek ["+ dbData +"] not supported.");
        }
    }

    private void logDBConversion(DayOfWeek dayOfWeek, String dbData) {
        log.info("Convert DayOfWeek enum |" + dayOfWeek + "| to |" + dbData +"| ");
    }

    private void logEntityConversion(String dbData, DayOfWeek dayOfWeek) {
        log.info("Convert |" + dbData + "| to DayOfWeek enum |" + dayOfWeek +"| ");
    }
}
