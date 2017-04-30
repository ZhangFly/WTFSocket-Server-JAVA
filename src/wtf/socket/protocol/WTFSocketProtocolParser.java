package wtf.socket.protocol;

import wtf.socket.exception.fatal.WTFSocketProtocolBrokenException;
import wtf.socket.util.WTFSocketPriority;

/**
 * 协议解析器接口
 * <p>
 * Created by ZFly on 2017/4/21.
 */
public interface WTFSocketProtocolParser {

    /**
     * 优先级
     *
     * @return 优先级
     */
    default int getPriority() {
        return WTFSocketPriority.MEDIUM;
    }

    /**
     * 是否负责该字符串数据
     *
     * @param data 原始数据
     *
     * @return 是否负责
     */
    boolean isResponse(String data);

    /**
     * 是否负责该消息对象
     *
     * @param msg 消息对象
     *
     * @return 是否负责
     */
    boolean isResponse(WTFSocketMsg msg);

    /**
     * 从字符串数据中解析数据生产消息对象
     *
     * @param data 原始数据
     *
     * @return 消息对象
     */
    WTFSocketMsg parseMsgFromString(String data) throws WTFSocketProtocolBrokenException;

    /**
     * 将消息对象打包成字符串数据
     *
     * @param msg 消息对象
     *
     * @return 打包后的字符串
     */
    String packageMsgToString(WTFSocketMsg msg);
}
