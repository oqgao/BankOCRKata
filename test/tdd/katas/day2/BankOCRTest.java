// **********************************************************
// Copyright (c) Cengage Learning 2013 - All rights reserved
// **********************************************************
package tdd.katas.day2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BankOCRTest {
    
    private BankOCR machine;
    
    @Before
    public void setup() {
        machine = new BankOCR();
    }
    
    @Test
    public void decodeAccountNumberOfAllZeros() {
        List<String> representations = new ArrayList<String>();
        representations.add(" _  _  _  _  _  _  _  _  _ ");
        representations.add("| || || || || || || || || |");
        representations.add("|_||_||_||_||_||_||_||_||_|");
        representations.add("                           ");
        
        List<String> accounts = machine.getAccounts(representations);
        
        assertEquals("000000000", accounts.get(0));         
    }
    
    @Test
    public void processAccountNumberWithDifferentDigits() {
        List<String> representations = new ArrayList<String>();
        representations.add(" _                       _ ");
        representations.add("| |  |  |  |  |  |  |  || |");
        representations.add("|_|  |  |  |  |  |  |  ||_|");
        representations.add("                           ");
        
        List<String> accounts = machine.getAccounts(representations);
        
        assertEquals("011111110", accounts.get(0));        
    }
   
    
    @Test
    public void processMultipleAccountNumbers() {
        List<String> representations = new ArrayList<String>();
        representations.add(" _  _  _  _  _  _  _  _  _ ");
        representations.add("| || || || || || || ||_||_|");
        representations.add("|_||_||_||_||_||_||_| _||_|");
        representations.add("                           ");
        
        representations.add(" _                    _  _ ");
        representations.add("| |  |  |  |  |  |  |  ||_ ");
        representations.add("|_|  |  |  |  |  |  |  ||_|");
        representations.add("                           ");
        
        representations.add(" _  _                    _ ");
        representations.add(" _| _||_|  |  |  |  |  ||_ ");
        representations.add("|_  _|  |  |  |  |  |  | _|");
        representations.add("                           ");        
        
        List<String> accounts = machine.getAccounts(representations);
        assertEquals("000000098", accounts.get(0));
        assertEquals("011111176", accounts.get(1));
        assertEquals("234111115", accounts.get(2));
    }
    
    @Test
    public void validAccountNumber() {
        boolean isValidAccount = machine.validateAccount("000000078");
        assertTrue("Account number 000000078 should be invalid!", isValidAccount);
        
        isValidAccount = machine.validateAccount("234111119");
        assertTrue("Account number 234111119 should be valid!", isValidAccount);        
    }
    
    @Test
    public void inValidAccountNumber() {
        boolean isValidAccount = machine.validateAccount("000000088");
        assertFalse("Account number 000000088 should be valid!", isValidAccount);
        
        isValidAccount = machine.validateAccount("234111115");
        assertFalse("Account number 234111115 should be invalid!", isValidAccount);
    }       
 
}
