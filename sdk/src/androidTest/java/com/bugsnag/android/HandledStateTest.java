package com.bugsnag.android;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class HandledStateTest {

    @Test
    public void testHandled() throws Exception {
        HandledState handled = HandledState.valueOf(HandledState.REASON_HANDLED_EXCEPTION);
        assertNotNull(handled);
        assertFalse(handled.isUnhandled());
        assertEquals(Severity.WARNING, handled.getSeverity());
    }

    @Test
    public void testUnhandled() throws Exception {
        HandledState unhandled = HandledState.valueOf(HandledState.REASON_UNHANDLED_EXCEPTION);
        assertNotNull(unhandled);
        assertTrue(unhandled.isUnhandled());
        assertEquals(Severity.ERROR, unhandled.getSeverity());
    }

    @Test
    public void testUserSpecified() throws Exception {
        HandledState userSpecified = HandledState.valueOf(HandledState.REASON_USER_SPECIFIED, Severity.INFO, null);
        assertNotNull(userSpecified);
        assertFalse(userSpecified.isUnhandled());
        assertEquals(Severity.INFO, userSpecified.getSeverity());
    }

    @Test
    public void testStrictMode() throws Exception {
        HandledState strictMode = HandledState.valueOf(HandledState.REASON_STRICT_MODE, null, "Test");
        assertNotNull(strictMode);
        assertTrue(strictMode.isUnhandled());
        assertEquals(Severity.WARNING, strictMode.getSeverity());
        assertEquals("Test", strictMode.getAttributeValue());
    }

    @Test
    public void testCallbackSpecified() throws Exception {
        HandledState handled = HandledState.valueOf(HandledState.REASON_HANDLED_EXCEPTION);
        assertEquals(HandledState.REASON_HANDLED_EXCEPTION,
            handled.calculateSeverityReasonType());

        handled.setCurrentSeverity(Severity.INFO);
        assertEquals(HandledState.REASON_CALLBACK_SPECIFIED,
            handled.calculateSeverityReasonType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidUserSpecified() throws Exception {
        HandledState.valueOf(HandledState.REASON_CALLBACK_SPECIFIED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidStrictmodeVal() throws Exception {
        HandledState.valueOf(HandledState.REASON_STRICT_MODE);
    }

}
