package com.udacity.jdnd.course3.critter.enumConverters;

import com.udacity.jdnd.course3.critter.pet.PetType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.logging.Logger;

@Converter(autoApply = true) // autoApply = true: tells Hibernate to apply this converter to all entity attributes of type PetType.
public class PetTypeConverter  implements AttributeConverter<PetType, String> {

    Logger log = Logger.getLogger(PetType.class.getSimpleName());

    @Override
    public String convertToDatabaseColumn(PetType petType) {
        switch (petType) {
            case CAT:
                logDBConversion(petType, "Cat");
                return "Cat";

            case DOG:
                logDBConversion(petType, "Dog");
                return "Dog";

            case LIZARD:
                logDBConversion(petType, "Lizard");
                return "Lizard";

            case BIRD:
                logDBConversion(petType, "Bird");
                return "Bird";

            case FISH:
                logDBConversion(petType, "Fish");
                return "Fish";

            case SNAKE:
                logDBConversion(petType, "Snake");
                return "Snake";

            case OTHER:
                logDBConversion(petType, "Other");
                return "Other";

            default:
                throw new IllegalArgumentException("PetType [" + petType + "] not supported.");
        }
    }

    @Override
    public PetType convertToEntityAttribute(String dbData) {
        switch (dbData) {
            case "Cat":
                logEntityConversion("Cat", PetType.CAT);
                return PetType.CAT;

            case "Dog":
                logEntityConversion("Dog", PetType.DOG);
                return PetType.DOG;

            case "Lizard":
                logEntityConversion("Lizard", PetType.LIZARD);
                return PetType.LIZARD;

            case "Bird":
                logEntityConversion("Bird", PetType.BIRD);
                return PetType.BIRD;

            case "Fish":
                logEntityConversion("Fish", PetType.FISH);
                return PetType.FISH;

            case "Snake":
                logEntityConversion("Snake", PetType.SNAKE);
                return PetType.SNAKE;

            case "Other":
                logEntityConversion("Other", PetType.OTHER);
                return PetType.OTHER;

            default:
                throw new IllegalArgumentException("PetType [" + dbData + "] not supported.");
        }
    }

    private void logDBConversion(PetType petType, String dbData) {
        log.info("Convert PetType enum |" + petType + "| to |" + dbData +"| ");
    }

    private void logEntityConversion(String dbData, PetType petType) {
        log.info("Convert |" + dbData + "| to PetType enum |" + petType +"| ");
    }
}
