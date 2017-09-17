package com.fast.develop.fastdemo.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by apple on 2017/9/15.
 */

public enum DemoIcons implements Icon {


    icon_scan('\ue602'),
    icon_ali_pay('\ue606');

    private char character;

    DemoIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
