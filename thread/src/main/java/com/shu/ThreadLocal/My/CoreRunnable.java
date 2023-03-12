package com.shu.ThreadLocal.My;

import java.util.concurrent.atomic.AtomicReference;

public class CoreRunnable implements Runnable {

    private AtomicReference<CoreThreadLocal.SnapShot> captureRef;

    private Runnable runnable;

    private CoreRunnable(Runnable runnable) {
        this.runnable = runnable;
        captureRef = new AtomicReference<>(CoreThreadLocal.Transmitter.capture());
    }

    @Override
    public void run() {
        CoreThreadLocal.SnapShot capture = captureRef.get();
        CoreThreadLocal.SnapShot backUp = CoreThreadLocal.Transmitter.replay(capture);
        try {
            runnable.run();
        } finally {
            CoreThreadLocal.Transmitter.restore(backUp);
        }
    }

    /**
     * 调用这个函数进行包装
     **/
    public static CoreRunnable getRunnable(Runnable runnable) {
        return new CoreRunnable(runnable);
    }
}
