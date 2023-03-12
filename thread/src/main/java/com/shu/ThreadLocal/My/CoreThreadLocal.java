package com.shu.ThreadLocal.My;

import java.util.Iterator;
import java.util.WeakHashMap;

public class CoreThreadLocal<T> extends InheritableThreadLocal<T> {
    /**
     * 用来保存当前线程持有的threadLocal
     * 它是static的
     * 所有线程都会有一个holder的threadLocal
     * 这个holder是一个map,保存着当前线程所持有的threadLocal
     **/
    private static InheritableThreadLocal<WeakHashMap<CoreThreadLocal<Object>, ?>> holder =
            new InheritableThreadLocal<WeakHashMap<CoreThreadLocal<Object>, ?>>() {
                @Override
                protected WeakHashMap<CoreThreadLocal<Object>, ?> childValue(WeakHashMap<CoreThreadLocal<Object>, ?> parentValue) {
                    return new WeakHashMap<>(parentValue);
                }

                @Override
                protected WeakHashMap<CoreThreadLocal<Object>, ?> initialValue() {
                    return new WeakHashMap<>();
                }
            };

    private void addToHolder() {
        if (!holder.get().containsKey(this)) {
            holder.get().put((CoreThreadLocal<Object>) this, null);
        }
    }

    private void removeFromHolder() {
        holder.get().remove(this);
    }

    static class SnapShot {
        final WeakHashMap<CoreThreadLocal<Object>, Object> ctlValue;

        private SnapShot(WeakHashMap<CoreThreadLocal<Object>, Object> ctlValue) {
            this.ctlValue = ctlValue;
        }
    }

    static class Transmitter {
        /**
         * 捕捉当前父线程的threadLocal
         **/
        public static SnapShot capture() {
            return new SnapShot(captureCtlValues());
        }

        private static WeakHashMap<CoreThreadLocal<Object>, Object> captureCtlValues() {
            WeakHashMap<CoreThreadLocal<Object>, Object> ctlValues = new WeakHashMap<>();
            /** 从holder中取当前线程持有的threadLocal **/
            for (CoreThreadLocal<Object> ctlItem : holder.get().keySet()) {
                ctlValues.put(ctlItem, ctlItem.get());
            }
            return ctlValues;
        }

        /**
         * 将capture设置到当前线程,并保存当前线程原有的threadLocal,返回
         **/
        public static CoreThreadLocal.SnapShot replay(CoreThreadLocal.SnapShot snapShot) {
            WeakHashMap<CoreThreadLocal<Object>, Object> capture = snapShot.ctlValue;
            WeakHashMap<CoreThreadLocal<Object>, Object> backValue = new WeakHashMap<>();
            /**
             * 从holder中获取当前线程持有的threadLocal的Map,进行迭代保存
             * **/
            Iterator<CoreThreadLocal<Object>> iterator = holder.get().keySet().iterator();
            while (iterator.hasNext()) {
                CoreThreadLocal<Object> threadLocal = iterator.next();
                backValue.put(threadLocal, threadLocal.get());
                if (!capture.containsKey(threadLocal)) {
                    iterator.remove();
                    threadLocal.remove();
                }
            }
            /**
             * 设置上capture
             * */
            setThreadLocal(capture);
            return new SnapShot(backValue);
        }

        private static void setThreadLocal(WeakHashMap<CoreThreadLocal<Object>, Object> ctlValues) {
            ctlValues.forEach((key, value) -> {
                key.set(value);
            });
        }

        /**
         * 恢复为backUp
         **/
        public static void restore(CoreThreadLocal.SnapShot backUp) {
            Iterator<CoreThreadLocal<Object>> iterator = holder.get().keySet().iterator();

            while (iterator.hasNext()) {
                CoreThreadLocal<Object> threadLocal = iterator.next();
                if (!backUp.ctlValue.containsKey(threadLocal)) {
                    iterator.remove();
                    threadLocal.remove();
                }
            }
            setThreadLocal(backUp.ctlValue);
        }
    }

    @Override
    public T get() {
        T value = super.get();
        if (null != value) {
            addToHolder();
        }
        return value;
    }

    @Override
    public void set(T value) {
        if (null == value) {
            removeFromHolder();
            super.remove();
        } else {
            super.set(value);
            addToHolder();
        }
    }

}
