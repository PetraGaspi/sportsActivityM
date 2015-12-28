package cz.muni.fi.pa165.sportsactivitymanager.Enums;

/**
 * Created by michal on 12/26/15.
 */
public enum UserState {
    ADMIN, CUSTOMER, INACTIVE;

    /**
     * Checks the enum for a string contained within
     *
     * @param valueString
     * @return true if the string is contained in the enum
     */
    public static boolean contains(final String valueString) {
        for (UserState os : UserState.values()) {
            if (os.name().equals(valueString)) {
                return true;
            }
        }
        return false;
    }
}
