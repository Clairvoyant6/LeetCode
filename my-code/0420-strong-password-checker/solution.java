
class Solution {
    public int strongPasswordChecker(String password) {
        int n = password.length();

        boolean lower = false, upper = false, digit = false;

        for (char c : password.toCharArray()) {
            if (c >= 'a' && c <= 'z') lower = true;
            if (c >= 'A' && c <= 'Z') upper = true;
            if (c >= '0' && c <= '9') digit = true;
        }

        int missing = 0;
        if (!lower) missing++;
        if (!upper) missing++;
        if (!digit) missing++;

        int replace = 0;
        int one = 0;
        int two = 0;

        for (int i = 0; i < n;) {
            int j = i;

            while (j < n && password.charAt(j) == password.charAt(i)) {
                j++;
            }

            int len = j - i;

            if (len >= 3) {
                replace += len / 3;

                if (len % 3 == 0) one++;
                else if (len % 3 == 1) two++;
            }

            i = j;
        }

        if (n < 6) {
            return Math.max(missing, 6 - n);
        }

        if (n <= 20) {
            return Math.max(missing, replace);
        }

        int delete = n - 20;

        replace -= Math.min(delete, one);
        replace -= Math.min(Math.max(0, delete - one), two * 2) / 2;

        replace -= Math.max(0, delete - one - two * 2) / 3;

        return delete + Math.max(missing, replace);
    }
}

