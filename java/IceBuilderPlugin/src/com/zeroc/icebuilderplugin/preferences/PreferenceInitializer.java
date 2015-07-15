// **********************************************************************
//
// Copyright (c) 2008-2015 ZeroC, Inc. All rights reserved.
//
// **********************************************************************

package com.zeroc.icebuilderplugin.preferences;

import java.io.File;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.zeroc.icebuilderplugin.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer
{
    private String getDefaultHome()
    {
        String os = System.getProperty("os.name"); //$NON-NLS-1$
        if(os.startsWith("Windows")) //$NON-NLS-1$
        {
            final String[] defaultLocations = new String[]{
                "C:\\Program Files\\ZeroC\\Ice-" + Messages.IceStringVersion,
                "C:\\Program Files (x86)\\ZeroC\\Ice-" + Messages.IceStringVersion,
                "C:\\Ice-" + Messages.IceStringVersion};
            for(String s : defaultLocations)
            {
                if(new File(s).exists())
                {
                    return s;
                }
            }
        }
        else
        {
            final String[] defaultLocations = new String[]{
                    "/usr/bin/slice2java",
                    "/usr/local/bin/slice2java",
                    "/opt/Ice-" + Messages.IceStringVersion + "/bin/slice2java"};
            
            for(String s : defaultLocations)
            {
                if(new File(s).exists())
                {
                    return s.replace("/bin/slice2java", "");
                }
            }
        }
        return "/opt/Ice-" + Messages.IceStringVersion; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#
     * initializeDefaultPreferences()
     */
    public void initializeDefaultPreferences()
    {
        IPreferenceStore store = Activator.getDefault().getPreferenceStore();
        
        store.setDefault(PluginPreferencePage.SDK_PATH, getDefaultHome() );
    }

}