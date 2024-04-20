package org.cst8288.finalproject.enums;

/**
 * Enumeration for defining the types of users within the system.
 * This enum facilitates the management of user roles and access within the application,
 * ensuring that operations and processes are executed in accordance with user privileges.
 *
 * The enumeration defines three types of users:
 * - CONSUMER: An end user who purchases or claims items from the inventory.
 * - RETAILER: A user who lists items in the inventory, possibly managing sales or claims.
 * - CHARITABLE_ORGANIZATION: A user associated with a non-profit entity that might receive donations or special privileges.
 *
 * These user types are crucial for role-based access control (RBAC) systems and help maintain
 * clear separation of functionalities and responsibilities among different user groups.
 *
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-10
 */
public enum UserType {
	
    CONSUMER,
    RETAILER,
    CHARITABLE_ORGANIZATION
}
