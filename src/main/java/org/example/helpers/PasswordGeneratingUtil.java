package org.example.helpers;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.util.ArrayList;
import java.util.List;

public class PasswordGeneratingUtil {

    public PasswordGeneratingUtil() {
    }

    public static String generatePassword() {
        CharacterRule alphabetsUppercase = new CharacterRule(EnglishCharacterData.UpperCase, 2);
        CharacterRule alphabetsLowercase = new CharacterRule(EnglishCharacterData.LowerCase, 2);
        CharacterRule special = new CharacterRule(EnglishCharacterData.Special, 1);
        List<CharacterRule> rulesList = new ArrayList<>();
        rulesList.add(alphabetsUppercase);
        rulesList.add(alphabetsLowercase);
        rulesList.add(special);

        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String password = passwordGenerator.generatePassword(8, rulesList);
        return password;
    }
}
