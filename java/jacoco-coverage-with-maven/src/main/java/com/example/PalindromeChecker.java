package com.example;

interface PalindromeChecker {

  static boolean isPalindrome(final String input) {
    var forward = 0;
    var backward = input.length() - 1;
    var str = input.toLowerCase();
    while (backward > forward) {
      if (str.charAt(forward) != str.charAt(backward)) {
        return false;
      }
      forward++;
      backward--;
    }
    return true;
  }

  static boolean isPalindrome(final int input) {
    int MINUS = -1;
    return input < 0 ? isPalindrome(String.valueOf(input * MINUS))
        : isPalindrome(String.valueOf(input));
  }
}
