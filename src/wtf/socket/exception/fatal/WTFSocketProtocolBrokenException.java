package wtf.socket.exception.fatal;

/**
 * 协议格式错误
 * 一般是约定了某种格式如JSON
 * 但实际格式破损，导致数据无法正常
 * <p>
 * Created by ZFly on 2017/4/22.
 */
public class WTFSocketProtocolBrokenException extends WTFSocketFatalException {

    public WTFSocketProtocolBrokenException(String msg) {
        super(msg);
    }

    public int getErrCode() {
        return 64;
    }
}
