// **********************************************************
// Copyright (c) Cengage Learning 2013 - All rights reserved
// **********************************************************
package tdd.katas.day2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankOCR {
    
    private static final int ACCOUNT_REPRESENTATION_LINES = 4;
    private static final int CHECKSUM_VALIDATOR = 11;
    private static final int ACCOUNT_NUMBER_LENGTH = 9;
    private static final int DIGIT_WIDTH = 3;
    private static final int START_INDEX = 0;
    
    Map<String, String> stringDigitsCode = new HashMap<String, String>();
    
    
    
    public BankOCR() {
        stringDigitsCode.put(" _ | ||_|", "0");
        stringDigitsCode.put("     |  |", "1");
        stringDigitsCode.put(" _  _||_ ", "2");
        stringDigitsCode.put(" _  _| _|", "3");
        stringDigitsCode.put("   |_|  |", "4");
        stringDigitsCode.put(" _ |_  _|", "5");
        stringDigitsCode.put(" _ |_ |_|", "6");
        stringDigitsCode.put(" _   |  |", "7");
        stringDigitsCode.put(" _ |_||_|", "8");
        stringDigitsCode.put(" _ |_| _|", "9");
    }
    
    public List<String> getAccounts(List<String> accountNumbers) {
        List<String> accounts = new ArrayList<String>();
        for (int numberOfAccounts=0;numberOfAccounts<accountNumbers.size(); numberOfAccounts+=ACCOUNT_REPRESENTATION_LINES) {
            accounts.add(parseAccount(numberOfAccounts, accountNumbers));
        }        
        return accounts;
    }
    
    private String parseAccount(int startRow, List<String> accountNumber) {
        int startIndex = START_INDEX;
        int endIndex   = DIGIT_WIDTH;
        String number = "";
        for (int digitCounter = 0; digitCounter < ACCOUNT_NUMBER_LENGTH; digitCounter++) {
            String digit = accountNumber.get(startRow).substring(startIndex, endIndex) + 
                           accountNumber.get(startRow+1).substring(startIndex,endIndex) +
                           accountNumber.get(startRow+2).substring(startIndex, endIndex);
            number     += stringDigitsCode.get(digit);
            startIndex += DIGIT_WIDTH;
            endIndex   += DIGIT_WIDTH;
        }
        return number;
    }

    public boolean validateAccount(String accountNumber) {
        return isValidChecksum(buildChecksum(accountNumber));
    }

    private int buildChecksum(String number) {
        int checksum = 0;
        for(int digitCounter=0; digitCounter < ACCOUNT_NUMBER_LENGTH; digitCounter++) {
             int digit = convertDigitToNumber(number, digitCounter);  
             checksum += digit*(ACCOUNT_NUMBER_LENGTH - digitCounter);
        }
        return checksum;
    }

    private int convertDigitToNumber(String number, int digitCounter) {
        return Integer.valueOf(number.charAt(digitCounter) + "");
    }

    private boolean isValidChecksum(int checksum) {
        return checksum % CHECKSUM_VALIDATOR == 0;
    }    
}
