/**
 * 
 */
package com.revature.models;

/**
 * @author Hassan
 *
 */
public enum ReimbType {
	TRAVEL {
        @Override
        public String toString() {
            return "Travel";
        }
    },
    CERTIFICATION {
        @Override
        public String toString() {
            return "Certification";
        }
    }

}
