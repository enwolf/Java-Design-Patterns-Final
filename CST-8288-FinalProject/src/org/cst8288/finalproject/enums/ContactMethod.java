package org.cst8288.finalproject.enums;
/**
 * Enumeration for defining the methods of contact available within the system.
 * This enum is used throughout the system to specify how entities prefer to be contacted, 
 * allowing for uniform handling of contact preferences across different modules and functions.
 *
 * The enumeration defines two possible contact methods:
 * - EMAIL: Representing contact via email.
 * - PHONE: Representing contact via telephone.
 *
 * These constants can be used to ensure that other parts of the application handle contact details correctly,
 * such as formatting messages, sending notifications, or validating contact information depending on the chosen method.
 *
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-10
 */
public enum ContactMethod {
    EMAIL,
    PHONE
}
