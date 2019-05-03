package com.revivalmodding.revivalcore.util.helper;

import net.minecraft.util.text.translation.I18n;

import java.text.SimpleDateFormat;

public class StringHelper
{
    public static String unlocalizedToResourceName(String unlocalizedName) 
    {
        String s = "";

        for (int i = 0; i < unlocalizedName.toCharArray().length; i++)
        {
            char c = unlocalizedName.toCharArray()[i];

            if (Character.isUpperCase(c))
                s = s + "_";

            s = s + Character.toLowerCase(c);
        }

        return s;
    }

    public static String translateToLocal(String string) 
    {
        return I18n.translateToLocal(string);
    }

    public static String translateToLocal(String string, Object... parameters) 
    {
        return I18n.translateToLocalFormatted(string, parameters);
    }

    public static String intToTime(int time) 
    {
        SimpleDateFormat df = new SimpleDateFormat("mm:ss");
        String formatted = df.format(time * 1000);
        return formatted;
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    public static boolean isInteger(String str)
    {
        try
        {
            double d = Integer.parseInt(str);
        }
        catch (NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
