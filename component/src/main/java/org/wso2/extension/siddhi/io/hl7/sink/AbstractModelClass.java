/*
 *  Copyright (c) 2019 WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */

package org.wso2.extension.siddhi.io.hl7.sink;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;

public abstract class AbstractModelClass {
    // ~ Instance fields
    // ------------------------------------------------------------------------------------------------

    private transient PropertyChangeSupport myPropertyChangeSupport = new PropertyChangeSupport(this);
    private transient VetoableChangeSupport myVetoableChangeSupport = new VetoableChangeSupport(this);

    // ~ Methods
    // --------------------------------------------------------------------------------------------------------

    /**
     * Add a PropertyChangeListener for a specific property. The listener will
     * be invoked only when a call on firePropertyChange names that specific
     * property. The same listener object may be added more than once. For each
     * property, the listener will be invoked the number of times it was added
     * for that property. If <code>propertyName</code> or <code>listener</code>
     * is null, no exception is thrown and no action is taken.
     *
     * @param propertyName
     *            The name of the property to listen on.
     * @param listener
     *            The PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        myPropertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * Add a PropertyChangeListener for a specific property. The listener will
     * be invoked only when a call on firePropertyChange names that specific
     * property. The same listener object may be added more than once. For each
     * property, the listener will be invoked the number of times it was added
     * for that property. If <code>propertyName</code> or <code>listener</code>
     * is null, no exception is thrown and no action is taken.
     *
     * @param propertyName
     *            The name of the property to listen on.
     * @param listener
     *            The PropertyChangeListener to be added
     */
    public void addVetoableyChangeListener(String propertyName, VetoableChangeListener listener) {
        myVetoableChangeSupport.addVetoableChangeListener(propertyName, listener);
    }

    public abstract Object exportConfigToXml();

    /**
     * Report a bound property update to any registered listeners. No event is
     * fired if old and new are equal and non-null.
     *
     * <p>
     * This is merely a convenience wrapper around the more general
     * firePropertyChange method that takes {@code PropertyChangeEvent} value.
     *
     * @param propertyName
     *            The programmatic name of the property that was changed.
     * @param oldValue
     *            The old value of the property.
     * @param newValue
     *            The new value of the property.
     */
    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        myPropertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    /**
     * Report a bound property update to any registered listeners. No event is
     * fired if old and new are equal and non-null.
     *
     * <p>
     * This is merely a convenience wrapper around the more general
     * firePropertyChange method that takes {@code PropertyChangeEvent} value.
     *
     * @param propertyName
     *            The programmatic name of the property that was changed.
     * @param oldValue
     *            The old value of the property.
     * @param newValue
     *            The new value of the property.
     */
    public void fireVetoableChange(String propertyName, Object oldValue, Object newValue) throws PropertyVetoException {
        myVetoableChangeSupport.fireVetoableChange(propertyName, oldValue, newValue);
    }

    /**
     * Remove a PropertyChangeListener for a specific property. If
     * <code>listener</code> was added more than once to the same event source
     * for the specified property, it will be notified one less time after being
     * removed. If <code>propertyName</code> is null, no exception is thrown and
     * no action is taken. If <code>listener</code> is null, or was never added
     * for the specified property, no exception is thrown and no action is
     * taken.
     *
     * @param propertyName
     *            The name of the property that was listened on.
     * @param listener
     *            The PropertyChangeListener to be removed
     */
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        myPropertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(String propertyName, Class<? extends PropertyChangeListener> listenerClass) {
        boolean found = false;
        do {
            for (PropertyChangeListener next : myPropertyChangeSupport.getPropertyChangeListeners(propertyName)) {
                if (next.getClass().isInstance(listenerClass)) {
                    myPropertyChangeSupport.removePropertyChangeListener(propertyName, next);
                    found = true;
                    break;
                }
            }
        } while (found);
    }

    /**
     * Remove a PropertyChangeListener for a specific property. If
     * <code>listener</code> was added more than once to the same event source
     * for the specified property, it will be notified one less time after being
     * removed. If <code>propertyName</code> is null, no exception is thrown and
     * no action is taken. If <code>listener</code> is null, or was never added
     * for the specified property, no exception is thrown and no action is
     * taken.
     *
     * @param propertyName
     *            The name of the property that was listened on.
     * @param listener
     *            The PropertyChangeListener to be removed
     */
    public void removeVetoableChangeListener(String propertyName, VetoableChangeListener listener) {
        myVetoableChangeSupport.removeVetoableChangeListener(propertyName, listener);
    }
}
