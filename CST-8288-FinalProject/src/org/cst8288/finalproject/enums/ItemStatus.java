package org.cst8288.finalproject.enums;
/**
 * Enumeration for defining the status of items in the inventory system.
 * This enum is used to track the current state of items within the system,
 * facilitating status-based operations and queries, such as filtering items by their sales status.
 *
 * The enumeration defines three states for inventory items:
 * - AVAILABLE: Indicates that the item is available for purchase or claim.
 * - CLAIMED: Indicates that the item has been claimed but not yet paid for or permanently taken.
 * - SOLD: Indicates that the item has been sold and is no longer available.
 *
 * Utilizing these statuses allows for systematic and efficient management of inventory states
 * and supports various features such as reservation systems and sales tracking.
 *
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-10
 */
public enum ItemStatus{
    AVAILABLE,
    CLAIMED,
    SOLD
}
