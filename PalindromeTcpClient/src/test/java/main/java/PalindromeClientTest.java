package main.java;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PalindromeClientTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void isPalindromeCanReturnTreeForPalindrome() throws Exception {
        String palindromeString = "zittmttiz";
        Assert.assertTrue(PalindromeClient.isPalindrome(palindromeString));
    }

    @Test
    public void isPalindromeCanReturnFalseForNonPalindrome() throws Exception {
        String palindromeString = "rzainiazr";
        Assert.assertTrue(PalindromeClient.isPalindrome(palindromeString));
    }

    @Test
    public void isPalindromeCanReturnThrowWhenInputIsNull() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Palindrome string can't be null.");
        PalindromeClient.isPalindrome(null);
    }

    @Test
    public void isPalindromeCanReturnThrowWhenInputIsEmpty() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Palindrome string can't be empty.");
        PalindromeClient.isPalindrome("");
    }
}