package wtf.socket.protocol;

import org.springframework.stereotype.Component;
import wtf.socket.exception.fatal.WTFSocketProtocolBrokenException;
import wtf.socket.exception.fatal.WTFSocketProtocolUnsupportedException;
import wtf.socket.protocol.msg.WTFSocketDefaultProtocolParser;

import java.util.ArrayList;
import java.util.List;

/**
 * 协议簇，通过向协议簇注册解析器
 * 将不同协议的数据包统一转化到 WTFSocketMsg
 *
 * Created by zfly on 2017/4/21.
 */
@Component("wtf.socket.protocolFamily")
public class WTFSocketProtocolFamily {

    /**
     * 解析器列表
     */
    private List<WTFSocketProtocolParser> parsers = new ArrayList<WTFSocketProtocolParser>() {{
        add(new WTFSocketDefaultProtocolParser());
    }};

    /**
     * 选择解析器从字符串数据中解析出消息对象
     *
     * @param data 字符串数据
     * @return 消息对象
     * @throws WTFSocketProtocolBrokenException 消息格式错误
     * @throws WTFSocketProtocolUnsupportedException 没有适合的解析器
     */
    public WTFSocketMsg parseMsgFromString(String data) throws WTFSocketProtocolBrokenException, WTFSocketProtocolUnsupportedException {
        for (WTFSocketProtocolParser parser : parsers) {
            if (parser.isResponse(data)) {
                return parser.parseMsgFromString(data);
            }
        }
        throw new WTFSocketProtocolUnsupportedException(data);
    }

    /**
     * 选择解析器将消息对象打包为字符串数据
     *
     * @param msg 消息对象
     * @return 字符串数据
     */
    public String packageMsgToString(WTFSocketMsg msg) throws WTFSocketProtocolUnsupportedException {
        for (WTFSocketProtocolParser parser : parsers) {
            if (parser.isResponse(msg)) {
                return parser.packageMsgToString(msg);
            }
        }
        throw new WTFSocketProtocolUnsupportedException("msg.version = " + msg.getVersion());
    }

    /**
     * 注册解析器
     *
     * @param parser 要解析器对象
     */
    public void registerParser(WTFSocketProtocolParser parser) {
        parsers.add(parser);
    }

    /**
     * 注销解析器
     *
     * @param parser 要解析器对象
     */
    public void unRegisterParser(WTFSocketProtocolParser parser) {
        parsers.remove(parser);
    }

    /**
     * 清空所以解析器
     */
    public void clear() {
        parsers.clear();
    }

}
