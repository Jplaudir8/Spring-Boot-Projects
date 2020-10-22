package com.udacity.jdnd.course3.critter.enumConverters;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.logging.Logger;

@Converter(autoApply = true) // autoApply = true: tells Hibernate to apply this converter to all entity attributes of type EmployeeSkill.
public class EmployeeSkillConverter implements AttributeConverter<EmployeeSkill, String> {

    Logger log = Logger.getLogger(EmployeeSkill.class.getSimpleName());

    @Override
    public String convertToDatabaseColumn(EmployeeSkill employeeSkill) {
        switch (employeeSkill) {
            case PETTING:
                logDBConversion(employeeSkill, "Petting");
                return "Petting";

            case WALKING:
                logDBConversion(employeeSkill, "Walking");
                return "Walking";

            case FEEDING:
                logDBConversion(employeeSkill, "Feeding");
                return "Feeding";

            case MEDICATING:
                logDBConversion(employeeSkill, "Medicating");
                return "Medicating";

            case SHAVING:
                logDBConversion(employeeSkill, "Shaving");
                return "Shaving";

            default:
                throw new IllegalArgumentException("EmployeeSkill [" + employeeSkill + "] not supported.");
        }
    }

    @Override
    public EmployeeSkill convertToEntityAttribute(String dbData) {
        switch (dbData) {
            case "Petting":
                logEntityConversion("Petting", EmployeeSkill.PETTING);
                return EmployeeSkill.PETTING;

            case "Walking":
                logEntityConversion("Petting", EmployeeSkill.WALKING);
                return EmployeeSkill.WALKING;

            case "Feeding":
                logEntityConversion("Petting", EmployeeSkill.FEEDING);
                return EmployeeSkill.FEEDING;

            case "Medicating":
                logEntityConversion("Petting", EmployeeSkill.MEDICATING);
                return EmployeeSkill.MEDICATING;

            case "Shaving":
                logEntityConversion("Petting", EmployeeSkill.SHAVING);
                return EmployeeSkill.SHAVING;

            default:
                throw new IllegalArgumentException("EmployeeSkill [" + dbData + "] not supported.");
        }
    }

    private void logDBConversion(EmployeeSkill employeeSkill, String dbData) {
        log.info("Convert EmployeeSkill enum |" + employeeSkill + "| to |" + dbData +"| ");
    }

    private void logEntityConversion(String dbData, EmployeeSkill employeeSkill) {
        log.info("Convert |" + dbData + "| to EmployeeSkill enum |" + employeeSkill +"| ");
    }
}
