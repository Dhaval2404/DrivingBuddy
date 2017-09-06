package com.android.internal.telephony;

/**
 * @author Dhaval Patel
 * @version 1.0, Sep 6, 2017
 * @since 1.0
 *
 */
public interface ITelephony {

    boolean endCall();

    void answerRingingCall();

    void silenceRinger();

}
