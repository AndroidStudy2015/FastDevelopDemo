package com.fast.develop.fastdemo.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by apple on 2017/9/15.
 */

public class FontDemoMoudle implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return DemoIcons.values();
    }
}
