package bt4;

public class PasswordEvaluator {

    public String evaluatePasswordStrength(String password) {
        if (password == null) {
            return "Yếu";
        }

        boolean lengthValid = password.length() >= 8;
        boolean hasUpper = password.chars().anyMatch(Character::isUpperCase);
        boolean hasLower = password.chars().anyMatch(Character::isLowerCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        boolean hasSpecial = password.chars().anyMatch(ch -> !Character.isLetterOrDigit(ch));

        if (!lengthValid) {
            return "Yếu";
        }

        int categoryCount = (hasUpper ? 1 : 0) + (hasLower ? 1 : 0) + (hasDigit ? 1 : 0) + (hasSpecial ? 1 : 0);
        if (categoryCount == 4) {
            return "Mạnh";
        }

        if (categoryCount >= 3) {
            return "Trung bình";
        }

        return "Yếu";
    }
}
