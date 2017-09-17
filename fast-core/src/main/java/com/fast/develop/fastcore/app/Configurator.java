package com.fast.develop.fastcore.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by apple on 2017/9/15.<br/>
 * Configurator功能：（单例模式，懒汉）<br/>
 * <p>
 * 1. 初始化所有配置参数map：FAST_CONFIGS <br/>
 * 2. 提供了一系列设置参数值的方法：withApiHost.......，并且将这些参数保存在了map中<br/>
 * 3. 提供了获取参数值得方法：getConfiguation（内部检查，是不是调用了configure，标志是否配置完成）<br/>
 * 4. 提供了一个标志者配置完成的方法：configure<br/>
 */

public class Configurator {
    /**
     * FAST_CONFIGS是所有配置字段的map集合，所有的配置文件都存在了这个map中
     */
    private static final HashMap<String, Object> FAST_CONFIGS = new HashMap<>();


    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    //   ************************************ 单例模式(线程安全的懒汉模式)*********************************
    // 1. 私有构造函数，构造函数里面开始调用时，这时候给CONFIG_READ传递一个false，表示配置还没有完成，
    private Configurator() {
        FAST_CONFIGS.put(ConfigType.CONFIG_READ.name(), false);
    }

    //2. 私有静态内部类
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    /**
     * 3.得到Configurator的单例实例
     */
    public static Configurator getInstace() {
        return Holder.INSTANCE;
    }
//  *************************************** 单例模式(线程安全的懒汉模式)******************************

    /**
     * 得到配置参数的map
     *
     * @return
     */
    public static HashMap<String, Object> getFastConfigs() {

        return FAST_CONFIGS;
    }

    /**
     * 当调用configure时候，这时候给CONFIG_READ传递一个true，表示配置已经完成，
     */
    public final void configure() {
        initIcons();
        FAST_CONFIGS.put(ConfigType.CONFIG_READ.name(), true);
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                Iconify.with(ICONS.get(i));//从1开始初始化，0 在得到initializer时候，已经初始化了
            }
        }
    }

    /**
     * 配置Url地址
     *
     * @param host
     * @return
     */
    public final Configurator withApiHost(String host) {
        FAST_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    public final Configurator withIcons(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    /**
     * 检查是不是配置完毕了，如果没完成，则抛出运行时异常，
     * 这个函数是私有的，他只是被getConfiguation调用
     * 因为用户想要获取配合参数时，需要检测一个配置完了没有，
     * 没配置完，可能获取到的参数为null
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) FAST_CONFIGS.get(ConfigType.CONFIG_READ.name());
        if (!isReady) {
            throw new RuntimeException("***************************************************************************************************************************************************************************\n" +
                    "请先运行 Fast.init(context).configure()完成配置\n" +
                    "Configuaition is not ready , call configure first\n" +
                    "*********************************************************");
        }
    }

    /**
     * 从FAST_CONFIGS里获取配置参数，传入对应的key名称，得到对应的参数
     * 由于用了泛型，声明了什么类型，就直接得到了，不用强转（可能强转失败，你要知道自己想要什么类型）
     *
     * @param key
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public final <T> T getConfiguation(Enum<ConfigType> key) {
        checkConfiguration();//先检查是否配置完成
        return (T) FAST_CONFIGS.get(key.name());
    }
}
