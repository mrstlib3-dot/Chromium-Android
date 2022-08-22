// Copyright 2017 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.chromium.chrome.browser.content;

import android.util.Log;

import com.google.android.gms.common.util.JsonUtils;

import org.chromium.base.metrics.RecordUserAction;
import org.chromium.content_public.browser.WebContents;
import org.json.JSONObject;

/**
 * A utility class to expose content functionality.
 */
public class ContentUtils {
    /**
     * @return The user agent string of Chrome.
     */
    public static String getBrowserUserAgent() {
        return nativeGetBrowserUserAgent();
    }

    /**
     * Set the user agent used for override. Currently, the only use case we have
     * for overriding a user agent involves spoofing a desktop Linux user agent
     * for "Request desktop site". Set it for WebContents so that it is available
     * when a NavigationEntry requires the user agent to be overridden.
     */
    public static void setUserAgentOverride(WebContents webContents) {
        boolean useDesktopUserAgent = webContents.getNavigationController().getUseDesktopUserAgent();

//        webContents.getNavigationController().setUseDesktopUserAgent(
//                true, false);
//        RecordUserAction.record("MobileMenuRequestDesktopSite");
        nativeSetUserAgentOverride(webContents);
    }

    private static native String nativeGetBrowserUserAgent();

    private static native void nativeSetUserAgentOverride(WebContents webContents);
}
